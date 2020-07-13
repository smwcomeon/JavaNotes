[狂神redis学习笔记](https://www.jianshu.com/p/ded098a2d29a)

[1、Redis-Key](https://github.com/smwcomeon/JavaNotes/blob/master/redis/redis%E5%B8%B8%E7%94%A8%E5%91%BD%E4%BB%A4.md#redis-key)

[String（字符串）](https://github.com/smwcomeon/JavaNotes/blob/master/redis/redis%E5%B8%B8%E7%94%A8%E5%91%BD%E4%BB%A4.md#string%E5%AD%97%E7%AC%A6%E4%B8%B2)

[List（列表）](https://github.com/smwcomeon/JavaNotes/blob/master/redis/redis%E5%B8%B8%E7%94%A8%E5%91%BD%E4%BB%A4.md#list%E5%88%97%E8%A1%A8)

[Hash类型](https://github.com/smwcomeon/JavaNotes/blob/master/redis/redis%E5%B8%B8%E7%94%A8%E5%91%BD%E4%BB%A4.md#hash%E7%B1%BB%E5%9E%8B)

[Set（集合）](https://github.com/smwcomeon/JavaNotes/blob/master/redis/redis%E5%B8%B8%E7%94%A8%E5%91%BD%E4%BB%A4.md#set%E9%9B%86%E5%90%88)

[Zset](https://github.com/smwcomeon/JavaNotes/blob/master/redis/redis%E5%B8%B8%E7%94%A8%E5%91%BD%E4%BB%A4.md#zset)

## Redis-Key
```
127 .0.0.1:6379> keys *                             # 查看所有的key
(empty list or set)
127 .0.0.1:6379> set name kuangshen                 # set key
OK
127 .0.0.1:6379> keys *
1 ) "name"
127 .0.0.1:6379> set age 1
OK
127 .0.0.1:6379> keys *
1 ) "age"
2 ) "name"
127 .0.0.1:6379> EXISTS name                       # 判断当前的key是否存在
(integer) 1
127 .0.0.1:6379> EXISTS name
(integer) 0
127 .0.0.1:6379> move name 1                        # 移除当前的key
(integer) 1
127 .0.0.1:6379> keys *
1 ) "age"
127 .0.0.1:6379> set name qinjiang
OK
127 .0.0.1:6379> keys *
1 ) "age"
2 ) "name"
127 .0.0.1:6379> clear
127 .0.0.1:6379> keys *
1 ) "age"
2 ) "name"
127 .0.0.1:6379> get name
"qinjiang"
127 .0.0.1:6379> EXPIRE name 10                     # 设置key的过期时间，单位是秒
(integer) 1
127 .0.0.1:6379> ttl name                           # 查看当前key的剩余时间
(integer) 4
127 .0.0.1:6379> ttl name
(integer) 3
127 .0.0.1:6379> ttl name
(integer) 2
127 .0.0.1:6379> ttl name
(integer) 1
127 .0.0.1:6379> ttl name
(integer) -
127 .0.0.1:6379> get name
(nil)
127 .0.0.1:6379> type name                          # 查看当前key的一个类型！
string
127 .0.0.1:6379> type age
string
```

### String（字符串）

```
##########################################################################
127 .0.0.1:6379> set key1 v1                                   # 设置值
OK
127 .0.0.1:6379> get key1                                      # 获得值
"v1"
127 .0.0.1:6379> keys *                                        # 获得所有的key
1 ) "key1"
127 .0.0.1:6379> EXISTS key1                                    # 判断某一个key是否存在
(integer) 1
127 .0.0.1:6379> APPEND key1 "hello"                            # 追加字符串，如果当前key不存在，就相当于setkey
(integer) 7
127 .0.0.1:6379> get key1
"v1hello"
127 .0.0.1:6379> STRLEN key1                                    # 获取字符串的长度！
(integer) 7
127 .0.0.1:6379> APPEND key1 ",kaungshen"
(integer) 17
127 .0.0.1:6379> STRLEN key1
(integer) 17
127 .0.0.1:6379> get key1
"v1hello,kaungshen"
##########################################################################
# i++
# 步长 i+=
127 .0.0.1:6379> set views 0                                    # 初始浏览量为 0
OK
127 .0.0.1:6379> get views
"0"
127 .0.0.1:6379> incr views                                     # 自增 1 浏览量变为 1
(integer) 1
127 .0.0.1:6379> incr views
(integer) 2
127 .0.0.1:6379> get views
"2"
127 .0.0.1:6379> decr views                                      # 自减 1 浏览量-1
(integer) 1
127 .0.0.1:6379> decr views
(integer) 0
127 .0.0.1:6379> decr views
(integer) -1
127 .0.0.1:6379> get views
"-1"
127 .0.0.1:6379> INCRBY views 10                                # 可以设置步长，指定增量！
(integer) 9
127 .0.0.1:6379> INCRBY views 10
(integer) 19
127 .0.0.1:6379> DECRBY views 5

127 .0.0.1:6379> set key1 "hello,kuangshen"                       # 设置 key1 的值
OK
127 .0.0.1:6379> get key1
"hello,kuangshen"
127 .0.0.1:6379> GETRANGE key1 0 3                               # 截取字符串 [0,3]
"hell"
127 .0.0.1:6379> GETRANGE key1 0 -1                              # 获取全部的字符串 和 get key是一样的
"hello,kuangshen"

127 .0.0.1:6379> set key2 abcdefg
OK
127 .0.0.1:6379> get key2
"abcdefg"
127 .0.0.1:6379> SETRANGE key2 1 xx                              # 替换指定位置开始的字符串！
(integer) 7
127 .0.0.1:6379> get key2
"axxdefg"


setex (set with expire)                                            # 设置过期时间
setnx (set if not exist)                                           # 不存在在设置 （在分布式锁中会常常使用！）
127 .0.0.1:6379> setex key3 30 "hello"                             # 设置key3 的值为 hello,30秒后过期
OK
127 .0.0.1:6379> ttl key3
(integer) 26
127 .0.0.1:6379> get key3
"hello"
127 .0.0.1:6379> setnx mykey "redis"                               # 如果mykey 不存在，创建mykey
(integer) 1
127 .0.0.1:6379> keys *
1 ) "key2"
2 ) "mykey"
3 ) "key1"
127 .0.0.1:6379> ttl key3
(integer) -2
127 .0.0.1:6379> setnx mykey "MongoDB"                            # 如果mykey存在，创建失败！
(integer) 0
127 .0.0.1:6379> get mykey
"redis"
##########################################################################
mset
mget

127 .0.0.1:6379> mset k1 v1 k2 v2 k3 v3                          # 同时设置多个值
OK  
127 .0.0.1:6379> keys *
1 ) "k1"
2 ) "k2"
3 ) "k3"
127 .0.0.1:6379> mget k1 k2 k3                                    # 同时获取多个值
1 ) "v1"
2 ) "v2"
3 ) "v3"

127 .0.0.1:6379> msetnx k1 v1 k4 v4                              # msetnx 是一个原子性的操作，要么一起成功，要么一起
失败！
(integer) 0
127 .0.0.1:6379> get k4
(nil)

# 对象
set user:1 {name:zhangsan,age:3}                                 # 设置一个user:1 对象 值为 json字符来保存一个对象！
# 这里的key是一个巧妙的设计： user:{id}:{filed} , 如此设计在Redis中是完全OK了！
127 .0.0.1:6379> mset user:1:name zhangsan user:1:age 2
OK
127 .0.0.1:6379> mget user:1:name user:1:age
1 ) "zhangsan"
2 ) "2"
##########################################################################
getset # 先get然后在set
127 .0.0.1:6379> getset db redis                                # 如果不存在值，则返回 nil
(nil)
127 .0.0.1:6379> get db
"redis
127 .0.0.1:6379> getset db mongodb                                # 如果存在值，获取原来的值，并设置新的值
"redis"
127 .0.0.1:6379> get db
"mongodb"
```

### List（列表）

```
##########################################################################
127 .0.0.1:6379> LPUSH list one                                 # 将一个值或者多个值，插入到列表头部 （左）
(integer) 1
127 .0.0.1:6379> LPUSH list two
(integer) 2
127 .0.0.1:6379> LPUSH list three
(integer) 3
127 .0.0.1:6379> LRANGE list 0 -1                              # 获取list中值！
1 ) "three"
2 ) "two"
3 ) "one"
127 .0.0.1:6379> LRANGE list 0 1                                # 通过区间获取具体的值！
1 ) "three"
2 ) "two"
127 .0.0.1:6379> Rpush list righr                                # 将一个值或者多个值，插入到列表位部 （右）
(integer) 4
127 .0.0.1:6379> LRANGE list 0 -1
1 ) "three"
2 ) "two"
3 ) "one"
4 ) "righr"
##########################################################################
LPOP
RPOP
127 .0.0.1:6379> LRANGE list 0 -1
1 ) "three"
2 ) "two"
3 ) "one"
4 ) "righr"
127 .0.0.1:6379> Lpop list                                     # 移除list的第一个元素
"three"
127 .0.0.1:6379> Rpop list                                      # 移除list的最后一个元素
"righr"
127 .0.0.1:6379> LRANGE list 0 -1
1 ) "two"
2 ) "one"
##########################################################################
Lindex
127 .0.0.1:6379> LRANGE list 0 -1
1 ) "two"
2 ) "one"
127 .0.0.1:6379> lindex list 1                                 # 通过下标获得 list 中的某一个值！
"one"
127 .0.0.1:6379> lindex list 0
"two"

127 .0.0.1:6379> Llen list                                     # 返回列表的长度
(integer) 3

Lrem
127 .0.0.1:6379> LRANGE list 0 -1
1 ) "three"
2 ) "three"
3 ) "two"
4 ) "one"
127 .0.0.1:6379> lrem list 1 one                              # 移除list集合中指定个数的value，精确匹配
(integer) 1
127 .0.0.1:6379> LRANGE list 0 -1
1 ) "three"
2 ) "three"
3 ) "two"
127 .0.0.1:6379> lrem list 1 three
(integer) 1
127 .0.0.1:6379> LRANGE list 0 -1
1 ) "three"
2 ) "two"
127 .0.0.1:6379> Lpush list three
(integer) 3
127 .0.0.1:6379> lrem list 2 three
(integer) 2
127 .0.0.1:6379> LRANGE list 0 -1
1 ) "two"

##########################################################################
trim 修剪。； list 截断!

127 .0.0.1:6379> keys *
(empty list or set)
127 .0.0.1:6379> Rpush mylist "hello"
(integer) 1
127 .0.0.1:6379> Rpush mylist "hello1"
(integer) 2
127 .0.0.1:6379> Rpush mylist "hello2"
(integer) 3
127 .0.0.1:6379> Rpush mylist "hello3"
(integer) 4
127 .0.0.1:6379> ltrim mylist 1 2                             # 通过下标截取指定的长度，这个list已经被改变了，截断了只剩下截取的元素！
OK
127 .0.0.1:6379> LRANGE mylist 0 -1
1 ) "hello1"
2 ) "hello2"

##########################################################################
rpoplpush # 移除列表的最后一个元素，将他移动到新的列表中！

127 .0.0.1:6379> rpush mylist "hello"

127 .0.0.1:6379> rpush mylist "hello1"
(integer) 2
127 .0.0.1:6379> rpush mylist "hello2"
(integer) 3
127 .0.0.1:6379> rpoplpush mylist myotherlist                 # 移除列表的最后一个元素，将他移动到新的列表中！
"hello2"    
127 .0.0.1:6379> lrange mylist 0 -1                           # 查看原来的列表
1 ) "hello"
2 ) "hello1"
127 .0.0.1:6379> lrange myotherlist 0 -1                      # 查看目标列表中，确实存在改值！
1 ) "hello2"

##########################################################################
lset 将列表中指定下标的值替换为另外一个值，更新操作
127 .0.0.1:6379> EXISTS list                                    # 判断这个列表是否存在
(integer) 0
127 .0.0.1:6379> lset list 0 item                               # 如果不存在列表我们去更新就会报错
(error) ERR no such key
127 .0.0.1:6379> lpush list value1
(integer) 1
127 .0.0.1:6379> LRANGE list 0 0
1 ) "value1"
127 .0.0.1:6379> lset list 0 item                               # 如果存在，更新当前下标的值
OK
127 .0.0.1:6379> LRANGE list 0 0
1 ) "item"
127 .0.0.1:6379> lset list 1 other                                # 如果不存在，则会报错！
(error) ERR index out of range
##########################################################################
linsert # 将某个具体的value插入到列把你中某个元素的前面或者后面！

127 .0.0.1:6379> Rpush mylist "hello"
(integer) 1
127 .0.0.1:6379> Rpush mylist "world"
(integer) 2
127 .0.0.1:6379> LINSERT mylist before "world" "other"
(integer) 3
127 .0.0.1:6379> LRANGE mylist 0 -1
1 ) "hello"
2 ) "other"
3 ) "world"
127 .0.0.1:6379> LINSERT mylist after world new
(integer) 4
127 .0.0.1:6379> LRANGE mylist 0 -1
1 ) "hello"
2 ) "other"
3 ) "world"
4 ) "new"

如果移除了所有值，空链表，也代表不存在！
在两边插入或者改动值，效率最高！ 中间元素，相对来说效率会低一点~
消息排队！消息队列 （Lpush Rpop）， 栈（ Lpush Lpop）！

```


## Hash类型

```
> hset map key1 v1                                             #设置一个key value
(integer) 1

> hmset map key4 v4 key5 v5                                     #设置多个key value
OK

> hset map key2 value2
(integer) 1
> hget map key2                                                  #获取map中指定的key
"value2"

> hgetall map                                                  #获取map中所有的key value
1) "key1"
2) "v1"
3) "key2"
4) "value2"

> hdel map key3                                                #删除map中指定的key 对应的value也删除
(integer) 1
> hgetall map
1) "key1"
2) "v1"
3) "key2"
4) "value2"

> hlen map                                                      #获取map的size 
2

> hexists map k1                                              # 判断map是否存在k1
(integer) 1     

> hexists map k12
(integer) 0

> hkeys map                                                     #获取map中所有的key
1) "key1"
2) "key2"
3) "k1"
> hvals map                                                        #获取map中所有的value
1) "v1"
2) "value2"
3) "v3"

> hset map key 3
(integer) 1
> hincrby map key 1                                                 #map中value 自增
(integer) 4
> hincrby map key -1
(integer) 3

> hsetnx map key v                                             #map新增 key value 如果key不存在新增 如何key存在不做任何操作
(integer) 0
> hsetnx map key11 v
(integer) 1
```

### Set（集合）

```
##########################################################################
127 .0.0.1:6379> sadd myset "hello"                              # set集合中添加匀速
(integer) 1
127 .0.0.1:6379> sadd myset "kuangshen"
(integer) 1
127 .0.0.1:6379> sadd myset "lovekuangshen"
(integer) 1
127 .0.0.1:6379> SMEMBERS myset                                 # 查看指定set的所有值
1 ) "hello"
2 ) "lovekuangshen"
3 ) "kuangshen"
127 .0.0.1:6379> SISMEMBER myset hello                           # 判断某一个值是不是在set集合中！
(integer) 1
127 .0.0.1:6379> SISMEMBER myset world
(integer) 0

##########################################################################
127 .0.0.1:6379> scard myset                                     # 获取set集合中的内容元素个数！
(integer) 4
127 .0.0.1:6379> srem myset hello                                  # 移除set集合中的指定元素
(integer) 1
127 .0.0.1:6379> scard myset
(integer) 3
127 .0.0.1:6379> SMEMBERS myset
1 ) "lovekuangshen2"
2 ) "lovekuangshen"
3 ) "kuangshen"

##########################################################################
set 无序不重复集合。抽随机！
127 .0.0.1:6379> SMEMBERS myset
1 ) "lovekuangshen2"
2 ) "lovekuangshen"
3 ) "kuangshen"
127 .0.0.1:6379> SRANDMEMBER myset                               # 随机抽选出一个元素
"kuangshen"
127 .0.0.1:6379> SRANDMEMBER myset
"kuangshen"
127 .0.0.1:6379> SRANDMEMBER myset
"kuangshen"
127 .0.0.1:6379> SRANDMEMBER myset
"kuangshen"
127 .0.0.1:6379> SRANDMEMBER myset 2                              # 随机抽选出指定个数的元素

##########################################################################
删除定的key，随机删除key！

127 .0.0.1:6379> SMEMBERS myset
1 ) "lovekuangshen2"
2 ) "lovekuangshen"
3 ) "kuangshen"
127 .0.0.1:6379> spop myset                                       # 随机删除一些set集合中的元素！
"lovekuangshen2"
127 .0.0.1:6379> spop myset
"lovekuangshen"
127 .0.0.1:6379> SMEMBERS myset
1 ) "kuangshen"

##########################################################################
将一个指定的值，移动到另外一个set集合！
127 .0.0.1:6379> sadd myset "hello"
(integer) 1
127 .0.0.1:6379> sadd myset "world"
(integer) 1
127 .0.0.1:6379> sadd myset "kuangshen"
(integer) 1
127 .0.0.1:6379> sadd myset2 "set2"
(integer) 1
127 .0.0.1:6379> smove myset myset2 "kuangshen"                    # 将一个指定的值，移动到另外一个set集
合！
(integer) 1
127 .0.0.1:6379> SMEMBERS myset
1 ) "world"
2 ) "hello"
127 .0.0.1:6379> SMEMBERS myset2
1 ) "kuangshen"
2 ) "set2"

##########################################################################
微博，B站，共同关注！(并集)
数字集合类：

差集 SDIFF
交集
并集
127 .0.0.1:6379> SDIFF key1 key2                                  # 差集
1 ) "b"
2 ) "a"
127 .0.0.1:6379> SINTER key1 key2                                 # 交集 共同好友就可以这样实现
1 ) "c"
127 .0.0.1:6379> SUNION key1 key2                                 # 并集
1 ) "b"
2 ) "c"
3 ) "e"
4 ) "a"
微博，A用户将所有关注的人放在一个set集合中！将它的粉丝也放在一个集合中！

共同关注，共同爱好，二度好友，推荐好友！（六度分割理论）

```

## Zset
在set的基础上加一个zhi set key value ;Zset key score value

```
> zadd myzset 1 one                     #新增元素
(integer) 1
> zadd myzset 2 two 3 three             # 新增多个值
(integer) 2

> zrem myzset fiv                       #移除某个key
1
> zcard myzset                          #查询集合个数
4

> zadd salary 2000 xiaohong
(integer) 1
> zadd salary 3000 zhangsan
(integer) 1
> zadd salary 1000 lisi
(integer) 1
> zrangebyscore salary -inf +inf       #排序 从小到大
1) "lisi"
2) "xiaohong"
3) "zhangsan"

> zrevrange salary 0 -1                #排序 从大到小
1) "zhangsan"
2) "xiaohong"
3) "lisi"

> zrangebyscore salary -inf +inf withscores  #排序 从小到大
1) "lisi"
2) 1000.0
3) "xiaohong"
4) 2000.0
5) "zhangsan"
6) 3000.0

> zrangebyscore salary -inf 2000 withscores #排序 显示工资200以下的员工
1) "lisi"
2) 1000.0
3) "xiaohong"
4) 2000.0

> zcount myzset 1 3                             #获取指定区间个数
3
```



