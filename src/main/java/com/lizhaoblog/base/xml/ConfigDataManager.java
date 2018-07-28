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

  /**
   * 假设我在resources/cfg目录中的dataConfig.xml列出了这个项目所有的配置数据
   * ---文件的格式是,
   * <config>
   * <file name="Hero" />
   * <file name="BBBBB" />
   * </config>
   * /config/file标签对应的类名为name标签---》resources/cfg目录的data-static-config目录下有标签对应的xml
   * ---文件的格式是,会是一个数组，内部是Hero数据
   * <Root>
   * <Hero id="1" roleId="1001" name="曹操" type="1" countryType="1" icon="ui://rgylkpibej3p9" />
   * <Hero id="2" roleId="1002" name="刘备" type="2" countryType="2" icon="ui://rgylkpibej3p8" />
   * <Hero id="3" roleId="1003" name="孙权" type="3" countryType="3" icon="ui://rgylkpibej3p5" />
   * </Root>
   * <p>
   * 这个xml对应的类在com.lizhaoblog.server.biz.entity.cfg.中，因为是一个数组，所以我们是需要一个数组类来接收
   * 前缀就是ConfigDataArrCfg
   * <p>
   * 在上述的流程中依次出现的是
   * String catalogDir = "/cfg/";
   * String catalogFile = "dataConfig.xml";
   * String catalogMainNode = "/config/file";
   * String catalogAttribute = "name";
   * String xmlFileDir = "/cfg/data-static-config/";
   * String cfgPackageName = "com.lizhaoblog.server.biz.entity.cfg.";
   * String cfgPrefix = "ConfigDataArrCfg";
   *
   * @param cfgPackageName   转换出来的类所在的包名
   * @param cfgPrefix        转换出来类的前缀
   * @param catalogDir       所有配置的清单文件 所在的文件夹
   * @param catalogFile      所有配置的清单文件
   * @param catalogMainNode  清单文件的标签
   * @param catalogAttribute 清单文件的标签名称
   * @param xmlFileDir       xml文件对应的路径
   */
  public void loadXml(String cfgPackageName, String cfgPrefix, String catalogDir, String catalogFile,
          String catalogMainNode, String catalogAttribute, String xmlFileDir) throws XmlConfigReadException {
    XmlDataLoader dl = new XmlDataLoader(cfgPackageName, cfgPrefix, catalogDir, catalogFile, catalogMainNode,
            catalogAttribute, xmlFileDir);
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