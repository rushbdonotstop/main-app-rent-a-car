import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UserPrivilegeDTO } from 'src/app/shared/models/user/UserPrivilegeDTO';


const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class PrivilegeService {

  constructor(private http: HttpClient) { }

  getPrivileges(id : number) {
    return this.http.get<UserPrivilegeDTO>('server/user/userPrivilege/'+id,  httpOptions);
  }
}
