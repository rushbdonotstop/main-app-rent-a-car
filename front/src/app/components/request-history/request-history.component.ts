import { Component, OnInit } from '@angular/core';
import { BundleDTO } from 'src/app/shared/models/request/bundleDTO';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { RequestService } from 'src/app/core/services/request.service';
import { RequestDetailsComponent } from './request-details/request-details.component';
import { RequestDTO } from 'src/app/shared/models/request/requestDTO';

@Component({
  templateUrl: './request-history.component.html',
  styleUrls: ['./request-history.component.css']
})
export class RequestHistoryComponent implements OnInit {

  selectedHistory = 'receivedRequests';
  showSelectedHistory = 'Received Requests'
  displayedColumns: string[] = ['username', 'totalCost', 'numberOfRequests', 'status', 'details'];
  displayedColumns2: string[] = ['makePlusModel', 'startDate', 'endDate', 'totalCost', 'status'];
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
      }
    )
  }
  
  onChange() {
    if(this.selectedHistory == 'sentRequests') {
      this.requestService.getBuyerRequestHistory().subscribe(
        bundleList => {
          this.bundleList = bundleList;
          this.dataSource = new MatTableDataSource<BundleDTO>(this.bundleList);
        }
      )
    }

    if(this.selectedHistory == 'receivedRequests') {
      this.requestService.getOwnerRequestHistory().subscribe(
        bundleList => {
          this.bundleList = bundleList;
          this.dataSource = new MatTableDataSource<BundleDTO>(this.bundleList);
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
}
