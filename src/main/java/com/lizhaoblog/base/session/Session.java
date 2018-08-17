/*
 * Copyright (C), 2015-2018
 * FileName: Session
 * Author:   zhao
 * Date:     2018/6/22 15:44
 * Description: Session会话
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.session;

import com.lizhaoblog.base.util.TimeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import io.netty.channel.Channel;

/**
 * 〈一句话功能简述〉<br>
 * 〈Session会话，包括管道及用户信息〉
 * 里面的所有信息都需要交由SessionManager操作
 * 外部无法直接访问到这个类的方法
 *
 * @author zhao
 * @date 2018/6/22 15:44
 * @since 1.0.0
 */
public class Session {

  private static final Logger logger = LoggerFactory.getLogger(Session.class);

  /**
   * 与客户端的管道
   */
  private final Channel channel;
  /**
   * 用户信息
   * 用户信息可能为空。
   * 只有在register(登录)之后，里面才会赋值
   */
  private IUser user;
  /**
   * 创建时间
   */
  private final long createTime;

  private final HashMap<String, Object> hashMap;

  Session(Channel channel) {
    this.channel = channel;
    this.createTime = TimeUtil.getSysCurTimeMillis();
    hashMap = new HashMap<>();
  }

  /**
   * 玩session里面写入user，一般是在登录之后调用
   *
   * @param user 用户 信息
   */
  void registerUser(IUser user) {
    this.user = user;
  }

  /**
   * 关闭与客户端的连接
   */
  void close() {
    if (channel == null) {
      return;
    }
    try {
      if (channel.isActive() || channel.isOpen()) {
        channel.close().sync();
      }
    } catch (InterruptedException e) {
      logger.error("channel.close find error ", e);
    }

  }

  IUser getUser() {
    return user;
  }

  Channel getChannel() {
    return channel;
  }

  void put(String key, Object value) {
    hashMap.put(key, value);
  }

  Object getByKey(String key) {
    return hashMap.getOrDefault(key, null);
  }

}