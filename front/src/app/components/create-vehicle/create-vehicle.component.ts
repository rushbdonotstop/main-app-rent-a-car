import { Component, OnInit, NgZone, ViewChild } from '@angular/core';
import { CatalogueItem } from 'src/app/shared/models/catalogue/CatalogueItem';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { PricelistService } from 'src/app/core/services/pricelist.service';
import { LocationService } from 'src/app/core/services/location.service';
import { CatalogueService } from 'src/app/core/services/catalogue.service';
import { MatSnackBar } from '@angular/material';
import { Pricelist } from 'src/app/shared/models/pricelist/Pricelist';
import { Vehicle } from 'src/app/shared/models/vehicle/Vehicle';
import { CreatePriceListComponent } from '../price-list/create-price-list/create-price-list.component';
import { VehicleLocation } from 'src/app/shared/models/location/VehicleLocation';
import { City } from 'src/app/shared/models/location/City';
import { State } from 'src/app/shared/models/location/State';
import { Street } from 'src/app/shared/models/location/Street';
import { HttpClient } from '@angular/common/http';
import { Image } from 'src/app/shared/models/vehicle/Image';
import { User } from 'src/app/shared/models/user/User';
import { UserService } from 'src/app/core/services/user.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './create-vehicle.component.html',
  styleUrls: ['./create-vehicle.component.css']
})
export class CreateVehicleComponent implements OnInit {
  /// GOOGLE PLACES///
  address: Object;
  establishmentAddress: Object;

  formattedAddress: string;
  formattedEstablishmentAddress: string;

  phone: string;
  /////////////////
  /// IMAGES /////
  imgURL: any;
  public imagePath;
  selectedFile: File

  onFileChanged(event) {

    this.selectedFile = event.target.files[0];

    if (event.target.files.length === 0)
      return;

    var mimeType = event.target.files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      alert("Only images are supported.");
      return;
    }

