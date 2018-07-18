/*
 * Copyright (C), 2015-2018
 * FileName: MessageFactory
 * Author:   zhao
 * Date:     2018/7/18 14:58
 * Description: 用于生成message
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.message.impl;

import com.lizhaoblog.base.constant.ConstantValue;
import com.lizhaoblog.base.message.IMessage;
import com.lizhaoblog.server.pojo.ServerConfig;

/**
 * 〈一句话功能简述〉<br>
 * 〈用于生成message〉
 *
 * @author zhao
 * @date 2018/7/18 14:58
 * @since 1.0.1
 */
public class MessageFactory {

  /**
   * 返回一个与配置相关的message
   *
   * @return IMessage的子类
   */
  public static IMessage create() {
    IMessage iMessage = null;
    String messageType = ServerConfig.getInstance().getMessageType();
    switch (messageType) {
      case ConstantValue.MESSAGE_TYPE_STRING:
        iMessage = new StringMessage();
        break;
      case ConstantValue.MESSAGE_TYPE_BYTE:
        iMessage = new ByteMessage();
        break;
      default:
        break;
    }
    return iMessage;
  }

}
