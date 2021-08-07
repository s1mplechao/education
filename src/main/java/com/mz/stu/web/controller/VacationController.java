package com.mz.stu.web.controller;


import com.mz.stu.entity.VacTask;
import com.mz.stu.entity.Vacation;
import com.mz.stu.query.BaseQuery;
import com.mz.stu.serivce.activi.VacationService;

import com.mz.stu.util.CommonUtils;
import com.mz.stu.util.MzResult;
import com.mz.stu.util.PageList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 请假
 */
@RestController
public class VacationController {

    @Resource
    private VacationService vacationService;

    @PostMapping("/startVac")
    public MzResult startVac(Vacation vac) {
        try {
            String userName = CommonUtils.getLoginUser().getUsername();
             vacationService.startVac(userName, vac);
            return new MzResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MzResult("请假失败");


    }

    //学生申请的请假
    @GetMapping("/myVac")
    public PageList myVac(BaseQuery baseQuery) {

        String userName = CommonUtils.getLoginUser().getUsername();
        return vacationService.myVac(userName,baseQuery);

    }

    //待我审核的数据
    @GetMapping("/myAudit")
    public PageList myAudit(BaseQuery baseQuery) {
        PageList pageList  = new PageList();
        String userName = CommonUtils.getLoginUser().getUsername();
        pageList = vacationService.myAudit(userName,baseQuery);

        return pageList;
    }

    //老师审核数据
    @PostMapping("/passAudit")
    public MzResult passAudit( @RequestBody VacTask vacTask) {
        try {
            String userName = CommonUtils.getLoginUser().getUsername();
            vacationService.passAudit(userName, vacTask);
            return new MzResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MzResult("审核失败");
    }
    //我申请过的记录
    @GetMapping("/myVacRecord")
    public PageList myVacRecord(BaseQuery baseQuery) {

        String userName = CommonUtils.getLoginUser().getUsername();
        return vacationService.myVacRecord(userName,baseQuery);

    }

    //我的审核记录
    @GetMapping("/myAuditRecord")
    public PageList myAuditRecord(BaseQuery baseQuery) {
        String userName = CommonUtils.getLoginUser().getUsername();
        return vacationService.myAuditRecord(userName,baseQuery);

    }

}
