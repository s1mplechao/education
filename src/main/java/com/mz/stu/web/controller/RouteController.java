package com.mz.stu.web.controller;

import com.mz.stu.entity.Classes;
import com.mz.stu.serivce.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RouteController {

    @Autowired
    private IClassesService classesService;

    /**
     * 登录失败
     */
    @RequestMapping("/error403")
    public String loginError() {
        return "error403";
    }

    /**
     * 后台主页
     * @return
     */
    @RequestMapping("/main")
    public String main() {
        return "views/main";
    }

    /**
     * 跳转注册页面
     */
    @RequestMapping("/goRegPage")
    public String goRegPage(Model model) {
        //查询班级数据
        List<Classes> classes = classesService.queryAll();
        model.addAttribute("classes",classes);
        return "views/reg";
    }




}
