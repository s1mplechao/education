package com.mz.stu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 请假
 */
@Data
public class Vacation {

    /**
     * 申请人
     */
    private String applyUser;
    private int days;
    private String reason;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date applyTime;
    private String applyStatus;

    /**
     * 审核人
     */
    private String auditor;
    private String result;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date auditTime;



}
