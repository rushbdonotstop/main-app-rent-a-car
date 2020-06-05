import { Injectable } from '@angular/core';
import { Cart } from 'src/app/shared/models/cart/Cart';
import { Vehicle } from 'src/app/shared/models/vehicle/Vehicle';
import { Request } from 'src/app/shared/models/cart/Request';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { RequestAndVehicle } from 'src/app/shared/models/cart/RequestAndVehicle';
import { DetailedCart } from 'src/app/shared/models/cart/DetailedCart';
import { BundleAndVehicle } from 'src/app/shared/models/cart/BundleAndVehicle';
import { faCartPlus } from '@fortawesome/free-solid-svg-icons';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UserService } from './user.service';
import { User } from 'src/app/shared/models/user/User';
import { manualRequest } from 'src/app/shared/models/cart/manualRequest';

const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
//TODO : get owner id
export class CartService {

  constructor(private http: HttpClient, private loginService: UserService) { }

  addItemToCart(vehicle: VehicleMainViewDTO, startDate: Date, endDate: Date) {
    var cart = localStorage.getItem('cart')
    if (cart == null) {
      var newCart = new DetailedCart()
      var request = new RequestAndVehicle(vehicle)
      request.startDate = startDate
      request.endDate = endDate
      request.ownerId = 1
      newCart.requests.push(request)
      localStorage.setItem('cart', JSON.stringify(newCart))
      console.log(localStorage.getItem('cart'))
    }
    else {
      var oldCart = JSON.parse(localStorage.getItem('cart'));
      var request = new RequestAndVehicle(vehicle)
      request.startDate = startDate
      request.endDate = endDate
      request.ownerId = 1
      oldCart.requests.push(request)
      localStorage.setItem('cart', JSON.stringify(oldCart))
      console.log(localStorage.getItem('cart'))
    }
  }

  addBundleToCart(bundleList: VehicleMainViewDTO[]) {
    var cart = localStorage.getItem('cart')
    if (cart == null) {
      var newCart = new DetailedCart()
      var bundle = new BundleAndVehicle()
      bundle.id=null
      for (let b of bundleList) {
        bundle.requests.push(new RequestAndVehicle(b))
      }
      newCart.bundles.push(bundle)
      localStorage.setItem('cart', JSON.stringify(newCart))
      console.log(localStorage.getItem('cart'))
    }
    else {
      var oldCart = JSON.parse(localStorage.getItem('cart'));
      var bundle = new BundleAndVehicle()
      bundle.id=null
      for (let b of bundleList) {
        bundle.requests.push(new RequestAndVehicle(b))
      }
      oldCart.bundles.push(bundle)
      localStorage.setItem('cart', JSON.stringify(oldCart))
    }
  }

  getCart() {
    return JSON.parse(localStorage.getItem('cart'));
  }

  updateRequests(requests: RequestAndVehicle[]) {
    var oldCart = JSON.parse(localStorage.getItem('cart'));
    oldCart.requests = requests
    localStorage.setItem('cart', JSON.stringify(oldCart))
  }

  buy() {
    var detailedCart = this.getCart()
    var userId = JSON.parse(localStorage.getItem('userObject')).id;
    var cart = new Cart(detailedCart, userId)
    console.log(detailedCart)
    console.log(cart)
    return this.http.post<boolean>('server/request/request', JSON.stringify(cart), httpOptions);
  }

  newCart() {
    localStorage.setItem('cart', JSON.stringify(new DetailedCart()))
  }

  manualRent(request: manualRequest) {
    return this.http.post<boolean>('server/request/request/physicalRent', JSON.stringify(request), httpOptions);
  }

}
