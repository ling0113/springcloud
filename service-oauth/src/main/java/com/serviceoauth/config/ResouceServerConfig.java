package com.serviceoauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_ID = "res1";

    @Override
    public void configure(
        ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(true);

//        resources.resourceId(RESOURCE_ID) .tokenServices(tokenService()) .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
/*http.authorizeRequests()
                .antMatchers("/**")
                .access("#oauth2.hasScope('all')")
                .and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS);*/

        http.authorizeRequests()
            //.antMatchers("/admin/**").hasRole("admin")
            //.antMatchers("/user/**").hasRole("user")
            .anyRequest().authenticated()
        ;
    }

    //资源服务令牌解析服务
  /*@Bean
    public ResourceServerTokenServices tokenService() {
        //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret
        RemoteTokenServices service=new RemoteTokenServices();
        service.setCheckTokenEndpointUrl("http://localhost:8781/oauth/check_token");
        service.setClientId("c1"); service.setClientSecret("secret"); return service;
    }*/


}