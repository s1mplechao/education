package com.mz.stu.mapper;

import com.mz.stu.entity.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-22
 * @Description: 菜单mapper
 */
@Mapper
public interface MenuMapper {
    /**
     * 根据登录用户 查询菜单
     * @param loginUserid
     * @return
     */
    List<Menu> findAll(Long loginUserid);

    /**
     * 查询所有的菜单
     * @return
     */
    List<Menu> queryAllMenu();

    /**
     * 保存一级菜单
     * @param menu
     */
    void addTopMenu(Menu menu);

    //保存子菜单
    void addSubMenu(Menu menu);


    @Delete("delete from t_menu where id=#{id} or pid=#{id}")
    void deleteMenuById(Long id);


    void editMenu(Menu menu);
}
