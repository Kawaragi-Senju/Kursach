package com.kawaragi.mainservice.services;

import com.kawaragi.mainservice.dtos.*;
import com.kawaragi.mainservice.repository.AssignmentRepository;
import com.kawaragi.mainservice.utils.ContextUtil;
import com.kawaragi.mainservice.dtos.Assignment;
import com.kawaragi.mainservice.dtos.AssignmentStatus;
import com.kawaragi.mainservice.dtos.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AssignmentService {
    private final AssignmentRepository aRepo;

    public void addAssignmentToDatabase(Assignment a) {
        aRepo.save(a);
    }

    public void deleteAssignmentToDatabase(long id) {
        aRepo.deleteById(id);
    }

    public void modifyAssignment(Assignment a) {
        //
    }

    public Assignment getAssignment(long id) {
        return aRepo.getById(id);
    }

    public List<Assignment> getAllAssignmentsForThisUser(){
        return aRepo.getAssignmentsForThisUser(ContextUtil.getAuthorizedUserName());
    }
    public List<Assignment> getGivenAssignments(){
        return aRepo.getGivenAssignments(ContextUtil.getAuthorizedUserName());
    }

    public boolean canMakeAnAssignment(Assignment a) {
        var userFromPosition = a.getWhoCreated().getPosition();
        var userToPosition = a.getWhoAssignee().getPosition();
        //necessary checks
        return userFromPosition.equals(Position.Director)
                || userFromPosition.equals(Position.HeadOfDepartment)
                || !userToPosition.equals(Position.Director)
                || !userFromPosition.equals(Position.DepartmentWorker);
    }
    public void cancelAssignment(Long id){
        var ass = getAssignment(id);
        ass.setStatus(AssignmentStatus.cancelled);
        aRepo.save(ass);
    }
    public void improveAssignment(Long id){
        var ass = getAssignment(id);
        ass.setStatus(AssignmentStatus.toImprove);
        aRepo.save(ass);
    }


}
