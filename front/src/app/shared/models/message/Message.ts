import { MessageType } from './MessageType';

export class Message {
    id : number;
    senderId : number;
    receiverId : number;
    dateAndTime : Date;
    text : String;
    conversationId : number;
    messageType : MessageType;
}