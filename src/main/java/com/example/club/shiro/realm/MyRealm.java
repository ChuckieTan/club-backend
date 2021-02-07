package com.example.club.shiro.realm;

import com.example.club.mapper.UserMapper;
import com.example.club.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        var token = (UsernamePasswordToken) authenticationToken;
        String username = (String) token.getPrincipal();
        User user = null;
        if(username != null) {
            user = userMapper.selectByNumber(username);
        }
        if(username == null || user == null) {
            throw new UnknownAccountException();
        }
//        System.out.println(username + " " + password);
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
