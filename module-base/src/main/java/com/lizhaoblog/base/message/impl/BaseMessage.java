/*
 * Copyright (C), 2015-2018
 * FileName: BaseMessage
 * Author:   zhao
 * Date:     2018/7/18 10:56
 * Description: message的基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.message.impl;

import com.lizhaoblog.base.message.IMessage;

/**
 * 〈一句话功能简述〉<br>
 * 〈message的基类〉
 *
 * @author zhao
 * @date 2018/7/18 10:56
 * @since 1.0.1
 */
public abstract class BaseMessage implements IMessage {
  /**
   * 消息来源的flag
   */
  protected short flag;
  /**
   * 消息号
   */
  protected short messageId;
  /**
   * 状态码
   */
  protected short statusCode;
  /**
   * 内容长度
   */
  protected int length;

  @Override
  public short getFlag() {
    return flag;
  }

  @Override
  public void setFlag(short flag) {
    this.flag = flag;
  }

  @Override
  public short getMessageId() {
    return messageId;
  }

  @Override
  public void setMessageId(short messageId) {
    this.messageId = messageId;
  }

  @Override
  public short getStatusCode() {
    return statusCode;
  }

  @Override
  public void setStatusCode(short statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public int getLength() {
    return length;
  }

  @Override
  public void setLength(int length) {
    this.length = length;
  }

}
