package com.mz.stu.entity;

import lombok.Data;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 权限
 */
@Data
public class Permission {

    private Long id;
    private String name;
    private String title;
    private Long pid;
    private Long menuid;
}