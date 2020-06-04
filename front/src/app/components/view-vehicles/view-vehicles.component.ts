import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { MatSnackBar, MatTableDataSource, MatDialog } from '@angular/material';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { VehicleDetailsComponent } from '../vehicle-details/vehicle-details.component';
import { ViewPriceListComponent } from '../price-list/view-price-list/view-price-list.component';
import { SearchVehicleComponent } from '../search-vehicle/search-vehicle.component';

@Component({
  templateUrl: './view-vehicles.component.html',
  styleUrls: ['./view-vehicles.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ViewVehiclesComponent implements OnInit {

  displayedColumns: string[] = ['make', 'model', 'price', 'owner', 'details', 'prices', 'add', 'bundle'];
  displayedColumns2: string[] = ['make', 'model', 'price', 'owner', 'remove'];
  vehicleList: VehicleMainViewDTO[];
  bundleList : VehicleMainViewDTO[];
  dataSource: MatTableDataSource<VehicleMainViewDTO>;
  dataSourceBundle: MatTableDataSource<VehicleMainViewDTO>;

  results : VehicleMainViewDTO[]

  getUpdatedvalue($event) {  
    this.results = $event;  
    this.dataSource = new MatTableDataSource<VehicleMainViewDTO>(this.results)
}  
  
  constructor(public dialog: MatDialog, private vehicleService: VehicleService, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.vehicleService.getAll()
      .subscribe(vehicles => {
        this.vehicleList = vehicles;
        this.dataSource = new MatTableDataSource<VehicleMainViewDTO>(this.vehicleList);
        this.dataSourceBundle = new MatTableDataSource<VehicleMainViewDTO>();
        this.bundleList = [];
      },
        error => {
          this._snackBar.open("Server error!", "", {
            duration: 2000,
            verticalPosition: 'top'
          });
        }
      )
  }

  removeFromBundle(id: number) {
    this.bundleList.forEach(element => {
      if (element.id == id) {
        this.bundleList.splice(this.bundleList.indexOf(element), 1);
        this.dataSourceBundle = new MatTableDataSource<VehicleMainViewDTO>(this.bundleList);
        return;
      }
    });
  }

  openPrices(vehicleId : number){
    const dialogRef = this.dialog.open(ViewPriceListComponent, {
      width: '1200px',
      height: '600px',
      data: {id: vehicleId}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openDetails(vehicleId: number) {
    const dialogRef = this.dialog.open(VehicleDetailsComponent, {
      width: '1200px',
      height: '600px',
      data: {id: vehicleId}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  addToCart(id: number) {

  }

  addToBundle(element: VehicleMainViewDTO) {
    if (this.bundleList.length != 0) {
      if (this.bundleList[0].ownerUsername == element.ownerUsername) {
        this.bundleList.push(element);
        this.dataSourceBundle = new MatTableDataSource<VehicleMainViewDTO>(this.bundleList);
      }
      else {
        this._snackBar.open("Owner mismatch in bundle!", "", {
          duration: 2000,
          verticalPosition: 'top'
        });
      }
    }
    else {
      this.bundleList.push(element);
      this.dataSourceBundle = new MatTableDataSource<VehicleMainViewDTO>(this.bundleList);
    }

  }

}
