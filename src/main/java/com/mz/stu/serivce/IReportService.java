package com.mz.stu.serivce;

import com.mz.stu.vo.ReportVo;

import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 报表Service层
 */
public interface IReportService {
    //课程平均分报表
    List<ReportVo> courseAvgReport();
}
