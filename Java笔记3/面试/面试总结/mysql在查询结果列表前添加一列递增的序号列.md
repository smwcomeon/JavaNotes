SELECT @rownum:=@rownum+1 AS 序号, t_user.* FROM  t_user ,(SELECT @rownum:=0) r  -- 最简写法1

 

更多写法供参考：

 

 ①  select (@rowNO := @rowNo+1) AS rowno,uid,uname from (SELECT *  FROM t_user ) ;

 

 

 ②  select (@rowNO := @rowNo+1) AS rowno,a.* from (SELECT * FROM t_user) a,(select @rowNO :=0) b ;

 

 

 ③  set @rn=0; select @rn:=@rn+1 as rn, m.* from (select * from t_user  )m; -- 连续执行两个语句

 

 

 ④  select (@rowNO := @rowNo+1) AS 序号,a.* from  t_user a,(select @rowNO :=0) b  -- 最简写法2 

 

 

 

注意：@rownum的写法不唯一，但一定有@，等于号写法一定是:=（冒号等于）

 

以下写法都可行：

 

SELECT @xxoo:=@xxoo+1 AS 序号, t_user.* FROM  t_user ,(SELECT @xxoo:=0) r;  

 

SELECT @1:=@1+1 AS 序号, t_user.* FROM  t_user ,(SELECT @1:=0) r;

 

SELECT @:=@+1 AS 序号, a.* FROM  t_user a ,(SELECT @:=0) r; （首推）  

 

来张查询结果图：

 