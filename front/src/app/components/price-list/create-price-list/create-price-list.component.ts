import { Component, OnInit, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { MatTableDataSource, MatSnackBar } from '@angular/material';
import { Pricelist } from 'src/app/shared/models/pricelist/Pricelist';
import { PricelistService } from 'src/app/core/services/pricelist.service';
import { VehicleInfoPricelists } from '../../create-vehicle/create-vehicle.component';
import { User } from 'src/app/shared/models/user/User';
import { UserType } from 'src/app/shared/models/user/UserType';
import { VehicleDiscount } from 'src/app/shared/models/pricelist/VehicleDiscount';

@Component({
  selector : 'pm-pricelist-create',
  templateUrl: './create-price-list.component.html',
  styleUrls: ['./create-price-list.component.css']
})
export class CreatePriceListComponent implements OnInit{

  displayedColumns: string[] = ['startDate', 'endDate', 'price', 'priceByMile', 'priceCollision', 'discount'];
  dataSource: MatTableDataSource<Pricelist>;

  @Input() public results : VehicleInfoPricelists;
  @Output() valueUpdate = new EventEmitter(); 

  startDate: Date
  endDate: Date

  priceCollision : number
  numDays : number
  priceByMile : number
  discount : number
  price : number

  agentTrue : boolean

  tempPrices : Pricelist []

  constructor(private _snackBar: MatSnackBar, private pricelistService : PricelistService) { }

  ngOnInit() {

    var user = new User()
    user = JSON.parse(localStorage.getItem('userObject'))
    if (user.userDetails.userType.toString() == "AGENT"){
      this.agentTrue = true;
    }
    else{
      this.agentTrue = false;
    }

    this.tempPrices = []
  }


  addPricelist(){
    var pricelist = new Pricelist()

      if (this.startDate > this.endDate || this.startDate == undefined || this.endDate == undefined){
        this._snackBar.open("In order to add prices you must choose valid date!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        return
      }

      pricelist.startDate = this.startDate
      pricelist.endDate = this.endDate

      if (this.price == undefined){
        this._snackBar.open("In order to add prices you must set valid price!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        return
      }

      pricelist.price = this.price

      if (this.priceByMile == undefined && this.results.vehicleInfo.mileageLimit != 0){
        this._snackBar.open("In order to add prices you must set valid price by mile!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        return
      }

      pricelist.priceByMile = this.priceByMile ? this.priceByMile : 0

      if (this.priceCollision == undefined && this.results.vehicleInfo.collisionProtection){
        this._snackBar.open("In order to add prices you must set valid price for collision protection!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        return
      }

      pricelist.priceCollision = this.priceCollision ? this.priceCollision : 0

      if(this.agentTrue){
        if (this.discount == undefined || this.numDays == undefined || this.discount <= 0 || this.numDays < 0 || this.discount > 100){
          this._snackBar.open("In order to add discount you must set valid discount!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
          return
        }
        pricelist.vehicleDiscount = new VehicleDiscount()
        pricelist.vehicleDiscount.discount = this.discount
        pricelist.vehicleDiscount.numDays = this.numDays
      }

      if (this.price < 0 || this.priceByMile < 0 || this.priceCollision < 0){
        this._snackBar.open("Numbers can't be negative!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        return
      }

      this.tempPrices.push(pricelist);
      this.dataSource = new MatTableDataSource<Pricelist>(this.tempPrices)
  }

  finish(){
    this.pricelistService.validatePricelists(this.tempPrices, this.results.vehicleInfo.startDate, this.results.vehicleInfo.endDate).subscribe(pricelists => {
      if (pricelists != null){
        this.results.pricelists = this.tempPrices
        this.results.vehicleInfo.id = 0;
        this.valueUpdate.emit(this.results); 
      }
    })
  }
}
