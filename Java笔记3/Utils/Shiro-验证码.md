### 1.验证码生成的相关配置
```java
package com.qf.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {


    @Bean(name="kaptcha")
    public DefaultKaptcha kaptcha(){

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        //验证码的设置
        properties.setProperty("kaptcha.border","no");
        properties.setProperty("kaptcha.textproducer.char.length","1");//验证码个数
        properties.setProperty("kaptcha.textproducer.font.color","black");//字体颜色

        Config config = new Config(properties);
        kaptcha.setConfig(config);

        return  kaptcha;

    }

}

```
### 2.Controller代码逻辑
```java
 @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response){
        // 缓存设置-设置不缓存（可选操作）
        response.setHeader("Cache-Control","no-store, no-cache");
        // 设置响应内容
        response.setContentType("image/jpg");
        //生成验证码
        String text = kaptcha.createText();//文本
        //生成图片
        BufferedImage image = kaptcha.createImage(text);
        //验证码存储到shiro的 session
        ShiroUtils.setKaptcha(text);
        try {
            //返回到页面
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image,"jpg",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
