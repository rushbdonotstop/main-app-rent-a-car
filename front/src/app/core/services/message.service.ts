import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from 'src/app/shared/models/user/User';
import { Conversation } from 'src/app/shared/models/message/Conversation';
import { Message } from 'src/app/shared/models/message/Message';
import { NotificationFromServer } from 'src/app/shared/models/Notification';
import { NewMessageDTO } from 'src/app/shared/models/message/NewMessageDTO';

const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  user: User;

  constructor(private http: HttpClient) { }

  getUserConversations() {
    this.user = JSON.parse(localStorage.getItem('userObject'));
    let userId = this.user.id;
    return this.http.get<Array<Conversation>>('server/message/conversation/' + userId, httpOptions);
  }

  getMessagesFromConversation(conversationId : number) {
    this.user = JSON.parse(localStorage.getItem('userObject'));
    let userId = this.user.id;
    return this.http.get<Array<Message>>('server/message/message/inbox?userId=' + userId + '&conversationId=' + conversationId, httpOptions);
  }

  sendMessage(message : Message){
    return this.http.post<NotificationFromServer>('server/message/message', JSON.stringify(message), httpOptions);
  }

  sendNewMessage(message : NewMessageDTO){
    return this.http.post<NotificationFromServer>('server/message/message/newMessage', JSON.stringify(message), httpOptions);
  }
}
