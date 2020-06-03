import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { CatalogueItem } from 'src/app/shared/models/catalogue/CatalogueItem';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class CatalogueService {

  constructor(private http: HttpClient) { }

  getMake(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/catalogue/vehicleMake/'+id,  httpOptions);
  }

  getModel(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/catalogue/vehicleModel/'+id,  httpOptions);
  }

  getFuelType(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/catalogue/vehicleFuelType/'+id,  httpOptions);
  }

  getStyle(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/catalogue/vehicleStyle/'+id,  httpOptions);
  }

  getTransmission(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/catalogue/vehicleTransmission/'+id,  httpOptions);
  }
}
