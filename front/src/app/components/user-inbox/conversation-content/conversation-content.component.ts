import { Component, OnInit, Inject } from '@angular/core';
import { Message } from 'src/app/shared/models/message/Message';
import { MatTableDataSource, MatDialogRef, MatDialog, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { MessageService } from 'src/app/core/services/message.service';
import { MessageType } from 'src/app/shared/models/message/MessageType';
import { User } from 'src/app/shared/models/user/User';
import { NotificationFromServer } from 'src/app/shared/models/Notification';

@Component({
  selector: 'pm-conversation-content',
  templateUrl: './conversation-content.component.html',
  styleUrls: ['./conversation-content.component.css']
})
export class ConversationContentComponent implements OnInit {

  conversationId : number;
  conversationWithUser : String;
  messages : Message[];
  dataSource: MatTableDataSource<Message>;
  displayedColumns: string[] = ['text', 'dateAndTime', 'type'];
  newMessageText : String;
  user : User;
  otherUserId : number;

  constructor(public dialogRef: MatDialogRef<ConversationContentComponent>,
    public dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any, private _snackBar: MatSnackBar, private messageService : MessageService) { 
      this. conversationWithUser = data.conversation.username;
      this.conversationId = data.conversation.id;
      this.otherUserId = data.conversation.otherUserId;
    }

  ngOnInit() {
    this.messageService.getMessagesFromConversation(this.conversationId).subscribe(
        messages => {
          this.messages = messages;
          this.dataSource = new MatTableDataSource<Message>(this.messages);
        }
    )

  }

  returnType(message : Message) {
    if (message.messageType.toString() == 'SENT_MESSAGE') {
      return 'Sent message'
    } else {
      return 'Received message'
    }
  }

  returnBgColor(message : Message) {
    if (message.messageType.toString() == 'SENT_MESSAGE') {
      return 'blue';
    } else {
      return 'red';
    }
  }


  close(): void {
    this.dialogRef.close();
  }

  sendMessage() {
    this.user = JSON.parse(localStorage.getItem('userObject'));
    let userId = this.user.id;
    let messageToSend = new Message()
    messageToSend.conversationId = this.conversationId;
    messageToSend.senderId = userId;
    messageToSend.receiverId = this.otherUserId;
    messageToSend.text = this.newMessageText;
    messageToSend.messageType = MessageType.SENT_MESSAGE;

    var today = new Date();
    var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    var dateTime = date+' '+time;

    messageToSend.dateAndTime = new Date(dateTime);

    this.messageService.sendMessage(messageToSend).subscribe(
      notification => {
        var notification = new NotificationFromServer();

        this.messageService.getMessagesFromConversation(this.conversationId).subscribe(
          messages => {
            this.messages = messages;
            this.dataSource = new MatTableDataSource<Message>(this.messages);
            this.newMessageText = '';
          }
      )

      },
      error => {
        this._snackBar.open("Users don't have opened requests!", "", {
          duration: 2000,
          verticalPosition: 'bottom'
        });
      }
    )
  }
}
