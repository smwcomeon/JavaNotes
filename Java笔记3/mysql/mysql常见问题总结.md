### 解决mysql字段大小写不敏感问题
```xml
  select * from t_ucp_servicetype where servicetypeid  binary regexp '^[A-Z]'
```

### JDBC出现The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrec问题
```word
BC出现The server time zone value ‘ÖÐ¹ú±ê×¼Ê±¼ä’ is unrec问题
后来查阅资料发现这都是因为安装mysql的时候时区设置的不正确 mysql默认的是美国的时区，而我们中国大陆要比他们迟8小时，采用+8:00格式
使用的数据库是MySQL，从上面图看出SpringBoot2.1在你没有指定MySQL驱动版本的情况下它自动依赖的驱动是8.0.12很高的版本，这是由于数据库和系统时区差异所造成的，在jdbc连接的url后面加上serverTimezone=GMT即可解决问题，如果需要使用gmt+8时区，需要写成GMT%2B8，否则会被解析为空。再一个解决办法就是使用低版本的MySQL jdbc驱动，5.1.28不会存在时区的问题。
2.解决办法
1.修改MySQL的配置文件，MySQL配置文件是my.ini文件在你的安装目录下去找，我的是在C:\Program Files (x86)\MySQL\MySQL Server 5.5\my.ini
找到这个文件之后用Notepad++打开它，搜索[mysqld]节点在节点下面加上下面这句话
default-time-zone=’+08:00’
然后记得重启MySQL的服务，打开cmd窗口登录MySQL执行show variables like ‘%time_zone%’;这句命令
2.直接在mysql执行语句:
set global time_zone='+8:00'
```
### 查询库中是否存在某张表
``` xml
<select id="selectTableByNameList" resultType="java.lang.String" parameterType="java.util.List">
         SELECT table_name FROM information_schema.TABLES WHERE table_name in
           <foreach collection="list"  item="ids" open="(" separator="," close=")"  >
              #{ids}
           </foreach>
 </select>
```
查询某个数据库中是否存在某张表
```xml
*SHOW TABLES LIKE '%tb_bp_d_case%';
*select TABLE_NAME from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='dbname' and TABLE_NAME='tablename' ;
```
