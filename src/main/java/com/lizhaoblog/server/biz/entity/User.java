/*
 * Copyright (C), 2015-2018
 * FileName: User
 * Author:   zhao
 * Date:     2018/6/28 14:31
 * Description: 用户表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.entity;

import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户表对应的实体类〉
 *
 * @author zhao
 * @date 2018/6/28 14:31
 * @since 1.0.0
 */
@Table(name = "user")
@Alias("User")
public class User {
  @Id
  @Column(name = "id")
  private Integer id;
  @Column(name = "name")
  private String name;
  @Column(name = "account")
  private String account;
  @Column(name = "password")
  private String password;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}