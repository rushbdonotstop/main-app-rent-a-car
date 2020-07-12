import { Component, OnInit, ViewChild } from '@angular/core';
import { Review } from 'src/app/shared/models/review/Review';
import { MatTableDataSource, MatPaginator, MatSort, MatSnackBar } from '@angular/material';
import { RequestService } from 'src/app/core/services/request.service';
import { ReviewService } from 'src/app/core/services/review.service';
import { Status } from 'src/app/shared/models/cart/Status';
import { ReviewStatus } from 'src/app/shared/models/review/ReviewStatus';

@Component({
  templateUrl: './comment-requests.component.html',
  styleUrls: ['./comment-requests.component.css']
})
export class CommentRequestsComponent implements OnInit {

  displayedColumns: string[] = ['text', 'rating', 'vehicleId', 'userId', 'date', 'approve', 'reject'];
  reviews: Review[];
  dataSource: MatTableDataSource<Review>;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(private _snackBar: MatSnackBar, private reviewService:ReviewService) { }

  ngOnInit() {
    this.reviewService.getPendingReviews().subscribe(results => {
        this.reviews = results;
        this.dataSource = new MatTableDataSource<Review>(this.reviews);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    },
    error => alert("Server error!"))
  }

  approve(review : Review){
    review.status = ReviewStatus.APPROVED
    this.reviewService.updateReview(review).subscribe(results => {
      this.reviewService.getPendingReviews().subscribe(results => {
        this.reviews = results;
        this.dataSource = new MatTableDataSource<Review>(this.reviews);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    },
    error => alert("Server error!"))
      this._snackBar.open(results.text.toString(), "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    },
    error => alert("Server error!"))
  }

  reject(review : Review){
    review.status = ReviewStatus.REJECTED
    this.reviewService.updateReview(review).subscribe(results => {
      this.reviewService.getPendingReviews().subscribe(results => {
        this.reviews = results;
        this.dataSource = new MatTableDataSource<Review>(this.reviews);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    },
    error => alert("Server error!"))
      this._snackBar.open(results.text.toString(), "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    },
    error => alert("Server error!"))
  }

}
