import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { ReportService } from 'src/app/core/services/report.service';
import { BundleDTO } from 'src/app/shared/models/request/bundleDTO';
import { RequestDTO } from 'src/app/shared/models/request/requestDTO';
import { User } from 'src/app/shared/models/user/User';
import { Report } from 'src/app/shared/models/report/Report';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'pm-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.css']
})
export class ReportDialogComponent implements OnInit {

  id: any
  mileage: any
  additionalInfo: any
  vehicleId: any
  userId: any


  valid: any

  constructor(
    public dialogRef: MatDialogRef<ReportDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: RequestDTO, private reportService: ReportService, private _snackBar: MatSnackBar, private authService: AuthService) {
  }

  ngOnInit() {
    this.vehicleId = this.data.vehicleId
    this.userId = this.authService.getUserId();
    this.valid = true
  }

  close() {
    this.dialogRef.close();

  }

  createReport() {
    if (this.mileage != null ) {
      var report = new Report(this.mileage, this.additionalInfo, this.vehicleId, this.userId)
      this.reportService.sendReport(report).subscribe()
      this._snackBar.open("Report sent", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
      this.close()
    } else {
      this.valid = false
    }

  }

}
