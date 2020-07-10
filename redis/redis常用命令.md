## Hash类型

```
> hset map key1 v1                       #设置一个key value
(integer) 1

> hmset map key4 v4 key5 v5             #设置多个key value
OK

> hset map key2 value2
(integer) 1
> hget map key2                          #获取map中指定的key
"value2"

> hgetall map                            #获取map中所有的key value
1) "key1"
2) "v1"
3) "key2"
4) "value2"

> hdel map key3                          #删除map中指定的key 对应的value也删除
(integer) 1
> hgetall map
1) "key1"
2) "v1"
3) "key2"
4) "value2"

> hlen map                                #获取map的size 
2

> hexists map k1                           # 判断map是否存在k1
(integer) 1     

> hexists map k12
(integer) 0

> hkeys map                                #获取map中所有的key
1) "key1"
2) "key2"
3) "k1"
> hvals map                                 #获取map中所有的value
1) "v1"
2) "value2"
3) "v3"

> hset map key 3
(integer) 1
> hincrby map key 1                        #map中value 自增
(integer) 4
> hincrby map key -1
(integer) 3

> hsetnx map key v                        #map新增 key value 如果key不存在新增 如何key存在不做任何操作
(integer) 0
> hsetnx map key11 v
(integer) 1
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

```bash
