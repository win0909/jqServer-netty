/*
 * Copyright (C), 2015-2018
 * FileName: ConfigDataManager
 * Author:   zhao
 * Date:     2018/7/24 15:12
 * Description: 配置数据的管理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.xml;

import com.lizhaoblog.base.exception.XmlConfigReadException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈配置数据的管理类〉
 *
 * @author zhao
 * @date 2018/7/24 15:12
 * @since 1.0.1
 */
public class ConfigDataManager {
  private static ConfigDataManager instance = null;
  /**
   * key:实体名 value:该实体下的所有模板数据
   */
  private Map<String, List<?>> configDataMap;

  private ConfigDataManager() {
    configDataMap = new HashMap<String, List<?>>();
  }

  public static ConfigDataManager getInstance() {
    if (instance == null) {
      instance = new ConfigDataManager();
    }
    return instance;
  }

  public void loadXml() throws XmlConfigReadException {
    XmlDataLoader dl = new XmlDataLoader("com.lizhaoblog.server.biz.entity.cfg.", "ConfigDataArrCfg", "/cfg/",
            "dataConfig.xml", "/config/file", "name",
            "/cfg/data-static-config/");
    dl.load();
  }

  /**
   * 获取该实体类下所有模板数据
   *
   * @param beanName 实体类
   * @return 实体类对应的所有模板数据
   */
  @SuppressWarnings("unchecked")
  public List listAll(String beanName) {
    return configDataMap.get(beanName);
  }

  /**
   * 注册对象到对应类的List中
   *
   * @param key     key
   * @param dataMap 数据map
   */
  public void registerObject(Object key, Map<String, List<?>> dataMap) {
    add(key.getClass().getSimpleName(), key, dataMap);
  }

  @SuppressWarnings("unchecked")
  private void add(String key, Object data, Map<String, List<?>> dataMap) {
    List list = dataMap.computeIfAbsent(key, k -> new ArrayList());
    list.add(data);
  }

  public Map<String, List<?>> getConfigDataMap() {
    return configDataMap;
  }

  public void setConfigDataMap(Map<String, List<?>> configDataMap) {
    this.configDataMap = configDataMap;
  }

  public void afterLoad() {
    // 加载后处理
  }

  public void loadCanShu() {
    // 加载全局参数xml配置
  }
}