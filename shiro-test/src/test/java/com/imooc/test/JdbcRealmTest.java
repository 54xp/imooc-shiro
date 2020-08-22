package com.imooc.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author XP
 * @create 2020-08-22 19:10
 */
public class JdbcRealmTest {
    DruidDataSource datasource = new DruidDataSource();
    {
        datasource.setUrl("jdbc:mysql://127.0.0.1:3306/imooc-shiro");
        datasource.setUsername("root");
        datasource.setPassword("a");
    }


    @Test
    public void authenticationTest(){
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(datasource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        // 1.构建 SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("XP","123456");
        subject.login(token);

        System.out.println("subject.isAuthenticated() =   " + subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkRoles("admin","user");
        subject.checkPermission("user:update");
    }
}
