package com.serviceoauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @ClassName: com.serviceoauth.config.AuthorizationServerConfig
 * @Description: spring security oauth 授权服务器配置
 * @Author: lgrong
 * @CreateDate: 2020/4/14 18:27
 * @Version: 1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//重写父类方法  ctrl+o


    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired(required = false)
    private AuthorizationCodeServices authorizationCodeServices;

/*    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;*/


    //认证管理器，当你选择了资源所有者密码（password）授权类型的时候，请设置 这个属性注入一个 AuthenticationManager 对象。
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 令牌管理服务
     */
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);//客户端详情服务
        service.setSupportRefreshToken(true);//支持刷新令牌
        service.setTokenStore(tokenStore);//令牌存储策略

        //令牌增强
        /*TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);*/

        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }

    /**
     * ClientDetailsServiceConfigurer 配置客户端详情,初始化客户端用户信息,把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 优化写法  让clientDetailsService继承JdbcClientDetailsService
        // clients.withClientDetails(clientDetailsService);

        //该client允许的授权类型
        // authorization_code 授权码模式
        // password 密码格式
        // refresh_token
        // implicit
        // client_credentials

        //clientId：（必须的）用来标识客户的Id。与资源服务器配置要一样
        // secret：（需要值得信任的客户端）客户端安全码，如果有的话。
        // scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
        // authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
        // authorities：此客户端可以使用的权限（基于Spring Security authorities）。

        //运用内存写法
        // clients.withClientDetails(clientDetailsService);
        clients.inMemory()// 使用in‐memory存储
            .withClient("c1")// client_id
            .secret(new BCryptPasswordEncoder().encode("secret"))
            .resourceIds("res1")
            .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit",
                "refresh_token")// 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
            .scopes("all")// 允许的授权范围
            .autoApprove(false) //加上验证回调地址
            .redirectUris("http://www.baidu.com");
    }

    /**
     * AuthorizationServerSecurityConfigurer 配置令牌的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("permitAll()")
            .allowFormAuthenticationForClients();//表单登陆(申请令牌)
    }

    /**
     * AuthorizationServerEndpointsConfigurer 配置令牌(token)的访问端点和令牌服务
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)//密码模式使用
            .authorizationCodeServices(authorizationCodeServices)//授权码使用
            .tokenServices(tokenService())
            .allowedTokenEndpointRequestMethods(
                HttpMethod.POST, HttpMethod.GET);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        //设置授权码模式的授权码如何 存取，暂时采用内存方式
        return new InMemoryAuthorizationCodeServices();
    }


}
