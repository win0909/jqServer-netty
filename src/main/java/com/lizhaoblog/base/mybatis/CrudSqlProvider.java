/*
 * Copyright (C), 2015-2018
 * FileName: CrudSqlProvider
 * Author:   zhao
 * Date:     2018/7/4 10:39
 * Description: 提供生成sql语句
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.mybatis;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 〈一句话功能简述〉<br>
 * 〈写的通用方法的sql提供者，@see CrudMapper〉
 *
 * @author zhao
 * @date 2018/7/4 10:39
 * @since 1.0.0
 */
public class CrudSqlProvider {
  private Class<?> entityType;
  private String tableName;
  private String idColumnName;
  private String idPropertyName;

  public CrudSqlProvider(Class<?> entityType) {
    this.entityType = entityType;
    initTableName();
    initIdColumnName();
    initIdPropertyName();
  }

  public String findOne() {
    return selectFrom().WHERE(eqId()).toString();
  }

  public String exists() {
    return countFrom().WHERE(eqId()).toString();
  }

  public String listAll() {
    return selectFrom().toString();
  }

  public String count() {
    return countFrom().toString();
  }

  public String listByExample(Object example) throws IllegalAccessException {
    SQL sql = selectFrom();
    appendConditionByExample(sql, example);
    return sql.toString();
  }

  public String countByExample(Object example) throws IllegalAccessException {
    SQL sql = countFrom();
    appendConditionByExample(sql, example);
    return sql.toString();
  }

  public String deleteById() {
    return deleteFrom().WHERE(eqId()).toString();
  }

  public String deleteByIds(List<Object> ids) {
    SQL sql = deleteFrom();
    if (CollectionUtils.isEmpty(ids)) {
      throw new BuilderException("参数ids不能为空");
    }
    StringBuilder sb = new StringBuilder(idColumnName).append(" in (");
    for (Object id : ids) {
      if (id instanceof String) {
        sb.append("'").append(id).append("'");
      } else {
        sb.append(id);
      }
      sb.append(",");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append(")");
    sql.WHERE(sb.toString());
    return sql.toString();
  }

  public String deleteByIdArray(Object[] ids) {
    return deleteByIds(Arrays.asList(ids));
  }

  public String delete() {
    return deleteFrom().WHERE(eqId()).toString();
  }

  public String deleteEntities(List<Object> entities)
          throws NoSuchFieldException, SecurityException, IllegalAccessException {
    if (CollectionUtils.isEmpty(entities)) {
      throw new BuilderException("参数entities不能为空");
    }
    List<Object> ids = new ArrayList<Object>();
    for (Object entity : entities) {
      Field idField = entityType.getDeclaredField(idPropertyName);
      idField.setAccessible(true);
      ids.add(idField.get(entity));
    }
    return deleteByIds(ids);
  }

  public String deleteAll() {
    return deleteFrom().toString();
  }

  public String insert(Object entity) throws IllegalAccessException {
    SQL sql = new SQL().INSERT_INTO(tableName);
    Field[] fields = entityType.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(GeneratedValue.class)) {
        continue;
      }
      field.setAccessible(true);
      Object fieldVal = field.get(entity);
      Column colomnAnnotation = field.getAnnotation(Column.class);
      boolean bool = StringUtils.isEmpty(Objects.toString(fieldVal, "")) && (colomnAnnotation != null || field
              .isAnnotationPresent(Id.class));
      if (!bool) {
        String propertyName = field.getName();
        String columnName = propertyName;
        if (colomnAnnotation != null) {
          columnName = colomnAnnotation.name();
          if (StringUtils.isEmpty(columnName)) {
            columnName = propertyName;
          }
        }
        sql.VALUES(columnName, wrapParameter(propertyName));
      }
    }
    return sql.toString();
  }

  public String update(Object entity) throws IllegalAccessException {
    SQL sql = new SQL().UPDATE(tableName);
    Field[] fields = entityType.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(Id.class)) {
        continue;
      }
      field.setAccessible(true);
      Object fieldVal = field.get(entity);
      Column colomnAnnotation = field.getAnnotation(Column.class);
      if (!StringUtils.isEmpty(Objects.toString(fieldVal, "")) && colomnAnnotation != null) {
        String propertyName = field.getName();
        String columnName = colomnAnnotation.name();
        if (StringUtils.isEmpty(columnName)) {
          columnName = propertyName;
        }
        sql.SET(assign(columnName, propertyName));
      }
    }
    return sql.WHERE(eqId()).toString();
  }

  private SQL deleteFrom() {
    return new SQL().DELETE_FROM(tableName);
  }

  public String findWithJpaStyle(String methodName) {
    // TODO support jpastyle
    SQL sql = selectFrom();
    return sql.toString();
  }

  private SQL selectFrom() {
    return new SQL().SELECT("*").FROM(tableName);
  }

  public String countWithJpaStyle(String methodName) {
    // TODO support jpastyle
    SQL sql = countFrom();
    return sql.toString();
  }

  private SQL countFrom() {
    return new SQL().SELECT("count(*)").FROM(tableName);
  }

  private void appendConditionByExample(SQL sql, Object example) throws IllegalAccessException {
    Field[] fields = entityType.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      Object fieldVal = field.get(example);
      Column colomnAnnotation = field.getAnnotation(Column.class);
      if (!StringUtils.isEmpty(Objects.toString(fieldVal, "")) && colomnAnnotation != null) {
        String propertyName = field.getName();
        String columnName = colomnAnnotation.name();
        if (StringUtils.isEmpty(columnName)) {
          columnName = propertyName;
        }
        sql.WHERE(eq(columnName, propertyName));
      }
    }
  }

  private String eqId() {
    return eq(idColumnName, idPropertyName);
  }

  private String eq(String columnName, String propertyName) {
    return String.format("%s = #{%s}", columnName, propertyName);
  }

  private String assign(String columnName, String propertyName) {
    return String.format("%s = #{%s}", columnName, propertyName);
  }

  private String wrapParameter(String propertyName) {
    return String.format("#{%s}", propertyName);
  }

  private void initIdColumnName() {
    Field[] fields = entityType.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(Column.class)) {
        idColumnName = field.getAnnotation(Column.class).name();
        if (StringUtils.isEmpty(idColumnName)) {
          idColumnName = field.getName();
        }
      } else if (field.isAnnotationPresent(Id.class)) {
        idColumnName = field.getName();
      }
    }
    if (StringUtils.isEmpty(idColumnName)) {
      throw new IllegalArgumentException("实体未注解Id");
    }
  }

  private void initIdPropertyName() {
    Field[] fields = entityType.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(Id.class)) {
        idPropertyName = field.getName();
      }
    }
    if (StringUtils.isEmpty(idPropertyName)) {
      throw new IllegalArgumentException("实体未注解Id");
    }
  }

  private void initTableName() {
    Table tableAnno = entityType.getAnnotation(Table.class);
    if (tableAnno == null) {
      throw new IllegalArgumentException("实体未注解Table");
    }
    tableName = tableAnno.name();
    if (StringUtils.isEmpty(tableName)) {
      tableName = getTableNameFromClass(entityType);
    }
  }

  private String getTableNameFromClass(Class<?> clazz) {
    String simpleName = clazz.getSimpleName();
    String first = simpleName.substring(0, 1);
    String remains = clazz.getSimpleName().substring(1);
    return first + remains;
  }

}