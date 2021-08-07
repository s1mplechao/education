package com.mz.stu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class VacTask {

    private String id;
    private String name;
    private Vacation vac;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
