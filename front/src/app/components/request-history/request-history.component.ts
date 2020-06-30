import { Component, OnInit } from '@angular/core';
import { BundleDTO } from 'src/app/shared/models/request/bundleDTO';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { RequestService } from 'src/app/core/services/request.service';
import { RequestDetailsComponent } from './request-details/request-details.component';
import { RequestDTO } from 'src/app/shared/models/request/requestDTO';
import { User } from 'src/app/shared/models/user/User';
import { ReportDialogComponent } from '../report-dialog/report-dialog.component';
import { VehicleStatisticComponent } from './vehicle-statistic/vehicle-statistic.component';
import { UserType } from 'src/app/shared/models/user/UserType';


@Component({
  templateUrl: './request-history.component.html',
  styleUrls: ['./request-history.component.css']
})
export class RequestHistoryComponent implements OnInit {

  selectedHistory = 'receivedRequests';
  showSelectedHistory = 'Received Requests'
  displayedColumns: string[] = ['username', 'totalCost', 'numberOfRequests', 'status', 'details'];
  displayedColumns2: string[] = ['username', 'makePlusModel', 'startDate', 'endDate', 'totalCost', 'status', 'report'];
  bundleList: BundleDTO[] = [];
  requestList: RequestDTO[];
  dataSource: MatTableDataSource<BundleDTO>;
  dataSourceRequests: MatTableDataSource<RequestDTO>


  constructor(private requestService: RequestService, public dialog: MatDialog) {

  }

  ngOnInit() {

    var user = JSON.parse(localStorage.getItem('userObject'));
    if (user.userDetails.userType == "AGENT") {
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
    } else {

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
  }

  onChange() {
    if (this.selectedHistory == 'sentRequests') {
      this.showSelectedHistory = 'Sent Requests';
      var user = JSON.parse(localStorage.getItem('userObject'));
      if (user.userDetails.userType == "AGENT") {
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
      } else {
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
      }
    }

    if (this.selectedHistory == 'receivedRequests') {
      this.showSelectedHistory = 'Received Requests'
      var user = JSON.parse(localStorage.getItem('userObject'));
      if (user.userDetails.userType == "AGENT") {
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
      } else {
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
      }
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

  openStatistic() {
    const dialogRef = this.dialog.open(VehicleStatisticComponent, {
      width: '750px',
      height: '350px'
    });
  }

  createReport(element: RequestDTO) {
    const dialogRef = this.dialog.open(ReportDialogComponent, {
      width: '400px',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      this.ngOnInit()
    });
  }

  isRentingFinished(request: RequestDTO) {
    if (new Date(request.endDate) < new Date()) {
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
}
