package com.kawaragi.mainservice.services;

import com.kawaragi.mainservice.dtos.Position;
import com.kawaragi.mainservice.dtos.Sign;
import com.kawaragi.mainservice.repository.SignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignService {
    private final SignRepository signRepository;
    private final AssignmentService assignmentService;
    private final DocumentService documentService;

    public void createSignAfterUploadingFile(Long assignmentId){
        var document = documentService.findDocByUniqueAssignmentID(assignmentId);
        var ass = assignmentService.getAssignment(assignmentId);
        var sign = new Sign(document,ass);
        signRepository.save(sign);

    }
    public Sign getSignByAssignment(Long assID){
        return signRepository.findSignByAssignment_Aid(assID).orElseThrow(()-> new UsernameNotFoundException("no sign"));
    }
    public void deleteByAssignment(Long assID){
        signRepository.deleteByAssignment_Aid(assID);
    }
    public void signPerson(Position pos, Long assId){
        switch (pos){
            case Director: setSignForDirector(assId);
            case DepartmentWorker: setSignForWorker(assId);
            case HeadOfDepartment:setSignForHead(assId);
        }
    }

    public void setSignForDirector(Long assId){
        var sign = signRepository.findSignByAssignment_Aid(assId);
        if (sign.isPresent()){
            var presentSign = sign.get();
            presentSign.setDirectorSigned(true);
            signRepository.save(presentSign);
        }
    }
    public void setSignForHead(Long assId){
        var sign = signRepository.findSignByAssignment_Aid(assId);
        if (sign.isPresent()){
            var presentSign = sign.get();
            presentSign.setHeadSigned(true);
            signRepository.save(presentSign);
        }
    }
    public void setSignForWorker(Long assId){
        var sign = signRepository.findSignByAssignment_Aid(assId);
        if (sign.isPresent()){
            var presentSign = sign.get();
            presentSign.setWorkerSigned(true);
            signRepository.save(presentSign);
        }
    }

}
