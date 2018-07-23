/*
 * Copyright (C), 2015-2018
 * FileName: ScheduledTask
 * Author:   zhao
 * Date:     2018/7/19 15:05
 * Description: 定时任务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.server.biz.scheduled;

import com.lizhaoblog.base.session.Session;
import com.lizhaoblog.base.session.SessionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈定时任务〉
 *
 * @author zhao
 * @date 2018/7/19 15:05
 * @since 1.0.1
 */
@Component
@Scope("singleton")
public class ScheduledTask {

  private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

  private int count = 0;

  @Scheduled(fixedRate = 15000)
  public void test() {
    count++;
    Session[] sessionArray = SessionManager.getInstance().getSessionArray();
    int length = sessionArray.length;
    logger.info("ScheduledTask " + count + " 在线人数：" + length);
  }

}
