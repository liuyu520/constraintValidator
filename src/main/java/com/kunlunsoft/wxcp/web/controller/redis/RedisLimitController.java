package com.kunlunsoft.wxcp.web.controller.redis;

import com.common.bean.BaseResponseDto;
import com.common.util.SortList;
import com.common.util.SystemHWUtil;
import com.kunlunsoft.util.RedisCacheUtil2;
import com.string.widget.util.ValueWidget;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/limit", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
public class RedisLimitController {
    @ResponseBody
    @RequestMapping(value = "/redis/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonLimittest2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(required = false) String demo) {

        return RedisCacheUtil2.limitBlock();
    }

    public static boolean isLimit() {
        Map records = RedisCacheUtil2.getPushRecordList();
        if (ValueWidget.isNullOrEmpty(records)) {
            return false;
        }
        List<String> timestamps = new ArrayList<String>(records.values());
        SortList<String> sortUtil = new SortList<String>();
        sortUtil.sort(timestamps, null, "desc");

        int limitCount = 11;
        int limitTime = 60;//1小时,单位:秒

        int length = timestamps.size();
        if (length < 2) {
            //没有超过限制
            return false;
        }
        int toIndex = 0;//要截取的最大序号
        if (limitCount > length) {
            toIndex = length;
        } else {
            toIndex = limitCount;
        }
        List<String> result = timestamps.subList(0, toIndex);
        long delter = Long.parseLong(result.get(0)) - Long.parseLong(result.get(toIndex - 1));
        long delterSecond = delter;
        System.out.println(delterSecond);
        if (delterSecond < limitTime) {
            System.out.println("超限");
            return true;
        } else {
            System.out.println("可以继续发短信");
            return false;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/push/accept/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonPut2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(required = false) String orderNo) {
        RedisCacheUtil2.acceptRequest(orderNo);
        if (isLimit()) {
            return new BaseResponseDto().setErrorMessage("您超过了限制").toJson();
        }
        return BaseResponseDto.jsonValue(orderNo);
    }
}
