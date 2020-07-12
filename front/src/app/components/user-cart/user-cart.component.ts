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
import { PricelistService } from 'src/app/core/services/pricelist.service';
import { Pricelist } from 'src/app/shared/models/pricelist/Pricelist';
import { PrivilegeService } from 'src/app/core/services/privilege.service';
import { User } from 'src/app/shared/models/user/User';
import { PenaltyStatus } from 'src/app/shared/models/user/PenaltyStatus';
import { PenaltyService } from 'src/app/core/services/penalty.service';

@Component({
  templateUrl: './user-cart.component.html',
  styleUrls: ['./user-cart.component.css']
})
export class UserCartComponent implements OnInit {

  emptyCart: boolean
  cart: DetailedCart = new DetailedCart()
  requests: RequestAndVehicle[] = []
  bundleList: BundleAndVehicle[] = []
  dataSourceRequests: MatTableDataSource<RequestAndVehicle>;
  dataSourceBundle: MatTableDataSource<BundleAndVehicle>;
  displayedColumns: string[] = ['make', 'model', 'price', 'startDate', 'endDate', 'owner', 'details', 'prices', 'remove'];
  displayedColumns2: string[] = ['make', 'model', 'price', 'owner', 'remove'];
  private price: number = 0
  discounts = []
  rentingPrivilege: boolean = false
  hasPenalties: boolean = false

  constructor(private cartService: CartService, public dialog: MatDialog, private _snackBar: MatSnackBar,
    private pricelistService: PricelistService, private privilegeService: PrivilegeService, private penaltyService: PenaltyService) { }

  ngOnInit() {
    this.cart = this.cartService.getCart()
    if (this.cart != null) {
      this.requests = this.cart.requests
      this.bundleList = this.cart.bundles
      this.dataSourceRequests = new MatTableDataSource<RequestAndVehicle>(this.requests);
      this.dataSourceBundle = new MatTableDataSource<BundleAndVehicle>(this.bundleList);
      console.log(this.requests)

      if (this.requests.length == 0 && this.bundleList.length == 0)
        this.emptyCart = true
      else {
        this.emptyCart = false
        this.calculateTotalPrice()
        //Check privileges
        this.privilegeService.getPrivileges(JSON.parse(localStorage.getItem('userObject')).id).subscribe(data => {
          for (let privilege of data.userPrivileges) {
            if (privilege.toString() == "RENT_VEHICLE") {
              this.rentingPrivilege = true;
            }
            //Check penalties
            var loggedInUser = new User()
            loggedInUser = JSON.parse(localStorage.getItem('userObject'))
            this.penaltyService.getPenalties(loggedInUser.id).subscribe(
              data => {
                if (data.length > 0) {
                  this.hasPenalties = true;
                }
              }
            )
          }
        })
      }
    }
  }

  removeFromBundle(element: RequestAndVehicle, bundle: BundleAndVehicle) {
    const index = bundle.requests.indexOf(element, 0);
    bundle.requests.splice(index, 1)
    this.cartService.updateBundles(this.bundleList)
    this.calculateTotalPrice()
    if (this.isCartEmpty)
      this.emptyCart = true
  }

