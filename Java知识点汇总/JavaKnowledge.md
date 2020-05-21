**注意：** ## 部分是知识点为网上学习所得，仅供个人学习使用

## 静态变量初始化时不能注入配置文件中

## config配置文件
./config/test.properties

```xml
kafka.addr=172.......(网址信息)
```

项目文件下的配置文件

```properties
kafka.dive=@kafka.addr@
```
##java 类引用方法##

```java
@Component
public class KafkaProduceUtil{
    private static String kafkaAddr;
    @Value("${kafka.div}")
    public void setKafakaAddr(String kafkaAddr){
      KafkaProduceUtil.kafkaAddr=kafkaAddr;
    }
}

```

参考网址


(@Value注解为什么不能直接为静态变量赋值)[https://blog.csdn.net/qq_27127145/article/details/88863702]

