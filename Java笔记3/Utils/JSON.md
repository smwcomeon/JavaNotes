**JSON**
	```
	- JSON就是一种数据交换的格式
	- JSON就是一个字符串，通过JSON可以表示不同的语言的对象，并且该字符串可以转换为不同语言中的对象
	- JavaScript Object Notation（JavaScript对象表示法）	
	- JSON的规范：
		1.JSON是一个字符串
		2.JSON中的属性名必须使用双引号引起来
	- JSON的两种格式
		JSON对象 {}
		JSON数组 []
	```	
	- JSON中可以保存的数据的类型   
		>>Number 数值
		>>String 字符串
		>>Boolean 布尔值
		>>null 空值
		>>Object 对象
		>>Array 数组
		
	
		`{"name":"孙悟空","age":18,"gender":"男"}`
		`[123,true,"hello"]`

**HTML中的json格式：**

```html
<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="utf-8">
        <title>Doument</title>
        <script type="text/javascript">
                var str = '{"name":"小熊","age":"11","gender":"男"}';
                // alert("str+json:"+str)

        var strObj={"name":"小熊","age":"11","gender":"男"};
        // alert("strObj:"+strObj)

        //json转成obj的方法
        var str2Obj = JSON.parse(str)
        alert("str2Obj:"+str2Obj)

        obj转成JSON的方法
        var strObj2Json = JSON.stringify(strObj)
        alert("str2Obj:"+str2Obj)
        </script>
</head>

<body>
        <p>json转成obj的方法是JSON.parse</p>
        <p>Obj转成Json的方法是JSON.stringify()</p>
</body>
</html>
```
**JAVA中的JSON格式：
```java
package com.admin.json;

import com.google.gson.Gson;

import java.util.Map;

public class JSONTest {
    public static void main(String[] args) {

        //java中的json格式 var str = '{"name":"小熊","age":"11","gender":"男"}';
        String str = "{\"name\":\"小熊\",\"age\":\"11\",\"gender\":\"男\"}";
        System.out.println("json格式："+str);

        //将json转换成对象
        Gson gson = new Gson();
        Map map = gson.fromJson(str, Map.class);
        System.out.println("Map格式的："+map);

        //将对象转成JSON
        String json = gson.toJson(map);
        System.out.println("json:"+json);
    }
}
```
