/*
 * Copyright (C), 2015-2018
 * FileName: MessageCodecException
 * Author:   zhao
 * Date:     2018/7/18 11:25
 * Description: 消息转换途中出现错误
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈消息转换途中出现错误〉
 *
 * @author zhao
 * @date 2018/7/18 11:25
 * @since 1.0.1
 */
public class MessageCodecException extends Exception{
  private String errMsg;

  public MessageCodecException(String errMsg) {
    super(errMsg);
    this.errMsg = errMsg;
  }

  public MessageCodecException(Throwable cause) {
    super(cause);
  }

}
