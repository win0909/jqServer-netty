/**
 * Copyright (C), 2015-2018
 * FileName: Main
 * Author:   zhao
 * Date:     2018/6/9 15:36
 * Description: 主入口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaobolg;

import com.lizhaobolg.net.SocketServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br>
 * 〈主入口〉
 *
 * @author zhao
 * @date 2018/6/9
 * @since 1.0.0
 */
public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) throws Exception {
    logger.info("开始启动Socket服务器...");
    new SocketServer().run();

  }
}