  removeBundle(bundle: BundleAndVehicle) {
    const index = this.bundleList.indexOf(bundle, 0);
    this.bundleList.splice(index, 1)
    this.cartService.updateBundles(this.bundleList)
    this.calculateTotalPrice()
    if (this.isCartEmpty)
      this.emptyCart = true
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
      height: '600px',
      data: { id: vehicleId }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  remove(request) {
    this.requests.splice(this.requests.indexOf(request), 1);
    this.dataSourceRequests = new MatTableDataSource<RequestAndVehicle>(this.requests);
    this.cartService.updateRequests(this.requests)
    this.calculateTotalPrice()
    if (this.isCartEmpty)
      this.emptyCart = true
    return;

  }

  buy() {
    if (this.rentingPrivilege && !this.hasPenalties) {
      this.cartService.buy().subscribe(data => {
        this._snackBar.open("Successfully rented!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
        this.cartService.newCart()
        this.cart = new DetailedCart()
        this.dataSourceRequests = new MatTableDataSource<RequestAndVehicle>();
        this.bundleList = [];
        if (this.isCartEmpty)
          this.emptyCart = true
      },
        error => {
          this._snackBar.open("Error occured", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        })
    }
    else {
      this._snackBar.open("You have no renting privileges. Please try contacting an administrator to get the issue resolved", "", {
        duration: 5000,
        verticalPosition: 'bottom'
      });
    }
  }

  clear() {
    this.cartService.newCart()
    this.ngOnInit()
  }

  calculateTotalPrice() {

    var price = 0

    this.discounts = []

    this.calculateRequestPrice(this.requests)

    for (let b of this.bundleList) {
      this.calculateRequestPrice(b.requests)
    }
    
  }

  calculateRequestPrice(requests) {
    for (let r of requests) {
      this.pricelistService.getPricelists(r.vehicleId).subscribe(data => {
        console.log("curr price")
        console.log(this.price)
        var requestPrice = 0
        var startDate = r.startDate
        var endDate = r.endDate
        var prices = []
        prices = data
        console.log(prices)
        for (let p of prices) {
          if (this.compareDate(p.startDate, startDate) == -1 && this.compareDate(p.endDate, endDate) == 1) {
            console.log("whole renting time is on 1 pricelist")
            var daysOnPriceList = this.daysDiff(endDate, startDate) //whole renting time is on 1 pricelist
            var discount = 0
            if (daysOnPriceList >= p.vehicleDiscount.numDays) {
              discount = (p.vehicleDiscount.discount / 100)
              this.discounts.push('discount ' + p.vehicleDiscount.discount + "% on vehicle " + r.make + " " + r.model + " (" + daysOnPriceList + " days / required " + p.vehicleDiscount.numDays + ")")
            }
            console.log(this.price)
            this.price += p.price * (daysOnPriceList) * (1-discount)
            requestPrice += p.price * (daysOnPriceList) * (1-discount)
            console.log(p.price)
            console.log(daysOnPriceList)
            console.log(discount)
            console.log(this.price)
          }
          else if (this.compareDate(p.startDate, startDate) == -1 && this.compareDate(p.endDate, endDate) == -1) {
            if (this.datesOverlap(startDate, endDate, p.startDate, p.endDate)) {
              console.log("pricelist starts before but ends before request")
              var daysOnPriceList = this.daysDiff(p.endDate, startDate) //pricelist starts before but ends before request
              var discount = 0
              if (daysOnPriceList >= p.vehicleDiscount.numDays) {
                this.discounts.push('discount ' + p.vehicleDiscount.discount + "% on vehicle " + r.make + " " + r.model + " (" + daysOnPriceList + " days / required " + p.vehicleDiscount.numDays + ")")
                discount = (p.vehicleDiscount.discount / 100)
              }
              console.log(this.price)
              this.price += p.price * (this.daysDiff(p.endDate, startDate)) * (1-discount)
              requestPrice += p.price * (this.daysDiff(p.endDate, startDate)) * (1-discount)
              console.log(p.price)
              console.log(daysOnPriceList)
              console.log(discount)
              console.log(this.price)
            }
          }
          else if (this.compareDate(p.startDate, startDate) == 1 && this.compareDate(p.endDate, endDate) == 1) {
            if (this.datesOverlap(startDate, endDate, p.startDate, p.endDate)) {
              console.log("pricelist starts after but ends after")
              var daysOnPriceList = this.daysDiff(endDate, p.startDate) //pricelist starts after but ends after
              var discount = 0
              if (daysOnPriceList >= p.vehicleDiscount.numDays) {
                this.discounts.push('discount ' + p.vehicleDiscount.discount + "% on vehicle " + r.make + " " + r.model + " (" + daysOnPriceList + " days / required " + p.vehicleDiscount.numDays + ")")
                discount = (p.vehicleDiscount.discount / 100)
              }
              console.log(this.price)
              this.price += p.price * (this.daysDiff(endDate, p.startDate)) * (1-discount)
              requestPrice += p.price * (this.daysDiff(endDate, p.startDate)) * (1-discount)
              console.log(p.price)
              console.log(daysOnPriceList)
              console.log(discount)
              console.log(this.price)
            }
            else if (this.compareDate(p.startDate, startDate) == 1 && this.compareDate(p.endDate, endDate) == -1) {
              console.log("pricelist in between start and end date")
              var daysOnPriceList = this.daysDiff(p.endDate, p.startDate) //pricelist in between start and end date
              var discount = 0
              if (daysOnPriceList >= p.vehicleDiscount.numDays) {
                this.discounts.push('discount ' + p.vehicleDiscount.discount + "% on vehicle " + r.make + " " + r.model + " (" + daysOnPriceList + " days / required " + p.vehicleDiscount.numDays + ")")
                discount = (p.vehicleDiscount.discount / 100)
              }
              console.log(this.price)
              this.price += p.price * (daysOnPriceList) * (1-discount)
              requestPrice += p.price * (daysOnPriceList) * (1-discount)
              console.log(p.price)
              console.log(daysOnPriceList)
              console.log(discount)
              console.log(this.price)
            }
          }
        }
        r.price = requestPrice
        console.log("subscribe")
        console.log(this.price)
        this.price = this.price
      });
    }
  }

  compareDate(date1: Date, date2: Date): number {
    let d1 = new Date(date1); let d2 = new Date(date2);

    // // Check if the dates are equal
    // let same = d1.getTime() === d2.getTime();
    // if (same) return 0;

    // Check if the first is greater than second
    if (d1 >= d2) return 1;

    // Check if the first is less than second
    if (d1 <= d2) return -1;
  }

  daysDiff(date1, date2) {
    var diff = Math.abs(new Date(date1).getTime() - new Date(date2).getTime());
    var diffDays = Math.ceil(diff / (1000 * 3600 * 24));
    return diffDays
  }

  datesOverlap(e1start, e1end, e2start, e2end) {
    return (e1start > e2start && e1start < e2end || e2start > e1start && e2start < e1end);
  }

  isCartEmpty(): boolean {
    if (this.requests.length == 0)
      if (this.bundleList.length == 0)
        return true;
      else {
        for (let bundle of this.bundleList) {
          if (bundle.requests.length != 0)
            return false;
        }
        return true;
      }
  }

  isBundleEmpty(bundle: Bundle): boolean {
    if (bundle.requests.length == 0)
      return true
    return false
  }

}
