/*
 * Copyright (C), 2015-2018
 * FileName: ConfigDataArr
 * Author:   zhao
 * Date:     2018/7/24 15:09
 * Description: 配置数据数组的基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.xml;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈配置数据数组的基类〉
 *
 * @author zhao
 * @date 2018/7/24 15:09
 * @since 1.0.1
 */
public interface ConfigDataArr {
  /**
   * 配置数据存有一个数组，该方法将返回这个数组
   *
   * @return 返回数组
   */
  List getList();
}
