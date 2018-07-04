/*
 * Copyright (C), 2015-2018
 * FileName: EntityUtils
 * Author:   zhao
 * Date:     2018/7/4 10:53
 * Description: 实体类操作工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.mybatis;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 〈一句话功能简述〉<br>
 * 〈实体类操作工具〉
 *
 * @author zhao
 * @date 2018/7/4 10:53
 * @since 1.0.0
 */
public final class EntityUtils {
  public static String getColumnName(Class<?> entityClass, String fieldName)
          throws NoSuchFieldException, SecurityException {
    String columnName = null;
    Field field = entityClass.getDeclaredField(fieldName);
    if (field.isAnnotationPresent(Column.class)) {
      columnName = field.getAnnotation(Column.class).name();
      if (StringUtils.isEmpty(columnName)) {
        columnName = fieldName;
      }
    } else if (field.isAnnotationPresent(Id.class)) {
      columnName = fieldName;
    }
    if (StringUtils.isEmpty(columnName)) {
      throw new IllegalArgumentException("实体未注解");
    }
    return columnName;
  }

  private EntityUtils() {

  }
}
