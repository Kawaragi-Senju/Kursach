package com.kawaragi.mainservice.controllers;

import com.kawaragi.mainservice.dtos.Assignment;
import com.kawaragi.mainservice.dtos.DelegatedAssignment;
import com.kawaragi.mainservice.services.AssignmentService;
import com.kawaragi.mainservice.services.DelegateAssignmentService;
import com.kawaragi.mainservice.services.WorkerService;
import com.kawaragi.mainservice.utils.ContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AssignmentsController {//сервис поручений
    private final AssignmentService service;
    private final DelegateAssignmentService delegateAssignmentService;
    private final WorkerService workerService;

//    @PostMapping("/assignments/new")
    @RequestMapping(value = "/assignments/new", method = RequestMethod.POST)
    public Assignment makeNewAssignment(@RequestParam(name="assigneeEmail") String assigneeEmail) {//create an assignment
        System.out.println(assigneeEmail);
        var worker = workerService.getWorkerByEmail(assigneeEmail);
        var thisWorker = workerService.getWorkerByEmail(ContextUtil.getAuthorizedUserName());
        System.out.println(worker);
        System.out.println(thisWorker);
        var a = new Assignment(thisWorker, worker);
        if (service.canMakeAnAssignment(a)) {
            service.addAssignmentToDatabase(a);
            return a;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/assignments/delete", method = RequestMethod.POST)
    public void deleteAnAssignment(@RequestParam(value = "id") Long aId) {//delete an assignment
        service.deleteAssignmentToDatabase(aId);
    }
    @RequestMapping(value = "/assignments/cancel", method = RequestMethod.POST)
    public void cancelAssignment(@RequestParam(value = "id") Long aId) {//delete an assignment
        service.cancelAssignment(aId);
    }
    @RequestMapping(value = "/assignments/improve", method = RequestMethod.POST)
    public void improveAssignment(@RequestParam(value = "id") Long aId){
        service.improveAssignment(aId);
    }


    @PostMapping("/assignments/modify")
    public String modifyAnAssignment(Assignment a) {
        service.modifyAssignment(a);
        return "null";
    }

    @GetMapping("/assignments/get/{id}")
    public Assignment getAnAssignment(@PathVariable long id) {
        return service.getAssignment(id);
    }

    @GetMapping("/assignments/getGivenToMe")
    public List<Assignment> getAllAssGivenToMe(){
        return service.getAllAssignmentsForThisUser();
    }
    @GetMapping(value = "/assignments/getAss")
    public List<Assignment> getAllAss() {
        return service.getGivenAssignments();
    }

    @GetMapping(value = "/assignments/delegatedToMe")
    public List<DelegatedAssignment> getAllDelegatedToMe() {
        return delegateAssignmentService.getDelegatedAssignmentsForThisWorker();
    }

    @RequestMapping(value = "/assignments/delegate", method = RequestMethod.POST)
    public void delegateAssignment(@RequestParam(value = "newAssigneeEmail") String newAssigneeEmail,
                                   @RequestParam(value = "assignmentId") Long assignmentId) {
        delegateAssignmentService.addDelegatedAssignment(newAssigneeEmail, assignmentId);
    }

    @RequestMapping(value = "/assignments/delegation/delete", method = RequestMethod.POST)
    public void deleteDelegation(@RequestParam(value = "id")Long delegatedAssID) {
        delegateAssignmentService.deleteDelegatedAssignment(delegatedAssID);
    }


}
