package com.mz.stu.vo;

import lombok.Data;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 课程分数统计
 */
@Data
public class ReportVo {
    //课程名称
    private  String name;

    //课程平均分
    private double avgScore;
}
