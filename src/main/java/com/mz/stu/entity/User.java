package com.mz.stu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 用户
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String tel;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Boolean sex;
    private String headImg;
    private Integer type; //type=1 是管理员  type=2是老师
    private List<Role> roles = new ArrayList();//用户对应的角色集合

    /**
     *     学生学号
     */
    private String stunum;

    /**
     * 班级id
     */
    private Long classesid;
    /**
     * 学生所属班级
     */
    private Classes classes;

    /**
     *  学生选课信息
     */
    private Long courseid;
    /**
     *  学生选课
     */
    private Course course;

    //用户组id
    private String groupId;

    public User() {
    }
}
