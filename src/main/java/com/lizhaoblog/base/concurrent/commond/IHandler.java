/*
 * Copyright (C), 2015-2018
 * FileName: IHandler
 * Author:   zhao
 * Date:     2018/6/25 15:35
 * Description: 队列执行器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.concurrent.commond;

/**
 * 〈一句话功能简述〉<br>
 * 〈队列执行器〉
 *
 * @author zhao
 * @date 2018/6/25 15:35
 * @since 1.0.0
 */
public interface IHandler<T,V> extends ICommand {

  /**
   * 消息
   * @return 消息request
   */
  T getMessage();

  /**
   * 设置消息request
   * @param message request
   */
  void setMessage(T message);

  /**
   * 参数
   * @return 参数
   */
  V getParam();

  /**
   *  设置参数
   * @param parm 参数
   */
  void setParam(V parm);

}