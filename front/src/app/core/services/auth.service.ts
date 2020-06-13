import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from 'src/app/shared/models/user/User';
import { LoginRequestDTO } from 'src/app/shared/models/user/LoginRequestDTO';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(user: LoginRequestDTO) {
    return this.http.post<User>(`server/user/user/loginTest`,  JSON.stringify(user), httpOptions);
  }
}
