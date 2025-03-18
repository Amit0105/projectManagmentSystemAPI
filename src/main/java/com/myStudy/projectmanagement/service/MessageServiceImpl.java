package com.myStudy.projectmanagement.service;

import com.myStudy.projectmanagement.modal.Chat;
import com.myStudy.projectmanagement.modal.Message;
import com.myStudy.projectmanagement.modal.User;
import com.myStudy.projectmanagement.repository.MessageRepository;
import com.myStudy.projectmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new Exception("User Not Found with id "+senderId));

        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setChat(chat);
        message.setCreatedAt(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {

        Chat chat = projectService.getChatByProjectId(projectId);

        return messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
    }
}
