package com.kunlunsoft.dto.constraint.wechat;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author mixata@chanjet.com
 * @Date 2018-03-26
 */
@Data
public class NotifyDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private String touser;

    private String url;

    private String template_id;

    private Map<String, NotifyDataDTO> data;

}
