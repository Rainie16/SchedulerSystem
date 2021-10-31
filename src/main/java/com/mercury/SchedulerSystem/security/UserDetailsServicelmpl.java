package com.mercury.SchedulerSystem.security;

import com.mercury.SchedulerSystem.bean.User;
import com.mercury.SchedulerSystem.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServicelmpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u =  userDao.findByUsername(username);
        if(u==null){
            throw new UsernameNotFoundException(username + " not exist");
        }
        return u;
    }
}
