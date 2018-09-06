package com.kunlunsoft.util;

import com.common.util.SortList;
import com.io.hw.json.HWJacksonUtils;
import com.string.widget.util.ValueWidget;

import java.util.List;

/**
 * 限制流量. <br />
 * 思路:限制次数limitCount 的时间窗口长度是否小于限制时间
 *
 * @author huangweii
 * @since 2018/9/6
 */
public class RateLimitUtil {
    /***
     *
     * @param n : 参照的当前时间,单位:毫秒
     * @param limitCount
     * @param limitTimeMillis : 毫秒
     * @param timestamps
     * @return
     */
    public static boolean checkLimit(long n, int limitCount, int limitTimeMillis, List<String> timestamps) {
        if (ValueWidget.isNullOrEmpty(timestamps)) {
            return false;
        }
        int length = timestamps.size();
        if (length < limitCount) {
            //没有超过限制
            return false;
        }
        SortList<String> sortUtil = new SortList<String>();
        sortUtil.sort(timestamps, null, "desc");


        int toIndex = limitCount;//要截取的最大序号
        /*if (limitCount + 1 > length) {
            toIndex = length;
        } else {*/
//        }
        List<String> result = timestamps.subList(0, toIndex);
        //和当前时间比较

        System.out.println("n :" + n);
        long delter = /*result.get(0))*/n - Long.parseLong(result.get(toIndex - 1));
        long delterSecond = delter;
        System.out.println("delter :" + delter);
        System.out.println(delterSecond);
        if (delterSecond < limitTimeMillis) {
            System.out.println("record :" + HWJacksonUtils.getJsonP(result));
            System.out.println("timestamps :" + HWJacksonUtils.getJsonP(timestamps));
            System.out.println("超限");
            return true;
        } else {
            System.out.println("可以继续发短信");
            return false;
        }
    }

}
