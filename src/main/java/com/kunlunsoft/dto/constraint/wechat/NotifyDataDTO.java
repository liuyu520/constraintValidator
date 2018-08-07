package com.kunlunsoft.dto.constraint.wechat;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author mixata@chanjet.com
 * @Date 2018-03-26
 */
@Data
public class NotifyDataDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private String value;

    private String color;

}
