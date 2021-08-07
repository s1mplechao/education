package com.mz.stu.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 角色
 */
@Data
public class Role {
    private Long id;
    private String name;
    private String sn;
    private String desc;
    List<Permission> permissions = new ArrayList();
}
