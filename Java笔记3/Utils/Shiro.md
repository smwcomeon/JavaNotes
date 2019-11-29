> # Shiro整合的相关步骤
>> ## Shiro相关配置
>>>### 1.1 Shiro的配置类
     
```java
package com.qf.config;

import com.qf.realm.UserRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //1,创建 SessionManager 管理会话
    @Bean(name = "sessionManager")//<bean class="">
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置过期时间
        sessionManager.setGlobalSessionTimeout(1000*60*30);
        //设置后台线程  清理过期的会话
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置地址比拼接sessionid
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        return sessionManager;
    }

    //2,创建SecurityManager
    @Bean(name="securityManager")
    public SecurityManager securityManager(SessionManager sessionManager,UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        //缓存管理
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        securityManager.setCacheManager(cacheManager);
        //cookie管理
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        Cookie cookie = cookieRememberMeManager.getCookie();
        cookie.setMaxAge(60*60*24*3);
        cookie.setPath("/");
        securityManager.setRememberMeManager(cookieRememberMeManager);
        //设置自定义realm
        securityManager.setRealm(userRealm);
        return  securityManager;
    }

    //3,创建ShiroFilter
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean  shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("unauthorized.html");
        //拦截的路径的详细设置
        //什么Map是存取有序的？
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/sys/login","anon");//匿名访问
        map.put("/captcha.jpg","anon");//验证码放行
        map.put("/public/**","anon");
        map.put("/json/**","anon");
        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return  shiroFilterFactoryBean;
    }

    //4,BeanLifeCycle  生命周期
    @Bean(name="lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
        return  lifecycleBeanPostProcessor;
    }

    //5,开启shiro的注解
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);//cglib方式
        return  defaultAdvisorAutoProxyCreator;
    }

    @Bean(name="authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}

```

>>>### 1.2 配置类中所用到的缓存配置文件 **classpath:ehcache.xml**
```xml
<!--
~ Licensed to the Apache Software Foundation (ASF) under one
~ or more contributor license agreements.  See the NOTICE file
~ distributed with this work for additional information
~ regarding copyright ownership.  The ASF licenses this file
~ to you under the Apache License, Version 2.0 (the
~ "License"); you may not use this file except in compliance
~ with the License.  You may obtain a copy of the License at
~
~     http://www.apache.org/licenses/LICENSE-2.0
~
~ Unless required by applicable law or agreed to in writing,
~ software distributed under the License is distributed on an
~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
~ KIND, either express or implied.  See the License for the
~ specific language governing permissions and limitations
~ under the License.
-->

<!-- EhCache XML configuration file used for Shiro spring sample application -->
<ehcache>

<!-- Sets the path to the directory where cache .data files are created.

If the path is a Java System Property it is replaced by
its value in the running VM.

The following properties are translated:
user.home - User's home directory
user.dir - User's current working directory
java.io.tmpdir - Default temp file path -->
<diskStore path="java.io.tmpdir/shiro-spring-sample"/>


<!--Default Cache configuration. These will applied to caches programmatically created through
the CacheManager.

The following attributes are required:

maxElementsInMemory            - Sets the maximum number of objects that will be created in memory
eternal                        - Sets whether elements are eternal. If eternal,  timeouts are ignored and the
                                 element is never expired.
overflowToDisk                 - Sets whether elements can overflow to disk when the in-memory cache
                                 has reached the maxInMemory limit.

The following attributes are optional:
timeToIdleSeconds              - Sets the time to idle for an element before it expires.
                                 i.e. The maximum amount of time between accesses before an element expires
                                 Is only used if the element is not eternal.
                                 Optional attribute. A value of 0 means that an Element can idle for infinity.
                                 The default value is 0.
timeToLiveSeconds              - Sets the time to live for an element before it expires.
                                 i.e. The maximum time between creation time and when an element expires.
                                 Is only used if the element is not eternal.
                                 Optional attribute. A value of 0 means that and Element can live for infinity.
                                 The default value is 0.
diskPersistent                 - Whether the disk store persists between restarts of the Virtual Machine.
                                 The default value is false.
diskExpiryThreadIntervalSeconds- The number of seconds between runs of the disk expiry thread. The default value
                                 is 120 seconds.
memoryStoreEvictionPolicy      - Policy would be enforced upon reaching the maxElementsInMemory limit. Default
                                 policy is Least Recently Used (specified as LRU). Other policies available -
                                 First In First Out (specified as FIFO) and Less Frequently Used
                                 (specified as LFU)
-->

<defaultCache
        maxElementsInMemory="10000"
        eternal="false"
        timeToIdleSeconds="120"
        timeToLiveSeconds="120"
        overflowToDisk="false"
        diskPersistent="false"
        diskExpiryThreadIntervalSeconds="120"
        />

<!-- We want eternal="true" (with no timeToIdle or timeToLive settings) because Shiro manages session
expirations explicitly.  If we set it to false and then set corresponding timeToIdle and timeToLive properties,
ehcache would evict sessions without Shiro's knowledge, which would cause many problems
(e.g. "My Shiro session timeout is 30 minutes - why isn't a session available after 2 minutes?"
Answer - ehcache expired it due to the timeToIdle property set to 120 seconds.)

diskPersistent=true since we want an enterprise session management feature - ability to use sessions after
even after a JVM restart.  -->
<cache name="shiro-activeSessionCache"
       maxElementsInMemory="10000"
       eternal="true"
       overflowToDisk="true"
       diskPersistent="true"
       diskExpiryThreadIntervalSeconds="600"/>

<cache name="org.apache.shiro.realm.SimpleAccountRealm.authorization"
       maxElementsInMemory="100"
       eternal="false"
       timeToLiveSeconds="600"
       overflowToDisk="false"/>

</ehcache>

```


