import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from 'src/app/shared/models/user/User';
import { LoginRequestDTO } from 'src/app/shared/models/user/LoginRequestDTO';
import { TokenDTO } from 'src/app/shared/models/token/TokenDTO';
import {Observable, of} from "rxjs";
import {catchError, mapTo, tap} from 'rxjs/operators';
import * as jwt from 'jwt-decode';
import { NotificationFromServer } from 'src/app/shared/models/Notification';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private readonly  JWT = 'JWT';
  private loggedUser: string;

  constructor(private http: HttpClient) {}


  isLoggedIn() {
    return this.getToken();
  }

  login(user: LoginRequestDTO): Observable<boolean>  {
    return this.http.post<any>(`server/user/login`,  user).pipe(
      tap(token => {this.doLoginUser(user.username, token.jwt);
        
      }),
      mapTo(true),
      catchError(error => {
        alert(error.error);
        return of(false);
      })
    );
  }

  logout() {
    this.doLogoutUser();
  }

  private doLoginUser(username: string, token: string) {
    this.loggedUser = username;
    this.storeToken(token);
  }

  private doLogoutUser() {
    this.loggedUser = null;
    this.removeToken();
  }

  getToken() {
    return localStorage.getItem(this.JWT);
  }

  private storeToken(token: string) {
    localStorage.setItem(this.JWT, token);
  }
  private removeToken() {
    localStorage.removeItem(this.JWT);
  }

  isExpectedRole(expectedRole: any) {
    let role = this.getRole;
      if(expectedRole == role) {
        return true;
      }
      return false;
  }

  getRole() {
    return jwt(this.getToken()).authorities[0];
  }

  register(user: User) {
    return this.http.post<NotificationFromServer>(`server/user/registration`,  JSON.stringify(user), httpOptions);
  }

  registerVerification(token : String) {
    return this.http.get<NotificationFromServer>('server/user/verification/' +  token, httpOptions);
  }

  getUserId() {
    return jwt(this.getToken()).userId;
  }
}
