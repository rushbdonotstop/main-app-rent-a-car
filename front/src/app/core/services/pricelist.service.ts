import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Pricelist } from 'src/app/shared/models/pricelist/Pricelist';
import { MinAndMaxPricesDTO } from 'src/app/shared/models/MinAndMaxPricesDTO';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class PricelistService {

  constructor(private http: HttpClient) { }

  getPricelists(id : number) {
    return this.http.get<Pricelist[]>('server/pricelist/pricelist/'+id,  httpOptions);
  }

  minAndMax() {
    return this.http.get<MinAndMaxPricesDTO>('server/pricelist/pricelist/minAndMax',  httpOptions);
  }
}
