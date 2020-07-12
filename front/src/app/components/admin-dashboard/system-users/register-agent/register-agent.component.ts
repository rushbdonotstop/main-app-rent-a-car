import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { User } from 'src/app/shared/models/user/User';
import { UserService } from 'src/app/core/services/user.service';
import { UserDetails } from 'src/app/shared/models/user/UserDetails';
import { AgentRequestTableData } from 'src/app/shared/models/AgentRequestTableData';
import { AgentRequest } from 'src/app/shared/models/AgentRequest';

@Component({
  templateUrl: './register-agent.component.html',
  styleUrls: ['./register-agent.component.css']
})
export class RegisterAgentComponent implements OnInit {
  displayedColumns: string[] = ['username', 'email', 'name', 'approve', 'reject'];
  dataSource: MatTableDataSource<AgentRequestTableData>;
  constructor(private userService: UserService) { }

  agentRequestList: AgentRequestTableData[] = [];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  ngOnInit() {
    this.userService.getAllAgentRequests().subscribe(
      requests => {
        requests.forEach(request => {
          this.userService.getUser(request.userId).subscribe(
            user => {
              this.userService.getUserDetails(user.id).subscribe(details => {
                user.userDetails = details;
                let agentReques = new AgentRequestTableData();
                agentReques.agentRequest = request;
                agentReques.user = user;
                this.agentRequestList.push(agentReques);
                this.dataSource = new MatTableDataSource(this.agentRequestList);
                this.dataSource.paginator = this.paginator;
                this.dataSource.sort = this.sort;
              });
            }
          );
        });
      }
    );
    
  }

  approve(agentRequest: AgentRequestTableData) {
    this.userService.approveAgent(agentRequest.agentRequest).subscribe();
  }

  reject(agentRequest: AgentRequestTableData) {
    this.userService.rejectAgent(agentRequest.agentRequest).subscribe();
  }

}
