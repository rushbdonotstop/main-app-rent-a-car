import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { LoginRequestDTO } from 'src/app/shared/models/user/LoginRequestDTO';
import { NotificationFromServer } from 'src/app/shared/models/Notification';
import { User } from 'src/app/shared/models/user/User';
import { UserDetails } from 'src/app/shared/models/user/UserDetails';
import { UserPrivilegesDTO } from 'src/app/shared/models/user/UserPrivilegesDTO';
import { UserPrivilegeRequest } from 'src/app/shared/models/user/UserPrivilegeRequest';
import { AgentRequest } from 'src/app/shared/models/AgentRequest';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  privilegeRequest: UserPrivilegeRequest = new UserPrivilegeRequest();
  constructor(private http: HttpClient) { }

  getUser(id: number) {
    return this.http.get<User>('server/user/user/'+id, httpOptions);
  }

  getAllAgentRequests() {
    return this.http.get<AgentRequest[]>('server/user/agentRequest', httpOptions);
  }

  getUsername(id : number) {
    return this.http.get<LoginRequestDTO>('server/user/user/username/'+id,  httpOptions);
  }

  canUserCreate(userId : number) {
    return this.http.get<boolean>('server/user/user/canUserCreate/'+userId,  httpOptions);
  }

  updateUserVehicleNum(userId : number) {
    return this.http.put<NotificationFromServer>('server/user/user/updateUserVehicleNumAfterCreate/'+userId,  httpOptions);
  }

  deleteUser(userId: number) {
    return this.http.delete<NotificationFromServer>('server/user/user/'+userId,  httpOptions);
  }

  getAllUsers() {
    return this.http.get<User[]>('server/user/user',  httpOptions);
  }

  getUserDetails(id: number) {
    return this.http.get<UserDetails>('server/user/userDetails/'+id,  httpOptions);
  }
  
  getUserPermissions(id: number) {
    return this.http.get<UserPrivilegesDTO>('server/user/userPrivilege/'+id,  httpOptions);
  }
  
  deletePermission(id: number, permission: string) {
    return this.http.delete<NotificationFromServer>('server/user/userPrivilege/'+id+'/'+permission, httpOptions);
  }

  postPermission(id: number, permission: string) {
    this.privilegeRequest.userPrivilege = permission;
    return this.http.post<NotificationFromServer>('server/user/userPrivilege/'+id, JSON.stringify(this.privilegeRequest), httpOptions);
  }

  rejectAgent(agentRequest: any) {
    return this.http.delete('server/user/agentRequest/'+agentRequest.id, httpOptions);
  }

  approveAgent(agentRequest: any) {
    return this.http.put('server/user/agentRequest/'+agentRequest.id, httpOptions);
  }
}
