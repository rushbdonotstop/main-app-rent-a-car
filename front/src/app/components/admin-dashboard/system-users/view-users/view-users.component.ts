import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { User } from 'src/app/shared/models/user/User';
import { MatTableDataSource, MatPaginator, MatSort, MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { UserService } from 'src/app/core/services/user.service';
import { UserDetails } from 'src/app/shared/models/user/UserDetails';
import { EditPermissionsComponent } from '../edit-permissions/edit-permissions.component';

@Component({
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {
  displayedColumns: string[] = ['id', 'username', 'userType','action'];
  dataSource: MatTableDataSource<User>;
  constructor(private userService: UserService, private dialog: MatDialog) { }

  userList: User[] = [];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  ngOnInit() {
    this.userService.getAllUsers().subscribe(data => {
      data.forEach(element => {
        this.userService.getUserDetails(element.id).subscribe(data1 => {
          element.userDetails = data1;
          this.userList.push(element);
        });
      });
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  openDetails(user: User) {
    const dialogRef = this.dialog.open(UserDetailsDialog, {
      width: '600px',
      height: '400px',
      data: user.userDetails,
    });
  }
  
  openPermissions(user: User) {
    let permissionBoolean = [false, false, false, false];
    let permissionBooleanOld = [false, false, false, false];
    this.userService.getUserPermissions(user.id).subscribe(permissionsString => {
      permissionsString.userPrivileges.forEach(permission => {
        switch(permission) { 
          case "RENT_VEHICLE": { 
             permissionBoolean[0] = true; 
             permissionBooleanOld[0] = true;
             break; 
          } 
          case "ADD_DISCOUNT": { 
             permissionBoolean[1] = true; 
             permissionBooleanOld[1] = true;
             break; 
          } 
          case "GET_STATISTIC": { 
            permissionBoolean[2] = true;  
            permissionBooleanOld[2] = true;
            break; 
          } 
          case "ADD_VEHICLE": { 
            permissionBoolean[3] = true; 
            permissionBooleanOld[3] = true;
            break; 
          } 
          default: { 
              break; 
          } 
       }
      });

      
      const dialogRef = this.dialog.open(EditPermissionsComponent, {
        width: '600px',
        height: '250px',
        data: permissionBoolean
      });

      dialogRef.afterClosed().subscribe(value => {
        if(value) {
          for(let i=0; i<=3; i++) {
            if(permissionBooleanOld[i]!=value[i]) {
              if(permissionBooleanOld[i]==false) {
                switch(i) {
                  case 0: {
                    this.userService.postPermission(user.id, "RENT_VEHICLE").subscribe();
                    break;
                  }
                  case 1: {
                    this.userService.postPermission(user.id, "ADD_DISCOUNT").subscribe();
                    break;
                  }
                  case 2: {
                    this.userService.postPermission(user.id, "GET_STATISTIC").subscribe();
                    break;
                  }
                  case 3: { 
                    this.userService.postPermission(user.id, "ADD_VEHICLE").subscribe();
                    break;
                  }
                }
              } else {
                switch(i) {
                  case 0: {
                    this.userService.deletePermission(user.id, "RENT_VEHICLE").subscribe();
                    break;
                  }
                  case 1: {
                    this.userService.deletePermission(user.id, "ADD_DISCOUNT").subscribe();
                    break;
                  }
                  case 2: {
                    this.userService.deletePermission(user.id, "GET_STATISTIC").subscribe();
                    break;
                  }
                  case 3: { 
                    this.userService.deletePermission(user.id, "ADD_VEHICLE").subscribe();
                    break;
                  }
                }
              }
            }
          }
        }
      })
    });
  }
}

@Component({
  selector:'user-details-dialog',
  templateUrl:'dialog/user-details-dialog.html'
})
export class UserDetailsDialog {
  value: UserDetails;

  constructor(public dialogRef: MatDialogRef<UserDetailsDialog>,
    @Inject(MAT_DIALOG_DATA) public data: UserDetails) {
      this.value = data;
    }
  
    onNoClick(): void {
      this.dialogRef.close();
    }
}
