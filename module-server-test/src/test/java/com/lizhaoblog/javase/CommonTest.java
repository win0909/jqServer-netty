/*
 * Copyright (C), 2015-2018
 * FileName: CommonTest
 * Author:   zhao
 * Date:     2018/7/20 10:37
 * Description: 默认的测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.javase;

import org.junit.Test;

/**
 * 〈一句话功能简述〉<br>
 * 〈默认的测试〉
 *
 * @author zhao
 * @date 2018/7/20 10:37
 * @since 1.0.1
 */
public class CommonTest {

  @Test
  public void testMap(){
    String str = "1a";
    String str2 = "b";
    Integer integer1 = 155;
    System.out.println(str.hashCode());
    System.out.println(str2.hashCode());
    System.out.println(integer1.hashCode());


  }
}
