/*
 * Copyright (C), 2015-2018
 * FileName: WebSocketChannelInitializer
 * Author:   zhao
 * Date:     2018/8/10 11:42
 * Description: webSocket的channel
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.channel.websocket;

import com.lizhaoblog.base.exception.SSLException;
import com.lizhaoblog.base.message.codec.IMessageToWebSocketFrameEncoder;
import com.lizhaoblog.base.message.codec.WebSocketFrameToIMessageDecoder;
import com.lizhaoblog.base.util.SslUtil;
import com.lizhaoblog.server.pojo.ServerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈webSocket的channel〉
 *
 * @author zhao
 * @date 2018/8/10 11:42
 * @since 1.0.1
 */
@Component
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
  private static final Logger logger = LoggerFactory.getLogger(WebSocketChannelInitializer.class);

  @Override
  protected void initChannel(SocketChannel ch) {
    ChannelPipeline pipeline = ch.pipeline();
    if (ServerConfig.getInstance().getSslOpen()) {
      try {
        SSLContext sslContext = null;
        // sslContext = SslUtil.createSSLContext("JKS", "D:\\workdir\\doc\\jks\\wss.jks", "netty123");
        sslContext = SslUtil
                .createSSLContext(ServerConfig.getInstance().getSslType(), ServerConfig.getInstance().getSslPath(),
                        ServerConfig.getInstance().getSslPassword());
        // SSLEngine 此类允许使用ssl安全套接层协议进行安全通信
        SSLEngine engine = sslContext.createSSLEngine();
        // 服务器模式
        engine.setUseClientMode(false);
        //ssl双向认证
        engine.setNeedClientAuth(false);
        engine.setWantClientAuth(false);
        // engine.setEnabledProtocols(new String[] { "SSLv3", "TLSv1" })
        // TLSv1.2包括了SSLv3
        engine.setEnabledProtocols(new String[] { "TLSv1.2" });
        pipeline.addLast("ssl", new SslHandler(engine));

      } catch (SSLException e) {
        logger.debug("添加ssl出现错误", e);
      }
    }

    pipeline.addLast("http-codec", new HttpServerCodec()); // Http消息编码解码
    pipeline.addLast("aggregator", new HttpObjectAggregator(65536)); // Http消息组装
    pipeline.addLast("http-chunked", new ChunkedWriteHandler()); // WebSocket通信支持
    // 消息编解码
    pipeline.addLast("encoder", new IMessageToWebSocketFrameEncoder());
    pipeline.addLast("decoder", new WebSocketFrameToIMessageDecoder());

    WebSocketHandler webSocketHandler = (WebSocketHandler) ServerConfig.getInstance().getApplicationContext()
            .getBean("webSocketHandler");
    pipeline.addLast(webSocketHandler);

  }

}
