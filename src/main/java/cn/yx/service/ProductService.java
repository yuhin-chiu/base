package cn.yx.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.yx.entity.JcProduct;
import cn.yx.entity.JcProductExample;
import cn.yx.mapper.JcProductMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:46:51
 * @version 1.0
 */

@Service
public class ProductService {

    @Resource
    private JcProductMapper productMapper;

    public List<JcProduct> list(String title, String timeRange, int page, int pageSize) {
        JcProductExample example = new JcProductExample();
        JcProductExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(timeRange)) {
            Date[] dates = TimeUtil.splitDayRangeToDateBig(timeRange);
            criteria.andCreateTimeBetween(dates[0], dates[1]);
        }
        if (!StringUtils.isBlank(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        criteria.andStatusNotEqualTo(-1);
        PageHelper.startPage(page, pageSize);
        return productMapper.selectByExample(example);
    }

    public JcProduct getById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    public Boolean insertOrUpdate(JcProduct demo) {
        if (demo.getId() == null) {
            return productMapper.insertSelective(demo) > 0 ? true : false;
        } else {
            return productMapper.updateByPrimaryKeySelective(demo) > 0 ? true : false;
        }
    }
}
