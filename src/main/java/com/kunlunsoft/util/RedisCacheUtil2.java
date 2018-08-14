package com.kunlunsoft.util;

import com.common.util.RedisHelper;

import java.util.Map;

public class RedisCacheUtil2 {
    public static String REDISKEY_SUITETICKET = "wx_suiteTicket";

    public static void saveSuiteTicket(String suiteTicket) {
        RedisHelper.getInstance().clearCache(REDISKEY_SUITETICKET);
        RedisHelper.getInstance().saveExpxKeyCache(REDISKEY_SUITETICKET, suiteTicket, 3600);
    }

    public static String getSuiteTicket() {
        return RedisHelper.getInstance().getCache(REDISKEY_SUITETICKET);
    }

    public static void acceptRequest(String orderNo) {
        RedisHelper.getInstance().saveKeyCacheExpire1day("pushLimit", orderNo, String.valueOf(System.currentTimeMillis()));
    }

    public static Map getPushRecordList() {
        return RedisHelper.getInstance().getAllKeyCache("pushLimit");
    }

    /***
     * 限制一个小时
     */
    public static String limitBlock() {
        //如果没有key,则返回"OK"
        String result = RedisHelper.getInstance().saveExpxKeyCache("pushLimitBlocked", "0", 3600);
        if (RedisHelper.isOk(result)) {
            System.out.println(" success:");
        } else {
            System.out.println(" failed !!!");
        }
        return result;
    }


}
