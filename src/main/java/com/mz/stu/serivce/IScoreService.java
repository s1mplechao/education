package com.mz.stu.serivce;

import com.mz.stu.entity.Score;
import com.mz.stu.query.ScoreQuery;
import com.mz.stu.util.PageList;

import java.util.List;

/**
 * @Auther: mfc
 * @Date: 2021-05-10
 * @Description: 成绩Service层
 */
public interface IScoreService {
    //查询成绩
    List<Score> queryAll();
    //分页查询成绩记录
    PageList listpage(ScoreQuery scoreQuery);

    /**
     * 添加分数
     */
    void addScore(Score score);

    //批量删除成绩
    void batchRemove(List list);


}
