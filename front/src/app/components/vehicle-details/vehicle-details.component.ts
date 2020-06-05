import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { Vehicle } from 'src/app/shared/models/vehicle/Vehicle';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { CatalogueService } from 'src/app/core/services/catalogue.service';
import { LocationService } from 'src/app/core/services/location.service';
import { UserService } from 'src/app/core/services/user.service';
import { PricelistService } from 'src/app/core/services/pricelist.service';
import { CatalogueItem } from 'src/app/shared/models/catalogue/CatalogueItem';
import { Pricelist } from 'src/app/shared/models/pricelist/Pricelist';
import { VehicleLocation } from 'src/app/shared/models/location/VehicleLocation';

@Component({
  templateUrl: './vehicle-details.component.html',
  styleUrls: ['./vehicle-details.component.css']
})
export class VehicleDetailsComponent implements OnInit {

  vehicleId : number;
  make : CatalogueItem;
  model : CatalogueItem;
  style : CatalogueItem;
  fuelType : CatalogueItem;
  transmission : CatalogueItem;
  location : VehicleLocation;
  username : String;
  vehicle : Vehicle;

  mileageLimit : String;
  collisionProtection : String;

  retrievedImage: any;

  constructor(public dialogRef: MatDialogRef<VehicleDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, 
    private vehicleService: VehicleService, 
    private catalogueService : CatalogueService,
    private locationService : LocationService,
    private userService : UserService,
    private _snackBar: MatSnackBar) {
      this.vehicleId = data.id;
  }

  ngOnInit() {
      this.vehicleService.getVehicle(this.vehicleId)
      .subscribe(vehicle => {
        this.vehicle = vehicle;
        
        this.vehicleService.getImage(this.vehicle.image.name).subscribe(results => {
          var base64Data = results.picByte
          this.retrievedImage = 'data:image/jpeg;base64,' + base64Data;
        },
        error => alert("ERROR while getting image"))

        if (this.vehicle.mileageLimit == 0){
          this.mileageLimit = "Unlimited";
        }
        else{
          this.mileageLimit = this.vehicle.mileageLimit.toString();
        }

        
        if (this.vehicle.collisionProtection){
          this.collisionProtection = "Yes";
        }
        else{
          this.collisionProtection = "No";
        }

        this.catalogueService.getMake(vehicle.makeId)
        .subscribe(make => {
          this.make = make;
        },
        error => {
          this._snackBar.open("Error while retreiving vehicle make!", "", {
            duration: 2000,
            verticalPosition: 'top'
          });
        })
  
        this.catalogueService.getModel(vehicle.modelId)
        .subscribe(model => {
          this.model = model;
        },
        error => {
          this._snackBar.open("Error while retreiving vehicle model!", "", {
            duration: 2000,
            verticalPosition: 'top'
          });
        })
  
        
        this.catalogueService.getFuelType(vehicle.fuelTypeId)
        .subscribe(fuelType => {
          this.fuelType = fuelType;
        },
        error => {
          this._snackBar.open("Error while retreiving vehicle fuel type!", "", {
            duration: 2000,
            verticalPosition: 'top'
          });
        })
  
        
        this.catalogueService.getStyle(vehicle.styleId)
        .subscribe(style => {
          this.style = style;
        },
        error => {
          this._snackBar.open("Error while retreiving vehicle style!", "", {
            duration: 2000,
            verticalPosition: 'top'
          });
        })
  
        
        this.catalogueService.getTransmission(vehicle.transmissionId)
        .subscribe(transmission => {
          this.transmission = transmission;
        },
        error => {
          this._snackBar.open("Error while retreiving vehicle transmission!", "", {
            duration: 2000,
            verticalPosition: 'top'
          });
        })
  
        this.locationService.getLocation(vehicle.locationId)
        .subscribe(location => {
          this.location = location;
        },
        error => {
          this._snackBar.open("Error while retreiving vehicle location!", "", {
            duration: 2000,
            verticalPosition: 'top'
          });
        })
  
        this.userService.getUsername(vehicle.userId)
        .subscribe(userDTO => {
          this.username = userDTO.username;
        },
        error => {
          this._snackBar.open("Error while retreiving vehicle owner username!", "", {
            duration: 2000,
            verticalPosition: 'top'
          });
        })
      },
      error => {
        this._snackBar.open("Error while retreiving vehicle!", "", {
          duration: 2000,
          verticalPosition: 'top'
        });
      })
  }

  close(): void {
    this.dialogRef.close();
  }

}
