/**
 * Copyright (C), 2015-2018
 * FileName: Sort
 * Author:   zhao
 * Date:     2018/6/15 14:44
 * Description: 排序测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.javase;

import org.junit.Test;

/**
 * 〈一句话功能简述〉<br>
 * 〈排序测试类〉
 *
 * @author zhao
 * @date 2018/6/15 14:44
 * @since 1.0.0
 */
public class Sort {

  private int[] arr = { 3, 8, 4, 2, 1, 6 };

  @Test
  public void sort1() {
    for (int i = 1; i < arr.length; i++) {
      for (int k = 0; k < arr.length - i; k++) {
        if (arr[k] > arr[k + 1]) {
          //          int tmp = arr[k];
          //          arr[k] = arr[k + 1];
          //          arr[k + 1] = tmp;

          arr[k] = arr[k] * arr[k + 1];
          arr[k + 1] = arr[k] / arr[k + 1];
          arr[k] = arr[k] / arr[k + 1];
        }
      }
    }
    for (int k = 0; k < arr.length; k++) {
      System.out.println(arr[k]);
    }

  }
}