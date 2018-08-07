/*
 * Copyright (C), 2015-2018
 * FileName: Redis
 * Author:   zhao
 * Date:     2018/8/4 10:30
 * Description: Redis工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.redis;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

/**
 * 〈一句话功能简述〉<br>
 * 〈Redis工具,从<<Java游戏服务器开发>>一书中获取〉
 *  <具体简单入门可以查看：https://blog.csdn.net/cmqwan/article/details/81481522>
 * @author zhao
 * @date 2018/8/4 10:30
 * @since 1.0.1
 */
public class Redis {
  private static Redis instance;
  private static Logger logger = LoggerFactory.getLogger(Redis.class);

  private JedisPool pool;

  public static Redis getInstance() {
    if (instance == null) {
      instance = new Redis();
      instance.init();
    }
    return instance;
  }

  public void init() {
    //    ApplicationContext applicationContext = ServerConfig.getInstance().getApplicationContext();
    ApplicationContext applicationContext = null;
    if (applicationContext != null && applicationContext.getBean("jedisPool") != null) {
      pool = (JedisPool) applicationContext.getBean("jedisPool");
    } else {
      String host = "127.0.0.1";
      int port = 6379;
      int timeout = 2000;
      String password = "admin123";
      int databaseIndex = 2;
      logger.info("Redis at {}:{}", host, port);
      //    pool = new JedisPool(host, port);
      //    pool.

      JedisPoolConfig config = new JedisPoolConfig();
      //设置最大连接数（100个足够用了，没必要设置太大）
      config.setMaxTotal(100);
      //最大空闲连接数
      config.setMaxIdle(10);
      //获取Jedis连接的最大等待时间（50秒）
      config.setMaxWaitMillis(50 * 1000);
      //在获取Jedis连接时，自动检验连接是否可用
      config.setTestOnBorrow(false);
      //在将连接放回池中前，自动检验连接是否有效
      config.setTestOnReturn(true);
      //自动测试池中的空闲连接是否都是可用连接
      config.setTestWhileIdle(true);
      //创建连接池
      //    pool = new JedisPool(config, PropKit.use(configFile).get("redisURL"),
      //            PropKit.use(configFile).getInt("redisPort"));
      pool = new JedisPool(config, host, port, timeout, password, databaseIndex);
    }
  }

  /**
   * 获取Jedis对象
   *
   * @return Jedis
   */
  public synchronized Jedis getJedis() {
    Jedis jedis = null;
    if (pool != null) {
      try {
        if (jedis == null) {
          jedis = pool.getResource();
        }
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
    return jedis;
  }

  //  public void select(int index) {
  //    Jedis jedis = pool.getResource();
  //    jedis.auth(password);
  //    jedis.select(index);
  //    jedis.close();
  //  }

  public Map<String, String> hgetAll(String key) {
    if (key == null) {
      return null;
    }
    Jedis jedis = getJedis();
    //

    Map<String, String> ret = jedis.hgetAll(key);
    jedis.close();
    return ret;
  }

  public static Map<String, String> objectToHash(Object obj)
          throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Map<String, String> map = new HashMap<String, String>();
    BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
    for (PropertyDescriptor property : propertyDescriptors) {
      if (!"class".equals(property.getName())) {
        map.put(property.getName(), "" + property.getReadMethod().invoke(obj));
      }
    }
    return map;
  }

  public static void hashToObject(Map<?, ?> map, Object obj) throws IllegalAccessException, InvocationTargetException {
    for (Map.Entry<?, ?> entry : map.entrySet()) {
      if ("null".equals(entry.getValue())) {
        entry.setValue(null);
      }
    }
    BeanUtils.populate(obj, (Map) map);
  }

  @SuppressWarnings("unchecked")
  public static <T> T hashToObject(Map<?, ?> map, Class c)
          throws IllegalAccessException, InvocationTargetException, InstantiationException {
    Object obj = c.newInstance();
    hashToObject(map, obj);
    return (T) obj;
  }

  public List<String> hmget(String key, String... fields) {
    Jedis jedis = getJedis();

    List<String> ret = jedis.hmget(key, fields);

    jedis.close();
    return ret;
  }

  public String hmset(String key, Object object)
          throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
    Jedis jedis = getJedis();

    String ret = jedis.hmset(key, objectToHash(object));
    jedis.close();
    return ret;
  }

  public String hmset(String key, Map<String, String> fields)
          throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
    Jedis jedis = getJedis();

