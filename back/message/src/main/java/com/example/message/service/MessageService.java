package com.example.message.service;

import com.example.message.dto.*;
import com.example.message.model.Conversation;
import com.example.message.model.Message;
import com.example.message.model.enums.MessageType;
import com.example.message.repository.ConversationRepository;
import com.example.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ConversationRepository conversationRepository;

    public List<Message> findAll() { return messageRepository.findAll(); }

    public List<Message> getMessageByConversationId(Long id) { return messageRepository.findAllByConversationId(id); }

    @Transactional
    public boolean sendMessage(MessageDTO message, List<RequestDTO> requestList) {
        System.err.println("PRINTOVI  UNUTAR  SLANJA  PORUKE");
        System.err.println("datum primljen je: " + message.getDateAndTime().toString());
        System.out.println("BROJ REQUESTOVA: " + requestList.size());
        for (RequestDTO request : requestList) {
            System.out.println("REQUEST: \n" + request.toString());
            if (((request.getOwnerId().equals(message.getSenderId()) && request.getUserId().equals(message.getReceiverId())) || (request.getOwnerId().equals(message.getReceiverId()) && request.getUserId().equals(message.getSenderId()))) && (request.getStatus().equals(Status.RESERVED) || request.getStatus().equals(Status.PAID) || request.getStatus().equals(Status.PENDING))  ) {
                Message mess = new Message(message);
                if (getConversationId(message) != null) {
                    System.out.println("USAO U IF");
                    Long id = getConversationId(message);
                    mess.setConversationId(id);
                    Conversation conversation = conversationRepository.findOneById(id);
                    conversation.setLastMessage(mess.getText());
                    conversation.setTimeOfLastMessage(mess.getDateAndTime());
                    conversationRepository.save(conversation);
                } else {
                    System.out.println("USAO U ELSE");
                    Conversation conv = new Conversation();
                    conv.setId((long)(conversationRepository.findAll().size() + 1));
                    System.out.println("ID CONVERZACIJE JE: " + conv.getId());
                    conv.setTimeOfLastMessage(mess.getDateAndTime());
                    conv.setLastMessage(mess.getText());
                    conv.setUserOneId(mess.getSenderId());
                    conv.setUserTwoId(mess.getReceiverId());
                    conversationRepository.save(conv);
                    mess.setConversationId(conv.getId());
                }
                messageRepository.save(mess);
                return true;
            }
        }
                return false;

    }

    public Long getConversationId(MessageDTO mess) {
        List<Conversation> convList = conversationRepository.findAll();

        for(Conversation conv : convList) {
            if ((conv.getUserOneId().equals(mess.getSenderId()) && conv.getUserTwoId().equals(mess.getReceiverId())) || (conv.getUserOneId().equals(mess.getReceiverId()) && conv.getUserTwoId().equals(mess.getSenderId()))) {
                return conv.getId();
            }
        }
        return null;
    }

    public MessageDTO convertMessToDTO(Long userId, Message mess) {
        MessageDTO messDTO = new MessageDTO(mess);
        if (messDTO.getReceiverId().equals(userId)) {
            messDTO.setMessageType(MessageType.RECEIVED_MESSAGE);
        } else {
            messDTO.setMessageType(MessageType.SENT_MESSAGE);
        }
        return messDTO;
    }

    public List<MessageDTO> getAllMessagesForUser(Long userId, Long conversationId) {
        List<Message> messageList = messageRepository.findAllByConversationId(conversationId);
        List<MessageDTO> listToSend = new ArrayList<>();
        for (Message message : messageList) {
            listToSend.add(convertMessToDTO(userId, message));
        }

        Collections.sort(listToSend, Collections.reverseOrder());
        return listToSend;
    }

    public MessageDTO convertMessage(NewMessageDTO message, List<UserDTO> userList) {
        MessageDTO convertedMessage = new MessageDTO();
        System.err.println("-----PRINTOVANJE  UNUTAR  KONVERTOVANJA  PORUKE");
        System.out.println("PORUKA KOJA STIGNE: \n" + message.toString());
        for (UserDTO user : userList) {
            if (user.getUsername().equals(message.getReceiverUsername())) {
                convertedMessage.setReceiverId(user.getId());
                break;
            }
        }
        convertedMessage.setSenderId(message.getSenderId());
        convertedMessage.setMessageType(message.getMessageType());
        convertedMessage.setDateAndTime(message.getDateAndTime());
        convertedMessage.setText(message.getText());
        System.err.println(convertedMessage.toString());
        return convertedMessage;
    }
}
