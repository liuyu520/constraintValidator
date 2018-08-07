package com.kunlunsoft.constraint;

import com.kunlunsoft.dto.constraint.wechat.MsgTemplate;
import com.kunlunsoft.dto.constraint.wechat.PushMessageRequest;
import oa.util.constraint.ValidatorUtil;
import org.junit.Test;

public class Test3 {

    /***
     * 2018/8/7
     */
    @Test
    public final void test_Validate() {
        PushMessageRequest pushMessageRequest = new PushMessageRequest();
        MsgTemplate template = new MsgTemplate();
        template.setTemplateId("aa");
        pushMessageRequest.setTemplate(template);
        ValidatorUtil.validate(pushMessageRequest);
    }
}
