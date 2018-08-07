/* * Copyright 2014-2016 Chanjet Information Technology Company Limited. */
package com.kunlunsoft.dto.constraint.wechat;

import lombok.Data;

import java.util.List;

/**
 * @author 黄威  <br>
 * 2018-08-07 10:01:25
 */
@Data
public class PushTarget implements java.io.Serializable {
    private List<Long> userIds;
    private List<Long> openIds;


}