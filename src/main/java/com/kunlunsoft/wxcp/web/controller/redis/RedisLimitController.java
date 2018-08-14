package com.kunlunsoft.wxcp.web.controller.redis;

import com.common.util.SystemHWUtil;
import com.kunlunsoft.util.RedisCacheUtil2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/limit", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
public class RedisLimitController {
    @ResponseBody
    @RequestMapping(value = "/redis/json", produces = SystemHWUtil.RESPONSE_CONTENTTYPE_JSON_UTF)
    public String jsonLimittest2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(required = false) String demo) {

        return RedisCacheUtil2.limitBlock();
    }
}
