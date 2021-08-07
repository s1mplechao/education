package com.mz.stu.web.controller;


import com.mz.stu.entity.Classes;
import com.mz.stu.query.ClassesQuery;

import com.mz.stu.serivce.IClassesService;
import com.mz.stu.util.MzResult;
import com.mz.stu.util.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Api(tags = "班级管理接口")
public class ClassesController {
    @Autowired
    private IClassesService classesService;


    @GetMapping("/classes/index")
    @ApiOperation("跳转班级页接口")
    public String index(){
        return "views/classes/classes_list";
    }

    @GetMapping("/classes/listpage")
    @ApiOperation("查询班级分页数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ClassesQuery", value = "班级查询对象", defaultValue = "classesQuery对象")
    })
    @ResponseBody
    public PageList listpage(ClassesQuery classesQuery){
        return  classesService.listpage(classesQuery);
    }


    @PostMapping("/classes/addClasses")
    @ApiOperation("添加班级接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Classes", value = "班级对象")
    })
    @ResponseBody
    public MzResult addClasses(Classes classes){
        try {
            classesService.addClasses(classes);
            return new MzResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MzResult("添加失败");
    }


    /**
     *   修改班级editSaveClasses
     */
    @PostMapping("/classes/editSaveClasses")
    @ApiOperation("修改班级接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Classes", value = "修改班级对象")
    })
    @PreAuthorize("hasRole('管理员') || hasRole('老师权限')")
    @ResponseBody
    public MzResult editSaveClasses(Classes classes){
        System.out.println("修改班级...."+classes);
        try {
            classesService.editSaveClasses(classes);
            return new MzResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MzResult("修改失败");
    }


    /**
     * 删除代码
     */
    @GetMapping("/classes/deleteClasses")
    @ApiOperation("删除班级接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "如:88",required = true)
    })
    @PreAuthorize("hasRole('管理员') || hasRole('老师权限')")
    @ResponseBody
    public MzResult deleteClasses(@RequestParam(required = true) Long id){
        MzResult ajaxResult = new MzResult();
        try {
            classesService.deleteClasses(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new MzResult("删除失败");
        }

        return ajaxResult;
    }

    @PostMapping(value="/classes/deleteBatchClasses")
    @ApiOperation("批量班级接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "如:88,89,99")
    })
    @PreAuthorize("hasRole('管理员')|| hasRole('老师权限')")
    @ResponseBody
    public MzResult deleteBatchClasses(String ids){
        String[] idsArr = ids.split(",");
        List list = new ArrayList();
        for(int i=0;i<idsArr.length;i++){
            list.add(idsArr[i]);
        }
        try{
            classesService.batchRemove(list);
            return new MzResult();
        }catch(Exception e){
            return new MzResult("批量删除失败");
        }
    }

}
