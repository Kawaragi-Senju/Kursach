package com.kawaragi.mainservice.controllers;

import com.kawaragi.mainservice.dtos.Worker;
import com.kawaragi.mainservice.dtos.Position;
import com.kawaragi.mainservice.services.WorkerService;
import com.kawaragi.mainservice.utils.ContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final WorkerService workerService;
    @GetMapping("/getEmployees")
    public List<Worker> getListOfEmployees(){
        return null;
    }

    @GetMapping("/getEmplByPosition")
    public List<Worker> getEmployeesOfThisPosition(@RequestParam("pos") Optional<Position> position){
        return null;
    }
    @PostMapping("/addEmployee")
    public ResponseEntity.BodyBuilder addEmployee(Worker e){
        return ResponseEntity.accepted();
    }
    @GetMapping("/getMyName")
    public Worker returnMyName(){
        return workerService.getWorkerByEmail(ContextUtil.getAuthorizedUserName());
        //return ContextUtil.getAuthorizedUserName();
    }
}
