/*
 * Copyright (C), 2015-2018
 * FileName: RedisTest
 * Author:   zhao
 * Date:     2018/8/6 16:00
 * Description: Redis的测试类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.redis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 〈一句话功能简述〉<br>
 * 〈Redis的测试类〉
 * 〈Redis工具,从<<Java游戏服务器开发>>一书中获取〉
 *
 * @author zhao
 * @date 2018/8/6 16:00
 * @see Redis
 * @since 1.0.1
 */
public class RedisTest {
  private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

  @Test
  public void test() {
    String host = "127.0.0.1";
    int port = 6379;
    int timeout = 2000;
    String password = "admin123";
    int databaseIndex = 16;
    logger.info("Redis at {}:{}", host, port);

    JedisPoolConfig poolConfig = new JedisPoolConfig();
    //设置最大连接数（100个足够用了，没必要设置太大）
    poolConfig.setMaxTotal(100);
    //最大空闲连接数
    poolConfig.setMaxIdle(10);
    //获取Jedis连接的最大等待时间（50秒）
    poolConfig.setMaxWaitMillis(50 * 1000);
    //在获取Jedis连接时，自动检验连接是否可用
    poolConfig.setTestOnBorrow(false);
    //在将连接放回池中前，自动检验连接是否有效
    poolConfig.setTestOnReturn(true);
    //自动测试池中的空闲连接是否都是可用连接
    poolConfig.setTestWhileIdle(true);

    Redis redis = Redis.getInstance();
    redis.createJedisPool(poolConfig, host, port, timeout, password, databaseIndex);

    /** redis save **/
    logger.info("=============redis save==============");
    // string save
    logger.info("string save:调用set时，若key不存在则添加key，否则为修改key对应的值");
    redis.set("testKey1", "test string val1");
    // set save
    logger.info("set save:set中的元素不允许出现重复且无序");
    redis.sadd("testKey2", "test set val1");
    redis.sadd("testKey2", "test set val2");
    redis.sadd("testKey2", "test set val3");
    // hash save
    logger.info("hash save:调用hset时，若key不存在则创建key，若hash中存在这个hashkey，则修改其值，不存在则添加一条hash数据");
    redis.hset("testKey3", "hashKey1", "hashVal1");
    redis.hset("testKey3", "hashKey2", "hashVal2");
    redis.hset("testKey3", "hashKey3", "hashVal3");
    redis.hset("testKey3", "hashKey4", "hashVal4");
    // list save
    logger.info("list save:数据在链表中是有序的，并可以重复添加数据");
    redis.lpush("testKey4", "test list val1");
    redis.lpush("testKey4", "test list val2");
    redis.lpush("testKey4", "test list val3");
    // sorted set save
    logger.info("sorted set save:有序set中的元素是有序的");
    redis.zadd("testKey5", 1, "test zset val1");
    redis.zadd("testKey5", 2, "test zset val2");
    redis.zadd("testKey5", 3, "test zset val3");
    redis.zadd("testKey5", 4, "test zset val4");
    /** redis get **/
    logger.info("=============redis get==============");
    // string get
    String stringRet = redis.get("testKey1");
    logger.info("string get:" + stringRet);
    // set get
    Set<String> setRet = redis.sget("testKey2");
    logger.info("set get:");
    for (String string : setRet) {
      logger.info(string + ",");
    }
    // hash get
    String hashKeyRet = redis.hget("testKey3", "hashKey2");
    logger.info("hash key get:" + hashKeyRet);
    Map<String, String> hashRet = redis.hgetAll("testKey3");
    logger.info("hash get:");
    for (String string : hashRet.keySet()) {
      logger.info("key[" + string + "]" + "value[" + hashRet.get(string) + "],");
    }
    // list get
    List<String> listRet = redis.lgetList("testKey4");
    logger.info("list get:");
    for (String string : listRet) {
      logger.info(string + ",");
    }
    // zset get
    long val2Rank = redis.zrank("testKey5", "test zset val2");
    logger.info("zset get val2 rank:" + val2Rank);
    Set<String> zsetRet = redis.zrange("testKey5", 0, 3);
    logger.info("zset get range:");
    for (String string : zsetRet) {
      logger.info(string + ",");
    }
    /** redis delete **/
    logger.info("=============redis delete==============");
    // string delete
    logger.info("string delete:调用Redis的del方法，可直接删除key，对于所有的数据类型来说，都可以通过这种方式直接删除整个key");
    redis.del("testKey1");
    // set delete
    logger.info("set delete:删除set中的val3");
    redis.sremove("testKey2", "test set val3");
    // hash delete
    logger.info("hash delete:删除hash中key为hashKey4的元素");
    redis.hdel("testKey3", "hashKey4");
    // list delete
    logger.info("list delete:删除list中值为test list val3的元素，其中count参数，0代表删除全部，正数代表正向删除count个此元素，负数代表负向删除count个此元素");
    redis.lrem("testKey4", 0, "test list val3");
    // zset delete
    logger.info("zset delete:同set删除元素的方式相同");
    redis.zrem("testKey5", "test zset val4");
    logger.info("除了以上常用api之外，还有更多api，在Redis类中都有列出，请参考Redis类，或直接参照Jedis的官方文档");
  }

}
