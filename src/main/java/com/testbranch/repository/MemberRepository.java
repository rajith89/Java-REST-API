package com.testbranch.repository;

import com.testbranch.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    @Transactional
    void deleteByGroupId(long groupId);

    @Query(value="SELECT * FROM member WHERE groupid=:groupId", nativeQuery=true)
    Optional<List<Member>> findAllByGroupId(@Param("groupId") Long groupId);

}
