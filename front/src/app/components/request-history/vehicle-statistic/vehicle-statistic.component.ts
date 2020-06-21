import { Component, OnInit } from '@angular/core';
import { MatDialogRef, MatDialog } from '@angular/material';

@Component({
  templateUrl: './vehicle-statistic.component.html',
  styleUrls: ['./vehicle-statistic.component.css']
})
export class VehicleStatisticComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<VehicleStatisticComponent>,
    public dialog: MatDialog) { }

  ngOnInit() {
  }

}
