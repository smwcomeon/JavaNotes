```java
package com.Clendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClendarDemo {
    public static void main(String[] args) {

        //Calendar使用示例
        clendarTest();
    }

    public static void clendarTest(){
        //当前时间
        Date date = new Date();
        System.out.println("当前时间="+date);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_MONTH,-1);
        Date time = instance.getTime();
        System.out.println("当前时间的前一天="+time);

        //格式化时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = sdf.format(date);
        format1+=" 00:00:00";
        String format2 = sdf.format(time);
        System.out.println("格式化后的当前时间:  "+format1);
        System.out.println("格式化后的当前时间的前一天:   "+format2);
    }
}


```
