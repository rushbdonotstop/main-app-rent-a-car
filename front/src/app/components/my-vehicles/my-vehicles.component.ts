import { Component, OnInit, ViewChild } from "@angular/core";
import { MatPaginator, MatSort, MatDialog, MatSnackBar, MatTableDataSource } from '@angular/material';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { CartService } from 'src/app/core/services/cart.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { User } from 'src/app/shared/models/user/User';
import { MyVehiclesReportDialogComponent } from './my-vehicles-report-dialog/my-vehicles-report-dialog.component';


@Component({
    templateUrl: './my-vehicles.component.html',
    styleUrls: ['./my-vehicles.component.css']
})
export class MyVehiclesComponent implements OnInit {
    displayedColumns: string[] = ['make', 'model', 'reports'];
    dataSource: MatTableDataSource<VehicleMainViewDTO>;
    vehicleList: VehicleMainViewDTO[] = [];

    @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
    @ViewChild(MatSort, { static: true }) sort: MatSort;

    constructor(public dialog: MatDialog, private vehicleService: VehicleService, private _snackBar: MatSnackBar, private cartService: CartService, private authService: AuthService) {}

    ngOnInit(): void {
        var loggedInUser = new User();
        loggedInUser = JSON.parse(localStorage.getItem('userObject'));
        this.vehicleService.getAllFromUser(loggedInUser.id).subscribe(vehicles=> {
            this.vehicleList = vehicles;
            this.dataSource = new MatTableDataSource<VehicleMainViewDTO>(vehicles);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sort;
        },
        error => {
          this._snackBar.open("Server error!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        });
    }

    openReports(vehicle: VehicleMainViewDTO) {
        const dialogRef = this.dialog.open(MyVehiclesReportDialogComponent, {
            width: '400px',
            data: vehicle.id
          });
      
          dialogRef.afterClosed().subscribe(result => {
          });
    }
}