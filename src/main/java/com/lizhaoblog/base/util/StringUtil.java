/*
 * Copyright (C), 2015-2018
 * FileName: StringUtil
 * Author:   zhao
 * Date:     2018/6/26 10:59
 * Description: 字符串的方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.util;

/**
 * 〈一句话功能简述〉<br>
 * 〈字符串的方法〉
 *
 * @author zhao
 * @date 2018/6/26 10:59
 * @since 1.0.0
 */
public class StringUtil {
  /**
   * 将传入的字符串首字母转小写
   *
   * @param s 需要改变的字符串
   * @return 改变之后的字符串
   */
  public static String toLowerCaseFirstOne(String s) {
    if (Character.isLowerCase(s.charAt(0))) {
      return s;
    } else {
      return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
  }


  /**
   * 将传入的字符串首字母转大写
   *
   * @param s 需要改变的字符串
   * @return 改变之后的字符串
   */
  public static String toUpperCaseFirstOne(String s) {
    if (Character.isUpperCase(s.charAt(0))) {
      return s;
    } else {
      return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
  }
}