package com.kawaragi.mainservice.controllers;

import com.kawaragi.mainservice.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DownloadDocContr {
    private final DocumentService documentService;
    @GetMapping(value = "/docByAssId/{id}")
    public ResponseEntity<byte[]> getDocByAssId(@PathVariable(name = "id") Long assId) {
        var doc = documentService.findDocByUniqueAssignmentID(assId);
//        ByteArrayResource resource = new ByteArrayResource(doc.getData());
//        var len = doc.getData().length;
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=myDoc.png");
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(len)
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getFilename() + "\"")
                .body(doc.getData());

    }
}
