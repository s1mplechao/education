package com.mz.stu.mapper;

import com.mz.stu.vo.ReportVo;

import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-22
 * @Description:
 */
public interface ReportMapper {

    //课程平均分报表
    List<ReportVo> courseAvgReport();
}
