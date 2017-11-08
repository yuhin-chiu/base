package cn.yx.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ProductService extends AbstractService {

    @Resource
    private JcProductMapper productMapper;

    public List<JcProduct> list(String title, String timeRange, Integer lang, int page, int pageSize) {
        JcProductExample example = new JcProductExample();
        example.setOrderByClause("create_time desc");

        JcProductExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(timeRange)) {
            Date[] dates = TimeUtil.splitDayRangeToDateBig(timeRange);
            criteria.andCreateTimeBetween(dates[0], dates[1]);
        }
        if (!StringUtils.isBlank(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (lang == null) {
            lang = 0;
        }
        criteria.andLangEqualTo(lang);
        criteria.andStatusNotEqualTo(-1);

        // query
        PageHelper.startPage(page, pageSize);
        List<JcProduct> list = productMapper.selectByExample(example);

        // format to String
        list.forEach(e -> {
            e.setCreateTimeStr(TimeUtil.formatDataToTime(e.getCreateTime()));
            e.setCreateTime(null);
        });
        return list;
    }

    public JcProduct getById(Integer id) {
        JcProduct e = productMapper.selectByPrimaryKey(id);
        e.setCreateTimeStr(TimeUtil.formatDataToTime(e.getCreateTime()));
        e.setCreateTime(null);
        return e;
    }

    public Boolean insertOrUpdate(JcProduct demo) {
        if (demo.getId() == null) {
            return this.insertLang(demo) > 0 ? true : false;
        } else if (demo.getStatus() != null && demo.getStatus().equals(-1)) {
            return deleteLang(demo.getId());
        } else {
            return productMapper.updateByPrimaryKeySelective(demo) > 0 ? true : false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertLang(JcProduct demo) {
        // 这里的逻辑是插入第一条是中文的，同时插一条英文的，这里返回的是英文版的id
        productMapper.insertSelective(demo);
        demo.setLang(1);
        if (demo.getId() != null) {
            demo.setId(demo.getId() + 1);
        }
        return productMapper.insertSelective(demo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteLang(Integer id) {
        JcProduct news = new JcProduct();
        news.setId(id);
        news.setStatus(-1);
        if (productMapper.updateByPrimaryKeySelective(news) <= 0) {
            return false;
        }
        news.setId(id + 1);
        return productMapper.updateByPrimaryKeySelective(news) > 0 ? true : false;
    }
}
