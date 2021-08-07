package com.mz.stu.serivce.impl;

import com.mz.stu.entity.Course;
import com.mz.stu.mapper.CourseMapper;
import com.mz.stu.query.CourseQuery;
import com.mz.stu.serivce.ICourseService;
import com.mz.stu.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: soulcoder-项目库分享圈
 * @datetime: 2020/7/1 8:34
 * @qq: 2579692606
 * @description: 课程列表Service接口层
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public List<Course> queryAll() {
        return courseMapper.queryAll();
    }



    /**
     *  @description:   分页查询
     *  @params:  courseQuery
     *  @return  PageList
     */
    @Override
    public PageList listpage(CourseQuery courseQuery) {
        PageList pageList  = new PageList();
        //查询总的条数
        Long total = courseMapper.queryTotal(courseQuery);
        List<Course> users = courseMapper.queryData(courseQuery);
        pageList.setTotal(total);
        pageList.setRows(users);
        //分页查询的数据
        return pageList;
    }

    @Override
    public void addCourse(Course course) {
        //设置创建时间
        course.setCreateTime(new Date());
         courseMapper.addCourse(course);
    }


    @Override
    public void editSaveCourse(Course course) {
        courseMapper.editSaveCourse(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseMapper.deleteCourse(id);
    }

    @Override
    public void batchRemove(List list) {
        courseMapper.batchRemove(list);
    }


}
