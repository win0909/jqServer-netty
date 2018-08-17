/*
 * Copyright (C), 2015-2018
 * FileName: BasicServerImpl
 * Author:   zhao
 * Date:     2018/6/20 20:23
 * Description: BasicServerImpl
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.core;

import com.lizhaoblog.base.constant.ConstantValue;
import com.lizhaoblog.base.exception.RedisException;
import com.lizhaoblog.base.exception.ServerErrException;
import com.lizhaoblog.base.exception.XmlConfigReadException;
import com.lizhaoblog.base.factory.ServerChannelFactory;
import com.lizhaoblog.base.network.IServer;
import com.lizhaoblog.base.redis.Redis;
import com.lizhaoblog.base.xml.ConfigDataManager;
import com.lizhaoblog.server.core.listener.NetworkListener;
import com.lizhaoblog.server.pojo.ServerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 〈一句话功能简述〉<br>
 * 〈BasicServerImpl〉
 *
 * @author zhao
 * @date 2018/6/20 20:23
 * @since 1.0.0
 */
@Component
public class BasicServerImpl implements IServer {
  private Channel acceptorChannel;
  private static final Logger logger = LoggerFactory.getLogger(BasicServerImpl.class);

  @Override
  public void start() {
    try {
      init();
      ServerConfig.getInstance().printServerInfo();
      acceptorChannel.closeFuture().sync();
    } catch (XmlConfigReadException | RedisException | ServerErrException | InterruptedException e) {
      logger.debug("服务启动失败，程序即将退出", e);
      stop();
      System.exit(0);
    }
  }

  private void init() throws XmlConfigReadException, ServerErrException, RedisException {
    ServerConfig serverConfig = ServerConfig.getInstance();

    // 初始化xml-cfg工具类
    logger.info("初始化xml-cfg工具类  开始");
    serverConfig.printXmlCfgInfo();
    ConfigDataManager.getInstance()
            .loadXml(serverConfig.getCfgPackageName(), serverConfig.getCfgPrefix(), serverConfig.getCatalogDir(),
                    serverConfig.getCatalogFile(), serverConfig.getCatalogMainNode(),
                    serverConfig.getCatalogAttribute(), serverConfig.getXmlFileDir());
    logger.info("初始化xml-cfg工具类  结束");

    // 初始化redis工具类
    logger.info("redis工具类  开始");
    serverConfig.printRedisInfo();
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    //设置最大连接数（100个足够用了，没必要设置太大）
    poolConfig.setMaxTotal(serverConfig.getRedisPoolConfigMaxTotal());
    //最大空闲连接数
    poolConfig.setMaxIdle(serverConfig.getRedisPoolConfigMaxIdle());
    //获取Jedis连接的最大等待时间（50秒）
    poolConfig.setMaxWaitMillis(serverConfig.getRedisPoolConfigMaxWaitMillis());
    //在获取Jedis连接时，自动检验连接是否可用
    poolConfig.setTestOnBorrow(serverConfig.getRedisPoolConfigTestOnBorrow());
    //在将连接放回池中前，自动检验连接是否有效
    poolConfig.setTestOnReturn(serverConfig.getRedisPoolConfigTestOnReturn());
    //自动测试池中的空闲连接是否都是可用连接
    poolConfig.setTestWhileIdle(serverConfig.getRedisPoolConfigTestWhileIdle());
    Redis.getInstance().createJedisPool(poolConfig, serverConfig.getRedisHost(), serverConfig.getRedisPort(),
            serverConfig.getRedisTimeout(), serverConfig.getRedisPassword(), serverConfig.getRedisDatabaseIndex());
    // 测试线程池是否已经建立完毕
//    Redis.getInstance().testConnection();
    logger.info("redis工具类  结束");

    // 启动通讯服务
    logger.info("启动通讯服务  开始");
    Integer port = serverConfig.getPort();
    String channelType = serverConfig.getChannelType();
    String protocolType = serverConfig.getProtocolType();
    ChannelInitializer<SocketChannel> channelInitializer = null;
    switch (protocolType) {
      case ConstantValue.PROTOCOL_TYPE_TCP:
        channelInitializer = (ChannelInitializer<SocketChannel>) serverConfig.getApplicationContext()
                .getBean("tcpServerStringInitializer");
        break;
      case ConstantValue.PROTOCOL_TYPE_WEBSOCKET:
        channelInitializer = (ChannelInitializer<SocketChannel>) serverConfig.getApplicationContext()
                .getBean("webSocketChannelInitializer");
        break;
      default:
        throw new ServerErrException("ChannelInitializer is not defind");
    }
    acceptorChannel = ServerChannelFactory.createAcceptorChannel(port, channelType, channelInitializer);
    logger.info("启动通讯服务  结束");

  }

  @Override
  public void stop() {
    if (acceptorChannel != null) {
      acceptorChannel.close().addListener(ChannelFutureListener.CLOSE);
    }
  }

  @Override
  public void restart() throws Exception {
    stop();
    start();
  }
}