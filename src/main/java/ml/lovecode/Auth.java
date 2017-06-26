package ml.lovecode;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2017/6/26.
 */


@RestController
public class Auth {

    //需要认证的url
    @RequestMapping("/user")
    public String r1(){
        return "认证成功<a href=\"logout\">退出</a>";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "退出成功";
    }

    //不需要认证的url
    @RequestMapping("/guest")
    public String login(){
        return "不需要认证，游客页面,登陆失败，跳转到此";
    }


    //登陆url
    @RequestMapping(value = "/login/{uName}/{uPwd}",method = RequestMethod.GET)
    public String login1(@PathVariable( value = "uName") String uName
                     ,@PathVariable( value = "uPwd") String uPwd){
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(uName, uPwd);
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            return "认证失败，用户名或者密码错误";
        }
        return "认证成功！<a href=\"logout\">退出</a>";
    }

}
