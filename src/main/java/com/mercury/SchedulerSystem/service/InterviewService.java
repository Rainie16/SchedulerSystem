package com.mercury.SchedulerSystem.service;

import com.mercury.SchedulerSystem.bean.Interview;
import com.mercury.SchedulerSystem.dao.InterviewDao;
import com.mercury.SchedulerSystem.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewService {

    @Autowired
    private InterviewDao interviewDao;

    public List<Interview> getAll() {
        return interviewDao.findAll();
    }

    public List<String> getScheduler() { return interviewDao.allScheduler(); }

    public List<Object> getInterviewAmount() { return interviewDao.interviewByScheduler(); }

    public HashMap<String, Double> getPassRate() {
        HashMap<String, Integer> map = new HashMap<>();
        List<Object> bar = new ArrayList<>();
        interviewDao.findAll().forEach(i -> {
            String scheduler = i.getScheduler();
            if (map.get(scheduler) == null &&i.getStatus().equals("pass")) {
                map.put(scheduler, 1);
            } else {
                //System.out.println(i.getStatus());
                if (i.getStatus().equals("pass")) {
                    map.put(scheduler, map.get(scheduler) + 1);
                }
            }
        });

        HashMap<String, Integer> newMap = new HashMap<>();
        interviewDao.findAll().forEach(i -> {
            String scheduler = i.getScheduler();
            if (newMap.get(scheduler) == null) {
                newMap.put(scheduler, 1);
            } else {
                newMap.put(scheduler, newMap.get(scheduler) + 1);
            }
        });
        //System.out.println(map);
        HashMap<String,Double> map1 = new HashMap<>();

        for (String key : map.keySet()) {
            for (String newKey : map.keySet()) {
                if(key == newKey){
                    //System.out.println(map.get(key));
                    map1.put(key,(double) map.get(key)/ (double)newMap.get(newKey));
                }
            }
        }
        return map1;
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

    public Response saveFromExcel(Interview interview) {
        try {
            interviewDao.save(interview);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, 400, "failed to import new interviews from excel");
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

    public Response uploadResume(int id, String resumeName) {
        try {
            Interview newInterview = interviewDao.getById(id);
            newInterview.setResume(resumeName);
            interviewDao.save(newInterview);

            return new Response(true, 200, "upload resume successfully");
        } catch (Exception e) {
            return new Response(false, 400, "upload resume failed");
        }
    }
}
