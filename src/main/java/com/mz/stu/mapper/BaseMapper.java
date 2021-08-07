package com.mz.stu.mapper;

import com.mz.stu.query.BaseQuery;

import java.util.List;


public interface BaseMapper<T> {
    //查询总的条数
    Long queryTotal(BaseQuery baseQuery);
    //分页查询数据
    List<T> queryData(BaseQuery baseQuery);
    //查询所有记录
    List<T> queryAll();
}
