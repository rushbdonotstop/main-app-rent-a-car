import { Component, OnInit, ViewChild, Input, Output, EventEmitter } from '@angular/core';
import { ThemePalette, MatSnackBar, MatPaginator, MatSort } from '@angular/material';
import { FormControl } from '@angular/forms';
import { CatalogueItem } from 'src/app/shared/models/catalogue/CatalogueItem';
import { State } from 'src/app/shared/models/location/State';
import { City } from 'src/app/shared/models/location/City';
import { CatalogueService } from 'src/app/core/services/catalogue.service';
import { LocationService } from 'src/app/core/services/location.service';
import { PricelistService } from 'src/app/core/services/pricelist.service';
import { SearchParams } from 'src/app/shared/models/SearchParams';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';

@Component({
  selector: 'pm-search-vehicle',
  templateUrl: './search-vehicle.component.html',
  styleUrls: ['./search-vehicle.component.css']
})
export class SearchVehicleComponent implements OnInit {

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

  minPrice: number
  maxPrice: number

  minMileageLimit: number
  maxMileage: number

  selectedState: State
  selectedCity: State

  collisionProtection: boolean
  childrenSeats: number

  startDate: Date
  endDate: Date

  minDate: Date

  states: State[] = []
  cities: City[] = []

  sliderValue: number;

  selectTime: boolean
  startTime
  endTime

  constructor(private vehicleService: VehicleService, private pricelistService: PricelistService, private locationService: LocationService, private catalogueService: CatalogueService, private _snackBar: MatSnackBar) { }

