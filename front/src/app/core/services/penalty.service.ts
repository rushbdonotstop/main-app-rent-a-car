import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Penalty } from 'src/app/shared/models/user/Penalty';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json' 
  })
}

@Injectable({
  providedIn: 'root'
})
export class PenaltyService {

  constructor(private http: HttpClient) { }

  getPenalties(userId: number){
    return this.http.get<Penalty[]>('server/user/penalty/'+userId,httpOptions);
  }
}
