package com.mz.stu.mapper;

import com.mz.stu.entity.Kq;

/**
 * @Auther: mfc
 * @Date: 2021-05-22
 * @Description: 考勤Mapper类
 */
public interface KqMapper extends BaseMapper<Kq> {

    void upKq(Kq kq);

    void downKq(Kq kq);
}
