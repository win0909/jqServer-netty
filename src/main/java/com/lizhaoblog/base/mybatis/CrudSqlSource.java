/*
 * Copyright (C), 2015-2018
 * FileName: CrudSqlSource
 * Author:   zhao
 * Date:     2018/7/4 10:46
 * Description: sql
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.mybatis;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈SqlSource:从XML文件或注释读取的映射语句的内容。
 * 创建将从用户接收的输入参数中传递到数据库的SQL。〉
 *
 * @author zhao
 * @date 2018/7/4 10:46
 * @since 1.0.0
 */
public class CrudSqlSource implements SqlSource {
  private SqlSourceBuilder sqlSourceParser;
  private Class<?> providerType;
  private Method providerMethod;
  private boolean providerTakesParameterObject;
  private Class<?> entityType;
  private boolean isJpaStyle = false;
  private Method mapperInterfaceMethod;

  public CrudSqlSource(Configuration config, CrudProvider provider, Class<?> entityType, Method mapperInterfaceMethod) {
    String providerMethodName = null;
    try {
      this.entityType = entityType;
      this.mapperInterfaceMethod = mapperInterfaceMethod;
      this.sqlSourceParser = new SqlSourceBuilder(config);
      this.providerType = provider.type();
      isJpaStyle = provider.isJpaStyle();
      providerMethodName = provider.method();
      if (StringUtils.isEmpty(providerMethodName)) {
        providerMethodName = mapperInterfaceMethod.getName();
      }
      if (isJpaStyle) {
        if (providerMethodName.startsWith("find")) {
          providerMethodName = "findWithJpaStyle";
        } else if (providerMethodName.startsWith("count")) {
          providerMethodName = "countWithJpaStyle";
        } else if (providerMethodName.startsWith("delete")) {
          providerMethodName = "deleteWithJpaStyle";
        } else {
          throw new BuilderException("only method startwith find/count/delete support jpa style");
        }
      }
      for (Method m : this.providerType.getMethods()) {
        if (providerMethodName.equals(m.getName())) {
          if (m.getParameterTypes().length < 2 && m.getReturnType() == String.class) {
            this.providerMethod = m;
            this.providerTakesParameterObject = m.getParameterTypes().length == 1;
          }
        }
      }
    } catch (Exception e) {
      throw new BuilderException("Error creating SqlSource for SqlProvider.  Cause: " + e, e);
    }
    if (this.providerMethod == null) {
      throw new BuilderException(
              "Error creating SqlSource for SqlProvider. Method '" + providerMethodName + "' not found in SqlProvider '"
                      + this.providerType.getName() + "'.");
    }
  }

  @Override
  public BoundSql getBoundSql(Object parameterObject) {
    SqlSource sqlSource = createSqlSource(parameterObject);
    return sqlSource.getBoundSql(parameterObject);
  }

  private SqlSource createSqlSource(Object parameterObject) {
    try {
      String sql;
      Object providerInstance = providerType.getConstructor(Class.class).newInstance(entityType);
      if (isJpaStyle) {
        sql = (String) providerMethod.invoke(providerInstance, mapperInterfaceMethod.getName());
      } else {
        if (providerTakesParameterObject) {
          if (parameterObject instanceof DefaultSqlSession.StrictMap) {
            DefaultSqlSession.StrictMap strictMap = (DefaultSqlSession.StrictMap) parameterObject;
            parameterObject = strictMap.get(strictMap.keySet().iterator().next());
          }
          sql = (String) providerMethod.invoke(providerInstance, parameterObject);
        } else {
          sql = (String) providerMethod.invoke(providerInstance);
        }
      }

      Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
      return sqlSourceParser.parse(sql, parameterType, new HashMap<String, Object>());
    } catch (Exception e) {
      throw new BuilderException(
              "Error invoking SqlProvider method (" + providerType.getName() + "." + providerMethod.getName()
                      + ").  Cause: " + e, e);
    }
  }

}
