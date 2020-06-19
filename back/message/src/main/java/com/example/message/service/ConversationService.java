package com.example.message.service;


import com.example.message.DTO.ConversationDTO;
import com.example.message.DTO.UserDTO;
import com.example.message.model.Conversation;
import com.example.message.repository.ConversationRepository;
import com.example.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ConversationService {

    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    MessageRepository messageRepository;

    public List<Conversation> findAll() { return conversationRepository.findAll(); }

    public List<ConversationDTO> getConversationForUser(Long userId, List<UserDTO> userList) {
        List<Conversation> newList = new ArrayList<>();
        newList = conversationRepository.findAllByUserOneId(userId);
        newList.addAll(conversationRepository.findAllByUserTwoId(userId));

        List<ConversationDTO> finalList = new ArrayList<>();
        if (newList.size() == 0) {
            return finalList;
        }
        for (Conversation conversation : newList) {
            finalList.add(converConvToDTO(conversation, userId, userList));
        }
        System.out.println(finalList);
        Collections.sort(finalList, Collections.reverseOrder());
        System.out.println(finalList);
        return finalList;
    }

    public ConversationDTO converConvToDTO(Conversation conv, Long userId, List<UserDTO> userList) {
        ConversationDTO newConvDTO = new ConversationDTO();
        newConvDTO.setId(conv.getId());
        newConvDTO.setMyUserId(userId);
        if (conv.getUserOneId().equals(userId)) {
            newConvDTO.setOtherUserId(conv.getUserTwoId());
        } else {
            newConvDTO.setOtherUserId(conv.getUserOneId());
        }
        newConvDTO.setLastMessage(conv.getLastMessage());
        newConvDTO.setTimeOfLastMessage(conv.getTimeOfLastMessage());

        for (UserDTO user : userList) {
            if (user.getId().equals(newConvDTO.getOtherUserId())) {
                newConvDTO.setUsername(user.getUsername());
                break;
            }
        }
        if (newConvDTO.getUsername().equals("") || newConvDTO.getUsername() == null) {
            newConvDTO.setUsername("Blocked user");
        }
        return newConvDTO;
    }
}
