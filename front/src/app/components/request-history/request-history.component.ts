import { Component, OnInit } from '@angular/core';
import { BundleDTO } from 'src/app/shared/models/request/bundleDTO';
import { MatTableDataSource, MatDialog, MatSnackBar } from '@angular/material';
import { RequestService } from 'src/app/core/services/request.service';
import { RequestDetailsComponent } from './request-details/request-details.component';
import { RequestDTO } from 'src/app/shared/models/request/requestDTO';
import { User } from 'src/app/shared/models/user/User';
import { ReportDialogComponent } from '../report-dialog/report-dialog.component';
import { VehicleStatisticComponent } from './vehicle-statistic/vehicle-statistic.component';
import { NewMessageDialogComponent } from '../user-inbox/new-message-dialog/new-message-dialog.component';
import { VehicleDetailsComponent } from '../vehicle-details/vehicle-details.component';



@Component({
  templateUrl: './request-history.component.html',
  styleUrls: ['./request-history.component.css']
})
export class RequestHistoryComponent implements OnInit {

  selectedHistory = 'receivedRequests';
  showSelectedHistory = 'Received Requests'

  displayedColumns: string[] = ['username', 'totalCost', 'numberOfRequests', 'status', 'details', 'message'];
  displayedColumns2: string[] = ['username', 'makePlusModel', 'startDate', 'endDate', 'totalCost', 'status', 'report', 'message', 'review', 'accept', 'cancel'];
  bundleList: BundleDTO[];
  requestList: RequestDTO[];
  dataSource: MatTableDataSource<BundleDTO>;
  dataSourceRequests: MatTableDataSource<RequestDTO>

  constructor(private requestService: RequestService, public dialog: MatDialog, public messageDialog: MatDialog, private _snackBar: MatSnackBar) {

  }

  ngOnInit() {

    // var user = JSON.parse(localStorage.getItem('userObject'));
    // if (user.userDetails.userType == "AGENT") {
    //   this.requestService.finishedBundle().subscribe(
    //     bundleList => {

    //       this.bundleList = bundleList;
    //       this.dataSource = new MatTableDataSource<BundleDTO>(this.bundleList);
    //       console.log("bundle:")
    //       console.log(bundleList)
    //     }
    //   );

    //   this.requestService.finishedRequests().subscribe(
    //     requestList => {
    //       this.requestList = requestList;
    //       this.dataSourceRequests = new MatTableDataSource<RequestDTO>(this.requestList);
    //       console.log("request:")
    //       console.log(requestList)
    //     }
    //   )
    // } else {

    this.requestService.getOwnerRequestHistory().subscribe(
      bundleList => {

        this.bundleList = bundleList;
        this.dataSource = new MatTableDataSource<BundleDTO>(this.bundleList);
        console.log(bundleList)
      }
    );

    this.requestService.getOwnerSingleRequests().subscribe(
      requestList => {
        this.requestList = requestList;
        this.dataSourceRequests = new MatTableDataSource<RequestDTO>(this.requestList);
        console.log(requestList)
      }
    )
  }

  onChange() {
    if (this.selectedHistory == 'sentRequests') {
      this.showSelectedHistory = 'Sent Requests';
      this.requestService.getBuyerRequestHistory().subscribe(
        bundleList => {
          this.bundleList = bundleList;
          this.dataSource = new MatTableDataSource<BundleDTO>(this.bundleList);
        }
      )

      this.requestService.getBuyerSingleRequests().subscribe(
        requestList => {
          this.requestList = requestList;
          this.dataSourceRequests = new MatTableDataSource<RequestDTO>(this.requestList);
        }
      )

    } else if (this.selectedHistory == 'receivedRequests') {
      console.log('OnChange function')
      this.showSelectedHistory = 'Received Requests'
      this.requestService.getOwnerRequestHistory().subscribe(
        bundleList => {
          this.bundleList = bundleList;
          this.dataSource = new MatTableDataSource<BundleDTO>(this.bundleList);
        }
      )

      this.requestService.getOwnerSingleRequests().subscribe(
        requestList => {
          this.requestList = requestList;
          this.dataSourceRequests = new MatTableDataSource<RequestDTO>(this.requestList);
        }
      )
    } else {
      this.showSelectedHistory = 'Finished Requests';
      this.requestService.finishedBundle().subscribe(
        bundleList => {

          this.bundleList = bundleList;
          this.dataSource = new MatTableDataSource<BundleDTO>(this.bundleList);
          console.log("bundle:")
          console.log(bundleList)
        }
      );

      this.requestService.finishedRequests().subscribe(
        requestList => {
          this.requestList = requestList;
          this.dataSourceRequests = new MatTableDataSource<RequestDTO>(this.requestList);
          console.log("request:")
          console.log(requestList)
        }
      )
    }
  }

