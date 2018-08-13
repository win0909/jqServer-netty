/*
 * Copyright (C), 2015-2018
 * FileName: NettyClientTest
 * Author:   zhao
 * Date:     2018/6/12 15:34
 * Description: NettyClient的测试数据
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.channel;

import com.lizhaoblog.server.channel.tcp.bytec.TcpByteClientTest;
import com.lizhaoblog.server.channel.tcp.str.TcpStringClientTest;
import com.lizhaoblog.server.channel.websocket.WebSocketClientTest;
import com.lizhaoblog.server.pojo.ServerConfig;

import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

/**
 * 〈一句话功能简述〉<br>
 * 〈NettyClient的测试数据〉
 *
 * @author zhao
 * @date 2018/6/12 15:34
 * @since 1.0.0
 */
public class NettyClientTest {
  @Before
  public void init() {
    ServerConfig.getInstance().init();
    ServerConfig.getInstance().setMessageType("STRING");
  }

  @Test
  public void testTcpStringClient() throws InterruptedException {
    TcpStringClientTest tcpStringClientTest = new TcpStringClientTest();
    tcpStringClientTest.run();
  }

  @Test
  public void testTcpByteClient() throws InterruptedException {
    ServerConfig.getInstance().setMessageType("BYTE");
    TcpByteClientTest tcpByteClientTest = new TcpByteClientTest();
    tcpByteClientTest.run();
  }
  @Test
  public void testWebSocketByteClient() throws InterruptedException, URISyntaxException {
    ServerConfig.getInstance().setMessageType("BYTE");
    WebSocketClientTest webSocketClientTest = new WebSocketClientTest();
    webSocketClientTest.run();
  }
}