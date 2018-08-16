/*
 * Copyright (C), 2015-2018
 * FileName: SslUtil
 * Author:   zhao
 * Date:     2018/8/16 12:06
 * Description: Ssl工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

/**
 * 〈一句话功能简述〉<br>
 * 〈Ssl工具类〉
 *
 * @author zhao
 * @date 2018/8/16 12:06
 * @since 1.0.1
 */
public class SslUtil {
  /**
   * 创建一个SSLContext
   * @param type  类型
   * @param path  路径
   * @param password  密码
   * @return SSLContext
   * @throws Exception 返回里面的错误
   */
  public static SSLContext createSSLContext(String type, String path, String password) throws Exception {
    KeyStore ks = KeyStore.getInstance(type); /// "JKS"
    InputStream ksInputStream = new FileInputStream(path); /// 证书存放地址
    ks.load(ksInputStream, password.toCharArray());
    //KeyManagerFactory充当基于密钥内容源的密钥管理器的工厂。
    KeyManagerFactory kmf = KeyManagerFactory
            .getInstance(KeyManagerFactory.getDefaultAlgorithm());//getDefaultAlgorithm:获取默认的 KeyManagerFactory 算法名称。
    kmf.init(ks, password.toCharArray());
    //SSLContext的实例表示安全套接字协议的实现，它充当用于安全套接字工厂或 SSLEngine 的工厂。
    SSLContext sslContext = SSLContext.getInstance("TLS");
    sslContext.init(kmf.getKeyManagers(), null, null);
    return sslContext;
  }

}
