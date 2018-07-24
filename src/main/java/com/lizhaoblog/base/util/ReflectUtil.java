/*
 * Copyright (C), 2015-2018
 * FileName: ReflectUtil
 * Author:   zhao
 * Date:     2018/7/24 16:07
 * Description: 反射工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;

/**
 * 〈一句话功能简述〉<br>
 * 〈反射工具类〉
 *
 * @author zhao
 * @date 2018/7/24 16:07
 * @since 1.0.1
 */
public class ReflectUtil {
  /**
   * 将obj对象的field属性设置值为value
   *
   * @param obj   对象
   * @param field 属性
   * @param value 值
   * @throws IllegalArgumentException 非法参数异常
   * @throws IllegalAccessException   安全权限异常,一般来说,是由于java在反射时调用了private方法所导致
   */
  private static void setField(Object obj, Field field, String value) throws IllegalArgumentException, IllegalAccessException {
    field.setAccessible(true);
    if (field.getType() == int.class) {
      field.setInt(obj, Integer.parseInt(value));
    } else if (field.getType() == short.class) {
      field.setShort(obj, Short.parseShort(value));
    } else if (field.getType() == byte.class) {
      field.setByte(obj, Byte.parseByte(value));
    } else if (field.getType() == long.class) {
      field.setLong(obj, Long.parseLong(value));
    } else if (field.getType() == double.class) {
      field.setDouble(obj, Double.parseDouble(value));
    } else if (field.getType() == float.class) {
      field.setFloat(obj, Float.parseFloat(value));
    } else if (field.getType() == Timestamp.class) {
      field.set(obj, Timestamp.valueOf(value));
    } else {
      field.set(obj, field.getType().cast(value));
    }
  }

}
