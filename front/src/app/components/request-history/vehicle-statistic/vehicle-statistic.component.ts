import { Component, OnInit } from '@angular/core';
import { MatDialogRef, MatDialog, MatSnackBar } from '@angular/material';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { User } from 'src/app/shared/models/user/User';
import { Statistics } from 'src/app/shared/models/statistic/Statistics';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  templateUrl: './vehicle-statistic.component.html',
  styleUrls: ['./vehicle-statistic.component.css']
})
export class VehicleStatisticComponent implements OnInit {

  statistic : Statistics;

  constructor(public dialogRef: MatDialogRef<VehicleStatisticComponent>,
    public dialog: MatDialog, private vehicleService : VehicleService, private _snackBar: MatSnackBar
    ,private authService: AuthService) { }

  ngOnInit() {
    var role = this.authService.getRole();
    
    if(role.toString() == 'ROLE_AGENT'){
      this.vehicleService.getStatistics(this.authService.getUserId()).subscribe(
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
