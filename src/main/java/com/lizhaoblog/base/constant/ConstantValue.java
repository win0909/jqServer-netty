/*
 * Copyright (C), 2015-2018
 * FileName: ConstantValue
 * Author:   zhao
 * Date:     2018/6/12 11:01
 * Description: 静态数据类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.constant;

/**
 * 〈一句话功能简述〉<br>
 * 〈静态数据类〉
 *
 * @author zhao
 * @date 2018/6/12
 * @since 1.0.0
 */
public class ConstantValue {
  public static final String CHANNEL_TYPE_NIO = "NIO";
  public static final String CHANNEL_TYPE_OIO = "OIO";

  public static final String PROTOCOL_TYPE_HTTP = "HTTP";
  public static final String PROTOCOL_TYPE_HTTPS = "HTTPS";
  public static final String PROTOCOL_TYPE_TCP = "TCP";
  public static final String PROTOCOL_TYPE_CUSTOM = "CUSTOM";
  public static final String PROTOCOL_TYPE_WEBSOCKET = "WEBSOCKET";

  public static final String PROJECT_CHARSET = "UTF-8";


  /**
   * 登录和下线队列
   */
  public static final int QUEUE_LOGIN_LOGOUT = 1;

  /**
   * 业务队列
   */
  public static final int QUEUE_LOGIC = 2;

  private ConstantValue() {
  }

}