    var reader = new FileReader();
    this.imagePath = event.target.files;
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    }
  }

  uploadImage() {
    // this.http is the injected HttpClient
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);

    this.vehicleService.uploadPicture(uploadImageData).subscribe(results => {
      alert("Success!")
    },
      error => {
        alert("Error!")
      })
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////

  makes: CatalogueItem[] = []
  models: CatalogueItem[] = []
  fuelTypes: CatalogueItem[] = []
  transmissions: CatalogueItem[] = []
  styles: CatalogueItem[] = []

  selectedMake: CatalogueItem
  selectedModel: CatalogueItem
  selectedFuelType: CatalogueItem
  selectedTransmission: CatalogueItem
  selectedStyle: CatalogueItem

  mileage: number
  mileageLimit: number

  collisionProtection: boolean
  childrenSeats: number

  startDate: Date
  endDate: Date

  minDate: Date

  vehicleInfoValid: boolean
  vehicleInfoShow: boolean

  constructor(private router: Router, private httpClient: HttpClient, public zone: NgZone, private userService:UserService, private vehicleService: VehicleService, private pricelistService: PricelistService, private locationService: LocationService, private catalogueService: CatalogueService, private _snackBar: MatSnackBar) { }

  ngOnInit() {

    var user = new User()
    user = JSON.parse(localStorage.getItem('userObject'))
    this.userService.canUserCreate(user.id).subscribe(result =>{
        if(result){
          this.imgURL = 'assets/vehicles/nopicture.jpg'
          this.minDate = new Date();
          this.minDate.setDate(this.minDate.getDate() + 2)
          this.collisionProtection = false
          this.vehicleInfoValid = false
          this.vehicleInfoShow = true
      
          this.results = new VehicleInfoPricelists()
          this.results.vehicleInfo = new Vehicle()
          this.results.pricelists = []
      
          this.catalogueService.getMakes()
            .subscribe(makes => {
              this.makes = makes;
              this.selectedMake = makes[0]
              this.onMakeChange();
            },
              error => {
                this._snackBar.open("Error while retreiving makes!", "", {
                  duration: 2000,
                  verticalPosition: 'bottom'
                });
              })
      
          this.catalogueService.getStyles()
            .subscribe(styles => {
              this.styles = styles;
              this.selectedStyle = styles[0]
            },
              error => {
                this._snackBar.open("Error while retreiving styles!", "", {
                  duration: 2000,
                  verticalPosition: 'bottom'
                });
              })
      
          this.catalogueService.getTransmissions()
            .subscribe(transmissions => {
              this.transmissions = transmissions;
              this.selectedTransmission = transmissions[0]
            },
              error => {
                this._snackBar.open("Error while retreiving transmissions!", "", {
                  duration: 2000,
                  verticalPosition: 'bottom'
                });
              })
      
          this.catalogueService.getFuelTypes()
            .subscribe(fuelTypes => {
              this.fuelTypes = fuelTypes;
              this.selectedFuelType = fuelTypes[0]
            },
              error => {
                this._snackBar.open("Error while retreiving fuel types!", "", {
                  duration: 2000,
                  verticalPosition: 'bottom'
                });
              })
        }
        else{
          this._snackBar.open("You can't create new vehicles!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
          this.router.navigate(['home']);
        }
    },
    error => alert("Error while checking user!"))

  }

  onMakeChange() {
    this.catalogueService.getModelByMake(this.selectedMake.id)
      .subscribe(models => {
        this.models = models;
        this.selectedModel = models[0];
      },
        error => {
          this._snackBar.open("Error while retreiving models!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        })
  }

  results: VehicleInfoPricelists

  getUpdatedvalue($event) {
    this.results = $event;
    this.vehicleInfoShow = this.results.vehicleInfo.id == 0 ? true : false;
    this.vehicleInfoValid = this.results.vehicleInfo.id == 0 ? false : true;
  }

  addPricelists() {

    if (this.startDate > this.endDate || this.startDate == undefined || this.endDate == undefined) {
      this._snackBar.open("In order to add prices you must choose valid date!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      this.vehicleInfoValid = false;
      return;
    }
    else {
      this.startDate.setTime(this.startDate.getTime() + (10 * 60 * 60 * 1000))
      this.endDate.setTime(this.endDate.getTime() + (10 * 60 * 60 * 1000))

      this.results.vehicleInfo.startDate = this.startDate
      this.results.vehicleInfo.endDate = this.endDate
    }

    if (this.mileageLimit < 0) {
      this._snackBar.open("In order to add prices you must give valid mileage limit number!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      this.vehicleInfoValid = false;
      return;
    }
    else {
      if (this.mileageLimit == undefined) {
        this.mileageLimit = 0
        this.results.vehicleInfo.mileageLimit = 0
      }
      else {
        this.results.vehicleInfo.mileageLimit = this.mileageLimit
      }
    }

    this.results.vehicleInfo.collisionProtection = this.collisionProtection;
    this.vehicleInfoValid = true;
    this.vehicleInfoShow = false;
  }

  locationInvalid(): boolean {

    if (this.formattedAddress == undefined) {
      return true;
    }

    var location = this.formattedAddress.split(',');

    if (location.length != 3) {
      return true;
    }

    this.doGeocode();

    if (this.formattedAddress == null) {
      return true;
    }

    return false;
  }

  doGeocode() {
    // Get geocoder instance
    var geocoder = new google.maps.Geocoder();

    // Geocode the address
    geocoder.geocode({
      'address': this.formattedAddress
    }, function (results, status) {
      if (status === google.maps.GeocoderStatus.OK && results.length > 0) {
        // set it to the correct, formatted address if it's valid
        this.formatted_address = results[0].formatted_address;;

        // show an error if it's not
      } else {
        this.formatted_address = "";
      };
    });
  };

  createVehicle() {

    if (this.startDate > this.endDate || this.startDate == undefined || this.endDate == undefined) {
      this._snackBar.open("In order to add prices you must choose valid date!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      this.vehicleInfoValid = false;
      return;
    }
    else {
      if ((this.results.vehicleInfo.startDate != this.startDate) || (this.results.vehicleInfo.endDate != this.endDate)){
        this.results.pricelists = []
        this._snackBar.open("You must add new pricelists with new dates!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        this.vehicleInfoValid = false;
        return;
      }
    }


    if (this.collisionProtection != this.results.vehicleInfo.collisionProtection) {
      this.results.pricelists = []
      if (this.collisionProtection){
        this._snackBar.open("You must add new pricelists with price for collision protection!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        this.vehicleInfoValid = false;
        return;
      }
      else{
        this._snackBar.open("You must add new pricelists without price for collision protection!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        this.vehicleInfoValid = false;
        return;
      }
    }

    if (this.mileageLimit < 0) {
      this._snackBar.open("In order to add prices you must give valid mileage limit number!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      this.vehicleInfoValid = false;
      return;
    }
    else {
      if (this.mileageLimit == undefined) {
        this.mileageLimit = 0
      }
    }

    if (this.results.vehicleInfo.mileageLimit == 0 && this.mileageLimit > 0) {
      this._snackBar.open("You must add new pricelists with price by mile values!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      this.results.pricelists = []
      return;
    }

    if (this.results.vehicleInfo.mileageLimit != 0 && this.mileageLimit == 0) {
      this._snackBar.open("You must add new pricelists without price by mile values!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      this.results.pricelists = []
      return;
    }

    if (this.results.pricelists.length != 0) {

      if (this.locationInvalid()) {
        this._snackBar.open("In order to add prices you must choose valid location - street, city, state format!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        return;
      }

      if (this.mileage < 0 || this.mileage == undefined) {
        this._snackBar.open("In order to add prices you must give valid mileage number!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        return;
      }

      if (this.childrenSeats < 0 || this.childrenSeats == undefined) {
        this._snackBar.open("In order to add prices you must give valid children seats number!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        return;
      }

      if (this.imgURL != 'assets/vehicles/nopicture.jpg'){
        const uploadImageData = new FormData();
        uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
  
        // Make a call to the Spring Boot Application to save the image
        this.httpClient.post('server/vehicle/vehicle/image/upload', uploadImageData, { observe: 'response' })
          .subscribe((response) => {
            var location = new VehicleLocation()
            var splitted = this.formattedAddress.split(',')
            location.state = new State()
            location.state.value = splitted[2]
            location.city = new City()
            location.city.value = splitted[1]
            location.street = new Street()
            location.street.value = splitted[0]
            this.locationService.create(location).subscribe(location => {
              var vehicle = new Vehicle()
              vehicle.makeId = this.selectedMake.id
              vehicle.styleId = this.selectedStyle.id
              vehicle.fuelTypeId = this.selectedFuelType.id
              vehicle.transmissionId = this.selectedTransmission.id
              vehicle.modelId = this.selectedModel.id
              vehicle.locationId = location.id
              vehicle.mileage = this.mileage
              vehicle.mileageLimit = this.mileageLimit ? this.mileageLimit : 0
              vehicle.startDate = this.startDate
              vehicle.endDate = this.endDate
              vehicle.collisionProtection = this.collisionProtection
              vehicle.childrenSeats = this.childrenSeats
              vehicle.image = response.body as Image
              var user = new User()
              user = JSON.parse(localStorage.getItem('userObject'))
              vehicle.userId = user.id;
              this.vehicleService.create(vehicle).subscribe(resultedVehicle => {
                var itemsProcessed = 0;
                this.results.pricelists.forEach(element => {
                  itemsProcessed++;
                  element.vehicleId = resultedVehicle.id
                  if (itemsProcessed === this.results.pricelists.length){
                    this.createPricelists();
                  }
                });
              },
                error => { })
  
            },
              error => {
                this._snackBar.open("Creating vehicle failed!", "", {
                  duration: 2000,
                  verticalPosition: 'bottom'
                });
              })
          }
          );
      }
      else{
        var location = new VehicleLocation()
        var splitted = this.formattedAddress.split(',')
        location.state = new State()
        location.state.value = splitted[2]
        location.city = new City()
        location.city.value = splitted[1]
        location.street = new Street()
        location.street.value = splitted[0]
        this.locationService.create(location).subscribe(location => {
          var vehicle = new Vehicle()
          vehicle.makeId = this.selectedMake.id
          vehicle.styleId = this.selectedStyle.id
          vehicle.fuelTypeId = this.selectedFuelType.id
          vehicle.transmissionId = this.selectedTransmission.id
          vehicle.modelId = this.selectedModel.id
          vehicle.locationId = location.id
          vehicle.mileage = this.mileage
          vehicle.mileageLimit = this.mileageLimit ? this.mileageLimit : 0
          vehicle.startDate = this.startDate
          vehicle.endDate = this.endDate
          vehicle.collisionProtection = this.collisionProtection
          vehicle.childrenSeats = this.childrenSeats
          vehicle.image = null
          var user = new User()
          user = JSON.parse(localStorage.getItem('userObject'))
          vehicle.userId = user.id;
          this.vehicleService.create(vehicle).subscribe(resultedVehicle => {
            var itemsProcessed = 0;
            this.results.pricelists.forEach(element => {
              itemsProcessed++;
              element.vehicleId = resultedVehicle.id
              if (itemsProcessed === this.results.pricelists.length){
                this.createPricelists();
              }
            });
          },
            error => { })
  
        },
          error => {
            this._snackBar.open("Creating vehicle failed!", "", {
              duration: 2000,
              verticalPosition: 'bottom'
            });
          })
        }
    }
    else{
      this._snackBar.open("You must add pricelists!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }
  }

  createPricelists(){
    this.pricelistService.savePricelists(this.results.pricelists, this.startDate, this.endDate).subscribe(results => {
      if(results != null){
        this.updateUser();
        this._snackBar.open("Created vehicle with defined prices!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      }
      else{
        this._snackBar.open("Something went wrong! You probably have invalid pricelists defined!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      }
  });
  }

  updateUser(){
    var user = new User()
    user = JSON.parse(localStorage.getItem('userObject'))
    this.userService.updateUserVehicleNum(user.id).subscribe(result => {
      this._snackBar.open(result.text.toString(), "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      location.reload()
    },
    error => alert("Error while updating user vehicle number!"))
  }

  removePicture() {
      this.imgURL = 'assets/vehicles/nopicture.jpg'
  }

  ////// GOOGLE PLACES


  getAddress(place: object) {
    this.address = place['formatted_address'];
    this.formattedAddress = place['formatted_address'];
    this.zone.run(() => this.formattedAddress = place['formatted_address']);
  }

  getAddrComponent(place, componentTemplate) {
    let result;

    for (let i = 0; i < place.address_components.length; i++) {
      const addressType = place.address_components[i].types[0];
      if (componentTemplate[addressType]) {
        result = place.address_components[i][componentTemplate[addressType]];
        return result;
      }
    }
    return;
  }

  getStreetNumber(place) {
    const COMPONENT_TEMPLATE = { street_number: 'short_name' },
      streetNumber = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return streetNumber;
  }

  getStreet(place) {
    const COMPONENT_TEMPLATE = { route: 'long_name' },
      street = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return street;
  }

  getCity(place) {
    const COMPONENT_TEMPLATE = { locality: 'long_name' },
      city = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return city;
  }

  getState(place) {
    const COMPONENT_TEMPLATE = { administrative_area_level_1: 'short_name' },
      state = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return state;
  }

  getDistrict(place) {
    const COMPONENT_TEMPLATE = { administrative_area_level_2: 'short_name' },
      state = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return state;
  }

  getCountryShort(place) {
    const COMPONENT_TEMPLATE = { country: 'short_name' },
      countryShort = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return countryShort;
  }

  getCountry(place) {
    const COMPONENT_TEMPLATE = { country: 'long_name' },
      country = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return country;
  }

  getPostCode(place) {
    const COMPONENT_TEMPLATE = { postal_code: 'long_name' },
      postCode = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return postCode;
  }

  getPhone(place) {
    const COMPONENT_TEMPLATE = { formatted_phone_number: 'formatted_phone_number' },
      phone = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return phone;
  }



}

export class VehicleInfoPricelists {
  vehicleInfo: Vehicle;
  pricelists: Pricelist[];
}
