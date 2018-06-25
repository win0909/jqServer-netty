/*
 * Copyright (C), 2015-2018
 * FileName: AbstractHandler
 * Author:   zhao
 * Date:     2018/6/25 15:54
 * Description: 继承ICommand，将一些通用的处理过程写在里面
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.concurrent.handler;

import com.lizhaoblog.base.concurrent.commond.IHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈继承ICommand，将一些通用的处理过程写在里面〉
 *
 * @author zhao
 * @date 2018/6/25 15:54
 * @since 1.0.0
 */
public abstract class AbstractHandler<T, V> implements IHandler<T, V> {
  protected T message;

  protected V param;

  /**
   * 执行具体的操作，交由子类实现
   */
  @Override
  public abstract void doAction();

  @Override
  public void run() {
    doAction();
  }

  @Override
  public T getMessage() {
    return message;
  }

  @Override
  public void setMessage(T message) {
    this.message = message;
  }

  @Override
  public V getParam() {
    return param;
  }

  @Override
  public void setParam(V param) {
    this.param = param;
  }

}