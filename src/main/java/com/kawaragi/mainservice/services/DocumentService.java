package com.kawaragi.mainservice.services;

import com.kawaragi.mainservice.dtos.Document;
import com.kawaragi.mainservice.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {
    private final DocumentRepository documentRepository;

    public Document getDocumentFromDB(long id) {
        return documentRepository.getById(id);
    }

    @SneakyThrows
    public void addDocumentToDB(MultipartFile document, Long assId) {
        var len = document.getSize();
        System.out.println(len);
        String fileName = StringUtils.cleanPath(document.getOriginalFilename());
        var type = document.getContentType();
        var doc = new Document(document.getBytes(), assId, fileName, type);
        documentRepository.save(doc);
        System.out.println("да");

    }

    public Document findDocByUniqueAssignmentID(Long assid) {
        return documentRepository.findDocumentByAssignmentID(assid);
    }
}
