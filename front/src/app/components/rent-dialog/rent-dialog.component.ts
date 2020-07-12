import { Component, OnInit, Inject } from '@angular/core';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { manualRequest } from 'src/app/shared/models/cart/manualRequest';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { CartService } from 'src/app/core/services/cart.service';
import * as moment from 'moment'

@Component({
  selector: 'pm-rent-dialog',
  templateUrl: './rent-dialog.component.html',
  styleUrls: ['./rent-dialog.component.css']
})
export class RentDialogComponent implements OnInit {

  id: any
  make: any
  model: any
  price: any
  owner: any

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
    @Inject(MAT_DIALOG_DATA) public data: VehicleMainViewDTO, private rentService: CartService, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.id = this.data.id
    this.make = this.data.make
    this.model = this.data.model
    this.price = this.data.price
    this.owner = this.data.ownerUsername
    this.datesValid = true
  }

  close() {
    this.dialogRef.close();

  }

  rent() {
    if (this.startDate != null && this.endDate != null) {
      var request = new manualRequest()
      request.ownerId = 1
      request.id = null
      request.userId = null
      request.endDate = this.endDate
      request.startDate = this.startDate
      request.vehicleId = this.id
      this.rentService.manualRent(request).subscribe(() => {
        this._snackBar.open("Successfully rented", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        this.close()
      },
        () => {
          this._snackBar.open("Failed", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        })
    } else {
      this.datesValid = false
    }

  }

  startDateSelected(val){
    this.minEndDate=val
  }

}
