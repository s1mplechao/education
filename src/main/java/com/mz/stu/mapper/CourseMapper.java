package com.mz.stu.mapper;

import com.mz.stu.entity.Course;
import com.mz.stu.query.CourseQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-22
 * @Description: 课程列表Mapper
 */
@MapperScan
public interface CourseMapper {

    //查询所有课程
    List<Course> queryAll();
    //查询总的条数
    Long queryTotal(CourseQuery courseQuery);
    //分页查询数据
    List<Course> queryData(CourseQuery courseQuery);

    /**
     * 新增课程
     */
    @Insert("insert into t_course(name,createTime,tid) values(#{name},#{createTime},#{tid})")
    void addCourse(Course course);

    /**
     * 修改保存课程
     */
    @Update("update t_course set name=#{name},tid=#{tid} where id =#{id}")
    void editSaveCourse(Course course);

    /**
     * 删除课程
     */
    @Delete("delete from t_course where id=#{id}")
    void deleteCourse(Long id);

    /**
     * 批量删除
     */
    @Delete("<script>"  +
            "delete from t_course where id in " +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'> " +
                 "#{id}" +
            "</foreach>" +
            "</script>")
    void batchRemove(List ids);
}
