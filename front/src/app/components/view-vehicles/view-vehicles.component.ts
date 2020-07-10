import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { MatSnackBar, MatTableDataSource, MatDialog, MatPaginator, MatSort } from '@angular/material';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { VehicleDetailsComponent } from '../vehicle-details/vehicle-details.component';
import { ViewPriceListComponent } from '../price-list/view-price-list/view-price-list.component';
import { SearchVehicleComponent } from '../search-vehicle/search-vehicle.component';
import { CartService } from 'src/app/core/services/cart.service';
import { Vehicle } from 'src/app/shared/models/vehicle/Vehicle';
import { RentDialogComponent } from '../rent-dialog/rent-dialog.component';
import { CartDialogComponent } from '../cart-dialog/cart-dialog.component';
import { DialogType } from 'src/app/shared/models/cart/DialogType';
import { AuthService } from 'src/app/core/services/auth.service';
import { UserType } from 'src/app/shared/models/user/UserType';
import { User } from 'src/app/shared/models/user/User';
import { MapDialogComponent } from '../map-dialog/map-dialog.component';

@Component({
  templateUrl: './view-vehicles.component.html',
  styleUrls: ['./view-vehicles.component.css']
})
export class ViewVehiclesComponent implements OnInit {

  displayedColumns: string[] = ['make', 'model', 'averageRating', 'price', 'mileage', 'owner', 'details', 'prices', 'add', 'bundle', 'rent', 'map'];
  displayedColumns2: string[] = ['make', 'model', 'price', 'owner', 'remove'];
  vehicleList: VehicleMainViewDTO[];
  bundleList: VehicleMainViewDTO[];
  dataSource: MatTableDataSource<VehicleMainViewDTO>;
  dataSourceBundle: MatTableDataSource<VehicleMainViewDTO>;

  results: VehicleMainViewDTO[]

  getUpdatedvalue($event) {
    this.results = $event;
    this.dataSource = new MatTableDataSource<VehicleMainViewDTO>(this.results)
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(public dialog: MatDialog, private vehicleService: VehicleService, private _snackBar: MatSnackBar, private cartService: CartService, private authService: AuthService) { }

  ngOnInit() {
    this.vehicleService.getAll()
      .subscribe(vehicles => {
        this.vehicleList = vehicles;
        this.dataSource = new MatTableDataSource<VehicleMainViewDTO>(this.vehicleList);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.dataSourceBundle = new MatTableDataSource<VehicleMainViewDTO>();
        this.bundleList = [];
      },
        error => {
          this._snackBar.open("Server error!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
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

  openPrices(vehicleId: number) {
    const dialogRef = this.dialog.open(ViewPriceListComponent, {
      width: '1200px',
      height: '600px',
      data: { id: vehicleId }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openDetails(vehicleId: number) {
    const dialogRef = this.dialog.open(VehicleDetailsComponent, {
      width: '1200px',
      height: '700px',
      data: { id: vehicleId }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  addToCart(vehicle: VehicleMainViewDTO) {
    this.openAddToCartDialog(vehicle, DialogType.Cart)

  }

  addToBundleDialog(vehicle: VehicleMainViewDTO) {
    this.openAddToCartDialog(vehicle, DialogType.Bundle)
  }

  addBundleToCart() {
    if (this.bundleList.length > 1) {
      this.cartService.addBundleToCart(this.bundleList);
      this.clearBundle()
      this._snackBar.open("Bundle added to cart", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    } else {
      this._snackBar.open("Bundle must contain at least two vehicles", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }
  }

  addToBundle(element: VehicleMainViewDTO) {
    if (this.isRentingAllowed(element)) {
      if (this.bundleList.length != 0) {
        if (this.bundleList[0].ownerUsername == element.ownerUsername) {
          this.bundleList.push(element);
          this.dataSourceBundle = new MatTableDataSource<VehicleMainViewDTO>(this.bundleList);
        }
        else {
          this._snackBar.open("Owner mismatch in bundle!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        }
      }
      else {
        this.bundleList.push(element);
        this.dataSourceBundle = new MatTableDataSource<VehicleMainViewDTO>(this.bundleList);
      }
    } else {
      this._snackBar.open("Renting your own car not allowed!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }
  }

  clearBundle() {
    this.bundleList = []
    this.dataSourceBundle = new MatTableDataSource<VehicleMainViewDTO>([]);
  }
  inStoreRent(element) {
    this.openManualRentDialog(element)
  }

  viewMap(element){
    this.openMapDialog(element)
  }

  openMapDialog(element): void {
    const dialogRef = this.dialog.open(MapDialogComponent, {
      width: '400px',
      data:{id: element.id}
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openManualRentDialog(element): void {
    const dialogRef = this.dialog.open(RentDialogComponent, {
      width: '400px',
      data: { make: element.make, price: element.price, ownerUsername: element.ownerUsername, model: element.model, id: element.id }
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  openAddToCartDialog(element, dialog: DialogType) {
    if (this.isRentingAllowed(element)) {
      const dialogRef = this.dialog.open(CartDialogComponent, {
        width: '400px',
        data: { make: element.make, price: element.price, ownerUsername: element.ownerUsername, model: element.model, id: element.id, dialog: dialog }
      });

      if (dialog == 1) {
        dialogRef.afterClosed().subscribe(result => {
          var request = new VehicleMainViewDTO(element.id, element.make, element.model, element.price, element.ownerUsername)
          request.startDate = result.startDate
          request.endDate = result.endDate
          this.addToBundle(request)
          this._snackBar.open("Item added to bundle", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        });
      }
    }
    else {
      this._snackBar.open("Renting your own car not allowed!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }
  }

  isRentingAllowed(car: VehicleMainViewDTO) {
    var loggedInUser = new User()
    loggedInUser = JSON.parse(localStorage.getItem('userObject'))

    //agent cant rent his own car
    if (loggedInUser.userDetails.userType.toString() == "AGENT")
      if (car.ownerId == loggedInUser.id)
        return false;
      else
        return true
    else
        return true;

  }

  //simulate false check
  hasApp(element){
    if(element.id==1)
    return true
    else
    return false
  }

}
