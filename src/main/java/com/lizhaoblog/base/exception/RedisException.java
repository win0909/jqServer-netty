/*
 * Copyright (C), 2015-2018
 * FileName: RedisException
 * Author:   zhao
 * Date:     2018/8/7 17:01
 * Description: redis工具类抛出来的异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.exception;

/**
 * 〈一句话功能简述〉<br>
 * 〈redis工具类抛出来的异常〉
 *
 * @author zhao
 * @date 2018/8/7 17:01
 * @since 1.0.1
 */
public class RedisException extends Exception {
  private String errMsg;

  public RedisException(String errMsg) {
    super(errMsg);
    this.errMsg = errMsg;
  }

  public RedisException(Throwable cause) {
    super(cause);
  }
}
