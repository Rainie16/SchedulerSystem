package com.mercury.SchedulerSystem.dao;

import com.mercury.SchedulerSystem.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
