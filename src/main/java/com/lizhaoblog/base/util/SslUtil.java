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

import com.lizhaoblog.base.exception.SSLException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
   *
   * @param type     类型
   * @param path     路径
   * @param password 密码
   * @return SSLContext
   * @throws Exception 返回里面的错误
   */
  public static SSLContext createSSLContext(String type, String path, String password) throws SSLException {
    KeyStore ks = null; /// "JKS"
    SSLContext sslContext = null; /// "JKS"
    try {
      ks = KeyStore.getInstance(type);
      InputStream ksInputStream = new FileInputStream(path); /// 证书存放地址
      ks.load(ksInputStream, password.toCharArray());
      //KeyManagerFactory充当基于密钥内容源的密钥管理器的工厂。
      KeyManagerFactory kmf = KeyManagerFactory
              .getInstance(KeyManagerFactory.getDefaultAlgorithm());//getDefaultAlgorithm:获取默认的 KeyManagerFactory 算法名称。
      kmf.init(ks, password.toCharArray());
      //SSLContext的实例表示安全套接字协议的实现，它充当用于安全套接字工厂或 SSLEngine 的工厂。
      sslContext = SSLContext.getInstance("TLS");
      sslContext.init(kmf.getKeyManagers(), null, null);
    } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | NoSuchAlgorithmException | IOException | KeyManagementException e) {
      throw new SSLException("create ssl find error "+e.getMessage());
    }

    return sslContext;
  }

  static class miTM implements TrustManager, X509TrustManager {
    public X509Certificate[] getAcceptedIssuers() {
      return null;
    }

    public boolean isServerTrusted(X509Certificate[] certs) {
      return true;
    }

    public boolean isClientTrusted(X509Certificate[] certs) {
      return true;
    }

    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
      return;
    }

    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
      return;
    }
  }

  /**
   * 忽略HTTPS请求的SSL证书，必须在openConnection之前调用
   *
   * @throws Exception
   */
  public static void ignoreSsl() throws Exception {
    HostnameVerifier hv = new HostnameVerifier() {
      public boolean verify(String urlHostName, SSLSession session) {
        System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
        return true;
      }
    };
    trustAllHttpsCertificates();
    HttpsURLConnection.setDefaultHostnameVerifier(hv);
  }

  private static void trustAllHttpsCertificates() throws Exception {
    TrustManager[] trustAllCerts = new TrustManager[1];
    TrustManager tm = new miTM();
    trustAllCerts[0] = tm;
    SSLContext sc = SSLContext.getInstance("SSL");
    sc.init(null, trustAllCerts, null);
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
  }

}
