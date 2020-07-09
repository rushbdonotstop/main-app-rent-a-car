import { OnInit, Inject, Component, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar, MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { Report } from 'src/app/shared/models/report/Report';
import { ReportService } from 'src/app/core/services/report.service';
import { JsonPipe } from '@angular/common';

@Component({
    selector: 'my-vehicles-report-dialog',
    templateUrl: './my-vehicles-report-dialog.html',
    styleUrls: ['./my-vehicles-report-dialog.css']
})
export class MyVehiclesReportDialogComponent implements OnInit {
    dataSource: MatTableDataSource<Report>;
    displayedColumns: string[] = ['mileage', 'additionalInfo', 'startDate', 'endDate'];

    constructor(
        public dialogRef: MatDialogRef<MyVehiclesReportDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: number, private reportService: ReportService, private _snackBar: MatSnackBar 
    ) {
    }
    @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
    @ViewChild(MatSort, { static: true }) sort: MatSort;

    ngOnInit() {
        this.reportService.getAllFromVehicle(this.data).subscribe(reports => {
            this.dataSource = new MatTableDataSource<Report>(reports);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort = this.sort;
        });
    }
}