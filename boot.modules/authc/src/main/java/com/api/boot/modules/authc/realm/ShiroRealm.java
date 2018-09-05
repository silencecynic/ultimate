package com.api.boot.modules.authc.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class ShiroRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }


    @Override
    public void setName(String shiroRealm) {
        super.setName(shiroRealm);
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = token.getCredentials().toString();
        String password = new String((char[])token.getCredentials());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();

        return authenticationInfo;
    }



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String name = principals.getPrimaryPrincipal().toString();


        return null;
    }
}
