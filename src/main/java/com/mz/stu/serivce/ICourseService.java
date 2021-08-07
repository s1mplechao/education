package com.mz.stu.serivce;

import com.mz.stu.entity.Course;
import com.mz.stu.query.CourseQuery;
import com.mz.stu.util.PageList;

import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-04-10
 * @Description: 课程列表Service接口层
 */
public interface ICourseService {

    //查询所有课程
    List<Course> queryAll();

    //分页查询课程
    public PageList listpage(CourseQuery courseQuery);

    //添加课程
    void addCourse(Course course);
    /**
     *     修改保存课程
     */
    void editSaveCourse(Course course);
    /**
     *     删除课程
     */
    void deleteCourse(Long id);

    /**
     *     批量课程
     */
    void batchRemove(List ids);
}
