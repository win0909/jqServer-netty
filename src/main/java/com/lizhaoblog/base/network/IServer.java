/*
 * Copyright (C), 2015-2018
 * FileName: IServer
 * Author:   zhao
 * Date:     2018/6/20 20:20
 * Description: Server服务核心
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.network;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Server服务核心〉
 *
 * @author zhao
 * @date 2018/6/20 20:20
 * @since 1.0.0
 */
public interface IServer {
  /**
   * 服务启动
   * @throws Exception 启动时异常
   */
  void start() throws Exception;

  /**
   * 服务关闭
   * @throws Exception 关闭异常
   */
  void stop() throws Exception;

  /**
   * 服务重启
   * @throws Exception 重启时异常
   */
  void restart() throws Exception;
}