/*
 * Copyright (C), 2015-2018
 * FileName: CrudMapper
 * Author:   zhao
 * Date:     2018/7/4 10:33
 * Description: 封装dao层通用的增删改查方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.mybatis;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装dao层通用的增删改查方法〉
 *
 * @author zhao
 * @date 2018/7/4 10:33
 * @since 1.0.0
 */
public interface CrudMapper<T, ID extends Serializable>  {

  @CrudProvider
  @ResultMap("resultMap")
  T findOne(ID id);

  @CrudProvider
  @ResultType(Boolean.class)
  @Options(useCache = false)
  boolean exists(ID id);

  @CrudProvider
  @ResultMap("resultMap")
  @Options(useCache = false)
  List<T> listAll();

  @CrudProvider
  @ResultType(Long.class)
  @Options(useCache = false)
  Long count();

  @CrudProvider
  @ResultMap("resultMap")
  @Options(useCache = false)
  List<T> listByExample(T example);

  @CrudProvider
  @ResultType(Long.class)
  @Options(useCache = false)
  Long countByExample(T example);

  @CrudProvider(sqlType = "delete")
  @Options(flushCache = true)
  void deleteById(ID id);

  @CrudProvider(sqlType = "delete")
  @Options(flushCache = true)
  void deleteByIds(List<ID> ids);

  @CrudProvider(sqlType = "delete")
  @Options(flushCache = true)
  void deleteByIdArray(ID[] ids);

  @CrudProvider(sqlType = "delete")
  @Options(flushCache = true)
  void delete(T entity);

  @CrudProvider(sqlType = "delete")
  @Options(flushCache = true)
  void deleteEntities(List<T> entities);

  @CrudProvider(sqlType = "insert")
  @Options(flushCache = true)
  void insert(T entity);

  @CrudProvider(sqlType = "update")
  @Options(flushCache = true)
  void update(T entity);


}