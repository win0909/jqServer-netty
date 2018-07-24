/*
 * Copyright (C), 2015-2018
 * FileName: ConfigDataManagerTest
 * Author:   zhao
 * Date:     2018/7/24 16:40
 * Description: ConfigDataManager的测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.xml;

import com.lizhaoblog.base.exception.XmlConfigReadException;

import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈ConfigDataManager的测试类〉
 *
 * @author zhao
 * @date 2018/7/24 16:40
 * @since 1.0.1
 */
public class ConfigDataManagerTest {

  @Test
  public void testLoadXml() throws XmlConfigReadException {
    ConfigDataManager.getInstance().loadXml();
    Map<String, List<?>> configDataMap = ConfigDataManager.getInstance().getConfigDataMap();
  }

}
