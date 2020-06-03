import { Component, OnInit } from '@angular/core';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { MatSnackBar } from '@angular/material';

@Component({
  templateUrl: './view-vehicles.component.html',
  styleUrls: ['./view-vehicles.component.css']
})
export class ViewVehiclesComponent implements OnInit {

  displayedColumns: string[] = ['make', 'model', 'price', 'owner', 'details', 'add', 'bundle'];
  displayedColumns2: string[] = ['make', 'model', 'price', 'owner', 'remove'];
  vehicleList: VehicleMainViewDTO[];
  dataSource : VehicleMainViewDTO[] = [];
  dataSourceBundle : VehicleMainViewDTO[] = [];

  constructor(private vehicleService: VehicleService, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.vehicleService.getAll()
      .subscribe(vehicles => {
        this.vehicleList = vehicles;
        this.dataSource = this.vehicleList;
        this.dataSourceBundle = []
        this.dataSourceBundle.push(vehicles[0]);
      },
        error => {
          this._snackBar.open("Server error!", "", {
            duration: 2000,
            verticalPosition: 'top'
          });
        }
      )
  }

}
