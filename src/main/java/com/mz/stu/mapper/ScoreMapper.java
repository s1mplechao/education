package com.mz.stu.mapper;

import com.mz.stu.entity.Score;

import java.util.List;


/**
 * @Auther: mfc
 * @Date: 2021-05-22
 * @Description: 分数类
 */
public interface ScoreMapper extends BaseMapper<Score> {
    //添加积分
    void addScore(Score score);

    void batchRemove(List list);
}
