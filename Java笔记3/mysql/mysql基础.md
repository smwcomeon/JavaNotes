** 


表格中格式为Timestamp格式时，不给改字段赋值或者为null时，默认使用当前时间为该字段赋值


TRUNCATE table 表名:删除表中所有记录速率最快



ifnull（表达式1，表达式2）：表示如果表达式为null时，该字段被表达式2替换
例

注意：如果有多个排序条件，则前边的条件一样时，才会判断第二个条件


唯一约束：unique


主键


自动增长

外建



三范式

左外连接：查询的是所有数据以及其交集部分




alter表：
修改表名
	alert table 表名 rename to 新表名
修改表注释：
	alert table 表名 comment '信息'；
	
	
非空约束：
	1、创建表完成之后，添加非空约束 例：
	ALTER TABLE 表名 MODIFY 字段名 VARCHAR(20) not null
	2、删除非空约束
	alter table 表名 modify 字段名 Varchar(20)
主键：
	1、添加主键
	alter table 表名 modify 字段名 int primary key；
	2、删除主键
	alter table 表名 drop primary key；
自动增长：
	1、添加自动增长
	alter table 表名 modify 字段名 int auto_increment；
	2、删除自动增长
	alter table 表名 modify 字段名 int ；
外键：
	1、添加外键
	alter table 表名 add constraint 外键名称 foreign key(外键字段名) references 主表名称（主表列名称）
	2、删除外键
	alter table 表名 drop foreign key 外键名称 ；
	
	
顺序
	sql语句中的每个关键字都按照顺序往下执行，而每一步操作会生成一个虚拟表，最后的虚拟表就是最终结果（虚拟表可以理解成上述临时表的内部临时表）
	from->on->join(各种join)->where->group by->having->select->distinct->union->order by->limit
	
