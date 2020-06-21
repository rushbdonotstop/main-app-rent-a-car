import { Component, OnInit } from '@angular/core';
import { BundleDTO } from 'src/app/shared/models/request/bundleDTO';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { RequestService } from 'src/app/core/services/request.service';
import { RequestDetailsComponent } from './request-details/request-details.component';
import { RequestDTO } from 'src/app/shared/models/request/requestDTO';
import { User } from 'src/app/shared/models/user/User';
import { ReportDialogComponent } from '../report-dialog/report-dialog.component';
import { VehicleStatisticComponent } from './vehicle-statistic/vehicle-statistic.component';


@Component({
  templateUrl: './request-history.component.html',
  styleUrls: ['./request-history.component.css']
})
export class RequestHistoryComponent implements OnInit {

  selectedHistory = 'receivedRequests';
  showSelectedHistory = 'Received Requests'
  displayedColumns: string[] = ['username', 'totalCost', 'numberOfRequests', 'status', 'details'];
  displayedColumns2: string[] = ['username', 'makePlusModel', 'startDate', 'endDate', 'totalCost', 'status', 'report'];
  bundleList : BundleDTO[];
  requestList : RequestDTO[];
  dataSource: MatTableDataSource<BundleDTO>;
  dataSourceRequests : MatTableDataSource<RequestDTO>


  constructor(private requestService : RequestService, public dialog: MatDialog)  { 
    
  }

  ngOnInit() {

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
      }
    )
  }
  
  onChange() {
    if(this.selectedHistory == 'sentRequests') {
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
    }

    if(this.selectedHistory == 'receivedRequests') {
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
    }
  }

  openDetails(bundle: BundleDTO) {
    const dialogRef = this.dialog.open(RequestDetailsComponent, {
      width: '1200px',
      height: '600px',
      data: { bundle: bundle,
              selectedHistory : this.selectedHistory }
    });
    
    dialogRef.afterClosed().subscribe(result => {
      this.onChange();

    });
  }

  openStatistic(){
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
    });
  }

  isRentingFinished(request: RequestDTO) {
    if (new Date(request.endDate) < new Date()){
      return true;
    }
    else{
      return false;
    };
  }

}
