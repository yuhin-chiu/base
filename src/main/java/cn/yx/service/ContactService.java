package cn.yx.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.yx.entity.JcContact;
import cn.yx.entity.JcContactExample;
import cn.yx.mapper.JcContactMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:46:51
 * @version 1.0
 */

@Service
public class ContactService extends AbstractService {

    @Resource
    private JcContactMapper contactMapper;

    public List<JcContact> list(Integer parent, String timeRange, Integer lang, int page, int pageSize) {
        JcContactExample example = new JcContactExample();
        example.setOrderByClause("create_time desc");

        JcContactExample.Criteria criteria = example.createCriteria();
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
        List<JcContact> list = contactMapper.selectByExampleWithBLOBs(example);

        // format to String
        list.forEach(e -> {
            e.setCreateTimeStr(TimeUtil.formatDataToTime(e.getCreateTime()));
            e.setImgUrl(parseUri2Url(e.getImgKey()));
            e.setCreateTime(null);
            e.setImgKey(null);
        });
        return list;
    }

    public JcContact getById(Integer id) {
        JcContact e = contactMapper.selectByPrimaryKey(id);
        e.setCreateTimeStr(TimeUtil.formatDataToTime(e.getCreateTime()));
        e.setImgUrl(parseUri2Url(e.getImgKey()));
        e.setCreateTime(null);
        e.setImgKey(null);
        return e;
    }

    public Boolean insertOrUpdate(JcContact demo) {
        if (demo.getId() == null) {
            return this.insertLang(demo) > 0 ? true : false;
        } else if (demo.getStatus() != null && demo.getStatus().equals(-1)) {
            return deleteLang(demo.getId());
        } else {
            return contactMapper.updateByPrimaryKeySelective(demo) > 0 ? true : false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertLang(JcContact demo) {
        // 这里的逻辑是插入第一条是中文的，同时插一条英文的，这里返回的是英文版的id
        contactMapper.insertSelective(demo);
        demo.setLang(1);
        if (demo.getId() != null) {
            demo.setId(demo.getId() + 1);
        }
        return contactMapper.insertSelective(demo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteLang(Integer id) {
        JcContact contact = new JcContact();
        contact.setId(id);
        contact.setStatus(-1);
        if (contactMapper.updateByPrimaryKeySelective(contact) <= 0) {
            return false;
        }
        contact.setId(id + 1);
        return contactMapper.updateByPrimaryKeySelective(contact) > 0 ? true : false;
    }
    // 需要确定一下图片更新是否中英文全更新
}
