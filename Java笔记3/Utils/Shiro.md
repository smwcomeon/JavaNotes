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
