import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { CatalogueItem } from 'src/app/shared/models/catalogue/CatalogueItem';
import { VehicleModel } from 'src/app/shared/models/catalogue/VehicleModel';
import { NotificationFromServer } from 'src/app/shared/models/Notification';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class CatalogueService {
  constructor(private http: HttpClient) { }

  getModelByMake(makeId : number) {
    return this.http.get<CatalogueItem[]>('server/catalogue/catalogue/vehicleModel/byMake/'+makeId,  httpOptions);
  }

  getMake(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/catalogue/vehicleMake/'+id,  httpOptions);
  }

  getModel(id : number) {
    return this.http.get<VehicleModel>('server/catalogue/catalogue/vehicleModel/'+id,  httpOptions);
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

  getMakes() {
    return this.http.get<CatalogueItem[]>('server/catalogue/catalogue/vehicleMake/',  httpOptions);
  }

  getModels() {
    return this.http.get<VehicleModel[]>('server/catalogue/catalogue/vehicleModel/',  httpOptions);
  }

  getFuelTypes() {
    return this.http.get<CatalogueItem[]>('server/catalogue/catalogue/vehicleFuelType/',  httpOptions);
  }

  getStyles() {
    return this.http.get<CatalogueItem[]>('server/catalogue/catalogue/vehicleStyle/',  httpOptions);
  }

  getTransmissions() {
    return this.http.get<CatalogueItem[]>('server/catalogue/catalogue/vehicleTransmission/',  httpOptions);
  }
  
  putFuelType(fuelType: CatalogueItem) {
    return this.http.put<NotificationFromServer>('server/catalogue/catalogue/vehicleFuelType/'+fuelType.id, JSON.stringify(fuelType), httpOptions);
  }

  
  putVehicleMake(catalogueItemEditRequest: CatalogueItem) {
    return this.http.put<NotificationFromServer>('server/catalogue/catalogue/vehicleMake/'+catalogueItemEditRequest.id, JSON.stringify(catalogueItemEditRequest), httpOptions);
  }

  
  putVehicleModel(catalogueItemEditRequest: VehicleModel) {
    return this.http.put<NotificationFromServer>('server/catalogue/catalogue/vehicleModel/'+catalogueItemEditRequest.id, JSON.stringify(catalogueItemEditRequest), httpOptions);
  }
  
  putVehicleTransmission(catalogueItemEditRequest: CatalogueItem) {
    return this.http.put<NotificationFromServer>('server/catalogue/catalogue/vehicleTransmission/'+catalogueItemEditRequest.id, JSON.stringify(catalogueItemEditRequest), httpOptions);
  }

  putVehicleStyle(catalogueItemEditRequest: CatalogueItem) {
    return this.http.put<NotificationFromServer>('server/catalogue/catalogue/vehicleStyle/'+catalogueItemEditRequest.id, JSON.stringify(catalogueItemEditRequest), httpOptions);
  }
  
  addFuelType(newFuelType: CatalogueItem) {
    return this.http.post<NotificationFromServer>('server/catalogue/catalogue/vehicleFuelType/', JSON.stringify(newFuelType), httpOptions);
  }
  
  addMake(newMake: CatalogueItem) {
    return this.http.post<NotificationFromServer>('server/catalogue/catalogue/vehicleMake/', JSON.stringify(newMake), httpOptions);
  }
  
  addModel(newModel: VehicleModel) {
    return this.http.post<NotificationFromServer>('server/catalogue/catalogue/vehicleModel/', JSON.stringify(newModel), httpOptions);
  }

  addStyle(newStyle: CatalogueItem) {
    return this.http.post<NotificationFromServer>('server/catalogue/catalogue/vehicleStyle/', JSON.stringify(newStyle), httpOptions);
  }

  addTransmission(newTransmission: CatalogueItem) {
    return this.http.post<NotificationFromServer>('server/catalogue/catalogue/vehicleTransmission/', JSON.stringify(newTransmission), httpOptions);
  }
}