    String ret = jedis.hmset(key, fields);
    jedis.close();
    return ret;
  }

  public boolean hexist(String key, String field) {
    if (key == null) {
      return false;
    }
    Jedis jedis = getJedis();

    boolean ret = jedis.hexists(key, field);
    jedis.close();
    return ret;
  }

  public Long hdel(String key, String... fields) {
    Jedis jedis = getJedis();

    Long cnt = jedis.hdel(key, fields);
    jedis.close();
    return cnt;
  }

  public String hget(String key, String field) {
    if (key == null) {
      return null;
    }
    Jedis jedis = getJedis();

    String ret = jedis.hget(key, field);
    jedis.close();
    return ret;
  }

  public void hset(String key, String field, String value) {
    if (field == null || field.length() == 0) {
      return;
    }
    if (value == null || value.length() == 0) {
      return;
    }
    Jedis jedis = getJedis();

    jedis.hset(key, field, value);
    jedis.close();
  }

  /**
   * Map 的存放和获取
   */
  public void add(String group, Map<String, String> values) {
    if (values == null || values.size() == 0) {
      return;
    }
    Jedis jedis = getJedis();

    jedis.hmset(group, values);
    jedis.close();
  }

  public void add(String group, String key, String value) {
    if (value == null || key == null) {
      return;
    }
    Jedis jedis = getJedis();

    jedis.hset(group, key, value);
    jedis.close();
  }

  public void set(String key, String value) {
    if (value == null || key == null) {
      return;
    }
    Jedis jedis = getJedis();
    jedis.set(key, value);
    jedis.close();
  }

  public Long hDelBuilder(String group, String... keys) {
    Jedis jedis = getJedis();

    byte[][] fields = new byte[keys.length][];
    for (int i = 0; i < keys.length; i++) {
      fields[i] = keys[i].getBytes();
    }
    Long cnt = jedis.hdel(group.getBytes(), fields);
    jedis.close();
    return cnt;
  }

  public Map<String, String> getMap(String group) {
    Jedis jedis = getJedis();
    Map<String, String> ret = jedis.hgetAll(group);
    jedis.close();
    return ret;
  }

  public String get(String key) {
    Jedis jedis = getJedis();
    String ret = jedis.get(key);
    jedis.close();
    return ret;
  }

  /**
   * 添加元素到集合中
   *
   * @param key
   * @param element
   */
  public boolean sadd(String key, String... element) {
    if (element == null || element.length == 0) {
      return false;
    }
    Jedis jedis = getJedis();
    boolean success = jedis.sadd(key, element) == 1;
    jedis.close();
    return success;
  }

  public boolean smove(String oldKey, String newKey, String element) {
    if (element == null) {
      return false;
    }
    Jedis jedis = getJedis();
    boolean success = (jedis.smove(oldKey, newKey, element) == 1);
    jedis.close();
    return success;
  }

  /**
   * 删除指定set内的元素
   */
  public boolean sremove(String key, String... element) {
    if (element == null) {
      return false;
    }
    Jedis jedis = getJedis();
    boolean success = (jedis.srem(key, element) == 1);
    jedis.close();
    return success;
  }

  public Set<String> sget(String key) {
    Jedis jedis = getJedis();
    Set<String> m = jedis.smembers(key);
    jedis.close();
    return m;
  }

  /**
   * 返回set的的元素个数
   *
   * @param key
   * @return
   * @Title: zcard
   * @Description:
   */
  public long scard(String key) {
    Jedis jedis = getJedis();
    long size = jedis.scard(key);
    jedis.close();
    return size;
  }

  public void laddList(String key, String... elements) {
    if (elements == null || elements.length == 0) {
      return;
    }
    Jedis jedis = getJedis();
    jedis.lpush(key, elements);
    jedis.close();
  }

  /**
   * @param key
   * @param id
   * @Title: lpush
   * @Description:
   */
  public void lpush(String key, String id) {
    Jedis jedis = getJedis();
    jedis.lpush(key, id);
    jedis.close();
  }

  public void rpush(String key, String id) {
    Jedis jedis = getJedis();
    jedis.rpush(key, id);
    jedis.close();
  }

  /**
   * add by wangzhuan
   *
   * @param key
   * @param start
   * @param end
   * @return
   * @Title: lrange
   * @Description:
   */
  public List<String> lrange(String key, int start, int end) {
    Jedis jedis = getJedis();
    List<String> list = jedis.lrange(key, start, end);
    jedis.close();
    return list;
  }

  public List<String> lgetList(String key) {
    Jedis jedis = getJedis();
    long len = jedis.llen(key);
    List<String> ret = jedis.lrange(key, 0, len - 1);
    jedis.close();
    return ret;
  }

  /**
   * 列表list中是否包含value
   *
   * @param key
   * @param value
   * @return
   */
  public boolean lexist(String key, String value) {
    List<String> list = lgetList(key);
    return list.contains(value);
  }

  public List<String> lgetList(String key, long len) {
    Jedis jedis = getJedis();
    long max = jedis.llen(key);
    long l = max > len ? len : max;
    List<String> ret = jedis.lrange(key, 0, l - 1);
    jedis.close();
    return ret;
  }

  public Long del(String key) {
    Jedis jedis = getJedis();
    Long cnt = jedis.del(key);
    jedis.close();
    return cnt;
  }

  /**
   * 模糊删除
   *
   * @param key
   * @return
   */
  public Long delKeyLikes(String key) {
    Jedis jedis = getJedis();
    Set<String> keys = jedis.keys(key);
    Long cnt = jedis.del(keys.toArray(new String[keys.size()]));
    jedis.close();
    return cnt;
  }

  /**
   * 测试元素是否存在
   *
   * @param key
   * @param element
   * @return
   */
  public boolean sexist(String key, String element) {
    Jedis jedis = getJedis();
    boolean ret = jedis.sismember(key, element);
    jedis.close();
    return ret;
  }

  /**
   * 判断某一个key值得存储结构是否存在
   *
   * @param key
   * @return
   * @Title: exist
   * @Description:
   */
  public boolean exist(String key) {
    Jedis jedis = getJedis();
    boolean yes = jedis.exists(key);
    jedis.close();
    return yes;
  }

  /**********************************************************************
   * 排行用到的SortedSet
   **********************************************************************/
  public long zadd(String key, int score, String member) {
    Jedis jedis = getJedis();
    long ret = 0;
    try {
      ret = jedis.zadd(key, score, member);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    } finally {
      jedis.close();
    }
    return ret;
  }

  /**
   * 添加 分数，并返回修改后的值
   *
   * @param key
   * @param update
   * @param member
   * @return
   */
  public double zincrby(String key, int update, String member) {
    Jedis jedis = getJedis();
    double ret = jedis.zincrby(key, update, member);
    jedis.close();
    return ret;
  }

  /**
   * 返回有序集 key 中，成员 member 的 score 值,存在返回score，不存在返回-1
   *
   * @param key
   * @param member
   * @return
   */
  public double zscore(String key, String member) {
    Jedis jedis = getJedis();
    Double ret = jedis.zscore(key, member);
    jedis.close();
    if (ret == null) {
      return -1;
    }
    return ret;
  }

  /**
   * 按 从大到小的排名，获取 member的 排名
   *
   * @param key
   * @param member
   * @return
   */
  public long zrevrank(String key, String member) {
    Jedis jedis = getJedis();
    long ret = jedis.zrevrank(key, member);
    jedis.close();
    return ret;
  }

  /**
   * 按照score的值从小到大排序，返回member的排名 排序是从0开始
   *
   * @param key
   * @param member
   * @return 设置为名次从1开始。返回为-1，表示member无记录
   * @Title: zrank
   * @Description:
   */
  public long zrank(String key, String member) {
    Jedis jedis = getJedis();
    long ret = -1;
    Long vv = jedis.zrank(key, member);
    if (vv != null) {
      ret = vv.longValue();
    }
    jedis.close();
    if (ret != -1) {
      ret += 1;
    }
    return ret;
  }

  /**
   * 返回的是score的值
   *
   * @param key
   * @param member
   * @return 返回有序集 key 中，成员 member 的 score 值 如果 member 元素不是有序集 key 的成员，或 key
   * 不存在，返回 null 。
   * @Title: zscore
   * @Description:
   */
  public int zscoreDouble(String key, String member) {
    Jedis jedis = getJedis();
    int ret = -1;
    Double vv = jedis.zscore(key, member);
    if (vv != null) {
      ret = (int) vv.doubleValue();
    }
    jedis.close();
    if (ret != -1) {
      ret += 1;
    }
    return ret;
  }

  /**
   * min 和max 都是score的值
   *
   * @param key
   * @param min
   * @param max
   * @return
   * @Title: zrangebyscore
   * @Description:
   */
  // add 20141216
  public Set<String> zrangebyscore(String key, long min, long max) {
    Jedis jedis = getJedis();
    Set<String> ss = jedis.zrangeByScore(key, min, max);
    jedis.close();
    return ss;
  }

  public Set<String> zrange(String key, long min, long max) {
    Jedis jedis = getJedis();
    Set<String> ss = jedis.zrange(key, min, max);
    jedis.close();
    return ss;
  }

  /**
   * min 和max 都是score的值 获得一个包含了score的元组集合. 元组（Tuple）
   * 笛卡尔积中每一个元素（d1，d2，…，dn）叫作一个n元组（n-tuple）或简称元组
   *
   * @param key
   * @param min
   * @param max
   * @return
   * @Title: zrangebyscorewithscores
   * @Description:
   */
  public Set<Tuple> zrangebyscorewithscores(String key, long min, long max) {
    Jedis jedis = getJedis();
    Set<Tuple> result = null;
    try {
      result = jedis.zrangeByScoreWithScores(key, min, max);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    } finally {
      jedis.close();
    }
    return result;
  }

  /**
   * zrevrangeWithScores ： 从大到小排序 zrangeWithScores ： 从小到大排序
   *
   * @param key
   * @param start ： （排名）0表示第一个元素，-x：表示倒数第x个元素
   * @param end   ： （排名）-1表示最后一个元素（最大值）
   * @return 返回 排名在start 、end之间带score元素
   * @Title: zrangeWithScores
   * @Description:
   */
  public Map<String, Double> zrevrangeWithScores(String key, long start, long end) {
    Jedis jedis = getJedis();
    Set<Tuple> result = null;
    try {
      result = jedis.zrevrangeWithScores(key, start, end);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    } finally {
      jedis.close();
    }
    return tupleToMap(result);
  }

  /**
   * @param tupleSet
   * @return Map<String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               Double> ： 返回的是 有序<element, score>
   * @Title: tupleToMap
   * @Description:
   */
  public Map<String, Double> tupleToMap(Set<Tuple> tupleSet) {
    if (tupleSet == null) {
      return null;
    }
    Map<String, Double> map = new LinkedHashMap<String, Double>();
    for (Tuple tup : tupleSet) {
      map.put(tup.getElement(), tup.getScore());
    }
    return map;
  }

  /**
   * 删除key中的member
   *
   * @param key //   * @param member
   * @return
   * @Title: zrem
   * @Description:
   */
  public long zrem(String key, String member) {
    Jedis jedis = getJedis();
    long result = -1;
    try {
      result = jedis.zrem(key, member);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    } finally {
      jedis.close();
    }
    return result;
  }

  /**
   * 从高到低排名，返回前 num 个score和member
   *
   * @param key
   * @param num
   * @return
   */
  public Set<Tuple> ztopWithScore(String key, int num) {
    if (num <= 0) {
      return null;
    }
    Jedis jedis = getJedis();
    Set<Tuple> ret = jedis.zrevrangeWithScores(key, 0, num - 1);
    jedis.close();
    return ret;
  }

  /**
   * 返回score区间的member
   *
   * @param key
   * @param max
   * @param min
   * @return
   */
  public Set<String> zrankByScore(String key, int max, int min) {
    Jedis jedis = getJedis();
    Set<String> ret = jedis.zrevrangeByScore(key, max, min);
    jedis.close();
    return ret;
  }

  /**
   * 从高到低排名，返回前 num 个
   *
   * @param key
   * @param num
   * @return
   */
  public Set<String> ztop(String key, int num) {
    if (num <= 0) {
      return null;
    }
    Jedis jedis = getJedis();
    Set<String> ret = jedis.zrevrange(key, 0, num - 1);
    jedis.close();
    return ret;
  }

  /**
   * 从高到低排名，返回start到end的前 num 个
   *
   * @param key 排序方式
   * @return set
   */
  public Set<String> ztop(String key, int start, int end) {
    if (end <= start) {
      return Collections.emptySet();
    }
    Jedis jedis = getJedis();
    Set<String> ret = jedis.zrevrange(key, start, end - 1);
    jedis.close();
    return ret;
  }

  public long zcard(String key) {
    Jedis jedis = getJedis();
    long size = jedis.zcard(key);
    jedis.close();
    return size;
  }

  public static void destroy() {
    getInstance().pool.destroy();
  }

  public void publish(String channel, String message) {
    if (channel == null || message == null) {
      return;
    }
    Jedis jedis = getJedis();
    jedis.publish(channel, message);
    jedis.close();
  }

  public String lpop(String key) {
    Jedis jedis = getJedis();
    String value = jedis.lpop(key);
    jedis.close();
    return value;
  }

  public void lrem(String key, int count, String value) {
    Jedis jedis = getJedis();
    jedis.lrem(key, count, value);
    jedis.close();
  }

}
