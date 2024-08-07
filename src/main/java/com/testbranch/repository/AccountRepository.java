package com.testbranch.repository;

import com.testbranch.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query(value="SELECT * FROM account WHERE userid=:user_id", nativeQuery=true)
    Optional<Account> findByUserid(@Param("user_id")Long userId);


}
