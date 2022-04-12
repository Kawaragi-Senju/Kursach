package com.kawaragi.mainservice.services;

import com.kawaragi.mainservice.dtos.Assignment;
import com.kawaragi.mainservice.dtos.DelegatedAssignment;
import com.kawaragi.mainservice.dtos.Position;
import com.kawaragi.mainservice.dtos.Worker;
import com.kawaragi.mainservice.repository.DelegateAssignmentRepository;
import com.kawaragi.mainservice.utils.ContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DelegateAssignmentService {
    private final DelegateAssignmentRepository repo;
    private final AssignmentService assignmentService;
    private final WorkerService workerService;

    public boolean checkIfAssignmentIsDelegated(Assignment a) {
        return repo.isAssignmentDelegated(a.getAid());
    }

    public void addDelegatedAssignment(String emailTo, Long assignmentId) {
        if (!repo.isAssignmentDelegated(assignmentId)) {
            var worker = workerService.getWorkerByEmail(emailTo);
            var thisWorker = workerService.getWorkerByEmail(ContextUtil.getAuthorizedUserName());
            if (canPersonDelegate(worker, thisWorker)) {
                var assignment = assignmentService.getAssignment(assignmentId);
                assignment.setDelegated(true);
                assignmentService.addAssignmentToDatabase(assignment);
                var delegated = new DelegatedAssignment(assignment, worker);
                repo.save(delegated);
            }
        }
        System.out.println("нет");
    }

    public void deleteDelegatedAssignment(Long id) {
        var da = repo.findById(id).orElseThrow(()-> new UsernameNotFoundException("No ass"));
        var notDelegated = da.getAssignment();
        notDelegated.setDelegated(false);
        assignmentService.addAssignmentToDatabase(notDelegated);
        repo.delete(da);
    }

    public Worker getNewAssignee(DelegatedAssignment d) {
        return repo.findWhoIsANewAssignee(d.getId()).orElseThrow(() -> new UsernameNotFoundException("New assignee not found"));
    }

    public void saveDelegatedAssignment(DelegatedAssignment d) {
        repo.save(d);
    }

    public List<DelegatedAssignment> getDelegatedAssignmentsForThisWorker() {
        return repo.getAllDelegatedAssignmentsForThisUser(ContextUtil.getAuthorizedUserName());
    }

    public boolean canPersonDelegate(Worker from, Worker to) {
        return to.getPosition() != Position.Director
                || from.getPosition().equals(to.getPosition())
                || from.getPosition().equals(Position.DepartmentWorker)
                || from.equals(to);
    }

}
