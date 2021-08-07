package com.mz.stu.serivce;



import com.mz.stu.entity.Kq;
import com.mz.stu.query.KqQuery;
import com.mz.stu.util.PageList;

import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 考勤记录Service接口层
 */
public interface IKqService {

    //查询考勤记录
    List<Kq> queryAll();
    //分页查询考勤记录
    PageList listpage(KqQuery kqQuery);
    /**
     * 上课打卡
     */
    void upKq();
    /**
     * 下班打卡
     */
    void downKq();
}
