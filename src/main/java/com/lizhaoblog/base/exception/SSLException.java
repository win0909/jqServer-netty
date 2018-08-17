/*
 * Copyright (C), 2015-2018
 * FileName: SSLException
 * Author:   zhao
 * Date:     2018/8/17 18:04
 * Description: 添加ssl时发生的错误
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈添加ssl时发生的错误〉
 *
 * @author zhao
 * @date 2018/8/17 18:04
 * @since 1.0.1
 */
public class SSLException extends Exception {
  private String errMsg;

  public SSLException(String errMsg) {
    super(errMsg);
    this.errMsg = errMsg;
  }

  public SSLException(Throwable cause) {
    super(cause);
  }

}

