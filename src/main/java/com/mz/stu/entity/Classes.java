package com.mz.stu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: mfc
 * @Date: 2021-04-10
 * @Description:班级
 */
@Data
public class Classes {
    private Long id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
