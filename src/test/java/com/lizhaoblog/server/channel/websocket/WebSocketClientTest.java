/*
 * Copyright (C), 2015-2018
 * FileName: WebSocketClientTest
 * Author:   zhao
 * Date:     2018/8/2 17:32
 * Description: websocket测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.channel.websocket;

import com.lizhaoblog.base.message.codec.IMessageToWebSocketFrameEncoder;
import com.lizhaoblog.base.message.codec.WebSocketFrameToIMessageDecoder;
import com.lizhaoblog.base.message.impl.ByteMessage;
import com.lizhaoblog.common.CommonValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

/**
 * 〈一句话功能简述〉<br>
 * 〈websocket测试〉
 *
 * @author zhao
 * @date 2018/8/2 17:32
 * @since 1.0.1
 */
public class WebSocketClientTest {
  private static final Logger logger = LoggerFactory.getLogger(WebSocketClientTest.class);
  private static EventLoopGroup group = new NioEventLoopGroup();

  private static WebSocketClientHandlerTest handler;

  private String uriStr = "ws//" + CommonValue.IP + ":" + CommonValue.PORT;

  public void run() throws InterruptedException, URISyntaxException {
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.group(group);
    bootstrap.channel(NioSocketChannel.class);

    // 主要是为handler(自己写的类)服务
    URI wsUri = new URI(uriStr);
    WebSocketClientHandshaker webSocketClientHandshaker = WebSocketClientHandshakerFactory
            .newHandshaker(wsUri, WebSocketVersion.V13, null, true, new DefaultHttpHeaders(), 100 * 1024 * 1024);
    handler = new WebSocketClientHandlerTest(webSocketClientHandshaker);

    bootstrap.handler(new ChannelInitializer() {
      @Override
      protected void initChannel(Channel ch) {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpClientCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast("encoder", new IMessageToWebSocketFrameEncoder());
        pipeline.addLast("decoder", new WebSocketFrameToIMessageDecoder());

//        pipeline.addLast("encoder", new MessageEncoder());
//        pipeline.addLast("decoder", new MessageDecoder(ConstantValue.MESSAGE_CODEC_MAX_FRAME_LENGTH,
//                ConstantValue.MESSAGE_CODEC_LENGTH_FIELD_LENGTH, ConstantValue.MESSAGE_CODEC_LENGTH_FIELD_OFFSET,
//                ConstantValue.MESSAGE_CODEC_LENGTH_ADJUSTMENT, ConstantValue.MESSAGE_CODEC_INITIAL_BYTES_TO_STRIP,
//                false, ServerConfig.getInstance().getMessageType()));

        pipeline.addLast(handler);
      }
    });

    ByteMessage byteMessage = new ByteMessage();
    byteMessage.setMessageId(com.lizhaoblog.server.biz.constant.CommonValue.CM_MSG_TEST_BYTE);
    byteMessage.setStatusCode(com.lizhaoblog.server.biz.constant.CommonValue.MSG_STATUS_CODE_SUCCESS);
    byteMessage.addAttr(2);

    // 连接服务端
    //    ChannelFuture channelFuture = bootstrap.connect(CommonValue.IP, CommonValue.PORT).sync();
    ChannelFuture channelFuture = bootstrap.connect(CommonValue.IP, CommonValue.PORT).sync();
    handler.handshakeFuture().sync();
//    channelFuture.channel().writeAndFlush(byteMessage);
    //    WebSocketFrame frame = new PingWebSocketFrame(Unpooled.wrappedBuffer(new byte[] { 8, 1, 8, 1 }));
//        WebSocketFrame frame = new TextWebSocketFrame("aaa");
//        channelFuture.channel().writeAndFlush(frame);
//    byte[] bytes = new byte[14];
//    bytes[0] = 0;
//    bytes[1] = 1;
//    bytes[2] = 0;
//    bytes[3] = 1;
//    bytes[4] = 0;
//    bytes[5] = 0;
//    bytes[6] = 0;
//    bytes[7] = 6;
//    bytes[8] = 2;
//    bytes[9] = 0;
//    bytes[10] = 0;
//    bytes[11] = 0;
//    bytes[12] = 0;
//    bytes[13] = 1;
////    channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(bytes));
//    BinaryWebSocketFrame frame = new BinaryWebSocketFrame(Unpooled.copiedBuffer(bytes));
    //    channelFuture.channel().writeAndFlush(frame);
        channelFuture.channel().writeAndFlush(byteMessage);

    logger.info("向Socket服务器发送数据:" + byteMessage);
    channelFuture.channel().closeFuture().sync();
  }
}