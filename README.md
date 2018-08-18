# JgServer

#### 项目介绍
JgServer，使用java语言开发，基于Netty、Spring、Mybatis和Redis等框架开发的服务端容器，支持Tcp，Socket，WebSocket(SSL)，HTTP(S)。
支持对各种通讯协议进行定制，可以用于开发游戏后端(意为JavaGameServer，最初用于开发Java游戏服务器的一套完整架构)。
项目代码简洁，注释丰富，上手容易，扩展方便。
项目使用框架：Spring，Netty，Mybatis，Redis，junit，log4j等。
已包含一些常规的工具：消息处理(包括json字符串、二进制byte消息)、事件机制、心跳检测、日志管理、定时任务、持久层框架、数据库连接池等。

#### 源码框架
    D:.
    ├─.idea
    │  └─inspectionProfiles
    ├─etc														脚本文件
    │  └─sql														
    ├─logs														日志
    ├─src
    │  ├─main
    │  │  ├─java
    │  │  │  ├─com
    │  │  │  │  └─lizhaoblog
    │  │  │  │      ├─base										基本的库
    │  │  │  │      │  ├─concurrent								并发包
    │  │  │  │      │  │  ├─commond
    │  │  │  │      │  │  ├─dictionary
    │  │  │  │      │  │  └─handler
    │  │  │  │      │  ├─constant								常量
    │  │  │  │      │  ├─event								    事件
    │  │  │  │      │  ├─exception								异常	
    │  │  │  │      │  ├─factory								工厂
    │  │  │  │      │  ├─message								消息
    │  │  │  │      │  ├─mybatis								数据库工具
    │  │  │  │      │  ├─network								网络相关	
    │  │  │  │      │  │  ├─customer							网络消费者
    │  │  │  │      │  │  ├─listener							网络监听器
    │  │  │  │      │  │  └─processor							线程处理器
    │  │  │  │      │  ├─redis								  redis内存数据库	
    │  │  │  │      │  ├─session								会话管理	
    │  │  │  │      │  ├─util									工具类
    │  │  │  │      │  └─xml									xml配置文件
    │  │  │  │      ├─demopro									测试代码
    │  │  │  │      │  └─net
    │  │  │  │      └─server									主包
    │  │  │  │          ├─biz									业务代码			
    │  │  │  │          │  ├─constant							常量
    │  │  │  │          │  ├─dao								数据库
    │  │  │  │          │  │  └─mysql
    │  │  │  │          │  ├─dictionary							消息字典
    │  │  │  │          │  ├─entity								实体类	
    │  │  │  │          │  │  └─cfg             xml配置文件对应的实体类
    │  │  │  │          │  ├─event							具体的事件类
    │  │  │  │          │  ├─handler							控制器
    │  │  │  │          │  └─scheduled							定时任务
    │  │  │  │          │  └─services							业务处理器
    │  │  │  │          │      └─impl
    │  │  │  │          ├─channel								各种协议存放
    │  │  │  │          │  └─tcp								tcp协议
    │  │  │  │          │      └─str
    │  │  │  │          ├─core									服务核心包
    │  │  │  │          │  ├─customer							消费者
    │  │  │  │          │  ├─listener
    │  │  │  │          │  └─processor
    │  │  │  │          └─pojo									配置文件
    │  │  │  └─org
    │  │  │      └─apache
    │  │  │          └─ibatis
    │  │  │              └─builder
    │  │  │                  └─annotation						重写mybatis中的
    │  │  └─resources											资源文件
    │  │      ├─cfg                     cfg的配置文件
    │  │      │  └─data-static-config  cfg的实体类
    │  │      ├─mybatis
    │  │      │  └─jpa
    │  │      ├─properties										
    │  │      └─spring
    │  └─test													测试代码		
    │      └─java
    │          └─com
    │              └─lizhaoblog
    │                  ├─common
    │                  ├─demopro
    │                  │  └─net
    │                  ├─javase
    │                  └─server									服务测试
    │                      └─channel
    │                          └─tcp
    │                              └─str
    └─target

#### 软件框架
1. 使用框架：Spring，Netty，Mybatis，Redis，junit，log4j等。
2. 编译器：IDEA
3. JDK版本：1.8

#### 配置文件
  1. db-config-dev.properties：数据库配置文件
  2. server-config-dev.properties：服务器属性
  3. etc--sql--jg_server.sql：数据库脚本
  4. redis-config-dev.properties：redis数据库属性
  5. xml-cfg-config-dev.properties：xml-cfg配置属性

#### 运行前提
1. server-config-dev.properties：服务器属性 需要修改
    - protocolType=TCP
    - protocolType=WEBSOCKET
2. db-config-dev.properties：数据库配置文件

#### 安装教程
  1. 在编译器中直接运行com.lizhaoblog.Main
      1. 编译器中打开
      2. 按照自己的需要修改配置文件(上条)
      3. 运行com.lizhaoblog.Main函数
      4. 测试：运行com.lizhaoblog.server.channel.NettyClientTest的对应方法
      - 这时候pom.xml文件中的build--resources--resource标签下的filtering/targetPath，将targetPath行注释掉
            
  2. jar启动
  	  1. 打包
          -	使用maven打包，然后使用命令行的方式运行，这里包括把资源配置打到一个jar包和 将资源配置单独打包出来的方式
          -	控制的开关是pom.xml文件中的build--resources--resource标签下的filtering/targetPath
          -	如果想要将资源文件打包到一个jar包中的话，就把targetPath注释掉
          -	反之则打开targetPath，注释掉filtering
  	  2. 运行
          -	使用 "java -jar xxx.jar"方式启动
          - original-all-in-one-1.0-SNAPSHOT.jar	这个是只有我们的项目，需要依赖同级目录下的lib
          - all-in-one-1.0-SNAPSHOT.jar		这个是包括lib在内的

   
#### 详细说明

1. 同步更新，地址：Java游戏服务器开发，https://blog.csdn.net/cmqwan/article/details/80858272
2. 可以加qq群一起探讨Java游戏服务器开发的相关知识	676231564

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request
