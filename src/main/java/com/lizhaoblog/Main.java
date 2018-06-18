/**
 * Copyright (C), 2015-2018
 * FileName: Main
 * Author:   zhao
 * Date:     2018/6/9 15:36
 * Description: 主入口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog;

import com.lizhaoblog.base.factory.ServerChannelFactory;
import com.lizhaoblog.server.pojo.ServerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import io.netty.channel.Channel;

/**
 * 〈一句话功能简述〉<br>
 * 〈主入口〉
 *
 * @author zhao
 * @date 2018/6/9
 * @since 1.0.0
 */
public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  static {
    // 先加载spring
    logger.info("准备载入spring...");
    ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:spring/ApplicationContext.xml");
    ServerConfig.getInstance().setApplicationContext(applicationContext);
    logger.info("载入spring 完毕...");

  }

  public static void main(String[] args) throws Exception {
    logger.info("开始启动Socket服务器...");

    //基本的netty启动
    //    new SocketServer().run()
    Channel acceptorChannel = ServerChannelFactory.createAcceptorChannel();
    acceptorChannel.closeFuture().sync();

  }
}