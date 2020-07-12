import { Component, OnInit, Inject } from '@angular/core';
import { RequestDTO } from 'src/app/shared/models/request/requestDTO';
import { MatDialogRef, MAT_DIALOG_DATA, MatTableDataSource, MatSnackBar, MatDialog } from '@angular/material';
import { RequestService } from 'src/app/core/services/request.service';
import { ReportDialogComponent } from '../../report-dialog/report-dialog.component';
import { RequestStatus } from 'src/app/shared/models/request/RequestStatus';
import { VehicleDetailsComponent } from '../../vehicle-details/vehicle-details.component';

@Component({
  selector: 'pm-request-details',
  templateUrl: './request-details.component.html',
  styleUrls: ['./request-details.component.css']
})
export class RequestDetailsComponent implements OnInit {

  bundleId: number;
  totalCost: number;
  username: String;
  requestList: RequestDTO[];
  userId: number;
  selectedHistory: String;
  dataSource: MatTableDataSource<RequestDTO>;
  displayedColumns: string[] = ['makePlusModel', 'startDate', 'endDate', 'totalCost', 'status', 'report', 'review'];
  isUserAgent: boolean;

  constructor(public dialogRef: MatDialogRef<RequestDetailsComponent>,
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private requestService: RequestService, private _snackBar: MatSnackBar) {
    this.bundleId = data.bundle.id;
    this.totalCost = data.bundle.totalCost;
    this.username = data.bundle.username;
    this.requestList = data.bundle.requestsList;
    this.selectedHistory = data.selectedHistory;
    this.dataSource = new MatTableDataSource<RequestDTO>(this.requestList);
  }

  ngOnInit() {
    var loggedInUser = JSON.parse(localStorage.getItem('userObject'))
    if (loggedInUser.userDetails.userType.toString() == "AGENT") {
      this.isUserAgent = true;
      this.displayedColumns = ['makePlusModel', 'startDate', 'endDate', 'totalCost', 'status', 'report', 'review'];
    }
  }


  close(): void {
    this.dialogRef.close();
  }

  accept() {
    this.requestService.changeStatusOfBundle(this.bundleId, 1).subscribe(
      data => {
        if (data) {
          this._snackBar.open('Request/s are accepted successfully.', "", {
            duration: 3000,
            verticalPosition: 'bottom'
          });
          this.close();
        } else {
          this._snackBar.open('Request/s are not accepted successfully', "", {
            duration: 3000,
            verticalPosition: 'bottom'
          });
        }
      }
    )
  }

  decline() {
    this.requestService.changeStatusOfBundle(this.bundleId, 2).subscribe(
      data => {
        if (data) {
          this._snackBar.open('Request/s are canceled successfully.', "", {
            duration: 3000,
            verticalPosition: 'bottom'
          });
          this.close();
        } else {
          this._snackBar.open('Request/s are not canceled successfully', "", {
            duration: 3000,
            verticalPosition: 'bottom'
          });
        }
      }
    )
  }

  canUserReview(request: RequestDTO) {
    //alert('Status je:' + request.status.toString() + '  a selected je: ' + this.selectedHistory)
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

  createReport(element: RequestDTO) {
    element.viewMode = false;
    const dialogRef = this.dialog.open(ReportDialogComponent, {
      width: '400px',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
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

  
  shouldButtonsBeShownAcceptDecline(requestStatus : RequestStatus) {
    if ((requestStatus.toString() == 'PENDING') && (this.selectedHistory == "receivedRequests")) {
      return true;
    } else { 
      return false;
    }
  }

  shouldCancelBeShown(requestStatus: RequestStatus) {
    if ((requestStatus.toString() == 'PENDING') && (this.selectedHistory == 'sentRequests')) {
      return true;
    } else {
      return false;
    }
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
}
