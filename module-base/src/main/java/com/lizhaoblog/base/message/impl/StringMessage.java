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
package com.lizhaoblog.base.message.impl;

import com.lizhaoblog.base.constant.ConstantValue;
import com.lizhaoblog.base.exception.MessageCodecException;
import com.lizhaoblog.base.message.IMessage;
import com.lizhaoblog.base.util.GsonUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 〈一句话功能简述〉<br>
 * 〈String类型的请求〉
 *
 * @author zhao
 * @date 2018/7/12 10:55
 * @since 1.0.1
 */
public class StringMessage extends BaseMessage implements IMessage {

  /**
   * 具体内容
   */
  private String body;

  public StringMessage() {
  }

  public StringMessage(short messageId) {
    this.messageId = messageId;
  }


  public static StringMessage create(String origin) {
    StringMessage stringMessage = GsonUtil.fromJson(origin, StringMessage.class);
    return stringMessage;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public byte[] getBodyByte() {
    return body.getBytes(Charset.forName(ConstantValue.PROJECT_CHARSET));
  }

  @Override
  public void setBodyByte(byte[] body) throws MessageCodecException {
    try {
      this.body = new String(body, ConstantValue.PROJECT_CHARSET);
    } catch (UnsupportedEncodingException e) {
      throw new MessageCodecException("setBodyByte UnsupportedEncodingException");
    }
  }

  @Override
  public String toString() {
    return "StringMessage{" + "messageId=" + messageId + ", statusCode=" + statusCode + ", length=" + length
            + ", body='" + body + '\'' + '}';
  }
}
