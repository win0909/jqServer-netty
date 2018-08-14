/*
 * Copyright (C), 2015-2018
 * FileName: EasyWsTest
 * Author:   zhao
 * Date:     2018/8/14 11:08
 * Description: EasyWs的测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.demopro.websocket;

import com.lizhaoblog.demopro.websocket.client.EasyWsClient;
import com.lizhaoblog.demopro.websocket.server.EasyWsServer;

import org.junit.Test;

/**
 * 〈一句话功能简述〉<br>
 * 〈EasyWs的测试类〉
 *
 * @author zhao
 * @date 2018/8/14 11:08
 * @since 1.0.1
 */
public class EasyWsTest {

  private static final String IP = "127.0.0.1";
  private static final int PORT = 8088;

  @Test
  public void startServer() throws Exception {
    EasyWsServer easyWsServer = new EasyWsServer(PORT);
    easyWsServer.start();
  }

  @Test
  public void startClient() throws Exception {
    EasyWsClient easyWsClient = new EasyWsClient(IP, PORT);
    easyWsClient.run();
  }
}
