import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar, MatTableDataSource } from '@angular/material';
import { PricelistService } from 'src/app/core/services/pricelist.service';
import { Pricelist } from 'src/app/shared/models/pricelist/Pricelist';

@Component({
  templateUrl: './view-price-list.component.html',
  styleUrls: ['./view-price-list.component.css']
})
export class ViewPriceListComponent implements OnInit {

  vehicleId : number;
  pricelists : Pricelist[] = [];

  displayedColumns: string[] = ['startDate', 'endDate', 'price', 'priceByMile', 'priceCollision', 'discount'];
  dataSource: MatTableDataSource<Pricelist>;

  constructor(public dialogRef: MatDialogRef<ViewPriceListComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private _snackBar: MatSnackBar, private pricelistService : PricelistService) {
      this.vehicleId = data.id;
  }

  ngOnInit() {
    this.pricelistService.getPricelists(this.vehicleId)
    .subscribe(pricelists => {
      this.pricelists = pricelists;
      this.dataSource = new MatTableDataSource<Pricelist>(this.pricelists);
    },
    error => {
      this._snackBar.open("Error while retreiving vehicle pricelists!", "", {
        duration: 2000,
        verticalPosition: 'top'
      });
    })
  }

  close(): void {
    this.dialogRef.close();
  }

}
