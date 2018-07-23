/*
 * Copyright (C), 2015-2018
 * FileName: CrudProvider
 * Author:   zhao
 * Date:     2018/7/4 10:38
 * Description: 对数据库的操作类型，注解
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 〈一句话功能简述〉<br>
 * 〈对数据库的操作类型，注解〉
 *
 * @author zhao
 * @date 2018/7/4 10:38
 * @since 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CrudProvider {
  Class<?> type() default CrudSqlProvider.class;

  String sqlType() default "select";

  String method() default "";

  boolean isJpaStyle() default false;

}
