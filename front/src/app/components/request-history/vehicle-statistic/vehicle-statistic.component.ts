import { Component, OnInit } from '@angular/core';
import { MatDialogRef, MatDialog, MatSnackBar } from '@angular/material';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { User } from 'src/app/shared/models/user/User';
import { Statistics } from 'src/app/shared/models/statistic/Statistics';

@Component({
  templateUrl: './vehicle-statistic.component.html',
  styleUrls: ['./vehicle-statistic.component.css']
})
export class VehicleStatisticComponent implements OnInit {

  statistic : Statistics;

  constructor(public dialogRef: MatDialogRef<VehicleStatisticComponent>,
    public dialog: MatDialog, private vehicleService : VehicleService, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    var user = new User()
    user = JSON.parse(localStorage.getItem('userObject'))
    
    if(user.userDetails.userType.toString() == 'AGENT'){
      this.vehicleService.getStatistics(user.id).subscribe(
        statistic => {
          this.statistic = statistic;
        }
      )
    }
    else{
      this._snackBar.open('Only agents have statistics privilege!', "", {
        duration: 3000,
        verticalPosition: 'bottom'
      });
    }
    

  }

}
