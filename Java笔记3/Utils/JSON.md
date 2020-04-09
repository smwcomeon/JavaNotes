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
		
	- JSON中可以保存的数据的类型   
		>>Number 数值
		>>String 字符串
		>>Boolean 布尔值
		>>null 空值
		>>Object 对象
		>>Array 数组
	```	
		```xml
		{"name":"孙悟空","age":18,"gender":"男"}
		[123,true,"hello"]
		```

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
**JAVA中的JSON格式一**
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
**JAVA中的JSON格式二**
```java
@Test
public void javaToJson() throws IOException{
	ObjectMapper objectMapper = new ObjectMapper();
	User user=new User();
	user.setId(1);
	user.setName("tomcat");
	user.setAge(10);

	//将对象转化为JSON串
	String userJSON = objectMapper.writeValueAsString(user);
	System.out.println(userJSON);

	//将json转化为对象
	User user1 = objectMapper.readValue(userJSON, User.class);
	System.out.println(user1);
}

```

### json中包含jsonarray
```java
package com.json;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonJsonArr {
    public static void main(String[] args) {
        //json中包含jsonarr格式
        String value = "{\"data\":[{\"school_name\":\"西北农林科技大学\",\"school_id\":\"8\"},{\"school_name\":\"西北大学\",\"school_id\":\"6\"},{\"school_name\":\"西北工业大学\",\"school_id\":\"5\"},{\"school_name\":\"西北政法大学\",\"school_id\":\"2\"},{\"school_name\":\"西安交通大学\",\"school_id\":\"4\"},{\"school_name\":\"西安建筑科技大学\",\"school_id\":\"10\"},{\"school_name\":\"西安电子科技大学\",\"school_id\":\"3\"},{\"school_name\":\"西安邮电大学\",\"school_id\":\"1\"},{\"school_name\":\"长安大学\",\"school_id\":\"9\"},{\"school_name\":\"陕西师范大学\",\"school_id\":\"7\"},{\"school_name\":\"陕西科技大学\",\"school_id\":\"11\"}],\"msg\":\"列表获取成功\",\"status\":0}";
        //net.sf.json包下的JSONObject
        JSONObject jsonObject = new JSONObject();
        //str转为json格式
        JSONObject jsonObject1 = jsonObject.fromObject(value);

        //获取jsonarr对象
        Object data = jsonObject1.get("data");
        System.out.println("josnArr原始数据"+data);
        JSONArray jsonArray = new JSONArray();
        //格式话json数据为jsonArr
        JSONArray jsonArray1 = jsonArray.fromObject(data);
        List<Map<String,Object>> list =new ArrayList<>();
        for (int i = 0; i < jsonArray1.size(); i++) {
            Object o = jsonArray1.get(i);
            Map jsonMap = jsonObject.fromObject(o);
            list.add(jsonMap);
            System.out.println(jsonMap);
        }
        System.out.println("list"+list);

    }
}

````
