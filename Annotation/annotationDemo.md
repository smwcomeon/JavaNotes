## 自定义注解的使用规范

a.@Target  自定义注解可以标注的位置

```txt
取值(ElementType)有：
　　　　1.CONSTRUCTOR:用于描述构造器
　　　　2.FIELD:用于描述域
　　　　3.LOCAL_VARIABLE:用于描述局部变量
　　　　4.METHOD:用于描述方法
　　　　5.PACKAGE:用于描述包
　　　　6.PARAMETER:用于描述参数
　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
```
b.@Retention 生命状态

```txt
取值（RetentionPoicy）有：

　　　　1.SOURCE:在源文件中有效（即源文件保留）
　　　　2.CLASS:在class文件中有效（即class保留）
　　　　3.RUNTIME:在运行时有效（即运行时保留）
```
c.@Documented 是否生成文本

```txt
Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
```
d.@Inherited


## 1.1自定义注解示范
```java
package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
}

```
## 1.2自定义注解的使用与检验

```java
  package com.annotation;

/**
 *
 */
public class CalculateTest {

    //加法
    @Check
    public static void add(){
        System.out.println("1+1 =" +(1+1));
    }
    //除法
    @Check
    public static void div(){
        System.out.println("1+1 =" +(1/0));
    }
    //减法
    @Check
    public static void sub(){
        System.out.println("1-1 =" +(1-1));
    }
    //乘法
    @Check
    public static void mul(){
        System.out.println("1*1 =" +(1*1));
    }

}
  
```
## 1.3 方法测试

```java
package com.annotation;

import java.io.*;
import java.lang.reflect.Method;

public class CalculateAnno {
    public static void main(String[] args) throws IOException {

        Class<CalculateTest> aClass = CalculateTest.class;
        //缓冲输出流书写异常方法 可以操作一行
        BufferedWriter bWriter = new BufferedWriter(new FileWriter("D:\\workspace\\Demo01\\src\\main\\java\\com\\annotation\\bug.txt"));
        //异常次数
        int count = 0;
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Check.class)){
                try {
                    method.invoke(aClass);
                } catch (Exception e) {
                    e.printStackTrace();
                    count++;
                    bWriter.write(method.getName()+"方法出异常了");
                    bWriter.newLine();
                    bWriter.write("异常类型"+e.getCause().getClass().getSimpleName());
                    bWriter.newLine();
                    bWriter.write("异常原因"+e.getCause().getMessage());
                    bWriter.newLine();
                    bWriter.write("---------------------------");
                    bWriter.newLine();
                }
            }
        }
        bWriter.write("本次测试一共出现"+count+"次异常");
        bWriter.close();
    }
}

```

### 1.4 结果示例

```txt
div方法出异常了
异常类型ArithmeticException
异常原因/ by zero
---------------------------
本次测试一共出现1次异常
```








