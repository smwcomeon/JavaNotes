
## 15 个 MyBatis 技巧

[引用地址](https://www.toutiao.com/i6937090947977134604/?tt_from=weixin&utm_campaign=client_share&wxshare_count=1&timestamp=1615337072&app=news_article&utm_source=weixin&utm_medium=toutiao_ios&use_new_style=1&req_id=20210310084432010150220075231E3B98&share_token=C38F1FE3-76A6-4DA7-AD8A-5497BA7C31C2&group_id=6937090947977134604)

** 模糊查询 ** 

MySQL 模糊查询一般是使用 like 条件，我们经常使用传入的变量作为 like 的条件。在 MyBatis 里面，like 使用变量需要用到 ${} 或者 concat。建议使用 concat，#{} 可以防止 SQL 注入。

```xml
like '%${value}%'
like concat('%',#{value},'%')

```


## 自动创建表
不建议在 MyBatis 里面建表，原则上数据表要由数据库管理员创建。但是对于一些小应用，可能开发者集多个角色于一身，自动建表反而利大于弊。如下的语句在 <select> 标签内使用了 CREATE TABLE IF NOT EXISTS 语法，可以在无表时自动建表，有表时会跳过执行。

<select id="createTable">
  CREATE TABLE IF NOT EXISTS `demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(128) NOT NULL COMMENT '路径'
  ) ENGINE=MyISAM DEFAULT CHARSET=utf8;
</select>


自动判断更新或插入
数据不存在则插入，数据已存在则更新，可以使用 MySQL on duplicate key update 来实现。当 primary key 或者 unique key 重复时会自动执行 update。

insert into `webVisit`(`date`,`ip`, uri, `count`)
values(#{date},#{ip},#{uri}, 1)
ON DUPLICATE KEY
update `count` = `count` + 1


自动忽略重复插入
数据不存在则插入，数据已存在则忽略，可以使用 MySQL ignore 来实现。当 primary key 或者 unique key 重复时会自动忽略本次插入操作。

insert ignore into `webVisit`(`ip`) values(#{ip})


自动替换重复数据
数据不存在则插入，数据已存在则替换，可以使用 MySQL replace into 来实现。当 primary key 或者 unique key 重复时会自动替换已存在的数据。实际上是先删除原有数据，然后插入新数据。

replace into `webVisit`(`date`,`ip`, uri, `count`)
values(#{date},#{ip},#{uri}, 1)


使用缓存
使用缓存能够极大提升应用的性能。二级缓存的开启方式是

<cache flushInterval="60000"></cache>


新增、更新数据不刷新缓存
缓存在新增、更新数据时会自动失效，对于部分影响不大的操作可以设置不刷新缓存，方法是在语句 xml 上设置 flushCache=false。

<update id="demo" flushCache="false"></update>


获取生成的自增id
使用自增 id 有很多好处，可以通过配置 selectKey 实现 insert 执行后返回生成的自增 id，以用于后续流程。

<insert id="insert" parameterType="demo">
  <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Long">
    SELECT LAST_INSERT_ID()
  </selectKey>
  insert into `demo`(demo) values(#{demo})
</insert>


只更新部分字段
使用 <if> <set> 标签配合，可以实现只更新部分数据。一般是传入 null 不更新，非 null 则更新。

<if> 标签支持 test 配置一个表达式，表达式返回 true 时 <if> 内部的 SQL 片段会生效，否则不会生效。<set> 标签能够自动生成 set 片段，并处理去除首尾多余的字符。

<update id="updateById" parameterType="UserDO">
  update `user`
  <set>
    <if test="nick != null">
      nick = #{nick},
    </if>
    <if test="avatar != null">
      avatar = #{avatar},
    </if>
  </set>
  where userId = #{userId}
</update>


动态查询条件
使用 <if> <where> 标签配合，可以实现动态查询条件。<if> 标签能够实现条件判断。<where> 标签能够自动组装 where 片段，去除首尾的非法字符。

<select id="select" resultMap="resultMap">
  SELECT * FROM demo
  <where>
    <if test="userId != null">
      userId = #{userId}
    </if>
    <if test="nick != null">
      and nick = #{nick}
    </if>
  </where>
</select>


设置 SQL 超时时间
通过 timeout 属性可以设置超时时间，也就是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。

<update id="demo" timeout="100"></update>


复用 SQL 片段
用 sql 标签可以定义 SQL 片段，用于 SQL 语句复用。

<sql id="Base_Column_List">
  userId, email
</sql>
<select id="selectById" parameterType="java.lang.Long" resultMap="ResultMap">
  select
  <include refid="Base_Column_List" />
  from `user`
  where userId = #{userId}
</select>


批量插入数据
批量插入数据能够极大提升性能，曾经测试过多条数据插入时间从分钟级降低到秒级完成。批量插入主要用到 forEach 标签，把批量数据转换成 values 语句。

foreach 标签的属性主要有 item，index，collection，open，separator，close。

item：集合中元素迭代时的别名；
index：集合中元素迭代时的索引；
open：常用语where语句中，表示以什么开始，比如以'('开始；
separator：表示在每次进行迭代时的分隔符；
close 常用语where语句中，表示以什么结束；
collection：集合值；
<insert id="batchInsert" parameterType="java.util.List">
  insert into `demo`(userId,`url`)
  values
  <foreach collection="list" item="item" separator=",">
    (#{item.userId}, #{item.url})
  </foreach>
</insert>


匹配多个值
使用 forEach 构建查询条件集合，where 语句使用 in 即可实现匹配多个值。

<select id="queryById" resultMap="BaseReslutMap" >
  select * FROM entity
  where id in 
  <foreach collection="userids" item="userid" index="index" open="(" separator="," close=")">
    #{userid}
  </foreach>
</select>


条件语句
java 可以很方便地使用 if 或 switch 实现分支功能。MyBatis 的 choose 标签可以实现条件分支。如下的配置中，传入 id 时使用 id，不传则使用其他条件。

choose 标签是按顺序判断其内部when标签中的test条件出否成立，如果有一个成立，则 choose 结束。当 choose 中所有 when 的条件都不满则时，则执行 otherwise 中的sql。类似于Java 的 switch 语句，choose 为 switch，when 为 case，otherwise 则为 default。

<update id="update" parameterType="Config">
  update `config` set `content` = #{content}
  <where>
    <choose>
      <when test="id != null">
        id = #{id}
      </when>
      <otherwise>
        appName = #{appName} and t = #{t} and x = #{x}
      </otherwise>
    </choose>
  </where>
</update>
