package com.mz.stu.query;

import lombok.Data;


@Data
public class RoleQuery {
    //开始位置
    private Integer offset = 0;
    //每页显示条数
    private Integer pageSize = 10;
}
