/*
 * Copyright (C), 2015-2018
 * FileName: XmlDataLoader
 * Author:   zhao
 * Date:     2018/7/24 12:14
 * Description: 加载配置文件获取到实际的配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.xml;

import com.lizhaoblog.base.exception.XmlConfigReadException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * 〈一句话功能简述〉<br>
 * 〈加载配置文件获取到实际的配置类〉
 *
 * @author zhao
 * @date 2018/7/24 12:14
 * @since 1.0.1
 */
public class XmlDataLoader {
  private static final Logger logger = LoggerFactory.getLogger(XmlDataLoader.class);

  /**
   * 业务中对应配置文件的包名全路径
   */
  private String cfgPackageName;
  /**
   * 对应配置文件的各个类的前缀
   */
  private String cfgPrefix;
  /**
   * 所有配置的清单文件 所在的文件夹
   */
  private String catalogDir;
  /**
   * 所有配置的清单文件
   */
  private String catalogFile;
  /**
   * 清单文件的标签
   */
  private String catalogMainNode = "/config/file";
  /**
   * 清单文件的标签名称
   */
  private String catalogAttribute = "name";

  /**
   * xml文件对应的路径
   */
  private String xmlFileDir;

  private static final String POSTFIX_XML = ".xml";

//  public XmlDataLoader(String packageName, String catalogFile) {
//    this.cfgPackageName = packageName;
//    this.catalogFile = catalogFile;
//  }

  public XmlDataLoader(String cfgPackageName, String cfgPrefix, String catalogDir, String catalogFile,
          String catalogMainNode, String catalogAttribute, String xmlFileDir) {
    this.cfgPackageName = cfgPackageName;
    this.cfgPrefix = cfgPrefix;
    this.catalogDir = catalogDir;
    this.catalogFile = catalogFile;
    this.catalogMainNode = catalogMainNode;
    this.catalogAttribute = catalogAttribute;
    this.xmlFileDir = xmlFileDir;
  }

  /**
   * 调用load方法加载所有的配置文件
   *
   * @throws XmlConfigReadException 加载配置文件时出现的错误
   */
  public void load() throws XmlConfigReadException {
    SAXReader reader = new SAXReader();
    try {
      InputStream resourceAsStream = getClass().getResourceAsStream(catalogDir + catalogFile);
      InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
      Document doc = reader.read(inputStreamReader);
      List<?> nodes = doc.selectNodes(catalogMainNode);
      Map<String, List<?>> dataMap = new HashMap<String, List<?>>();
      List<String> files = new LinkedList<String>();
      for (Object n : nodes) {
        Element t = (Element) n;
        String f = t.attributeValue(catalogAttribute);
        ConfigDataArr ob = (ConfigDataArr) loadFile(f);
        for (Object o : ob.getList()) {
          ConfigDataManager.getInstance().registerObject(o, dataMap);
        }
        files.add(f);
      }
      logger.info("读取配置完毕，准备afterLoad");
      ConfigDataManager.getInstance().setConfigDataMap(dataMap);
      ConfigDataManager.getInstance().afterLoad();
      logger.info("afterLoad 完毕");
    } catch (DocumentException e) {
      throw new XmlConfigReadException("载入文件出错：DocumentException " + e.getMessage());
    }
  }

  private Object loadFile(String className) throws XmlConfigReadException {

    InputStream resourceAsStream = null;
    try {
      String file = xmlFileDir + className + POSTFIX_XML;
      logger.info("load file: {}", file);
      resourceAsStream = getClass().getResourceAsStream(file);
      if (resourceAsStream == null) {
        throw new XmlConfigReadException("文件不存在:" + file);
      }
      return loadFromStream(className, resourceAsStream);
    } finally {
      try {
        if (resourceAsStream != null) {
          resourceAsStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public Object loadFromStream(String name, InputStream resourceAsStream) throws XmlConfigReadException {
    // get clazz
    String className = cfgPackageName + cfgPrefix + name;
    try {
      Class<?> classObject = Class.forName(className);
      if (classObject == null) {
        logger.error("未找到类" + className);
        return Collections.EMPTY_LIST;
      }
      JAXBContext context = JAXBContext.newInstance(classObject);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      return unmarshaller.unmarshal(resourceAsStream);
    } catch (JAXBException | ClassNotFoundException e) {
      throw new XmlConfigReadException("loadFromStream 出现错误：" + name + "  " + e.getClass() + "  " + e.getMessage());
    }
  }

}
