/**
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

import com.lizhaoblog.server.channel.tcp.str.TcpStringClientTest;

import org.junit.Test;

/**
 * 〈一句话功能简述〉<br>
 * 〈NettyClient的测试数据〉
 *
 * @author zhao
 * @date 2018/6/12 15:34
 * @since 1.0.0
 */
public class NettyClientTest {
  @Test
  public void testTcpStringClient() throws InterruptedException {
    TcpStringClientTest tcpStringClientTest = new TcpStringClientTest();
    tcpStringClientTest.run();
  }

}