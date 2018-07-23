/*
 * Copyright (C), 2015-2018
 * FileName: LogicProcessor
 * Author:   zhao
 * Date:     2018/6/25 16:57
 * Description: 具体的消息处理器，进程
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.core.processor;

import com.lizhaoblog.base.concurrent.commond.IHandler;
import com.lizhaoblog.base.network.processor.IProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈具体的消息处理器，进程〉
 *
 * @author zhao
 * @date 2018/6/25 16:57
 * @since 1.0.0
 */
public class LogicProcessor implements IProcessor {

  private ExecutorService executor = new ThreadPoolExecutor(8, 8, 0L, TimeUnit.MILLISECONDS,
          new LinkedBlockingQueue<>(100000), new ThreadPoolExecutor.CallerRunsPolicy());

  @Override
  public void process(IHandler handler) {
    this.executor.execute(handler);
  }

}