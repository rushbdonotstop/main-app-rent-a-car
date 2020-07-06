import { MessageType } from './MessageType';

export class NewMessageDTO {
    senderId : number;
    receiverUsername : String;
    dateAndTime : Date;
    text : String;
    messageType : MessageType;
}