package com.testbranch.repository;

import com.testbranch.model.Group;
import com.testbranch.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

    Optional<Group> findByName(String name);

    @Query(value="SELECT * FROM group WHERE certificationid=:certificationId", nativeQuery=true)
    Optional<List<Group>> findAllByCertificationId(@Param("certificationId") Long certificationId);

}
