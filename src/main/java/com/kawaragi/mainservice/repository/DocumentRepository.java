package com.kawaragi.mainservice.repository;

import com.kawaragi.mainservice.dtos.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query(value = "select * from document2 where assignmentid = ?1", nativeQuery = true)
    Document findDocumentByAssignmentID(Long signId);//by assignmentid;
}
