package com.mercury.SchedulerSystem.dao;

import com.mercury.SchedulerSystem.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query("SELECT username from User")
    List<String> allUsername();
}
