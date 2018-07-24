/*
 * Copyright (C), 2015-2018
 * FileName: XmlConfigReadException
 * Author:   zhao
 * Date:     2018/7/24 16:15
 * Description: 读取配置文件时出现错误
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈读取配置文件时出现错误〉
 *
 * @author zhao
 * @date 2018/7/24 16:15
 * @since 1.0.1
 */
public class XmlConfigReadException extends Exception {
  private String errMsg;

  public XmlConfigReadException(String errMsg) {
    super(errMsg);
    this.errMsg = errMsg;
  }

  public XmlConfigReadException(Throwable cause) {
    super(cause);
  }

}
