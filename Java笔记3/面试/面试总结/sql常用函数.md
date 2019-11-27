**一、字符函数**

**1) LENGTH** 

\#获取参数值的字节个数

SELECT LENGTH ('lala') ;

结果： 4

**2) CONCAT** 

\#拼接字符串

SELECT CONCAT ('asd' , 'we' );

结果： asdwe

**3) UPPER**

\#将参数全部变为大写

SELECT UPPER(' abcd' );

结果:   ABCD

**3) LOWER**

**#** 将参数全部变为小写

SELECT LOWER(' ABCD' );

结果:   abcd

**4) SUBSTR**

\#索引从1开始，返回指定位置及其之后的字符串

SELECT SUBSTR( ' ABCD' , 2 ); 

结果：BCD

\#截取从指定索引处（第二个参数）开始，获取索引位置及其后指定字符长度的字符串（第三个参数）

SELECT SUBSTR( ' ABCD' , 1,3 ); 

结果：ABC

**5) INSTR** 

**#** 返回第二个参数在第一个参数中第一次出现的位置，若没有出现，则返回0

SELECT INSTR('ABCD' , 'CD')

结果：  3

SELECT INSTR('ABCD' , 'E')

结果：  0

**6) TRIM**

\#去除首尾指定的字符，若没有指定，则表示去除空格

SELECT TRIM ( '#' , '###acd##sas###');

结果：  acd##sas

**7) LPAD** 

\#用指定的字符实现左填充指定长度，同时也是结果的最终长度

SELECT LPAD( 'abc' , 4 , '#'');

结果:  #ABC

**8) RPAD** 

\#用指定的字符实现右填充指定长度，同时也是结果的最终长度

SELECT LPAD( 'ABC' , 4 , '#');

结果:  ABC#

 

SELECT LPAD( 'ABC' , 2 , '#');

结果:  AB

**9) REPLACE**

\#替换所有的指定的字符串为另一个字符串

SELECT REPLACE( 'ABCBCD' , 'BC' ,'#' ); 

结果: A##D

**二、数学函数**

**1) ROUND**

\#四舍五入

 SELECT ROUND(-1.55);

结果：  -2 

\#保留指定小数的位数

SELECT ROUND (1.547,2);

结果：  1.54

**2) CEIL** 

\#向上取整，返回大于等于该参数的最小整数

SELECT CEIL (-1.0002);

结果：  -1

**3) FLOOR**

\#向下取整，返回小于等于该参数的最大整数

SELECT CEIL (9.99999);

结果：  9

**4) TRUNCATE**

\#截断

SELECT TRUNCATE (1.6699,2);

结果:  1.66

**5) MOD**

\#取模

SELECT MOD(-10,-3)

结果:  -1

**三、日期函数**

**1) NOW**

\#返回当前系统日期+时间

**SELECT NOW();**

**2) CURDATE**

\#返回当前系统时间，不包含时间

SELECT CURDATE();

**3) CURTIME** 

\#返回当前时间，不包含日期

SELECT CURTIME();

4) **MONTHNAME , DAYNAME  等**

\#返回日期的英文名字

SELECT DAYNAME(NOW());

**5) STR_TO_DATE**

\#将字符串通过指定的格式转化为日期

SELECT STR_TO_DATE('4-3#1992', '%m-%d#%Y');

结果：1992-04-03

**6) DATE_FORMAT**

\#将日期转化为指定格式字符串

SELECT DATE_FORMAT(NOW(), '%m#%d#%Y');

结果： 04#27#2019

**三、其他函数**

**1) SELECT VERSION()**

\#获取sql的版本号

**2) SELECT DATABASE()**

\#获取当前所在数据库名

**3)SELECT USER()**

\#获取登陆用户名

**四、流程控制函数**

**1) if**

\#一共三个参数，若第一个参数代表的表达式值为true, 则执行第二个参数代表的表达式，否则执行第三个参数代表的表达式

SELECT IF (10 > 5 , '大' , '小' )；

结果： 大

**2) case**

**用法一：**

\#相当于switch case

CASE 要判断的字段或者表达式

WHEN 常量1 THEN 要显示的值1或者与语句1

WHEN 常量2   THEN 要显示的值2或者与语句2

WHEN 常量3 THEN 要显示的值3或者与语句3

ELSE  要显示的值x或者与语句x

END

**用法二：**

\#相当于多重if

\#相当于switch case

CASE

WHEN 条件1 THEN 要显示的值1或者与语句1

WHEN 条件2   THEN 要显示的值2或者与语句2

WHEN 条件3 THEN 要显示的值3或者与语句3

ELSE  要显示的值x或者与语句x

END

 

