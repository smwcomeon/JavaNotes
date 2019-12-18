* [js中if()条件过滤](#js条件过滤)
* [window.location相关属性](#window相关方法)
* [获取url中的参数](#获取url中的参数)

## js条件过滤

*js 中if可以传入非布尔值，可以用来做非空判断，下面测试一下传入哪些值可以视为false*

```js
  let a = undefined;
    let b = null;
    let c = '';
    let d = 0;
    let e = {};
    let f = [];
    if (a) {
      console.log("undefined 能过");
    } else {
      console.log("undefined 不能过");
    }
    if (b) {
      console.log("null 能过");
    } else {
      console.log("null 不能过");
    }
    if (c) {
      console.log(" '' 能过");
    } else {
      console.log(" '' 不能过");
    }
    if (d) {
      console.log("0 能过");
    } else {
      console.log("0 不能过");
    }
    if (e) {
      console.log("{} 能过");
    } else {
      console.log("{} 不能过");
    }
    if (f) {
      console.log("[] 能过");
    } else {
      console.log("[] 不能过");
    }
```
**结构**
```js
[2018-09-04 11:21:51.835] [INFO] console - undefined 不能过
[2018-09-04 11:21:51.836] [INFO] console - null 不能过
[2018-09-04 11:21:51.837] [INFO] console -  '' 不能过
[2018-09-04 11:21:51.838] [INFO] console - 0 不能过
[2018-09-04 11:21:51.844] [INFO] console - {} 能过
[2018-09-04 11:21:51.849] [INFO] console - [] 能过

```
结论
```text
  可以过滤undefined，null，空字符串，0
```

## window相关方法

window.location方法获取URL
统一资源定位符 (Uniform Resource Locator, URL)

完整的URL由这几个部分构成：
```text 
scheme://host:port/path?query#fragment

 
　　scheme:通信协议  常用的http,ftp,maito等

 
　　host:主机　　服务器(计算机)域名系统 (DNS) 主机名或 IP 地址。

 
　　port:端口号　　整数，可选，省略时使用方案的默认端口，如http的默认端口为80。

 
　　path:路径　　由零或多个"/"符号隔开的字符串，一般用来表示主机上的一个目录或文件地址。

 
　　query:查询　　可选，用于给动态网页（如使用CGI、ISAPI、PHP/JSP/ASP/ASP.NET等技术制作的网页）传递参数，可有多个参数，用"&"符号隔开，每个参数的名和值用"="符号隔开。

 
　　fragment:信息片断　　字符串，用于指定网络资源中的片断。例如一个网页中有多个名词解释，可使用fragment直接定位到某一名词解释。(也称为锚点.)

 ```
　　

**示例：**

 
***http://www.home.com:8080/windows/location/page.html?ver=1.0&id=timlq#love***

 ```text
　　1.window.location.href　　整个URl字符串(在浏览器中就是完整的地址栏)

       返回值：http://www.home.com:8080/windows/location/page.html?ver=1.0&id=timlq#love
   
　　2.window.location.protocol　　URL 的协议部分
 
　　     返回值：http:
 
　　3.window.location.host　　URL 的主机部分 
　　    
          返回值：www.home.com

   4.window.location.hostname 

      返回值：www.home.com:8080

　 5.window.location.port
        
      URL 的端口部分。如果采用默认的80端口(update:即使添加了:80)，那么返回值并不是默认的80而是空字符。
　　   本例返回值:8080

　 6.window.location.pathname　　URL 的路径部分(就是文件地址)
  
      返回值：/windows/location/page.html

　　7.window.location.search　　
  
      查询(参数)部分。除了给动态语言赋值以外，我们同样可以给静态页面,并使用javascript来获得相信应的参数值
　　  返回值：?ver=1.0&id=timlq

 
　　8.window.location.hash　　锚点

  　　 返回值：#love
  ```
## 获取url中的参数

```js
 function GetQueryStr(url,name){
    var reg = new RegExp("(^|&)"+name+"=([^&]*(&|$))");
    var str = url.match(reg);
    if(r !=null){
    return unescape(r[s]);
    };
    return null;
 }
```
