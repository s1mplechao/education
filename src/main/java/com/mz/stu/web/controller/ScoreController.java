package com.mz.stu.web.controller;


import com.mz.stu.entity.Score;
import com.mz.stu.entity.User;
import com.mz.stu.query.ScoreQuery;
import com.mz.stu.serivce.ICourseService;
import com.mz.stu.serivce.IScoreService;
import com.mz.stu.serivce.IUserService;

import com.mz.stu.util.MzResult;
import com.mz.stu.util.PageList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@Api(tags = "成绩管理接口")
public class ScoreController {
    @Autowired
    private IScoreService scoreService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;


    @GetMapping("/score/index")
    @ApiOperation("跳转成绩页接口")
    public String index(Model model){
        //查询学生
        List<User> users = userService.findAll().stream().filter(user -> user.getType() == 3).collect(Collectors.toList());
        model.addAttribute("users",users);
        model.addAttribute("courses",courseService.queryAll());
        //查询课程
        return "views/score/score_list";
    }

    @GetMapping("/score/listpage")
    @ApiOperation("查询成绩分页数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ScoreQuery", value = "成绩查询对象", defaultValue = "scoreQuery对象")
    })
    @ResponseBody
    public PageList listpage(ScoreQuery scoreQuery){
        return  scoreService.listpage(scoreQuery);
    }

    @PostMapping("/score/addScore")
    @ApiOperation("添加成绩接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Score", value = "成绩对象")
    })
    @ResponseBody
    public MzResult addScore(Score score){
        try {
            scoreService.addScore(score);
            return new  MzResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new  MzResult("添加失败");

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @PostMapping(value="/score/deleteBatchScore")
    @ApiOperation("批量删除成绩接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "如:88,89,99")
    })
    @ResponseBody
    public MzResult deleteBatchScore(String ids){
        String[] idsArr = ids.split(",");
        List list = new ArrayList();
        for(int i=0;i<idsArr.length;i++){
            list.add(idsArr[i]);
        }
        try{
            scoreService.batchRemove(list);
            return new MzResult();
        }catch(Exception e){
            return new MzResult("批量删除失败");
        }
    }


}
