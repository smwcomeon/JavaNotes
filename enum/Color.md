### 枚举类示例

```java
package com.enumtt;

public enum Color {
//    RED("红色",1),YELLOW("黄色",2),GREEN("绿色",3);
    RED("红色","1"),YELLOW("黄色","2"),GREEN("绿色","3");

    /**
     * 添加构造参数
     * @param color
     * @param index
     */
//    Color(String color, int index) {
//        this.color = color;
//        this.index = index;
//    }
    private String color;
    private String index;
    Color(String color, String index) {
        this.color = color;
        this.index = index;
    }


    //set get方法根据业务需求添加 枚举类是否允许修改
//    private String color;
//    private int index;
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }

    public static String getValue(String index){
        //遍历枚举类
        Color[] values = Color.values();
        for (Color value : values) {
            if (value.index.equalsIgnoreCase(index)){
                return value.color;
            }
        }
        return "";
    }
}

```

### 枚举类使用
```java
package com.enumtt;

public class ColorEnum {
    public static void main(String[] args) {
        //枚举类的使用
        Color red = Color.RED;
//        red.setColor("hh");
//        String color = red.getColor();
//        int index = red.getIndex();
//        System.out.println(color+"---"+index);
        System.out.println(Color.getValue("1"));
    }
}

```
