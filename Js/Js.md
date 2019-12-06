### 1. js中if()条件过滤

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
