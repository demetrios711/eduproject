package org.euproject.eduproject.repositories;

import org.euproject.eduproject.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
    
}
