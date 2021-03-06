import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { RentDialogComponent } from '../rent-dialog/rent-dialog.component';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { CartService } from 'src/app/core/services/cart.service';
import { DialogType } from 'src/app/shared/models/cart/DialogType';
import { DialogDTO } from './DialogDTO';
import { DateDTO } from './DateDTO';
import * as moment from 'moment'

@Component({
  selector: 'pm-cart-dialog',
  templateUrl: './cart-dialog.component.html',
  styleUrls: ['./cart-dialog.component.css']
})

//TODO: Only show available dates
export class CartDialogComponent implements OnInit {

  id: any
  make: any
  model: any
  price: any
  owner: any
  ownerId : any
  dialog: DialogType

  startDate: Date
  endDate: Date

  datesValid: any

  public min = moment(new Date())
  .add(2,'d') 
  .toDate(); 

  public minEndDate = moment(new Date())
  .add(2,'d') 
  .toDate(); 


  public myFilter = (d: Date): boolean => {
    const day = d.getDay();
    // Prevent Sunday from being selected.
    return day !== 0
  }


  constructor(
    public dialogRef: MatDialogRef<RentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogDTO, private rentService: CartService, private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.id = this.data.id
    this.make = this.data.make
    this.model = this.data.model
    this.price = this.data.price
    this.owner = this.data.ownerUsername
    this.ownerId=this.data.ownerId
    this.dialog = this.data.dialog
    this.datesValid = true
  }

  close() {
    this.dialogRef.close();

  }

  addToCart() {
    if (this.startDate != null && this.endDate != null) {
      var request = new VehicleMainViewDTO(this.id, this.make, this.model, this.price, this.owner, this.ownerId)
      this.rentService.addItemToCart(request, this.startDate, this.endDate)
      this._snackBar.open("Item added to cart", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      this.close()
    } else {
      this.datesValid = false
    }

  }

  addToBundle() {
    this.dialogRef.close(new DateDTO(this.startDate, this.endDate))
  }

  startDateSelected(val){
    this.minEndDate=val
  }


}
