package com.kawaragi.mainservice.controllers;

import com.kawaragi.mainservice.dtos.Position;
import com.kawaragi.mainservice.dtos.Sign;
import com.kawaragi.mainservice.services.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignController {
    private final SignService signService;
    @RequestMapping(value = "/newSign", method = RequestMethod.POST)
    public void newSign(@RequestParam(name = "assId") Long assId){
        signService.createSignAfterUploadingFile(assId);
    }
    @RequestMapping(value = "/getSignByAssId", method = RequestMethod.GET)
    public Sign getSignByAssignmentID(@RequestParam(name = "assId") Long assID){
        return signService.getSignByAssignment(assID);
    }
    @RequestMapping(value = "/delSignByAssId", method = RequestMethod.POST)
    public void deleteByAssignment(@RequestParam(name = "assId") Long assId){
        signService.deleteByAssignment(assId);
    }
    @RequestMapping(value = "/signWorker", method = RequestMethod.POST)
    public void signWorker(@RequestParam(name = "position") String pos, @RequestParam (name = "assId") Long assId){
        var posi = Position.valueOf(pos);
        signService.signPerson(posi,assId);
    }

}
