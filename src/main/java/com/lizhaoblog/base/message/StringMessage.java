/*
 * Copyright (C), 2015-2018
 * FileName: StringMessage
 * Author:   zhao
 * Date:     2018/7/12 10:55
 * Description: 发送过来的请求
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.message;

import com.lizhaoblog.base.util.GsonUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈String类型的请求〉
 *
 * @author zhao
 * @date 2018/7/12 10:55
 * @since 1.0.1
 */
public class StringMessage {

  /**
   * 消息号
   */
  private int messageId;
  /**
   * 状态码
   */
  private int statusCode;
  /**
   * 内容长度
   */
  private int length;
  /**
   * 具体内容
   */
  private String body;

  public StringMessage() {
  }

  public static StringMessage create(int messageId) {
    StringMessage stringMessage = new StringMessage();
    stringMessage.setMessageId(messageId);
    return stringMessage;
  }

  public static StringMessage create(String origin) {
    StringMessage stringMessage = GsonUtil.fromJson(origin, StringMessage.class);
    return stringMessage;
  }

  public static StringMessage create(int length, int messageId, int statusCode, String content) {
    return new StringMessage(length, messageId, statusCode, content);
  }

  private StringMessage(int length, int messageId, int statusCode, String body) {
    this.length = length;
    this.messageId = messageId;
    this.statusCode = statusCode;
    this.body = body;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getMessageId() {
    return messageId;
  }

  public void setMessageId(int messageId) {
    this.messageId = messageId;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return "StringMessage{" + "messageId=" + messageId + ", statusCode=" + statusCode + ", length=" + length
            + ", body='" + body + '\'' + '}';
  }
}
