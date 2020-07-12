package com.example.message.repository;

import com.example.message.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    List<Conversation> findAll();

    Conversation findOneById(Long id);

    List<Conversation> findAllByUserOneId(Long userOneId);

    List<Conversation> findAllByUserTwoId(Long userTwoId);

    Conversation save(Conversation conversation);
}
