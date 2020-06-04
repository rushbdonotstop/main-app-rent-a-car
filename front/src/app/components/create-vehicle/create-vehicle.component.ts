import { Component, OnInit, NgZone } from '@angular/core';
import { CatalogueItem } from 'src/app/shared/models/catalogue/CatalogueItem';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { PricelistService } from 'src/app/core/services/pricelist.service';
import { LocationService } from 'src/app/core/services/location.service';
import { CatalogueService } from 'src/app/core/services/catalogue.service';
import { MatSnackBar } from '@angular/material';

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

  onFileChanged(files) {
    if (files.length === 0)
      return;
 
    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      alert("Only images are supported.");
      return;
    }
 
    var reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]); 
    reader.onload = (_event) => { 
      this.imgURL = reader.result; 
    }
  }

  ////////

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

  constructor(public zone: NgZone, private vehicleService : VehicleService, private pricelistService: PricelistService, private locationService: LocationService, private catalogueService: CatalogueService, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.imgURL = 'assets/vehicles/nopicture.jpg'
    this.minDate = new Date();
    this.collisionProtection = false

    this.catalogueService.getMakes()
    .subscribe(makes => {
      this.makes = makes;
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
    },
      error => {
        this._snackBar.open("Error while retreiving fuel types!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      })

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
