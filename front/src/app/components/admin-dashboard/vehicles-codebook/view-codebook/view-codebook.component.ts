import { Component, OnInit, ViewChild, Inject, ViewChildren, Query, QueryList } from '@angular/core';
import { MatSnackBar, MatSort, MatTableDataSource, MatPaginator, MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { CatalogueItem } from 'src/app/shared/models/catalogue/CatalogueItem';
import { CatalogueService } from 'src/app/core/services/catalogue.service';
import { VehicleModel } from 'src/app/shared/models/catalogue/VehicleModel';
import { NotificationFromServer } from 'src/app/shared/models/Notification';

@Component({
  selector:'catalogue',
  templateUrl: './view-codebook.component.html',
  styleUrls: ['./view-codebook.component.css'],
})
export class ViewCodebookComponent implements OnInit {
  displayedColumnsFuelTypes: string[] = ['id', 'value', 'action'];
  dataSourceFuelTypes: MatTableDataSource<CatalogueItem>;
  displayedColumnsMakes: string[] = ['id', 'value', 'action'];
  dataSourceMakes: MatTableDataSource<CatalogueItem>;
  displayedColumnsModels: string[] = ['id', 'value', 'makeId', 'makeValue', 'action'];
  dataSourceModels: MatTableDataSource<VehicleModel>;
  displayedColumnsStyles: string[] = ['id', 'value', 'action'];
  dataSourceStyles: MatTableDataSource<CatalogueItem>;
  displayedColumnsTransmissionTypes: string[] = ['id', 'value', 'action'];
  dataSourceTransmissionTypes: MatTableDataSource<CatalogueItem>;

  modelList: VehicleModel[] = [];
  makeList: CatalogueItem[] = [];

  @ViewChildren(MatPaginator) paginator: QueryList<MatPaginator>;
  @ViewChildren(MatSort) sort: QueryList<MatSort>;

  constructor(private snackBar: MatSnackBar, private catalogueService: CatalogueService, private dialog: MatDialog) { }
  
  ngOnInit() {
    this.catalogueService.getFuelTypes()
      .subscribe(data => {
        this.dataSourceFuelTypes = new MatTableDataSource<CatalogueItem>(data);
        this.dataSourceFuelTypes.paginator = this.paginator.toArray[0];
        this.dataSourceFuelTypes.sort = this.sort.toArray[0];
      },
      error => {
        this.snackBar.open("Server error!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      }
    );
    this.catalogueService.getMakes()
    .subscribe(data=> {
      this.dataSourceMakes = new MatTableDataSource<CatalogueItem>(data);
      this.dataSourceMakes.paginator = this.paginator.toArray[1];
      this.dataSourceMakes.sort = this.sort.toArray[1];
      this.makeList = data;
    },
    error => {
      this.snackBar.open("Server error!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    });
    this.catalogueService.getModels()
    .subscribe(data=> {
      this.dataSourceModels = new MatTableDataSource<VehicleModel>(data);
      this.dataSourceModels.paginator = this.paginator.toArray[2];
      this.dataSourceModels.sort = this.sort.toArray[2];
    },
    error => {
      this.snackBar.open("Server error!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    });
    this.catalogueService.getStyles()
    .subscribe(data => {
      this.dataSourceStyles = new MatTableDataSource<CatalogueItem>(data);
      this.dataSourceStyles.paginator = this.paginator.toArray[3];
      this.dataSourceStyles.sort = this.sort.toArray[3];
    },
    error => {
      this.snackBar.open("Server error!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    });
    this.catalogueService.getTransmissions()
    .subscribe(data => {
      this.dataSourceTransmissionTypes = new MatTableDataSource<CatalogueItem>(data);
      this.dataSourceTransmissionTypes.paginator = this.paginator.toArray[4];
      this.dataSourceTransmissionTypes.sort = this.sort.toArray[4];
    },
    error => {
      this.snackBar.open("Server error!", "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    });
  }

  notification: NotificationFromServer;

  editFuelType(fuelType: CatalogueItem) {
    const dialogRef = this.dialog.open(EditFuelTypeDialog, {
      data: fuelType,
    });

    let fuelTypeEditRequest: CatalogueItem;
    dialogRef.afterClosed().subscribe(data => {
      if(data != fuelType.value) {
      fuelTypeEditRequest = fuelType;
      fuelTypeEditRequest.value = data;
      this.catalogueService.putFuelType(fuelTypeEditRequest).subscribe(data1 => {
        this.notification = data1;

        if(this.notification.success) {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
          fuelType.value = data;
        } else {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
        }
      },
      error => {
        this.snackBar.open("Server error!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      });
      }
    });
  }

  editMake(catalogueItem: CatalogueItem) {
    const dialogRef = this.dialog.open(EditMakeDialog, {
      data: catalogueItem,
    });

    let catalogueItemEditRequest: CatalogueItem;
    dialogRef.afterClosed().subscribe(data => {
      if(data != catalogueItem.value) {
      catalogueItemEditRequest = catalogueItem;
      catalogueItemEditRequest.value = data;
      this.catalogueService.putVehicleMake(catalogueItemEditRequest).subscribe(data1 => {
        this.notification = data1;

        if(this.notification.success) {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
          catalogueItem.value = data;
        } else {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
        }
      },
      error => {
        this.snackBar.open("Server error!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      });
      }
    });
  }

  editModel(catalogueItem: VehicleModel) {
    const dialogRef = this.dialog.open(EditMakeDialog, {
      data: catalogueItem,
    });

    let catalogueItemEditRequest: VehicleModel;
    dialogRef.afterClosed().subscribe(data => {
      if(data != catalogueItem.value) {
      catalogueItemEditRequest = catalogueItem;
      catalogueItemEditRequest.value = data;
      this.catalogueService.putVehicleModel(catalogueItemEditRequest).subscribe(data1 => {
        this.notification = data1;

        if(this.notification.success) {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
          catalogueItem.value = data;
        } else {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
        }
      },
      error => {
        this.snackBar.open("Server error!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      });
      }
    });
  }

  editStyle(catalogueItem: CatalogueItem) {
    const dialogRef = this.dialog.open(EditMakeDialog, {
      data: catalogueItem,
    });

    let catalogueItemEditRequest: CatalogueItem;
    dialogRef.afterClosed().subscribe(data => {
      if(data != catalogueItem.value) {
      catalogueItemEditRequest = catalogueItem;
      catalogueItemEditRequest.value = data;
      this.catalogueService.putVehicleStyle(catalogueItemEditRequest).subscribe(data1 => {
        this.notification = data1;

        if(this.notification.success) {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
          catalogueItem.value = data;
        } else {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
        }
      },
      error => {
        this.snackBar.open("Server error!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      });
      }
    });
  }

  editTransmission(catalogueItem: CatalogueItem) {
    const dialogRef = this.dialog.open(EditMakeDialog, {
      data: catalogueItem,
    });

    let catalogueItemEditRequest: CatalogueItem;
    dialogRef.afterClosed().subscribe(data => {
      if(data != catalogueItem.value) {
      catalogueItemEditRequest = catalogueItem;
      catalogueItemEditRequest.value = data;
      this.catalogueService.putVehicleTransmission(catalogueItemEditRequest).subscribe(data1 => {
        this.notification = data1;

        if(this.notification.success) {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
          catalogueItem.value = data;
        } else {
          this.snackBar.open(this.notification.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
        }
      },
      error => {
        this.snackBar.open("Server error!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      });
      }
    });
  }

  newFuelType: CatalogueItem = new CatalogueItem();
  newMake: CatalogueItem = new CatalogueItem();
  newModel: VehicleModel = new VehicleModel();
  selectedMake: CatalogueItem = new CatalogueItem();
  newStyle: CatalogueItem = new CatalogueItem();
  newTransmission: CatalogueItem = new CatalogueItem();

  addFuelType() {
    if(!this.newFuelType.value) {
      this.snackBar.open("Enter fuel type value!", "", {
        duration: 2000,
          verticalPosition: 'bottom'
      });
    } else {
      this.catalogueService.addFuelType(this.newFuelType).subscribe(data=> {
          this.snackBar.open(data.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
      });
      this.newFuelType.value = "";
    }
  }

  addMake() {
    if(!this.newMake.value) {
      this.snackBar.open("Enter vehicle make value!", "", {
        duration: 2000,
          verticalPosition: 'bottom'
      });
    } else {
      this.catalogueService.addMake(this.newMake).subscribe(data=> {
          this.snackBar.open(data.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
      });
      this.newMake.value = "";
    }
  }

  addModel() {
    alert(JSON.stringify(this.selectedMake));
    if(!this.newModel.value) {
      this.snackBar.open("Enter vehicle model value!", "", {
        duration: 2000,
          verticalPosition: 'bottom'
      });
    } else if(!this.selectedMake) {
      this.snackBar.open("Select vehicle make value!", "", {
        duration: 2000,
          verticalPosition: 'bottom'
      });
    } else {
      this.newModel.vehicleMake = this.selectedMake;
      this.catalogueService.addModel(this.newModel).subscribe(data=> {
          this.snackBar.open(data.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
      });
      this.newModel.value = "";
      this.selectedMake = new CatalogueItem();
    }
  }

  addTransmission() {
    if(!this.newTransmission.value) {
      this.snackBar.open("Enter vehicle transmission type value!", "", {
        duration: 2000,
          verticalPosition: 'bottom'
      });
    } else {
      this.catalogueService.addTransmission(this.newTransmission).subscribe(data=> {
          this.snackBar.open(data.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
      });
      this.newTransmission.value = "";
    }
  }

  addStyle() {
    if(!this.newStyle.value) {
      this.snackBar.open("Enter vehicle style value!", "", {
        duration: 2000,
          verticalPosition: 'bottom'
      });
    } else {
      this.catalogueService.addStyle(this.newStyle).subscribe(data=> {
          this.snackBar.open(data.text, "", { 
            duration: 2000,
          verticalPosition: 'bottom'});
      });
      this.newStyle.value = "";
    }
  }

  deleteMake(catalogItem: CatalogueItem) {
    this.catalogueService.deleteMake(catalogItem).subscribe(data => {
      this.snackBar.open(data.text, "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }
    );
  }

  deleteModel(catalogItem: VehicleModel) {
    this.catalogueService.deleteModel(catalogItem).subscribe(data => {
      this.snackBar.open(data.text, "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }
    );
  }

  deleteFuelType(catalogItem: CatalogueItem) {
    this.catalogueService.deleteFuelType(catalogItem).subscribe(data => {
      this.snackBar.open(data.text, "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }
    );
  }

  deleteTransmission(catalogItem: CatalogueItem) {
    this.catalogueService.deleteTransmissionType(catalogItem).subscribe(data => {
      this.snackBar.open(data.text, "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }
    );
  }

  deleteStyle(catalogItem: CatalogueItem) {
    this.catalogueService.deleteVehicleStyle(catalogItem).subscribe(data => {
      this.snackBar.open(data.text, "", {
        duration: 2000,
        verticalPosition: 'bottom'
      });
    }
    );
  }
}

@Component({
  selector:'edit-fuel-type-dialog',
  templateUrl:'dialog/edit-fuel-type-dialog.html'
})
export class EditFuelTypeDialog {
  value: string;

  constructor(public dialogRef: MatDialogRef<EditFuelTypeDialog>,
  @Inject(MAT_DIALOG_DATA) public data: CatalogueItem) {
    this.value = data.value;
  }

  onNoClick(): void {
    this.dialogRef.close(this.data.value);
  }
}

@Component({
  selector:'edit-make-dialog',
  templateUrl:'dialog/edit-make-dialog.html'
})
export class EditMakeDialog {
  value: string;

  constructor(public dialogRef: MatDialogRef<EditMakeDialog>,
  @Inject(MAT_DIALOG_DATA) public data: CatalogueItem) {
    this.value = data.value;
  }

  onNoClick(): void {
    this.dialogRef.close(this.data.value);
  }
}

@Component({
  selector:'edit-model-dialog',
  templateUrl:'dialog/edit-model-dialog.html'
})
export class EditModelDialog {
  value: string;

  constructor(public dialogRef: MatDialogRef<EditModelDialog>,
  @Inject(MAT_DIALOG_DATA) public data: CatalogueItem) {
    this.value = data.value;
  }

  onNoClick(): void {
    this.dialogRef.close(this.data.value);
  }
}

@Component({
  selector:'edit-style-dialog',
  templateUrl:'dialog/edit-style-dialog.html'
})
export class EditStyleDialog {
  value: string;

  constructor(public dialogRef: MatDialogRef<EditStyleDialog>,
  @Inject(MAT_DIALOG_DATA) public data: CatalogueItem) {
    this.value = data.value;
  }

  onNoClick(): void {
    this.dialogRef.close(this.data.value);
  }
}

@Component({
  selector:'edit-transmission-dialog',
  templateUrl:'dialog/edit-transmission-dialog.html'
})
export class EditTransmissionlDialog {
  value: string;

  constructor(public dialogRef: MatDialogRef<EditTransmissionlDialog>,
  @Inject(MAT_DIALOG_DATA) public data: CatalogueItem) {
    this.value = data.value;
  }

  onNoClick(): void {
    this.dialogRef.close(this.data.value);
  }
}