  openDetails(bundle: BundleDTO) {
    const dialogRef = this.dialog.open(RequestDetailsComponent, {
      width: '1200px',
      height: '600px',
      data: {
        bundle: bundle,
        selectedHistory: this.selectedHistory
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.onChange();

    });
  }

  accept(request: RequestDTO) {
    this.requestService.changeStatusOfRequest(request.id, 1).subscribe(
      data => {
        if (data) {
          this._snackBar.open('Request is accepted successfully.', "", {
            duration: 3000,
            verticalPosition: 'bottom'
          });;
        } else {
          this._snackBar.open('Request is not accepted successfully', "", {
            duration: 3000,
            verticalPosition: 'bottom'
          });
        }
      }
    )
    this.onChange();
  }

  decline(request: RequestDTO) {
    this.requestService.changeStatusOfRequest(request.id, 2).subscribe(
      data => {
        if (data) {
          this._snackBar.open('Request is canceled successfully.', "", {
            duration: 3000,
            verticalPosition: 'bottom'
          });
        } else {
          this._snackBar.open('Request is not canceled successfully', "", {
            duration: 3000,
            verticalPosition: 'bottom'
          });
        }
      }
    )
    this.onChange();
  }

  openMessageDialog(bundle: BundleDTO) {
    const messageDialogRef = this.messageDialog.open(NewMessageDialogComponent, {
      width: '1200px',
      height: '600px',
      data: {
        bundle: bundle,
        selectedHistory: this.selectedHistory
      }
    });

    messageDialogRef.afterClosed().subscribe(result => {

    });
  }

  openMessageDialogSingleRequest(request: RequestDTO) {
    var bundle = new BundleDTO();
    bundle.requestsList = new Array<RequestDTO>();
    bundle.requestsList.push(request);
    bundle.username = request.username;
    this.openMessageDialog(bundle);
  }

  canUserReview(request: RequestDTO) {
    if (request.status.toString() == "PAID" && this.selectedHistory == 'sentRequests') {
      return true;
    } else {
      return false;
    }
  }

  leaveReview(vehicleId: number) {
    const reviewDialogRef = this.dialog.open(VehicleDetailsComponent, {
      width: '1200px',
      height: '700px',
      data: { id: vehicleId }
    });

    reviewDialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  

  openStatistic() {
    const dialogRef = this.dialog.open(VehicleStatisticComponent, {
      width: '750px',
      height: '350px'
    });
  }

  createReport(element: RequestDTO) {
    element.viewMode=false;
    const dialogRef = this.dialog.open(ReportDialogComponent, {
      width: '400px',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit()
    });
  }

  isRentingFinished(request: RequestDTO) {
    if (new Date(request.endDate) < new Date() && request.status.toString() == 'PAID' && this.selectedHistory == 'finishedRequests') {
      return true;
    }
    else {
      return false;
    };
  }

  agentLogged() {
    if (JSON.parse(localStorage.getItem('userObject')).userDetails.userType == "AGENT")
      return true
    return false
  }

  showTwoOrOneButton() {
    if (this.selectedHistory == 'receivedRequests') {
      return true;
    } else {
      return false;
    }
  }

  showCancelButton(request: RequestDTO) {
    if (request.status.toString() == 'PENDING' && this.selectedHistory == 'sentRequests') {
      return true;
    } else {
      return false;
    }
  }

  showAgentButtons(request: RequestDTO) {
    if (request.status.toString() == 'PENDING' && this.selectedHistory == 'receivedRequests') {
      return true;
    } else {
      return false;
    }
  }
}
