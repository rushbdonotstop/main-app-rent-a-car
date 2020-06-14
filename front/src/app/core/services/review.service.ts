import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Review } from 'src/app/shared/models/review/Review';
import { Injectable } from '@angular/core';
import { NotificationFromServer } from 'src/app/shared/models/Notification';

const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
    providedIn: 'root'
})
export class ReviewService {

    constructor(private http: HttpClient) { }

    getApprovedVehicleReviews(vehicleId: number) {
        return this.http.get<Review[]>('server/review/getByVehicleId/' + vehicleId, httpOptions);
    }

    getPendingReviews() {
        return this.http.get<Review[]>('server/review/getPending', httpOptions);
    }

    createReview(review: Review) {
        return this.http.post<NotificationFromServer>('server/review/', JSON.stringify(review), httpOptions);
    }

    updateReview(review: Review) {
        return this.http.put<NotificationFromServer>('server/review/', JSON.stringify(review), httpOptions);
    }
}