>>> ### 1.3 shiro框架中获取attribute封装的工具类
```java
package com.qf.utils;

import com.qf.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.Collection;

public class ShiroUtils {


    public static  Session getSession(){
//        Collection<Object> attributeKeys = SecurityUtils.getSubject().getSession().getAttributeKeys();
//        String  admin = SecurityUtils.getSubject().getSession().getAttribute("admin")+"";
        return SecurityUtils.getSubject().getSession();

    }

    public  static void setAttribute(String key,String v){
        getSession().setAttribute(key,v);
    }

    public  static Object getAttribute(String key){
        return getSession().getAttribute(key);
    }

    public  static void setKaptcha(String code){
        setAttribute(SysConstant.CAPTCHA_KEY,code);
    }
    public  static String getKaptcha(){
       return getAttribute(SysConstant.CAPTCHA_KEY)+"";
    }

    public static SysUser getUserEntity(){
       return  (SysUser) SecurityUtils.getSubject().getPrincipal();
    }



    public static long getUserId(){
        return  getUserEntity().getUserId();
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }


}

```
>> ## 2.1 Realm的认证与授权
```java
package com.qf.realm;

import com.qf.pojo.SysUser;
import com.qf.service.MenuService;
import com.qf.service.RoleService;
import com.qf.service.SysUserService;
import com.qf.utils.MD5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //得到用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        SysUser byUsername = sysUserService.findByUsername(username);
        if(byUsername==null){
            throw new UnknownAccountException("账户不存在");
        }
        if(!byUsername.getPassword().equals(password)){
            throw new IncorrectCredentialsException("密码不正确");
        }
        if(byUsername.getStatus()==0){
            throw new LockedAccountException("账户被冻结");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(byUsername,password,this.getName());
        return info;
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser sysUser = (SysUser)principals.getPrimaryPrincipal();
        Long userId = sysUser.getUserId();
        //用户的角色
        List<String> rolsByUserID = roleService.findRolsByUserID(userId);
        //用户的菜单权限
        List<String> permsByUserId = menuService.findPermsByUserId(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(rolsByUserID);
        info.addStringPermissions(permsByUserId);
        return info;
    }

}

```
>> ## 3.1 controller层控制
```java
 @RequestMapping("/sys/login")
    @ResponseBody
    public R login(@RequestBody UserDTO userDTO){
        //比对验证码
        String serverKaptcha = ShiroUtils.getKaptcha();
        if(!serverKaptcha.equalsIgnoreCase(userDTO.getCaptcha())){
            return R.error("验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        String newPass = MD5Utils.md5(userDTO.getPassword(),userDTO.getUsername(),1024);
        UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(),newPass);
        if(userDTO.isRememberMe()){
            token.setRememberMe(true);
        }
        subject.login(token);
        //会去调用自定义的realm
        return R.ok();
    }

```
## Md5Hash密码加密
```java
package com.qf.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Utils {


    public  static String md5(String source,String salt,int hashIterations){
        if (source==null){
            return null;
        }

        Md5Hash md5Hash = new Md5Hash(source,salt,hashIterations);

        return  md5Hash.toString();
    }


    public static void main(String[] args) {
        System.out.println(md5("admin","admin",1024));

//        System.out.println(md5("zhangsan","zhangsan",1024));

    }

}

```
