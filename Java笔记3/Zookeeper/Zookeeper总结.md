**top**
* [1.概述](#1概述)
* [2.crosssAPI.getContact相关方法](#crosssAPI相关方法)
* [3.替换宏变量](#替换宏变量)
* [4.加载静态资源](#加载静态资源)

## 1概述
	Zookeeper是一个开源的分布式的，为分布式应用提供协调服务的Apache项目。
	Zookeeper从设计模式角度来理解：是一个基于观察者模式设计的分布式服务管理框架，它负责存储和管理大家都关心的数据，然后接受观察者的注册，一旦这些数据的状态发生变化，Zookeeper就将负责通知已经在Zookeeper上注册的那些观察者做出相应的反应，从而实现集群中类似Master/Slave管理模式
	Zookeeper=文件系统+通知机制

[应用场景](https://github.com/smwcomeon/notes/blob/master/Java%E7%AC%94%E8%AE%B03/Zookeeper/image/%E6%95%B0%E6%8D%AE%E5%8F%91%E5%B8%83%E4%B8%8E%E8%AE%A2%E9%98%85.png)
