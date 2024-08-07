package com.testbranch.service.impl;

import com.testbranch.model.Messages;
import com.testbranch.repository.MessageRepository;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.MessagesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("messagesService")
public class MessageServiceImpl implements com.testbranch.service.MessageService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageRepository messageRepository;


    @Override
    public void createmessssges(MessagesDto messagesDto) {
        Messages message = new Messages();
        BeanUtils.copyProperties(messagesDto, message);
        message.setTimestamp(new Date());
        messageRepository.save(message);
    }

    @Override
    public List<MessagesDto> getMessagesBySenderId(long id) throws ResourceNotFoundException {
        Optional<List<Messages>> messagesList = messageRepository.findAllBySenderId(id);
        List<MessagesDto> messagesDtoList = new ArrayList<>();
        if(!messagesList.get().isEmpty()) {
            messagesDtoList = messagesList.get().stream().map(p -> {
                MessagesDto messagesDto = new MessagesDto();
                BeanUtils.copyProperties(p, messagesDto);
                return messagesDto;
            }).collect(Collectors.toList());
        }
        return messagesDtoList;
    }

    @Override
    public List<MessagesDto> getMessagesByReceiverId(long id) throws ResourceNotFoundException{
        Optional<List<Messages>> messagesList = messageRepository.findAllByReceiverId(id);
        List<MessagesDto> messagesDtoList = new ArrayList<>();
        if(!messagesList.get().isEmpty()) {
            messagesDtoList = messagesList.get().stream().map(p -> {
                MessagesDto messagesDto = new MessagesDto();
                BeanUtils.copyProperties(p, messagesDto);
                return messagesDto;
            }).collect(Collectors.toList());
        }
        return messagesDtoList;
    }

    @Override
    public void deleteMessage(Long id) throws ResourceNotFoundException {
        Optional<Messages> message = messageRepository.findById(id);
        if (!message.isPresent()) {
            throw new ResourceNotFoundException("message not found.");
        }
        Messages messagetodelete = message.get();
        messagetodelete.setDeletedFlag(true);
        messageRepository.save(messagetodelete);
    }

    @Override
    public MessagesDto updateMessageSeenby(Long id) throws ResourceNotFoundException {
        Optional<Messages> message = messageRepository.findById(id);
        if (!message.isPresent()) {
            throw new ResourceNotFoundException("message not found.");
        }
        Messages messagetoSeenby = message.get();
        messagetoSeenby.setSeenFlag(true);
        messageRepository.save(messagetoSeenby);
        MessagesDto messageDto = new MessagesDto();
        BeanUtils.copyProperties(messagetoSeenby, messageDto);
        return messageDto;
    }




}
