import { Component, OnInit } from '@angular/core';
import { Conversation } from 'src/app/shared/models/message/Conversation';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { MessageService } from 'src/app/core/services/message.service';
import { ConversationContentComponent } from './conversation-content/conversation-content.component';

@Component({
  templateUrl: './user-inbox.component.html',
  styleUrls: ['./user-inbox.component.css']
})
export class UserInboxComponent implements OnInit {

  displayedColumns: string[] = ['username', 'lastMessage', 'timeOfLastMessage', 'open'];
  conversationList : Conversation[];
  dataSource: MatTableDataSource<Conversation>;

  constructor(private messageService : MessageService, public dialog: MatDialog) { }

  ngOnInit() {
    this.messageService.getUserConversations().subscribe(
      conversationList => {
        this.conversationList = conversationList;
        this.dataSource = new MatTableDataSource<Conversation>(this.conversationList);
      }
    )

  }

  openConversation(conversation: Conversation) {
    const dialogRef = this.dialog.open(ConversationContentComponent, {
      width: '1200px',
      height: '600px',
      data: { conversation: conversation
               }
    });
    
    dialogRef.afterClosed().subscribe(result => {
      this.messageService.getUserConversations().subscribe(
        conversationList => {
          this.conversationList = conversationList;
          this.dataSource = new MatTableDataSource<Conversation>(this.conversationList);
        }
      )
    });
  }

}
