package cn.yx.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.yx.entity.JcAbout;
import cn.yx.entity.JcAboutExample;
import cn.yx.mapper.JcAboutMapper;
import cn.yx.util.ListUtil;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:46:51
 * @version 1.0
 */

@Service
public class AboutService extends AbstractService {

    @Resource
    private JcAboutMapper aboutMapper;

    public List<JcAbout> list(Integer parent, String timeRange, Integer lang, int page, int pageSize) {
        JcAboutExample example = new JcAboutExample();
        example.setOrderByClause("create_time desc");

        JcAboutExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isBlank(timeRange)) {
            Date[] dates = TimeUtil.splitDayRangeToDateBig(timeRange);
            criteria.andCreateTimeBetween(dates[0], dates[1]);
        }
        if (lang == null) {
            lang = 0;
        }
        criteria.andLangEqualTo(lang);
        criteria.andStatusNotEqualTo(-1);
        criteria.andParentEqualTo(parent);

        // query
        PageHelper.startPage(page, pageSize);
        List<JcAbout> list = aboutMapper.selectByExample(example);

        // format to String
        list.forEach(e -> {
            e.setCreateTimeStr(TimeUtil.formatDataToTime(e.getCreateTime()));
            e.setImgUrls(ListUtil.split(e.getImgKey()).stream().map(this::parseUri2Url).collect(Collectors.toList()));
            e.setCreateTime(null);
        });
        return list;
    }

    public JcAbout getById(Integer id) {
        JcAbout about = aboutMapper.selectByPrimaryKey(id);
        about.setImgUrl(parseUri2Url(about.getImgKey()));
        about.setImgKey(null);
        return about;
    }

    public Boolean insertOrUpdate(JcAbout demo) {
        if (demo.getParent() == null) {
            demo.setParent(2);
        }
        if (demo.getId() == null) {
            return this.insertLang(demo) > 0 ? true : false;
        } else if (demo.getStatus() != null && demo.getStatus().equals(-1)) {
            return deleteLang(demo.getId());
        } else {
            return aboutMapper.updateByPrimaryKeySelective(demo) > 0 ? true : false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertLang(JcAbout demo) {
        // 这里的逻辑是插入第一条是中文的，同时插一条英文的，这里返回的是英文版的id
        aboutMapper.insertSelective(demo);
        demo.setLang(1);
        if (demo.getId() != null) {
            demo.setId(demo.getId() + 1);
        }
        return aboutMapper.insertSelective(demo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteLang(Integer id) {
        JcAbout about = new JcAbout();
        about.setId(id);
        about.setStatus(-1);
        if (aboutMapper.updateByPrimaryKeySelective(about) <= 0) {
            return false;
        }
        about.setId(id + 1);
        return aboutMapper.updateByPrimaryKeySelective(about) > 0 ? true : false;
    }
    // 需要确定一下图片更新是否中英文全更新
}
