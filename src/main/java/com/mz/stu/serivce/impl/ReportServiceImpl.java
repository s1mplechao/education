package com.mz.stu.serivce.impl;

import com.mz.stu.mapper.ReportMapper;
import com.mz.stu.serivce.IReportService;
import com.mz.stu.vo.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: TODO
 * @author: soulcoder-码仔项目库分享圈
 * @qq: 交流咨询 qq2579692606
 * @datetime: 2020/7/1 18:32
 */
@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    //课程平均分报表
    @Override
    public List<ReportVo> courseAvgReport() {
        return reportMapper.courseAvgReport();
    }
}
