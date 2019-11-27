<select> :映射查询语句使用的标签

id： 命名空间中唯一标识符，可用来代表这条语句

resultMap：用于设置返回值的类型和映射关系

\#{id}：mybatis sql中使用预编译参数的一种方式，大括号中的id是传入参数名

resultMap的属性：

id ：必填，唯一，在select标签中，resultMap指定的值即为此处id所设置的值

type：必填，用于配置查询列所映射到的java对象类型

extends：选填，可以配置当前resultMap继承自其它的resultMap，属性值为继承resultMap的id

autoMapping：选填，true or false

resultMap的属性

constructor：配置使用构造方法注入结果，包含

idArg：id参数，标记结果作为id（唯一值），可以帮助提高整体性能

arg：注入到构造方法的一个普通结果

id：一个id的结果，标记结果作为id

result：注入到java对象属性的普通结果

association：一个复杂类型的关联，许多结果将包成这种类型

collection：复杂类型的集合

discriminator：根据结果值来决定那个结果映射

case：基于某些值的结果映射

 

来自 <<https://www.cnblogs.com/loveHawin/p/10370807.html>> 

 

 

\------------------------------------------------------------

 

目前比较流行的持久层框架，hibernate，mybatis，Spring Data等，hibernate一般还是比较老的项目会使用，spring Data还不是很广泛，只有mybatis是目前使用比较多，也是被经常问的，如果你说你用过mybatis，最喜欢被问两个问题:1.mybatis有哪些标签，2.#{}和${}有什么区别；

 

​     首先说下最最常用的<insert>,<delete>，<select>，<update>(增删查改)，四种标签之间分别放四种增删查改的语句，不过除了标签的使用，还会用到这些标签的属性，先拿最最最常用的<select>来说：（官方文档在这里）

 

 

 

图1-1

 

虽然比较多，但实际上常用的只有前四个，

 

id：在命名空间唯一的标识，可以被用来引用这条语句（实际使用时保证id的值与mapper.java 中方法名一致）

 

parameterType:不是必须的，用来表示传入的参数类型，可以是Integer，String等java的数据类型，也可以是自定义的Person，User等数据类型，还可以是List，Map集合等

 

resultType：这个和parameterType相对，这个表示返回的数据类型，同样不是必须的，也同样可以是Integer，String，还可以是自定义类型Person，User或者是List和Map集合，

 

resultMap:这个就比较独特了，一般在自定义映射的时候会用到，与<resultMap>标签合用，如果你返回的数据不是Integer，也不是自定义的Person,也不是List集合，这里就可以使用<resultMap>自定义返回的类型，并将resultMap的值设置为<resultMap>标签的id，（此属性不能和resultType同时存在）

 

例如：（不过这里最终映射的还是自定义类，只是显示下用法）

 

 

 

图1-2

 

下面说说另外三个增删改：

 

增删改比起select更加简单些，因为它默认返回结果类型是Integer，意思就是返回操作了几行数据。具体参数如下：

 

 

 

常用的只有id和parameterType，表示的意思也和select中两个参数值意义相同。

 

下面说说一些辅助类型的标签,包括<sql>,<resultMap>,<id>,<result>,

 

 

 

从上面的例子中可以很容易看出<sql>标签的作用，代替一小段sql，经常用在select查询的字段上：查询的方法很多，用<sql>标签可以很容易的简化多次要查询写的sql字段名；

 

<resultMap>,<id>，<sql>三种标签一般都是一起出现的的。

 

图1-2已经展示<resultMap>的用法，其中id与result区别是：id 元素表示的结果将是对象的标识属性，这会在比较对象实例时用到。 这样可以提高整体的性能，尤其是进行缓存和嵌套结果映射（也就是连接映射）的时候。如果你不理解这段话，那就直接记住，id的时候使用id标签，其他使用result标签。

 

下面直接来说说foreach标签，他是个循环标签

 

foreach元素的属性主要有 item，index，collection，open，separator，close。

 

item表示集合中每一个元素进行迭代时的别名，

 

index指 定一个名字，用于表示在迭代过程中，每次迭代到的位置，

 

collection表示传入的集合

 

open表示该语句以什么开始，

 

separator表示在每次进行迭代之间以什么符号作为分隔 符，

 

close表示以什么结束。

 

不过要注意的是：

 

\1. 如果传入的是单参数且参数类型是一个List的时候，collection属性值为list

\2. 如果传入的是单参数且参数类型是一个array数组的时候，collection的属性值为array

\3. 如果传入的参数是多个的时候，我们就需要把它们封装成一个Map了，当然单参数也可

举例：

 

单参数List的类型：

 

 <select id="dynamicForeachTest" parameterType="java.util.List" resultType="Blog">

​           select * from t_blog where id in

​        <foreach collection="list" index="index" item="item" open="(" separator="," close=")">

​                \#{item}       

​        </foreach>    

​    </select>

单参数array数组的类型：

 

  <select id="dynamicForeach2Test" parameterType="java.util.ArrayList" resultType="Blog">

​     select * from t_blog where id in

​     <foreach collection="array" index="index" item="item" open="(" separator="," close=")">

​          \#{item}

​     </foreach>

 </select>    

把参数封装成Map的类型：

 

 <select id="dynamicForeach3Test" parameterType="java.util.HashMap" resultType="Blog">

​         select * from t_blog where title like "%"#{title}"%" and id in

​          <foreach collection="ids" index="index" item="item" open="(" separator="," close=")">

​               \#{item}

​          </foreach>

 </select>

