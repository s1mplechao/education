package com.mz.stu.serivce;



import com.mz.stu.entity.Classes;
import com.mz.stu.query.ClassesQuery;
import com.mz.stu.util.PageList;

import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 班级列表Service接口层
 */
public interface IClassesService {

    /**
     *  查询所有的班级
     */
    List<Classes> queryAll();
    /**
     *  分页查询
     */
    PageList listpage(ClassesQuery classesQuery);
    /**
     *  保存班级
     */
    void addClasses(Classes classes);
    /**
     *  修改保存班级
     */
    void editSaveClasses(Classes classes);
    /**
     *  根据id删除班级
     */
    void deleteClasses(Long id);
    /**
     *  批量删除功能
     */
    void batchRemove(List list);
}
