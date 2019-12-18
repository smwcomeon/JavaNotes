## Db封装类使用手册(一定要打包下载 不要从页面预览复制粘贴 会缺失代码)
* [where条件](#where)
* [field](#field)
* [order](#order)
* [limit](#limit)
* [leftJoin](#leftjoin)
* [find查询](#find)
* [group分组](#group)
* [insert插入数据](#insert)
* [update更新数据](#update)
* [delete删除数据](#delete)
* [count查询行数](#count)
* [query执行原生语句](#query)
* [onlySql只输出sql语句](#onlysql)

## 获取Db类文件 
 :star2: 

可以从码云上获取最新的Db类文件  [地址点我](https://gitee.com/yancool/Db)

## 环境要求
 :star2: 

PHP版本5.4以上

## 开始使用
 :star2: 
在 _Db.php_ 文件里配置好你的数据库信息，然后在需要使用Db类的地方，引入 _Db.php_ 文件，实例化Db类即可
```php
$db     = new Db;
$select = $db->table('表名')->select();
```
- 使用多个数据库（暂时只能同个连接下的不同数据库）

一般是单数据库使用，直接在 _Db.php_ 文件配置好即可，如果在使用过程需要连接其他数据库，可以使用以下方法设置，每一次的语句都需要写

Db类定义了一个setDb方法，可以设置使用的数据库
```php
$db     = new Db;
$select = $db->setDb('other')->table('表名')->select();
```

## where
 :star2:  
### 数组条件 普通查询
定义一个一维数组，键名=字段名，传入where即可

以下代码查询出id=1的所有数据
```php
$db     = new Db;
$where['id'] = 1;
$select = $db->table('表名')->where($where)->select();
```
### 字符条件 特殊查询
如不等于、like模糊查询、IN等条件语句，第一个参数是上面的数组条件，可以同时使用

以下代码查询出id分别等于2,3,4的所有数据
```php
$db     = new Db;
$select = $db->table('表名')->where([],"'id' IN (2,3,4)")->select();
```
以下代码是模糊查询，名字是张*的所有数据
```php
$db     = new Db;
$select = $db->table('表名')->where([],"'name' like '张%'")->select();
```

## field
 :star2: 

设置需要查询的字段
```php
$db     = new Db;
$select = $db->field('id,name,sex')->table('表名')->select();
```
后面会有一个LEFT JOIN 关联查询，设置两个表的不同字段名如下

以下代码查询表1的所有字段，表2的id，当然，在这里是不对的，因为还没有传入left join的方法，详情可以看下面[left join条件](#leftjoin)
```php
$db     = new Db;
$select = $db->field('表1.*,表2.id')->table('表名')->select();
```

## order
 :star2: 

以下是根据name字段的排序，倒序

```php
$db     = new Db;
$select = $db->table('表名')->order('name')->select();
```
```php
$db     = new Db;
$select = $db->table('表名')->order('name DESC')->select();
```

## limit
 :star2: 

以下是查询5条数据
```php
$db     = new Db;
$select = $db->table('表名')->limit(5)->select();
```
以下是从第5开始，查询10条
```php
$db     = new Db;
$select = $db->table('表名')->limit(5,10)->select();
```
## leftjoin
 :star2: 

### 两表关联查询
leftJoin参数说明，'表1的字段' 和 '表2的字段' 是用于关联，比如两个表有相同的一个字段值，如用户的id，这样就可以将两个表的数据组合在同一行

```php
$db     = new Db;
$select = $db->table('表名')->leftJoin('表2', '表1的字段', '表2的字段')->select();
```
以下从table_1，table_2查出id=1的数据，返回所有字段，设置每个表的返回字段，查看[field条件](#field)


```php
$db     = new Db;
$select = $db->table('table_1')->leftJoin('table_2', 'id', 'id')->where(['id' = 1])->select();
```
## group
 :star2: 
将column不同的值分组

常用场景：统计每个月有订单的天数，以及查询每一天的订单详情

以下会返回所有的column值
```
id      column    title
1        1        分类1下的文章1
2        1        分类1下的文章2
3        2        分类2下的文章1
```
```php
$db     = new Db;
$select = $db->table('table_1')->field('column')->group('column')->select();
```
> 返回 1,2（数组形式，具体var_dump一下即可）

## find
 :star2: 

返回第一条数据，以下两种写法都可以，find返回一维数组，select返回二维数组
```php
$db     = new Db;
$select = $db->table('表名')->find();
```
```php
$db     = new Db;
$select = $db->table('表名')->limit(1)->select();
```
## insert
 :star2: 

跟where一样，定义一个一维数组，传入即可，insert方法返回自增ID
```php
$db     = new Db;
$insert['name'] = '哆啦A梦';
$insert['sex']  = '男';
$select = $db->table('表名')->insert($insert);
echo $select;
```

## update
 :star2: 

以下更改id=1的数据的名字
```php
$db     = new Db;
$update['name'] = '改名';
$select = $db->table('表名')->where(['id' => 1])->update($update);
```
## delete
 :star2: 

以下删除id=1的数据
```php
$db     = new Db;
$select = $db->table('表名')->where(['id' => 1])->delete();
```


## count
 :star2: 

以下返回表的所有数据行数
```php
$db     = new Db;
$select = $db->table('表名')->count();
```
## query
 :star2: 

```php
$db     = new Db;
$sql    = "select * from 表";
$select = $db->query($sql);
```
## onlysql
 :star2: 
```php
$db     = new Db;
$select = $db->table('表名')->onlySql()->select();
```

## 以上的操作 用判断是否成功 用以下
```php
$db         = new Db;
$a          = ....;
if($a !== false){
    echo 'success';
}
```
