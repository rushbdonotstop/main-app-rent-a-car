import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { Vehicle } from 'src/app/shared/models/vehicle/Vehicle';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<VehicleMainViewDTO[]>('server/vehicle/search',  httpOptions);
  }

  getVehicle(vehicleId : number) {
    return this.http.get<Vehicle>('server/vehicle/vehicle/'+vehicleId,  httpOptions);
  }

}