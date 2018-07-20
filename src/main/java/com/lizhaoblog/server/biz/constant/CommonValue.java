/*
 * Copyright (C), 2015-2018
 * FileName: CommonValue
 * Author:   zhao
 * Date:     2018/6/25 16:45
 * Description: 放置常量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.constant;

/**
 * 〈一句话功能简述〉<br>
 * 〈放置常量〉
 *
 * @author zhao
 * @date 2018/6/25 16:45
 * @since 1.0.0
 */
public class CommonValue {

  /***************************************通讯状态码**********************************************/
  public static final short MSG_STATUS_CODE_SUCCESS = 1;
  public static final short MSG_STATUS_CODE_FAIL = 900;

  /***************************************通讯消息号 客户端发送至服务端**********************************************/
  public static final short CM_MSG_TEST = 10001;
  public static final short CM_MSG_TEST_BYTE = 10002;

  /***************************************事件消息号**********************************************/
  public static final int EVENT_TYPE_LOGIN = 1001;
  public static final int EVENT_TYPE_LOGOUT = 1002;

  private CommonValue() {
  }
}