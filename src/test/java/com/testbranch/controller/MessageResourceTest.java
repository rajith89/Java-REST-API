package com.testbranch.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.testbranch.service.MessageService;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.MessagesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MessageResourceTest {


    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageResource messageResource;

    private MessagesDto messagesDto;

    @BeforeEach
    public void setUp() {
        messagesDto = new MessagesDto();
        // Set up your MessagesDto object here if needed
    }

    @Test
    public void testAddMessage() {
        ResponseEntity<Void> responseEntity = messageResource.addMessage(messagesDto);
        verify(messageService, times(1)).createmessssges(any(MessagesDto.class));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetMessagesBySender() throws ResourceNotFoundException {
        List<MessagesDto> messages = new ArrayList<>();
        when(messageService.getMessagesBySenderId(anyLong())).thenReturn(messages);

        ResponseEntity<List<MessagesDto>> responseEntity = messageResource.getMessagesBySender(1L);
        verify(messageService, times(1)).getMessagesBySenderId(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(messages, responseEntity.getBody());
    }

    @Test
    public void testGetMessagesByReceiver() throws ResourceNotFoundException {
        List<MessagesDto> messages = new ArrayList<>();
        when(messageService.getMessagesByReceiverId(anyLong())).thenReturn(messages);

        ResponseEntity<List<MessagesDto>> responseEntity = messageResource.getMessagesByReceiver(1L);
        verify(messageService, times(1)).getMessagesByReceiverId(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(messages, responseEntity.getBody());
    }

    @Test
    public void testUpdateMessageSeenby() throws ResourceNotFoundException {
        MessagesDto updatedMessage = new MessagesDto();
        when(messageService.updateMessageSeenby(anyLong())).thenReturn(updatedMessage);

        ResponseEntity<MessagesDto> responseEntity = messageResource.updateMessageSeenby(1L);
        verify(messageService, times(1)).updateMessageSeenby(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedMessage, responseEntity.getBody());
    }

    @Test
    public void testDeleteGroup() throws ResourceNotFoundException {
        ResponseEntity<Void> responseEntity = messageResource.deleteGroup(1L);
        verify(messageService, times(1)).deleteMessage(1L);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}


