import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
    providedIn: 'root'
})
export class RequestService {

    constructor(private http: HttpClient) { }

    canUserPostReview(vehicleId: number, userId : number) {
        return this.http.get<boolean>('server/request/request/canUserPostReview/' + vehicleId + '+' + userId);
    }
}