import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { Vehicle } from 'src/app/shared/models/vehicle/Vehicle';
import { VehicleService } from 'src/app/core/services/vehicle.service';
import { CatalogueService } from 'src/app/core/services/catalogue.service';
import { LocationService } from 'src/app/core/services/location.service';
import { UserService } from 'src/app/core/services/user.service';
import { CatalogueItem } from 'src/app/shared/models/catalogue/CatalogueItem';
import { VehicleLocation } from 'src/app/shared/models/location/VehicleLocation';
import { StarRatingColor } from '../vehicle-review/create-review/create-review.component';
import { ReviewService } from 'src/app/core/services/review.service';
import { Review } from 'src/app/shared/models/review/Review';
import { User } from 'src/app/shared/models/user/User';
import { RequestService } from 'src/app/core/services/request.service';

@Component({
  templateUrl: './vehicle-details.component.html',
  styleUrls: ['./vehicle-details.component.css']
})
export class VehicleDetailsComponent implements OnInit {

  vehicleId: number;
  make: CatalogueItem;
  model: CatalogueItem;
  style: CatalogueItem;
  fuelType: CatalogueItem;
  transmission: CatalogueItem;
  location: VehicleLocation;
  username: String;
  vehicle: Vehicle;

  mileageLimit: String;
  collisionProtection: String;

  retrievedImage: any;

  rating:number = 3;
  starCount:number = 5;
  starColor:StarRatingColor = StarRatingColor.accent;
  starColorP:StarRatingColor = StarRatingColor.primary;
  starColorW:StarRatingColor = StarRatingColor.warn;

  reviewText : String;

  userId : number;
  userIdInVehicle : number;

  addReview(){
    this.requestService.canUserPostReview(this.vehicleId, this.userId).subscribe(can => {
        if(can){
          this.reviewService.userPosted(this.vehicleId, this.userId).subscribe(review => {
            if (review.length != 0){
              this._snackBar.open("You already posted!", "", {
                duration: 2000,
                verticalPosition: 'bottom'
              });
            }
            else{
              var reviewNew = new Review()
              reviewNew.text = this.reviewText
              reviewNew.rating = this.rating
              reviewNew.vehicleId = this.vehicleId
              reviewNew.userId = this.userId
              reviewNew.date = new Date()
              this.reviewService.createReview(reviewNew).subscribe(results => {
                this._snackBar.open(results.text.toString(), "", {
                  duration: 2000,
                  verticalPosition: 'bottom'
                });
              },
              error => alert("Server error!"))
            }
        })
        }
        else{
          this._snackBar.open("You have never rented this vehicle!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        }
    },
    error => alert("Server error"))

  }

  onRatingChanged(rating){
    console.log(rating);
    this.rating = rating;
  }

  constructor(public dialogRef: MatDialogRef<VehicleDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private vehicleService: VehicleService,
    private catalogueService: CatalogueService,
    private locationService: LocationService,
    private userService: UserService,
    private _snackBar: MatSnackBar, private reviewService : ReviewService, private requestService : RequestService) {
    this.vehicleId = data.id;
    var user = new User()
    user = JSON.parse(localStorage.getItem('userObject'))
    this.userId = user.id
  }

  ngOnInit() {
    this.vehicleService.getVehicle(this.vehicleId)
      .subscribe(vehicle => {
        this.vehicle = vehicle;

        this.userIdInVehicle = this.vehicle.userId;

        if (this.vehicle.image != null) {
          this.vehicleService.getImage(this.vehicle.image.name).subscribe(results => {
            var base64Data = results.picByte
            this.retrievedImage = 'data:image/jpeg;base64,' + base64Data;
          },
            error => alert("ERROR while getting image"))
        }
        else {
          this.retrievedImage = 'assets/vehicles/nopicture.jpg'
        }

        if (this.vehicle.mileageLimit == 0) {
          this.mileageLimit = "Unlimited";
        }
        else {
          this.mileageLimit = this.vehicle.mileageLimit.toString();
        }

        if (this.vehicle.collisionProtection) {
          this.collisionProtection = "Yes";
        }
        else {
          this.collisionProtection = "No";
        }

        this.catalogueService.getMake(vehicle.makeId)
          .subscribe(make => {
            this.make = make;
          },
            error => {
              this._snackBar.open("Error while retreiving vehicle make!", "", {
                duration: 2000,
                verticalPosition: 'bottom'
              });
            })

        this.catalogueService.getModel(vehicle.modelId)
          .subscribe(model => {
            this.model = model;
          },
            error => {
              this._snackBar.open("Error while retreiving vehicle model!", "", {
                duration: 2000,
                verticalPosition: 'bottom'
              });
            })


        this.catalogueService.getFuelType(vehicle.fuelTypeId)
          .subscribe(fuelType => {
            this.fuelType = fuelType;
          },
            error => {
              this._snackBar.open("Error while retreiving vehicle fuel type!", "", {
                duration: 2000,
                verticalPosition: 'bottom'
              });
            })


        this.catalogueService.getStyle(vehicle.styleId)
          .subscribe(style => {
            this.style = style;
          },
            error => {
              this._snackBar.open("Error while retreiving vehicle style!", "", {
                duration: 2000,
                verticalPosition: 'bottom'
              });
            })


        this.catalogueService.getTransmission(vehicle.transmissionId)
          .subscribe(transmission => {
            this.transmission = transmission;
          },
            error => {
              this._snackBar.open("Error while retreiving vehicle transmission!", "", {
                duration: 2000,
                verticalPosition: 'bottom'
              });
            })

        this.locationService.getLocation(vehicle.locationId)
          .subscribe(location => {
            this.location = location;
          },
            error => {
              this._snackBar.open("Error while retreiving vehicle location!", "", {
                duration: 2000,
                verticalPosition: 'bottom'
              });
            })

        this.userService.getUsername(vehicle.userId)
          .subscribe(userDTO => {
            this.username = userDTO.username;
          },
            error => {
              this._snackBar.open("Error while retreiving vehicle owner username!", "", {
                duration: 2000,
                verticalPosition: 'bottom'
              });
            })
      },
        error => {
          this._snackBar.open("Error while retreiving vehicle!", "", {
            duration: 2000,
            verticalPosition: 'bottom'
          });
        })
  }

  close(): void {
    this.dialogRef.close();
  }

}
