package com.mz.stu.serivce.impl;


import com.mz.stu.entity.Score;
import com.mz.stu.mapper.ScoreMapper;
import com.mz.stu.query.ScoreQuery;
import com.mz.stu.serivce.IScoreService;
import com.mz.stu.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: soulcoder-项目库分享圈
 * @datetime: 2020/7/1 8:34
 * @qq: 2579692606
 * @description: 成绩Service接口层
 */
@Service
public class ScoreServiceImpl implements IScoreService {
    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public List<Score> queryAll() {
        return scoreMapper.queryAll();
    }

    /**
     *  @description:   分页查询
     *  @params:  courseQuery
     *  @return  PageList
     */
    @Override
    public PageList listpage(ScoreQuery courseQuery) {
        PageList pageList  = new PageList();
        //查询总的条数
        Long total = scoreMapper.queryTotal(courseQuery);
        List<Score> users = scoreMapper.queryData(courseQuery);
        pageList.setTotal(total);
        pageList.setRows(users);
        //分页查询的数据
        return pageList;
    }

    /**
     * 添加积分
     */
    @Override
    public void addScore(Score score) {
        scoreMapper.addScore(score);
    }

    //批量删除成绩
    @Override
    public void batchRemove(List list) {
        scoreMapper.batchRemove(list);
    }

}
