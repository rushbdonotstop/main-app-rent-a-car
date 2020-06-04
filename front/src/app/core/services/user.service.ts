import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { LoginRequestDTO } from 'src/app/shared/models/user/LoginRequestDTO';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsername(id : number) {
    return this.http.get<LoginRequestDTO>('server/user/user/username/'+id,  httpOptions);
  }
}
