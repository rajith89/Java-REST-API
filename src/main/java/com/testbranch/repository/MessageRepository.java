package com.testbranch.repository;

import com.testbranch.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository  extends JpaRepository<Messages,Long> {

    @Query(value="SELECT * FROM messages WHERE senderid=:senderId", nativeQuery=true)
    Optional<List<Messages>> findAllBySenderId(@Param("senderId") Long senderId);

    @Query(value="SELECT * FROM messages WHERE receiverid=:receiverId", nativeQuery=true)
    Optional<List<Messages>> findAllByReceiverId(@Param("receiverId") Long receiverId);



}
