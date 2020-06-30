package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("myRequestCache")
    RequestCache myRequestCache;
    @Resource
    private DataSource dataSource;
    @Autowired
    @Qualifier("myAccessDeniedHandler")
    private AccessDeniedHandler myAccessDeniedHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication() //Queries for authentication Manager builder
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, enabled from users where email=?") //authentication
                .authoritiesByUsernameQuery("select user_id, authority from authorities where user_id=(select emp_id from users where email=?)")//authorization   
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestCache()
                .requestCache(myRequestCache)
                .and()
                .authorizeRequests()// restrict access based on httpServletRequest
                .antMatchers("/", "/index", "/login", "/api/**", "/registration","/favicon.*" ).permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated() //any request to the app must be authenticated
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .csrf()
                .ignoringAntMatchers("/api/**");
    }
   
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
               .antMatchers("/resources/**", "/static/**", "/css/**", "/img/**");
    }
}
