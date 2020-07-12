import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VehicleLocation } from 'src/app/shared/models/location/VehicleLocation';
import { State } from 'src/app/shared/models/location/State';
import { City } from 'src/app/shared/models/location/City';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient) { }

  create(location : VehicleLocation){
    return this.http.post<VehicleLocation>('server/location/location/', location, httpOptions);
  }

  getCitiesByState(stateId : number) {
    return this.http.get<City[]>('server/location/location/citiesByState/'+stateId,  httpOptions);
  }

  getStates() {
    return this.http.get<State[]>('server/location/location/state/',  httpOptions);
  }

  getLocation(id : number) {
    return this.http.get<VehicleLocation>('server/location/location/'+id,  httpOptions);
  }
}
