## Shiro整合的相关步骤
### 1.1Shiro的配置类
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

### 1.2缓存配置文件classpath:ehcache.xml
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


### shiro框架中获取attribute封装的工具类
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
