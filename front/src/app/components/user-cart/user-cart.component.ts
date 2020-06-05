import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/core/services/cart.service';
import { Cart } from 'src/app/shared/models/cart/Cart';
import { Request } from 'src/app/shared/models/cart/Request';
import { MatTableDataSource, MatDialog, MatSnackBar } from '@angular/material';
import { RequestAndVehicle } from 'src/app/shared/models/cart/RequestAndVehicle';
import { DetailedCart } from 'src/app/shared/models/cart/DetailedCart';
import { Bundle } from 'src/app/shared/models/cart/Bundle';
import { ViewPriceListComponent } from '../price-list/view-price-list/view-price-list.component';
import { VehicleDetailsComponent } from '../vehicle-details/vehicle-details.component';
import { BundleAndVehicle } from 'src/app/shared/models/cart/BundleAndVehicle';
import { MatListModule } from '@angular/material/list';

@Component({
  templateUrl: './user-cart.component.html',
  styleUrls: ['./user-cart.component.css']
})
export class UserCartComponent implements OnInit {

  emptyCart: boolean
  cart: DetailedCart
  requests: RequestAndVehicle[] = []
  bundleList: BundleAndVehicle[] = []
  dataSourceRequests: MatTableDataSource<RequestAndVehicle>;
  dataSourceBundle: MatTableDataSource<BundleAndVehicle>;
  displayedColumns: string[] = ['make', 'model', 'price', 'owner', 'details', 'prices', 'remove'];
  displayedColumns2: string[] = ['make', 'model', 'price', 'owner', 'remove'];

  constructor(private cartService: CartService, public dialog: MatDialog, private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.cart = this.cartService.getCart()
    this.requests = this.cart.requests
    this.bundleList = this.cart.bundles
    this.dataSourceRequests = new MatTableDataSource<RequestAndVehicle>(this.requests);
    this.dataSourceBundle = new MatTableDataSource<BundleAndVehicle>(this.bundleList);
    if (this.requests.length == 0 && this.bundleList.length == 0)
      this.emptyCart = true
    else
      this.emptyCart = false
    console.log(this.cart)
    // for (let bundle of this.cart.bundles) {
    //   this.dataSourceBundle.push(new MatTableDataSource<RequestAndVehicle>(bundle.requests));
    // }
  }

  removeFromBundle(id: number) {
    // this.bundleList.forEach(element => {
    //   if (element.id == id) {
    //     this.bundleList.splice(this.bundleList.indexOf(element), 1);
    //     this.dataSourceBundle = new MatTableDataSource<RequestAndVehicle>(this.bundleList);
    //     return;
    //   }
    // });
  }

  removeBundle() { }

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
      height: '600px',
      data: { id: vehicleId }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  remove(request) {
    this.requests.forEach(element => {
      if (element.vehicleId == request.vehicleId) {
        this.requests.splice(this.requests.indexOf(element), 1);
        this.dataSourceRequests = new MatTableDataSource<RequestAndVehicle>(this.requests);
        this.cartService.updateRequests(this.requests)
        return;
      }
    });
  }

  buy() {
    this.cartService.buy().subscribe(data => {
      this._snackBar.open("Successfully rented!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      this.cartService.newCart()
      this.ngOnInit()
    })
  }

  clear() {
    this.cartService.newCart()
    this.ngOnInit()
  }



}
