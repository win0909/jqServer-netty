/*
 * Copyright (C), 2015-2018
 * FileName: IProcessor
 * Author:   zhao
 * Date:     2018/6/25 15:34
 * Description: 进程接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.network.processor;

import com.lizhaoblog.base.concurrent.commond.IHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈进程接口〉
 *
 * @author zhao
 * @date 2018/6/25 15:34
 * @since 1.0.0
 */
public interface IProcessor {
  /**
   * 执行具体的指令
   * @param handler 具体的执行
   */
  void process(IHandler handler);

}