package com.mz.stu.mapper;

import com.mz.stu.entity.User;
import com.mz.stu.query.UserQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
/**
 * @Auther: mfc
 * @Date: 2021-05-22
 * @Description: 用户mapper
 */
@Mapper
public interface UserMapper {
    @Select("select * from t_user where username=#{username}")
    User findUserByUserName(String username);

    @Select("select * from t_user")
    List<User> findAll();

    //查询总的条数
    Long queryTotal(UserQuery userQuery);
    //分页查询数据
    List<User> queryData(UserQuery userQuery);
    //条件用户
    void addUser(User user);
    //更新用户头像
    void updateUserHeadImg(User user);

    //修改保存用户
    void editSaveUser(User user);

    @Delete("delete from t_user where id=#{id}")
    void deleteUser(Long id);

    @Delete("<script>"  +
            "delete from t_user where id in " +
            "<foreach collection='list' item='id' open='(' separator=',' close=')'> " +
            "#{id}" +
            "</foreach>" +
            "</script>")
    void batchRemove(List list);


    //保存选课信息
    @Update("update t_user set courseid=#{courseid} where id =#{id}")
    void editSaveXk(User user);

    @Update("update t_user set password=#{password} where id=#{id}")
    void updatePwd(User user);
}
