### 一、SSM优缺点应该分开来说的，比如
1）spring 不说了，核心ioc、aop技术，ioc解耦，使得代码复用，可维护性大幅度提升，aop提供切面编程，同样的增强了生产力。
2）spring mvc，是对比struts2等mvc框架来说的，不说struts2爆出的那么多安全漏洞，而且是类拦截，所有Action变量共享，同时是filter入口的，而spring mvc是方法拦截，controller独享request response数据，采用的serlvet入口，与spring无缝对接。开发而言，spring mvc更加轻量和低入门。
3）mybatis嘛，看业务场景，主要是mybatis的sql可以由开发者去掌控和调优，相对hibernate等orm框架来说，更加直观。在业务场景比较复杂，sql好多联合关联的情况下，mybatis谁用谁知道。当然缺点就是对sql不熟悉的开发者就不太友好了。
二、 SSM框架和spring boot全家桶相比有哪些优缺点？
这两者对比起来有点奇怪。因为SSM是WEB应用框架，涵盖整个应用层，而spring boot你可以看做一个启动、配置、快速开发的辅助框架，本身针对的是微服务。
springboot 只是为了提高开发效率，是为了提升生产力的：
1、springboot一个应用是一个可执行jar（启动类main方法启动web应用），而不像传统的war，内嵌tomcat容器，可以jar形式启动一个服务，可以快速部署发布web服务，微服务最好不过了。
2、将原有的xml配置，简化为java配置
3、当然结构可能跟一般的ssm有一定区别，但其实主要是在资源文件。
Spring Boot 默认“约定”从资源目录的这些子目录读取静态资源：
	• src/main/resources/META-INF/resources
	• src/main/resources/static （推荐）
	• src/main/resources/public



--------------------------------------------------------------------

一、SSM优缺点应该分开来说的，比如
1）spring 不说了，核心ioc、aop技术，ioc解耦，使得代码复用，可维护性大幅度提升，aop提供切面编程，同样的增强了生产力。
2）spring mvc，是对比struts2等mvc框架来说的，不说struts2爆出的那么多安全漏洞，而且是类拦截，所有Action变量共享，同时是filter入口的，而spring mvc是方法拦截，controller独享request response数据，采用的serlvet入口，与spring无缝对接。开发而言，spring mvc更加轻量和低入门。
3）mybatis嘛，看业务场景，主要是mybatis的sql可以由开发者去掌控和调优，相对hibernate等orm框架来说，更加直观。在业务场景比较复杂，sql好多联合关联的情况下，mybatis谁用谁知道。当然缺点就是对sql不熟悉的开发者就不太友好了。
二、 SSM框架和spring boot全家桶相比有哪些优缺点？
这两者对比起来有点奇怪。因为SSM是WEB应用框架，涵盖整个应用层，而spring boot你可以看做一个启动、配置、快速开发的辅助框架，本身针对的是微服务。
springboot 只是为了提高开发效率，是为了提升生产力的：
1、springboot一个应用是一个可执行jar（启动类main方法启动web应用），而不像传统的war，内嵌tomcat容器，可以jar形式启动一个服务，可以快速部署发布web服务，微服务最好不过了。
2、将原有的xml配置，简化为java配置
3、当然结构可能跟一般的ssm有一定区别，但其实主要是在资源文件。
Spring Boot 默认“约定”从资源目录的这些子目录读取静态资源：
	• src/main/resources/META-INF/resources
	• src/main/resources/static （推荐）
	• src/main/resources/public



