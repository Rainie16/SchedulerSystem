package com.mercury.SchedulerSystem.controller;

import com.mercury.SchedulerSystem.bean.Interview;
import com.mercury.SchedulerSystem.http.Response;
import com.mercury.SchedulerSystem.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @GetMapping
    public List<Interview> getAll() {
        return interviewService.getAll();
    }

    @PostMapping
    public Response save(@RequestBody Interview interview, Authentication authentication) {
        return interviewService.save(interview, authentication);
    }

    @PutMapping
    public Response edit(@RequestBody Interview interview) {
        return interviewService.edit(interview);
    }

    @PutMapping("/{id}/{status}")
    public Response updateStatus(@PathVariable int id, @PathVariable String status) {
        System.out.println(status);
        return interviewService.updateStatus(id, status);
    }

}
