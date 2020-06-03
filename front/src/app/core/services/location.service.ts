import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VehicleLocation } from 'src/app/shared/models/location/VehicleLocation';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient) { }

  getLocation(id : number) {
    return this.http.get<VehicleLocation>('server/location/location/'+id,  httpOptions);
  }
}
