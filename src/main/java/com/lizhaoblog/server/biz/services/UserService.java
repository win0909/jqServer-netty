/*
 * Copyright (C), 2015-2018
 * FileName: UserService
 * Author:   zhao
 * Date:     2018/7/5 9:52
 * Description: 用户管理的业务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.services;

import com.lizhaoblog.base.session.Session;
import com.lizhaoblog.server.biz.entity.User;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户管理的业务层〉
 *
 * @author zhao
 * @date 2018/7/5 9:52
 * @since 1.0.0
 */
public interface UserService {

  Long count();

  List<User> listAll();

  void insert(User user);

  void doTest(String message, Session session);
}