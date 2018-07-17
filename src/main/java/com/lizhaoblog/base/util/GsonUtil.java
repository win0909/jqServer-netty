/*
 * Copyright (C), 2015-2018
 * FileName: GsonUtil
 * Author:   zhao
 * Date:     2018/7/12 16:10
 * Description: gson的使用
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.util;

import com.google.gson.Gson;

/**
 * 〈一句话功能简述〉<br>
 * 〈gson的使用〉
 *
 * @author zhao
 * @date 2018/7/12 16:10
 * @since 1.0.1
 */
public class GsonUtil {

    private static final Gson gson = new Gson();
    public static Gson getGson(){
        return gson;
    }
    public static <T> T fromJson(String json, Class<T> classOfT){
        T t = gson.fromJson(json, classOfT);
        return t;
    }

}
