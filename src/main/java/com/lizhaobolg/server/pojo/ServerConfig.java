/**
 * Copyright (C), 2015-2018
 * FileName: ServerConfig
 * Author:   zhao
 * Date:     2018/6/12 11:16
 * Description: 服务的配置内容
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaobolg.server.pojo;

import io.netty.channel.EventLoopGroup;

/**
 * 〈一句话功能简述〉<br>
 * 〈服务的配置内容〉
 *
 * @author zhao
 * @date 2018/6/12 11:16
 * @since 1.0.0
 */
public class ServerConfig {
  private Integer port;
  private String channelType;
  private String protocolType;

  private static ServerConfig instance = null;

  private ServerConfig() {
  }

  private void init() {
    port = 8088;
    channelType = "NIO";
    protocolType = "TCP";
  }

  public static ServerConfig getInstance() {
    if (instance == null) {
      instance = new ServerConfig();
      instance.init();
    }
    return instance;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getChannelType() {
    return channelType;
  }

  public void setChannelType(String channelType) {
    this.channelType = channelType;
  }

  public String getProtocolType() {
    return protocolType;
  }

  public void setProtocolType(String protocolType) {
    this.protocolType = protocolType;
  }
}