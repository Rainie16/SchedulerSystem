package com.mercury.SchedulerSystem.dao;

import com.mercury.SchedulerSystem.bean.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface InterviewDao extends JpaRepository<Interview, Integer> {
}
