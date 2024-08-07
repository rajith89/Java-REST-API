package com.testbranch.web.rest.dto;

import com.testbranch.constants.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessagesDto {

    private long id;
    private long senderId;
    private long receiverid;
    private Date timestamp;
    private String content;
    private MessageType messageType;
    private String atttachements;
    private Boolean deletedFlag;
    private Boolean seenFlag;

}
