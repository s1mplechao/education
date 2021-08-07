package com.mz.stu.query;

import lombok.Data;

@Data
public class UserQuery {
    //开始位置
    private Integer offset = 0;
    //每页显示条数
    private Integer pageSize = 10;

    private String username;

    private String email;

    private Long type;//1表示管理员 2表示老师用户

}
