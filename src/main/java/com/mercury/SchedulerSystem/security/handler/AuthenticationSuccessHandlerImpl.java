package com.mercury.SchedulerSystem.security.handler;

import com.mercury.SchedulerSystem.bean.User;
import com.mercury.SchedulerSystem.dao.UserDao;
import com.mercury.SchedulerSystem.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserDao userDao;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, "Login successfully.", null);

        User user= userDao.findByUsername(authentication.getName());
    }
}
