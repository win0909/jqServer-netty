/**
 * Copyright (C), 2015-2018
 * FileName: ServerErrException
 * Author:   zhao
 * Date:     2018/6/12 14:36
 * Description: 服务启动错误
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaobolg.base.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈服务启动错误〉
 *
 * @author zhao
 * @date 2018/6/12 14:36
 * @since 1.0.0
 */
public class ServerErrException extends Exception {
  private String errMsg;

  public ServerErrException(String errMsg) {
    super(errMsg);
    this.errMsg = errMsg;
  }

  public ServerErrException(Throwable cause) {
    super(cause);
  }
}