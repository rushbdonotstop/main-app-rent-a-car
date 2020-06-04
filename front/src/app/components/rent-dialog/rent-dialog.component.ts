import { Component, OnInit, Inject } from '@angular/core';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { manualRequest } from 'src/app/shared/models/cart/manualRequest';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { MatButtonModule } from '@angular/material/button';
import { CartService } from 'src/app/core/services/cart.service';

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

  constructor(
    public dialogRef: MatDialogRef<RentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: VehicleMainViewDTO, private rentService: CartService, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.id=this.data.id
    this.make=this.data.make
    this.model=this.data.model
    this.price=this.data.price
    this.owner=this.data.ownerUsername
  }

  rent() {
    var request = new manualRequest()
    request.ownerId = this.owner
    request.id = null
    request.userId = null
    request.endDate = new Date()
    request.startDate = new Date()
    request.vehicleId = this.id
    alert(this.model)
    this.rentService.manualRent(request).subscribe(data => {
      this._snackBar.open("Successfully rented", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    },
      error => {
        this._snackBar.open("Failed", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      })

  }

}
