/*
 * Copyright (C), 2015-2018
 * FileName: GsonTest
 * Author:   zhao
 * Date:     2018/7/16 10:10
 * Description: Gson的测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.javase;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈Gson的测试类〉
 *
 * @author zhao
 * @date 2018/7/16 10:10
 * @since 1.0.1
 */
public class GsonTest {

  @Test
  public void gsonMapTest() {
    Map<String, Object> linkedHashMap = new LinkedHashMap();// 使用LinkedHashMap将结果按先进先出顺序排列
    linkedHashMap.put("1", "2");
    linkedHashMap.put("key2", "value2");
    linkedHashMap.put("key3", 3);
    Gson gson = new Gson();
    String s = gson.toJson(linkedHashMap);
    System.out.print(s);
  }

}
