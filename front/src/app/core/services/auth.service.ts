import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from 'src/app/shared/models/user';
import { LoginDTO } from 'src/app/shared/models/loginDTO';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(user: LoginDTO) {
    alert(JSON.stringify(user))
    return this.http.post<User>(`/user/user/loginTest`,  JSON.stringify(user));
  }
}
