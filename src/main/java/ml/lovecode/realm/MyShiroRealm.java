package ml.lovecode.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2017/6/26.
 */
public class MyShiroRealm extends AuthorizingRealm {


    // 认证信息.(身份验证)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String uName = token.getUsername();//用户名
        //有了用户名，我们应该根据用户名查询数据库，获取用户名 密码 等等，此处不再查询数据库，模拟一个账号

        /**  如果根据用户名查询不到用户，就是登陆失败了
         *  if (user == null) {
             throw new AuthorizationException();
             }
             getName()位父类中的方法   public java.lang.String getName() { compiled code *
         */
        System.out.print("认证身份");
        return new SimpleAuthenticationInfo("test", "123456", getName());
    }

    //权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection principalCollection){
        // 根据用户配置用户与权限
        if (principalCollection == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        String uName = (String) getAvailablePrincipal(principalCollection);  //用户名，登录账号
        //实际项目，登录时会调用这里，应该根据uName查询数据库，获取用户信息，然后增加角色跟权限

        //角色名的集合
        Set<String> roles = new HashSet<String>();
        //权限名的集合
        Set<String> permissions = new HashSet<String>();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissions);
        System.out.print("权限认证");
        return simpleAuthorizationInfo;
    }


}
