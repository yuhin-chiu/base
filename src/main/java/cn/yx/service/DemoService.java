package cn.yx.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.yx.entity.Demo;
import cn.yx.entity.DemoExample;
import cn.yx.mapper.DemoMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:46:51
 * @version 1.0
 */

@Service
public class DemoService {

    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list(String title, String timeRange, int page, int pageSize) {
        DemoExample example = new DemoExample();
        DemoExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(timeRange)) {
            Date[] dates = TimeUtil.splitDayRangeToDateBig(timeRange);
            criteria.andCreateTimeBetween(dates[0], dates[1]);
        }
        if (!StringUtils.isBlank(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        criteria.andStatusNotEqualTo(-1);
        PageHelper.startPage(page, pageSize);
        return demoMapper.selectByExample(example);
    }

    public Demo getById(Long id) {
        return demoMapper.selectByPrimaryKey(id);
    }

    public Boolean insertOrUpdate(Demo demo) {
        if (demo.getId() == null) {
            return demoMapper.insertSelective(demo) > 0 ? true : false;
        } else {
            return demoMapper.updateByPrimaryKeySelective(demo) > 0 ? true : false;
        }
    }
}
