### Properties使用
```java
  package com.prop;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Propertytt {
    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.setProperty("加","112");
        properties.put("ss","1123");

        Set<String> strings = properties.stringPropertyNames();
        for (String string : strings) {
            Object o = properties.get(string);
            System.out.println(string+"-value:-"+o);
        }
        System.out.println("-------------------------");
        Set<Object> objects = properties.keySet();
        for (Object object : objects) {
            Object v = properties.get(object);
            System.out.println("key:"+object+"value: "+v);
        }

        FileWriter fileWriter = new FileWriter("D:\\workspace\\Demo01\\src\\main\\java\\com\\prop\\prop.txt");
        properties.store(fileWriter,"save  ");

        FileReader fileReader = new FileReader("D:\\workspace\\Demo01\\src\\main\\java\\com\\prop\\prop.txt");
        Properties pro = new Properties();
        pro.load(fileReader);
        for (String key:pro.stringPropertyNames()){
            System.out.println("pro-key"+key+"pro-value"+pro.getProperty(key));
        }
    }
}

```
