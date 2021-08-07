package com.mz.stu.web.controller;

import com.mz.stu.serivce.IReportService;
import com.mz.stu.vo.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 学生/选课/考勤/成绩  管理系统报表
 */
@Controller
public class ChartController {

    @Autowired
    private IReportService reportService;

    @GetMapping("/report/index")
    public String index(Model model){
        //查询 课程平均分数据
        List<ReportVo> reportVos = reportService.courseAvgReport();
        //存入model
        model.addAttribute("courseAvgs",reportVos);
        //返回页面
        return "views/report/report_list";
    }
}
