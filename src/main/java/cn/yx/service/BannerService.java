package cn.yx.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.yx.entity.JcBanner;
import cn.yx.entity.JcBannerExample;
import cn.yx.mapper.JcBannerMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年11月7日 上午10:49:45
 * @version 1.0
 */

@Service
public class BannerService extends AbstractService {

    @Resource
    private JcBannerMapper bannerMapper;

    public List<JcBanner> list(String timeRange, String title, Integer lang, int page, int pageSize) {
        JcBannerExample example = new JcBannerExample();
        example.setOrderByClause("create_time desc");

        JcBannerExample.Criteria criteria = example.createCriteria();
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
        List<JcBanner> list = bannerMapper.selectByExample(example);

        // format to String
        list.forEach(e -> {
            e.setImgUrl(parseUri2Url(e.getImgKey()));
            e.setImgKey(null);
            e.setCreateTimeStr(TimeUtil.formatDataToTime(e.getCreateTime()));
            e.setCreateTime(null);
        });
        return list;
    }

    public JcBanner getById(Integer id) {
        JcBanner banner = bannerMapper.selectByPrimaryKey(id);
        banner.setImgUrl(parseUri2Url(banner.getImgKey()));
        banner.setImgKey(null);
        return banner;
    }

    public Boolean insertOrUpdate(JcBanner demo) {
        if (demo.getId() == null) {
            return this.insertLang(demo) > 0 ? true : false;
        } else if (demo.getStatus() != null && demo.getStatus().equals(-1)) {
            return deleteLang(demo.getId());
        } else {
            return bannerMapper.updateByPrimaryKeySelective(demo) > 0 ? true : false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertLang(JcBanner demo) {
        // 这里的逻辑是插入第一条是中文的，同时插一条英文的，这里返回的是英文版的id
        bannerMapper.insertSelective(demo);
        demo.setLang(1);
        if (demo.getId() != null) {
            demo.setId(demo.getId() + 1);
        }
        return bannerMapper.insertSelective(demo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteLang(Integer id) {
        JcBanner news = new JcBanner();
        news.setId(id);
        news.setStatus(-1);
        if (bannerMapper.updateByPrimaryKeySelective(news) <= 0) {
            return false;
        }
        news.setId(id + 1);
        return bannerMapper.updateByPrimaryKeySelective(news) > 0 ? true : false;
    }
}
