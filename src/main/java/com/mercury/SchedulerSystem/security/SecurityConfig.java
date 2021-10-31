package com.mercury.SchedulerSystem.security;

import com.mercury.SchedulerSystem.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServicelmpl userDetailsServicelmpl;
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPointImpl;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;
    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;
    @Autowired
    private com.mercury.SchedulerSystem.security.LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.authorizeRequests((requests) -> {
            ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)requests
                    //.antMatchers(HttpMethod.GET).permitAll()
                    //指定http request, 指定特定url
                    //let everyone have permission to get all products info
                    //"/products/*"
//                    .antMatchers(HttpMethod.GET,"/products").permitAll()
//                    .antMatchers(HttpMethod.GET, "/products/*").hasRole("ADMIN")
                    .anyRequest())
                   // .authenticated();
            .permitAll();
        });
//        http.formLogin()
//                //set user name and password
//            .usernameParameter("username")
//            .passwordParameter("password");

        http.exceptionHandling()
                //权限
                .accessDeniedHandler(accessDeniedHandlerImpl)

                .authenticationEntryPoint(authenticationEntryPointImpl);

        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandlerImpl)
                .failureHandler(authenticationFailureHandlerImpl)
        ;

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandlerImpl)
        ;
        http.httpBasic();
    }

    @Bean // put the return object into spring container, as a bean
    //unable to put on class above, only above the function
    //next spring container run it  will run this method
    //key is method name value is return value put them together
    public PasswordEncoder passwordEncoder() {
        //11 is our db choice
        return new BCryptPasswordEncoder(11);
    }
    @Autowired // @Autowired on function will autowired the parameters auth
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //tell how it encode the password
        auth.userDetailsService(userDetailsServicelmpl).passwordEncoder(passwordEncoder());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*"); // You should only set trusted site here. e.g. http://localhost:4200 means only this site can access.
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD","OPTIONS"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
