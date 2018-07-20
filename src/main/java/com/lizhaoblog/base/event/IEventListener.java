/*
 * Copyright (C), 2015-2018
 * FileName: IEventListener
 * Author:   zhao
 * Date:     2018/7/20 11:07
 * Description: Event的基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.event;

/**
 * 〈一句话功能简述〉<br>
 * 〈Event的基类〉
 *
 * @author zhao
 * @date 2018/7/20 11:07
 * @since 1.0.1
 */
public interface IEventListener {

  /**
   * 事件的具体执行
   * 可以传入参数
   * @param param param
   */
  void execute(Object param);
}
