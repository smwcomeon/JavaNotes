**[where choose](#choose)

## IF函数

```text
IF(expr1,expr2,expr3)

如果 expr1 是TRUE (expr1 <> 0 and expr1 <> NULL)，则 IF()的返回值为expr2; 否则返回值则为 expr3。IF() 的返回值为数字值或字符串值，具体情况视其所在语境而定。

复制代码代码如下:

select *,if(sva=1,"男","女") as ssva from taname where sva != ""
```
***作为表达式的if也可以用CASE when来实现：***

```text
复制代码代码如下:

select CASE sva WHEN 1 THEN '男' ELSE '女' END as ssva from taname where sva != ''

```
** 2) IFNULL(expr1,expr2)**

```text
假如expr1 不为 NULL，则 IFNULL() 的返回值为 expr1; 否则其返回值为 expr2。
IFNULL()的返回值是数字或是字符串，具体情况取决于其所使用的语境。
```
**3) IF ELSE 语句**

语法规则如下：
```xml
if (condition)
begin
(statement block)
end
else if (condition)
begin
statement block)
end
else
begin
(statement block)
end
```
## choose

```xml
  <where>
    <chooose>
        <when test="serviceTypeId !=null and serviceTypeId !=''">
           serviceTypeId =#{serviceTypeId}
         <when>
         <otherwise>
           <if test ="value !=null and value !=''">
             serviceTypeId =#{value}
            <if>
          </otherwise>
     </choose> > 
   </where>
```
## UNIX_TIMESTAMP--时间格式转换
```text
mysql的UNIX_TIMESTAMP用法
一、UNIX_TIMESTAMP 一般是用于unix的时间戳。
例子一、日期转化为时间戳
SELECT UNIX_TIMESTAMP("2016-07-11")
结果：
-- 1468166400

例子二、日期转化为时间戳

SELECT UNIX_TIMESTAMP("2016-07-17 23:59:59")
结果：

-- 1468771199

二、FROM_UNIXTIME：表示把UNIX_TIMESTAMP还原成标准的时间格式

SELECT FROM_UNIXTIME(1468166400),FROM_UNIXTIME(1468771199)
结果：

2016-07-11 00:00:00	2016-07-17 23:59:59

```

## GROUP_CONCAT合并函数 

```
同一字段连接使用 但必须跟group by 连用

例：select id ,name,GROUP_CONCAT(skill_quece_id) as skills form tableName group by provId

```
[网页实例](https://blog.csdn.net/qq_35531549/article/details/90383022)
