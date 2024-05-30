package org.euproject.eduproject.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.euproject.eduproject.models.Document;
import org.euproject.eduproject.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    
    @Autowired
    private DocumentRepository documentRepository;

    public Optional<Document> getById(Long id){
        return documentRepository.findById(id);
    }

    public List<Document> getAll(){
        return documentRepository.findAll();
    }

    public void delete(Document document){
        documentRepository.delete(document);
    }

    public Document save(Document document){
        if(document.getId() == null){
            document.setCreatedAt(LocalDateTime.now());
        }
        return documentRepository.save(document);
    }


}
