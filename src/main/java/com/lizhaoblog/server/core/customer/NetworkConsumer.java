/**
 * Copyright (C), 2015-2018
 * FileName: INetworkConsumer
 * Author:   zhao
 * Date:     2018/6/23 21:06
 * Description: 网络消息处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.core.customer;

import com.lizhaoblog.base.network.customer.INetworkConsumer;
import com.lizhaoblog.base.session.SessionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.netty.channel.Channel;

/**
 * 〈一句话功能简述〉<br>
 * 〈网络消息处理器,实现类〉
 *
 * @author zhao
 * @date 2018/6/23 21:09
 * @since 1.0.0
 */
@Service
public class NetworkConsumer implements INetworkConsumer {
  private static final Logger logger = LoggerFactory.getLogger(NetworkConsumer.class);

  @Override
  public void consume(String message, Channel channel) {
    logger.info("服务器收到的数据内容：data=" + message);
    String result = "小李，我是服务器，我收到你的信息了。";
    SessionManager.getInstance().sendMessage(channel, result);
  }
}