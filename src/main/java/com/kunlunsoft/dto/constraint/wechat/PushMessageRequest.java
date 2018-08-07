/* * Copyright 2014-2016 Chanjet Information Technology Company Limited. */
package com.kunlunsoft.dto.constraint.wechat;

import lombok.Data;
import com.kunlunsoft.annotation.ObjColumnEitherHasVal;

import java.util.Map;

/**
 * @author 黄威  <br>
 * 2018-08-07 10:01:25
 */
@Data
public class PushMessageRequest implements java.io.Serializable {
    @ObjColumnEitherHasVal(columns = {"sceneCode", "templateId"}, message = "template 不能为空")
    private MsgTemplate template;
    private Map<String, NotifyDataDTO> data;
    //    @NotNull
    private String wechatAppId;
    private String link;
    //    @NotNull
    private PushTarget target;


}