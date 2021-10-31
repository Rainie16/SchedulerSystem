package com.mercury.SchedulerSystem.service;

import com.mercury.SchedulerSystem.bean.Interview;
import com.mercury.SchedulerSystem.dao.InterviewDao;
import com.mercury.SchedulerSystem.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewService {

    @Autowired
    private InterviewDao interviewDao;

    public List<Interview> getAll() {
        return interviewDao.findAll();
    }

    public Interview getById(int id) {
        return interviewDao.findById(id).get();
    }

    public List<Interview> getByScheduler(String name) {
        return interviewDao.findAll().stream().filter(i -> {
            return i.getScheduler().equals(name);
        }).collect(Collectors.toList());
    }

    public Response save(Interview interview, Authentication authentication) {
        try {

            interview.setScheduler(authentication.getName());
            interview.setStatus("pending");
            interviewDao.save(interview);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, 400, "failed to add new interview");
        }
    }

    public Response edit(Interview interview) {
        try{
            interviewDao.save(interview);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, 400, "unable to update");
        }
    }

    public Response updateStatus(int id, String status) {
        try {
            Interview newInterview = interviewDao.getById(id);
            newInterview.setStatus(status);
            interviewDao.save(newInterview);

            return new Response(true, 200, "update status successfully");

        } catch (Exception e) {
            return new Response(false, 400, "failed to update status");
        }
    }
}
