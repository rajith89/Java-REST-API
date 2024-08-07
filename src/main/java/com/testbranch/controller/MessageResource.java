package com.testbranch.controller;

import com.testbranch.service.MessageService;
import com.testbranch.web.error.ResourceNotFoundException;
import com.testbranch.web.rest.dto.MessagesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageResource {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageService messageService;

    @PostMapping("/")
    public ResponseEntity<Void> addMessage(@Valid @RequestBody MessagesDto messagesDto)  {
        messageService.createmessssges(messagesDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/sender/{id}")
    public ResponseEntity<List<MessagesDto>> getMessagesBySender(long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(messageService.getMessagesBySenderId(id));
    }

    @GetMapping("/receiver/{id}")
    public ResponseEntity<List<MessagesDto>> getMessagesByReceiver(long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(messageService.getMessagesByReceiverId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessagesDto> updateMessageSeenby(@PathVariable long id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(messageService.updateMessageSeenby(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable long id)
            throws ResourceNotFoundException {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
