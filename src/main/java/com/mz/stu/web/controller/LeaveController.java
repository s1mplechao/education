package com.mz.stu.web.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LeaveController {

    //学生请假跳转页面
    @RequestMapping("/leave/index")
    public String index(Model model){

        return "views/student/leave_list";
    }
    //老师审核跳转页面
    @RequestMapping("/audit/index")
    public String auditPage(Model model){

        return "views/teacher/audit_list";
    }
    //审核记录页面
    @RequestMapping("/history/index")
    public String history(Model model){

        return "views/history/history_auditlist";
    }
    //学生请假记录页面
    @RequestMapping("/history/leaveHistory")
    public String leaveHistory(Model model){

        return "views/history/history_leavelist";
    }


}
