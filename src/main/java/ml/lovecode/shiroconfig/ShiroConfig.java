package ml.lovecode.shiroconfig;

import ml.lovecode.realm.MyShiroRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import  org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by admin on 2017/6/26.
 */

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean myShiroFilter(SecurityManager securityManager){

        //Shiro过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //安全管理器 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置拦截器
        Map<String,String> filterChainMap = new LinkedHashMap<String,String>();
        //设置需要认证的url
        filterChainMap.put("/user/**","authc");
        filterChainMap.put("/logout","logout");
        //设置不需要认证的url
        filterChainMap.put("/guest/**","anon");
        //设置登录地址
        shiroFilterFactoryBean.setLoginUrl("/guest");
        //认证成功页面
        shiroFilterFactoryBean.setSuccessUrl("/user");
        //未授权 跳转到login
        shiroFilterFactoryBean.setUnauthorizedUrl("/guest");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return  shiroFilterFactoryBean;
    }



    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //注入自定义realm
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

}
/*
过滤器
* anon
org.apache.shiro.web.filter.authc.AnonymousFilter
authc
org.apache.shiro.web.filter.authc.FormAuthenticationFilter
authcBasic
org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
perms
org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
port
org.apache.shiro.web.filter.authz.PortFilter
rest
org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
roles
org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
ssl
org.apache.shiro.web.filter.authz.SslFilter
user
org.apache.shiro.web.filter.authc.UserFilter
* */