import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatTableDataSource } from '@angular/material';

@Component({
  templateUrl: './edit-permissions.component.html',
  styleUrls: ['./edit-permissions.component.css']
})
export class EditPermissionsComponent implements OnInit {
  value: boolean[];

  constructor(public dialogRef: MatDialogRef<EditPermissionsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: boolean[]) {
      this.value = data;
    }

  ngOnInit() {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(): void {
    this.dialogRef.close(this.value);
  }
}
