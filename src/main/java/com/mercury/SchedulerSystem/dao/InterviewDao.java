package com.mercury.SchedulerSystem.dao;

import com.mercury.SchedulerSystem.bean.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface InterviewDao extends JpaRepository<Interview, Integer> {

    @Query("SELECT distinct scheduler from Interview")
    List<String> allScheduler();

    @Query("SELECT scheduler, COUNT (id) from Interview GROUP BY scheduler")
    List<Object> interviewByScheduler();
}
