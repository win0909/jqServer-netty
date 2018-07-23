/*
 * Copyright (C), 2015-2018
 * FileName: ICommand
 * Author:   zhao
 * Date:     2018/6/25 15:37
 * Description: 继承runable接口，可以放在线程池中执行
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.concurrent.commond;

/**
 * 〈一句话功能简述〉<br>
 * 〈继承Runnable接口，可以放在线程池中执行〉
 *
 * @author zhao
 * @date 2018/6/25 15:37
 * @since 1.0.0
 */
public interface ICommand extends Runnable {
  /**
   * 执行具体的方法
   */
  void doAction();

  /**
   * 运行
   */
  @Override
  default void run() {
    doAction();
  }
}