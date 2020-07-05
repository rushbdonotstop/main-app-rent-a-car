import { Component, OnInit, Inject } from '@angular/core';
import { User } from 'src/app/shared/models/user/User';
import { NewMessageDTO } from 'src/app/shared/models/message/NewMessageDTO';
import { MatDialogRef, MatDialog, MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { MessageType } from 'src/app/shared/models/message/MessageType';
import { MessageService } from 'src/app/core/services/message.service';
import { NotificationFromServer } from 'src/app/shared/models/Notification';

@Component({
  selector: 'pm-new-message-dialog',
  templateUrl: './new-message-dialog.component.html',
  styleUrls: ['./new-message-dialog.component.css']
})
export class NewMessageDialogComponent implements OnInit {

  newMessageText : String;
  user : User;
  receiverUsername : String;

  constructor(public messageDialogRef: MatDialogRef<NewMessageDialogComponent>,
    public messageDialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public data: any, private _snackBar: MatSnackBar, private messageService : MessageService) { 
      this.receiverUsername = data.bundle.username;
    }

  ngOnInit() {
  }

  sendNewMessage() {
    this.user = JSON.parse(localStorage.getItem('userObject'));
    let userId = this.user.id;
    let messageToSend = new NewMessageDTO()
    
    var today = new Date();
    var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
    var hours = today.getHours();
    if (hours == 23) {
      let properHours = '01';
    } else if (hours == 24) {
      var properHours = '02'
    } else {
      var properHourse = today.getHours()+2;
    }
    alert(properHours);
    var time = properHours + ":" + today.getMinutes() + ":" + today.getSeconds();
    var dateTime = date+' '+time;

    messageToSend.dateAndTime = new Date(dateTime);

    messageToSend.senderId = this.user.id;

    messageToSend.text = this.newMessageText;

    messageToSend.receiverUsername = this.receiverUsername;

    messageToSend.messageType = MessageType.SENT_MESSAGE;

    this.messageService.sendNewMessage(messageToSend).subscribe(
      notification => {
        var notification = new NotificationFromServer();
            this.newMessageText = '';
            this._snackBar.open(notification.text, "", {
              duration: 2000,
              verticalPosition: 'bottom'
            });

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
