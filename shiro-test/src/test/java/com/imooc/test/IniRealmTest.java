package com.imooc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author XP
 * @create 2020-08-22 19:10
 */
public class IniRealmTest {

    @Test
    public void authenticationTest(){
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        // 1.构建 SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("XP","123456");
        subject.login(token);

        System.out.println("subject.isAuthenticated() =   " + subject.isAuthenticated());

        subject.checkRole("admin");

        subject.checkPermission("user:update");
    }
}
