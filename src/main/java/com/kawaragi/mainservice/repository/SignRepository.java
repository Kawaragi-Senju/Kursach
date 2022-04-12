package com.kawaragi.mainservice.repository;

import com.kawaragi.mainservice.dtos.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignRepository extends JpaRepository<Sign, Long> {
    //@Query()
    //boolean isDocumentAppliedToSign();
    Optional<Sign> findSignByAssignment_Aid(Long id);
    void deleteByAssignment_Aid(Long id);
}
