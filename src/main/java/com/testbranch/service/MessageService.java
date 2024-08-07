package com.testbranch.service;

import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.MessagesDto;

import java.util.List;

public interface MessageService {
    void createmessssges(MessagesDto messagesDto);

    List<MessagesDto> getMessagesBySenderId(long id) throws ResourceNotFoundException;

    List<MessagesDto> getMessagesByReceiverId(long id) throws ResourceNotFoundException;

    void deleteMessage(Long id) throws ResourceNotFoundException;

    MessagesDto updateMessageSeenby(Long id) throws ResourceNotFoundException;
}
