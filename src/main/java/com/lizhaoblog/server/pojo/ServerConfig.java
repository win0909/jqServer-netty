/*
 * Copyright (C), 2015-2018
 * FileName: ServerConfig
 * Author:   zhao
 * Date:     2018/6/12 11:16
 * Description: 服务的配置内容
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 〈一句话功能简述〉<br>
 * 〈服务的配置内容〉
 *
 * @author zhao
 * @date 2018/6/12 11:16
 * @since 1.0.0
 */
@Component
@Scope("singleton")
public class ServerConfig {
  private static final Logger logger = LoggerFactory.getLogger(ServerConfig.class);

  @Value("#{cfgProps['port']}")
  private Integer port;
  @Value("#{cfgProps['channelType']}")
  private String channelType;
  @Value("#{cfgProps['protocolType']}")
  private String protocolType;
  @Value("#{cfgProps['messageType']}")
  private String messageType;

  @Value("#{cfgProps['sslOpen']}")
  private Boolean sslOpen;
  @Value("#{cfgProps['sslType']}")
  private String sslType;
  @Value("#{cfgProps['sslPath']}")
  private String sslPath;
  @Value("#{cfgProps['sslPassword']}")
  private String sslPassword;

  // xml cfg
  @Value("#{cfgProps['xml.cfg.cfgPackageName']}")
  private String cfgPackageName;
  @Value("#{cfgProps['xml.cfg.cfgPrefix']}")
  private String cfgPrefix;
  @Value("#{cfgProps['xml.cfg.catalogDir']}")
  private String catalogDir;
  @Value("#{cfgProps['xml.cfg.catalogFile']}")
  private String catalogFile;
  @Value("#{cfgProps['xml.cfg.catalogMainNode']}")
  private String catalogMainNode;
  @Value("#{cfgProps['xml.cfg.catalogAttribute']}")
  private String catalogAttribute;
  @Value("#{cfgProps['xml.cfg.xmlFileDir']}")
  private String xmlFileDir;

  // Redis config
  @Value("#{cfgProps['redis.host']}")
  private String redisHost;
  @Value("#{cfgProps['redis.port']}")
  private Integer redisPort;
  @Value("#{cfgProps['redis.timeout']}")
  private Integer redisTimeout;
  @Value("#{cfgProps['redis.password']}")
  private String redisPassword;
  @Value("#{cfgProps['redis.databaseIndex']}")
  private Integer redisDatabaseIndex;

  @Value("#{cfgProps['redis.poolConfig.maxTotal']}")
  private Integer redisPoolConfigMaxTotal;
  @Value("#{cfgProps['redis.poolConfig.MaxIdle']}")
  private Integer redisPoolConfigMaxIdle;
  @Value("#{cfgProps['redis.poolConfig.MaxWaitMillis']}")
  private Long redisPoolConfigMaxWaitMillis;
  @Value("#{cfgProps['redis.poolConfig.testOnBorrow']}")
  private Boolean redisPoolConfigTestOnBorrow;
  @Value("#{cfgProps['redis.poolConfig.testOnReturn']}")
  private Boolean redisPoolConfigTestOnReturn;
  @Value("#{cfgProps['redis.poolConfig.testWhileIdle']}")
  private Boolean redisPoolConfigTestWhileIdle;

  private ApplicationContext applicationContext;

  private static ServerConfig instance = null;

  private ServerConfig() {
  }

  public static ServerConfig getInstance() {
    if (instance == null) {
      instance = new ServerConfig();
      logger.debug("ServerConfig is not init by spring");
    }
    return instance;
  }

  @PostConstruct
  public void init() {
    instance = this;
  }

  public void printServerInfo() {
    logger.info("**************Server INFO******************");
    logger.info("protocolType  : " + protocolType);
    logger.info("port          : " + port);
    logger.info("channelType   : " + channelType);
    logger.info("messageType   : " + messageType);
    logger.info("sslOpen       : " + sslOpen);
    logger.info("**************Server INFO******************");
  }

  public void printXmlCfgInfo() {
    logger.info("**************Xml Cfg INFO******************");
    logger.info("cfgPackageName     : " + cfgPackageName);
    logger.info("cfgPrefix          : " + cfgPrefix);
    logger.info("catalogDir         : " + catalogDir);
    logger.info("catalogFile        : " + catalogFile);
    logger.info("catalogMainNode    : " + catalogMainNode);
    logger.info("catalogAttribute   : " + catalogAttribute);
    logger.info("xmlFileDir         : " + xmlFileDir);
    logger.info("**************Xml Cfg INFO******************");
  }

  // Redis config
  public void printRedisInfo() {
    logger.info("**************Redis INFO******************");
    logger.info("redisHost                      : " + redisHost);
    logger.info("redisPort                      : " + redisPort);
    logger.info("redisTimeout                   : " + redisTimeout);
    logger.info("redisPassword                  : " + redisPassword);
    logger.info("redisDatabaseIndex             : " + redisDatabaseIndex);
    logger.info("redisPoolConfigMaxTotal        : " + redisPoolConfigMaxTotal);
    logger.info("redisPoolConfigMaxIdle         : " + redisPoolConfigMaxIdle);
    logger.info("redisPoolConfigMaxWaitMillis   : " + redisPoolConfigMaxWaitMillis);
    logger.info("redisPoolConfigTestOnBorrow    : " + redisPoolConfigTestOnBorrow);
    logger.info("redisPoolConfigTestOnReturn    : " + redisPoolConfigTestOnReturn);
    logger.info("redisPoolConfigTestWhileIdle   : " + redisPoolConfigTestWhileIdle);
    logger.info("**************Redis INFO******************");
  }

