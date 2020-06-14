import { Component, OnInit, Input } from '@angular/core';
import { Review } from 'src/app/shared/models/review/Review';
import { ReviewService } from 'src/app/core/services/review.service';

@Component({
  selector: 'pm-view-reviews',
  templateUrl: './view-vehicle-reviews.component.html',
  styleUrls: ['./view-vehicle-reviews.component.css']
})
export class ViewVehicleReviewsComponent implements OnInit {

  reviews : Review[]

  @Input('vehicleId') private vehicleId: number

  constructor(private reviewService : ReviewService) { }

  ngOnInit() {
    this.reviewService.getApprovedVehicleReviews(this.vehicleId).subscribe(results => {
      this.reviews = results;
    },
    error => alert("Server error!")
      
      )
  }

}
