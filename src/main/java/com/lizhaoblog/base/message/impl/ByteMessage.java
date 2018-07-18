/*
 * Copyright (C), 2015-2018
 * FileName: ByteMessage
 * Author:   zhao
 * Date:     2018/7/17 15:58
 * Description: 传输byte的消息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.message.impl;

import com.lizhaoblog.base.message.IMessage;
import com.lizhaoblog.base.util.ByteUtil;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br>
 * 〈传输byte的消息〉
 *
 * @author zhao
 * @date 2018/7/17 15:58
 * @since 1.0.1
 */
public class ByteMessage extends BaseMessage implements IMessage {

  /**
   * 具体内容
   */
  private byte[] body = new byte[0];

  public static ByteMessage create(short messageId) {
    ByteMessage byteMessage = new ByteMessage();
    byteMessage.setMessageId(messageId);
    return byteMessage;
  }

  public void addAttr(int result) {
    byte[] tmpBytes = ByteUtil.intToBytes4(result);
    body =  ByteUtil.concat(body, tmpBytes);
  }

  public void addAttr(short result) {
    byte[] tmpBytes = ByteUtil.shortToByte(result);
    ByteUtil.concat(body, tmpBytes);
  }

  public void addAttr(float result) {
    byte[] tmpBytes = ByteUtil.float2byte(result);
    ByteUtil.concat(body, tmpBytes);
  }

  public void addAttr(long result) {
    byte[] tmpBytes = ByteUtil.longToBytes(result);
    ByteUtil.concat(body, tmpBytes);
  }

  public void addAttr(String result) {
    byte[] tmpBytes = ByteUtil.stringToBytes(result);
    addAttr(tmpBytes.length);
    ByteUtil.concat(body, tmpBytes);
  }

  public byte[] getBody() {
    return body;
  }

  public void setBody(byte[] body) {
    this.body = body;
  }

  @Override
  public byte[] getBodyByte() {
    return body;
  }

  @Override
  public void setBodyByte(byte[] body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return "ByteMessage{" + " messageId=" + messageId + ", statusCode=" + statusCode + ", length=" + length + ",body="
            + Arrays.toString(body) + '}';
  }
}
