import { Component, OnInit, ViewChild } from '@angular/core';
import { User } from 'src/app/shared/models/user/User';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {
  displayedColumns: string[] = ['id', 'username', 'userType','action'];
  dataSource: MatTableDataSource<User>;
  constructor(private userService: UserService) { }

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
  

}
