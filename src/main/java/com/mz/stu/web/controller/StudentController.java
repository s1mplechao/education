package com.mz.stu.web.controller;


import com.mz.stu.entity.User;
import com.mz.stu.query.UserQuery;
import com.mz.stu.serivce.ICourseService;
import com.mz.stu.serivce.IUserService;

import com.mz.stu.util.CommonUtils;
import com.mz.stu.util.MzResult;
import com.mz.stu.util.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Api(tags = "教师管理接口")
public class StudentController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;


    //后台查询学生列表
    @RequestMapping("/student/index")
    public String index(Model model){
        //用户首页
        model.addAttribute("courses",courseService.queryAll());
        return "views/student/student_list";
    }

    @RequestMapping("/student/listpage")
    @ResponseBody
    public PageList listpage(UserQuery userQuery){
        userQuery.setType(3L);//2表示老师 3表示学生
        return  userService.listpage(userQuery);
    }

    //修改用户editSaveUser
    @PostMapping("/student/editSaveStu")
    @ApiOperation("修改用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User", value = "修改用户对象")
    })
    @PreAuthorize("hasRole('管理员')")
    @ResponseBody
    public MzResult editSaveStu(User user){
        try {
            userService.editSaveUser(user);
            return new MzResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MzResult("修改失败");
    }


    @PostMapping("/student/editSaveStuXk")
    @ApiOperation("修改用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "User", value = "学生选课")
    })
    @ResponseBody
    public MzResult editSaveStuXk(User user){
        try {
            User sessionUser = new User();
            sessionUser = CommonUtils.getLoginUser();
            String userName = user.getUsername();
            String sessionName = sessionUser.getUsername();
            if (userName.equals(sessionName)){
                userService.editSaveXk(user);
                return new MzResult();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MzResult("修改失败");
    }

}
