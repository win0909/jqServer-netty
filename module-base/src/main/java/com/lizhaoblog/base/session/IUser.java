/*
 * Copyright (C), 2015-2018
 * FileName: IUser
 * Author:   zhao
 * Date:     2018/6/22 15:46
 * Description: 用户的抽象类，主要用于session之中
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.session;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户的抽象类，主要用于session之中〉
 *
 * @author zhao
 * @date 2018/6/22 15:46
 * @since 1.0.0
 */
public interface IUser {

  /**
   * 获取用户id
   *
   * @return 用户id
   */
  Integer getId();

  /**
   * 获取用户状态id
   *
   * @return 状态id
   */
  Integer getStatus();

  /**
   * 获取用户角色类型id
   *
   * @return 角色类型id
   */
  Integer getRole();

}