  ngOnInit() {

    this.minDate = new Date();
    this.minDate.setDate(this.minDate.getDate() + 2)

    this.models.push({ "value": "All", "id": 0 })
    this.selectedModel = this.models[this.models.length - 1]
    this.collisionProtection = false

    this.locationService.getStates()
      .subscribe(states => {
        this.states = states;
      },
        error => {
          this._snackBar.open("Error while retreiving states!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        })

    this.catalogueService.getMakes()
      .subscribe(makes => {
        this.makes = makes;
        this.makes.push({ "value": "All", "id": 0 });
        this.selectedMake = this.makes[this.makes.length - 1]
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
        this.styles.push({ "value": "All", "id": 0 });
        this.selectedStyle = this.styles[this.styles.length - 1]
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
        this.transmissions.push({ "value": "All", "id": 0 });
        this.selectedTransmission = this.transmissions[this.transmissions.length - 1]
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
        this.fuelTypes.push({ "value": "All", "id": 0 });
        this.selectedFuelType = this.fuelTypes[this.fuelTypes.length - 1]
      },
        error => {
          this._snackBar.open("Error while retreiving fuel types!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        })

    this.pricelistService.minAndMax()
      .subscribe(minAndMax => {
        this.minPrice = minAndMax.minPrice;
        this.maxPrice = minAndMax.maxPrice;
        this.sliderValue = this.maxPrice;
      },
        error => {
          this._snackBar.open("Error while retreiving min and max price!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        })
  }

  setTime() {
    this.selectTime = !this.selectTime
  }

  onStateChange() {
    if (this.selectedState != undefined) {
      this.locationService.getCitiesByState(this.selectedState.id)
        .subscribe(cities => {
          this.cities = cities;
        },
          error => {
            this._snackBar.open("Error while retreiving cities!", "", {
              duration: 2000,
              verticalPosition: 'bottom'
            });
          })
    }
  }

  onMakeChange() {
    this.catalogueService.getModelByMake(this.selectedMake.id)
      .subscribe(models => {
        this.models = models;
        this.models.push({ "value": "All", "id": 0 })
        this.selectedModel = this.models[this.models.length - 1]
      },
        error => {
          this._snackBar.open("Error while retreiving models!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        })
  }

  price(value: number) {
    if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }

    return value;
  }

  onKeyCity(value) {
    this.cities = this.searchCity(value);
  }

  onKeyState(value) {
    this.states = this.searchState(value);
  }

  searchState(value: string) {
    let filter = value.toLowerCase();
    return this.states.filter(option => option.value.toLowerCase().startsWith(filter));
  }

  searchCity(value: string) {
    let filter = value.toLowerCase();
    return this.cities.filter(option => option.value.toLowerCase().startsWith(filter));
  }

  removeFilter() {
    this.vehicleService.getAll()
      .subscribe(vehicles => {
        this.results = vehicles
        this.valueUpdate.emit(this.results);
      },
        error => {
          this._snackBar.open("Server error!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        }
      )
  }

  @Input() public results: VehicleMainViewDTO[];
  @Output() valueUpdate = new EventEmitter();


  filter() {
    var searchParams = new SearchParams();

    searchParams.vehicleMake = this.selectedMake.id
    searchParams.vehicleModel = this.selectedModel.id
    searchParams.vehicleFuel = this.selectedFuelType.id
    searchParams.vehicleTransmission = this.selectedTransmission.id
    searchParams.vehicleStyle = this.selectedStyle.id

    this.stateValidation()
    this.cityValidation()
    searchParams.state = this.selectedState.value
    searchParams.city = this.selectedCity.value

    searchParams.collisionProtection = this.collisionProtection

    if (this.childrenSeats == undefined) {
      searchParams.childrenSeats = -1
    }
    else {
      if (this.childSeatsValidation()) {
        searchParams.childrenSeats = this.childrenSeats
      }
      else {
        this._snackBar.open("Invalid children seats number!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      }
    }

    if (this.minMileageLimit == undefined) {
      this.minMileageLimit = 0
    }

    if (this.maxMileage == undefined) {
      this.maxMileage = 0
    }

    if (this.mileageValidation()) {
      searchParams.mileageLimit = this.minMileageLimit
      searchParams.maxMileage = this.maxMileage
    }
    else {
      this._snackBar.open("Invalid mileage number!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }

    searchParams.priceLowerLimit = this.minPrice
    searchParams.priceUpperLimit = this.sliderValue

    if (this.startDate == undefined) {
      this.startDate = null
    }

    if (this.endDate == undefined) {
      this.endDate = null
    }


    if (this.dateValidation()) {
      searchParams.startDate = this.startDate
      searchParams.endDate = this.endDate
      if (this.selectTime && this.startTime != null && this.startTime != undefined && this.endTime != null && this.endTime != undefined) {
        searchParams.startDate.setHours(this.startTime.hour)
        searchParams.startDate.setMinutes(this.startTime.minute)
        searchParams.endDate.setHours(this.endTime.hour)
        searchParams.endDate.setMinutes(this.endTime.minute)

        if (searchParams.startDate >= searchParams.endDate) {
          searchParams.startDate = null
          searchParams.endDate = null
          this.startDate = null
          this.endDate = null
          this.startTime = null
          this.endTime = null
          this._snackBar.open("Invalid date range!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        }
      }

    }
    else {
      this._snackBar.open("Invalid date range!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }

    this.vehicleService.search(searchParams)
      .subscribe(vehicles => {
        this.results = vehicles;
        this.valueUpdate.emit(this.results);
      },
        error => {
          this._snackBar.open("Search failed!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        })

  }


  childSeatsValidation(): boolean {
    if (this.childrenSeats < 0) {
      return false;
    }
    return true;
  }

  mileageValidation(): boolean {
    if (this.minMileageLimit < 0 || this.maxMileage < 0) {
      return false;
    }
    return true;
  }

  dateValidation(): boolean {
    if (this.startDate > this.endDate) {
      return false;
    }
    return true;
  }

  stateValidation() {
    if (this.selectedState == undefined) {
      this.selectedState = new State()
      this.selectedState.value = ""
      this.selectedState.id = 0
    }
    this.states.forEach(element => {
      if (this.selectedState.value == element.value) {
        return;
      }
    })
  }

  cityValidation() {
    if (this.selectedCity == undefined) {
      this.selectedCity = new City()
      this.selectedCity.id = 0
      this.selectedCity.value = ""
    }
    this.cities.forEach(element => {
      if (this.selectedCity.value == element.value) {
        return;
      }
    });
  }

}
