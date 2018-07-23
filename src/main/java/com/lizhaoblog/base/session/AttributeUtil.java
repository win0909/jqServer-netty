/*
 * Copyright (C), 2015-2018
 * FileName: AttributeUtil
 * Author:   zhao
 * Date:     2018/6/22 16:15
 * Description: Attribute操作的工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.session;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * 〈一句话功能简述〉<br>
 * 〈Attribute操作的工具类〉
 *
 * @author zhao
 * @date 2018/6/22 16:15
 * @since 1.0.0
 */
public class AttributeUtil {

  /**
   * 获取绑定到channel上面的数据
   *
   * @param channel 与客户端连接的管道
   * @param key     {@link SessionAttributeKey}
   * @param <T>     绑定的类型
   * @return  绑定到channel上面的数据
   */
  public static <T> T get(Channel channel, AttributeKey<T> key) {
    return channel.attr(key).get();
  }

  /**
   * 绑定某个数据到channel
   *
   * @param channel 与客户端连接的管道
   * @param key     {@link SessionAttributeKey}
   * @param value   绑定的内容
   * @param <T> 绑定的类型
   */
  public static <T> void set(Channel channel, AttributeKey<T> key, T value) {
    channel.attr(key).set(value);
  }

  private AttributeUtil() {
  }
}