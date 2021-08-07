package com.mz.stu.entity;

import lombok.Data;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 分数
 */
@Data
public class Score extends BaseEntity {
    private Long userid;//学生id
    private User user;//学生用户
    private String score;//得分
    private Long courseid;//课程号
    private Course course;//课程实体对象
}
