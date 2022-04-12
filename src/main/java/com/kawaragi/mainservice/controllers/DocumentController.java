package com.kawaragi.mainservice.controllers;

import com.kawaragi.mainservice.dtos.Document;
import com.kawaragi.mainservice.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;
    @RequestMapping(value = "/docs/download", method = RequestMethod.GET)//из
    public Document downloadDocument(@RequestParam(name = "assID") long assId){
        return documentService.findDocByUniqueAssignmentID(assId);
    }

    @RequestMapping(value = "/docs/upload", method = RequestMethod.POST)
    public void uploadDocument(@RequestParam(value = "id") Long assID,
                               @RequestParam(name = "formElem") MultipartFile formElem){//в бд
        System.out.println(formElem.getName() + " " + assID);
        documentService.addDocumentToDB(formElem, assID);
    }
    @GetMapping("/delete/{id}")
    public String deleteDocument(@PathVariable long id){
        return "ok";
    }



}

