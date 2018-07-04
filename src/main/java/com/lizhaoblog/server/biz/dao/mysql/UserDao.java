/*
 * Copyright (C), 2015-2018
 * FileName: UserDao
 * Author:   zhao
 * Date:     2018/6/28 14:37
 * Description: User表数据处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.dao.mysql;

import com.lizhaoblog.base.mybatis.CrudMapper;
import com.lizhaoblog.server.biz.entity.User;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈User表数据处理 〉
 *
 * @author zhao
 * @date 2018/6/28 14:37
 * @since 1.0.0
 */
@CacheNamespaceRef(UserDao.class)
public interface UserDao extends CrudMapper<User, Integer> {

  @Select("SELECT * FROM user")
  @ResultMap("resultMap")
  @Options(useCache = false)
  List<User> listUser();
}