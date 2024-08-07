package com.testbranch.model;

import com.testbranch.constants.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "senderid")
    private Long senderId;

    @Column(name = "receiverid")
    private Long receiverid;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name="messagetype", length = 10)
    private MessageType messageType;

    @Column(name = "atttachements")
    private String atttachements;

    @Column(name = "deletedflag")
    private Boolean deletedFlag;

    @Column(name = "seenflag")
    private Boolean seenFlag;

}
