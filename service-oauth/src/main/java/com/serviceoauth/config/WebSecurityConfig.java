package com.serviceoauth.config;

import com.serviceoauth.service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceimpl userServiceimpl;

    //    @Bean
//    public PasswordEncoder passwordEncoder() {
//        //String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
    // System.out.println(hashpw);
//        return new BCryptPasswordEncoder();
//    }
    /*public static void main(String[] args) {
        String hashpw = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(hashpw);
    }*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
   /* @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
    // 配置用户及其对应的角色
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceimpl);
    }*/

    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http
            //.csrf().disable()
            .authorizeRequests()
            //.antMatchers("/r/r1").hasAnyAuthority("p1")
            .antMatchers("/login*").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin();*/
        http.rememberMe()
            .and()
            .authorizeRequests()
            //.anyRequest().authenticated()
            .antMatchers("/login*").permitAll()
            .antMatchers("/logout*").permitAll()
            .antMatchers("/**").hasAnyRole("ADMIN", "USER")
            .antMatchers("/auth/**", "/oauth2/**").permitAll()
            .and()
            .formLogin().permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/logout_success")
            .invalidateHttpSession(true)
            .permitAll()
            .and()
            .csrf()
            .disable();
    }
}