package com.mz.stu.web.controller;


import com.mz.stu.entity.Course;
import com.mz.stu.entity.User;
import com.mz.stu.query.CourseQuery;
import com.mz.stu.serivce.ICourseService;
import com.mz.stu.serivce.IUserService;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Api(tags = "课程管理接口")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserService userService;


    @GetMapping("/course/index")
    @ApiOperation("跳转课程页接口")
    public String index(Model model){
        //查询所有的老师-新增的时候课程选择对应老师
        List<User> teachers = userService.findAll().stream().filter(user -> user.getType() == 2).collect(Collectors.toList());
        model.addAttribute("teachers",teachers);
        return "views/course/course_list";
    }

    @GetMapping("/course/listpage")
    @ApiOperation("查询课程分页数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CourseQuery", value = "课程查询对象", defaultValue = "courseQuery对象")
    })
    @ResponseBody
    public PageList listpage(CourseQuery courseQuery){
        return  courseService.listpage(courseQuery);
    }

    //添加课程
    @PostMapping("/course/addCourse")
    @ApiOperation("添加课程接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Course", value = "课程对象")
    })
    @ResponseBody
    public MzResult addCourse(Course course){
        try {
            courseService.addCourse(course);
            return new MzResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MzResult("添加失败");
    }


    //修改课程editSaveCourse
    @PostMapping("/course/editSaveCourse")
    @ApiOperation("修改课程接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Course", value = "修改课程对象")
    })
    @PreAuthorize("hasRole('管理员')")
    @ResponseBody
    public MzResult editSaveCourse(Course course){
        System.out.println("修改课程...."+course);
        try {
            courseService.editSaveCourse(course);
            return new MzResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MzResult("修改失败");
    }

    //删除课程
    @GetMapping("/course/deleteCourse")
    @ApiOperation("删除课程接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "如:88",required = true)
    })
    @PreAuthorize("hasRole('管理员')")
    @ResponseBody
    public MzResult deleteCourse(@RequestParam(required = true) Long id){
        MzResult mzResult = new MzResult();
        try {
            courseService.deleteCourse(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new MzResult("删除失败");
        }

        return mzResult;
    }

    @PostMapping(value="/course/deleteBatchCourse")
    @ApiOperation("批量课程接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "如:88,89,99")
    })
    @PreAuthorize("hasRole('管理员')")
    @ResponseBody
    public MzResult deleteBatchCourse(String ids){
        String[] idsArr = ids.split(",");
        List list = new ArrayList();
        for(int i=0;i<idsArr.length;i++){
            list.add(idsArr[i]);
        }
        try{
            courseService.batchRemove(list);
            return new MzResult();
        }catch(Exception e){
            return new MzResult("批量删除失败");
        }
    }
}
