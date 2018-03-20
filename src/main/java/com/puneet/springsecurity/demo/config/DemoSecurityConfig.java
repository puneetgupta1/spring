package com.puneet.springsecurity.demo.config;

import com.puneet.springsecurity.demo.security.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    /* use these credentials:
        john - crazy123
        mary - fun123
        susan - fun123
     */

    //Add reference to our security data source
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //add our users for this in memory authentication --> NOTE: Use the below for quick tests of your code/Use Jdbc auth below that for more practical tests

        /*User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication().withUser(users.username("john").password("test123").roles("EMPLOYEE"));
        auth.inMemoryAuthentication().withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"));
        auth.inMemoryAuthentication().withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN")); */

        //JDBC Authentication
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    //for custom login page
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //Now since we want to restrict based on USER ROLES, so comment out the below line
                //.anyRequest().authenticated()
                //Configure Roles now
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/leaders/**").hasRole("MANAGER")
                .antMatchers("/systems/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateUser")
                .permitAll()
                .and()
                .logout()
                /*.deleteCookies("JESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())*/
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
}