  //region Get And Set Method
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

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public Boolean getSslOpen() {
    return sslOpen;
  }

  public void setSslOpen(Boolean sslOpen) {
    this.sslOpen = sslOpen;
  }

  public String getSslType() {
    return sslType;
  }

  public void setSslType(String sslType) {
    this.sslType = sslType;
  }

  public String getSslPath() {
    return sslPath;
  }

  public void setSslPath(String sslPath) {
    this.sslPath = sslPath;
  }

  public String getSslPassword() {
    return sslPassword;
  }

  public void setSslPassword(String sslPassword) {
    this.sslPassword = sslPassword;
  }

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public String getCfgPackageName() {
    return cfgPackageName;
  }

  public void setCfgPackageName(String cfgPackageName) {
    this.cfgPackageName = cfgPackageName;
  }

  public String getCfgPrefix() {
    return cfgPrefix;
  }

  public void setCfgPrefix(String cfgPrefix) {
    this.cfgPrefix = cfgPrefix;
  }

  public String getCatalogDir() {
    return catalogDir;
  }

  public void setCatalogDir(String catalogDir) {
    this.catalogDir = catalogDir;
  }

  public String getCatalogFile() {
    return catalogFile;
  }

  public void setCatalogFile(String catalogFile) {
    this.catalogFile = catalogFile;
  }

  public String getCatalogMainNode() {
    return catalogMainNode;
  }

  public void setCatalogMainNode(String catalogMainNode) {
    this.catalogMainNode = catalogMainNode;
  }

  public String getCatalogAttribute() {
    return catalogAttribute;
  }

  public void setCatalogAttribute(String catalogAttribute) {
    this.catalogAttribute = catalogAttribute;
  }

  public String getXmlFileDir() {
    return xmlFileDir;
  }

  public void setXmlFileDir(String xmlFileDir) {
    this.xmlFileDir = xmlFileDir;
  }

  public String getRedisHost() {
    return redisHost;
  }

  public void setRedisHost(String redisHost) {
    this.redisHost = redisHost;
  }

  public Integer getRedisPort() {
    return redisPort;
  }

  public void setRedisPort(Integer redisPort) {
    this.redisPort = redisPort;
  }

  public Integer getRedisTimeout() {
    return redisTimeout;
  }

  public void setRedisTimeout(Integer redisTimeout) {
    this.redisTimeout = redisTimeout;
  }

  public String getRedisPassword() {
    return redisPassword;
  }

  public void setRedisPassword(String redisPassword) {
    this.redisPassword = redisPassword;
  }

  public Integer getRedisDatabaseIndex() {
    return redisDatabaseIndex;
  }

  public void setRedisDatabaseIndex(Integer redisDatabaseIndex) {
    this.redisDatabaseIndex = redisDatabaseIndex;
  }

  public Integer getRedisPoolConfigMaxTotal() {
    return redisPoolConfigMaxTotal;
  }

  public void setRedisPoolConfigMaxTotal(Integer redisPoolConfigMaxTotal) {
    this.redisPoolConfigMaxTotal = redisPoolConfigMaxTotal;
  }

  public Integer getRedisPoolConfigMaxIdle() {
    return redisPoolConfigMaxIdle;
  }

  public void setRedisPoolConfigMaxIdle(Integer redisPoolConfigMaxIdle) {
    this.redisPoolConfigMaxIdle = redisPoolConfigMaxIdle;
  }

  public Long getRedisPoolConfigMaxWaitMillis() {
    return redisPoolConfigMaxWaitMillis;
  }

  public void setRedisPoolConfigMaxWaitMillis(Long redisPoolConfigMaxWaitMillis) {
    this.redisPoolConfigMaxWaitMillis = redisPoolConfigMaxWaitMillis;
  }

  public Boolean getRedisPoolConfigTestOnBorrow() {
    return redisPoolConfigTestOnBorrow;
  }

  public void setRedisPoolConfigTestOnBorrow(Boolean redisPoolConfigTestOnBorrow) {
    this.redisPoolConfigTestOnBorrow = redisPoolConfigTestOnBorrow;
  }

  public Boolean getRedisPoolConfigTestOnReturn() {
    return redisPoolConfigTestOnReturn;
  }

  public void setRedisPoolConfigTestOnReturn(Boolean redisPoolConfigTestOnReturn) {
    this.redisPoolConfigTestOnReturn = redisPoolConfigTestOnReturn;
  }

  public Boolean getRedisPoolConfigTestWhileIdle() {
    return redisPoolConfigTestWhileIdle;
  }

  public void setRedisPoolConfigTestWhileIdle(Boolean redisPoolConfigTestWhileIdle) {
    this.redisPoolConfigTestWhileIdle = redisPoolConfigTestWhileIdle;
  }
  //endregion
}