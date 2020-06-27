import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/shared/models/user/User';
import { BundleDTO } from 'src/app/shared/models/request/bundleDTO';
import { RequestDTO } from 'src/app/shared/models/request/requestDTO';
import { AuthService } from './auth.service';


const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
    providedIn: 'root'
})
export class RequestService {

    user: User;

    constructor(private authService: AuthService, private http: HttpClient) { }

    canUserPostReview(vehicleId: number, userId : number) {
        return this.http.get<boolean>('server/request/request/canUserPostReview/' + vehicleId + '+' + userId);
    }

    getOwnerRequestHistory() {
        
         let ownerId = this.authService.getUserId();
         return this.http.get<Array<BundleDTO>>('server/request/request/ownerRequestHistory?ownerId=' + ownerId, httpOptions);
    }

    getBuyerRequestHistory() {
        
        let userId = this.authService.getUserId();
        return this.http.get<Array<BundleDTO>>('server/request/request/buyerRequestHistory?userId=' + userId, httpOptions);
   }

   getOwnerSingleRequests() {
        let ownerId = this.authService.getUserId();
       return this.http.get<Array<RequestDTO>>('server/request/request/ownerSingleRequests?ownerId=' + ownerId, httpOptions);
   }

   getBuyerSingleRequests() {
    let userId = this.authService.getUserId();
    return this.http.get<Array<RequestDTO>>('server/request/request/buyerSingleRequests?ownerId=' + userId, httpOptions);
}

   changeStatusOfRequest(bundleId: number, changeType: number) {

    return this.http.get<boolean>('server/request/request/changeStatus?bundleId=' + bundleId + '&changeType=' + changeType, httpOptions);
   }


}