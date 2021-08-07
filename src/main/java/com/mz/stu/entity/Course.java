package com.mz.stu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 课程列表
 */
@Data
public class Course {
    //课程编号
    private Long id;
    //课程名称
    private String name;
    //课程老师的编号
    private Long tid;
    //课程所属老师
    private User user;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
}
