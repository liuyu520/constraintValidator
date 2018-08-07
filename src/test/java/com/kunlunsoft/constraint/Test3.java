package com.kunlunsoft.constraint;

import com.kunlunsoft.dto.constraint.wechat.MsgTemplate;
import com.kunlunsoft.dto.constraint.wechat.PushMessageRequest;
import com.kunlunsoft.dto.constraint.wechat.PushTarget;
import oa.util.constraint.ValidatorUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test3 {

    /***
     * 2018/8/7
     */
    @Test
    public final void test_Validate() {
        PushMessageRequest pushMessageRequest = new PushMessageRequest();
        MsgTemplate template = new MsgTemplate();
//        template.setTemplateId("aa");
        template.setSceneCode(2l);
        pushMessageRequest.setTemplate(template);

        PushTarget pushTarget = new PushTarget();
        List<Long> openIds = new ArrayList<>();
        openIds.add(11l);
        pushTarget.setOpenIds(openIds);
        pushMessageRequest.setTarget(pushTarget);

        ValidatorUtil.validate(pushMessageRequest);
    }
}
