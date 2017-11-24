package cn.yx.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.yx.entity.JcHomepage;
import cn.yx.entity.JcHomepageExample;
import cn.yx.mapper.JcHomepageMapper;
import cn.yx.util.ListUtil;

/**
 * @author yuxuanjiao
 * @date 2017年11月7日 上午9:54:59 
 * @version 1.0
 */

@Service
public class HomepageService extends AbstractService {

    @Resource
    private JcHomepageMapper homepageMapper;
    
    public JcHomepage getLastOne(Integer lang) {
        JcHomepageExample example = new JcHomepageExample();
        example.setOrderByClause("create_time desc");

        JcHomepageExample.Criteria criteria = example.createCriteria();
        if (lang == null) {
            lang = 0;
        }
        criteria.andLangEqualTo(lang);
        criteria.andStatusNotEqualTo(-1);

        // query
        PageHelper.startPage(1, 1);
        List<JcHomepage> list = homepageMapper.selectByExample(example);

        // format to String
        list.forEach(e -> {
                e.setMediaUrls(ListUtil.split(e.getMediaKey()).stream()
                        .map(this::parseUri2Url)
                        .collect(Collectors.toList()));
                e.setImgUrl(parseUri2Url(e.getImgKey()));
                e.setImgKey(null);
            });
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public JcHomepage getById(Integer id) {
        JcHomepage e = homepageMapper.selectByPrimaryKey(id);
        e.setMediaUrls(ListUtil.split(e.getMediaKey()).stream()
                .map(this::parseUri2Url)
                .collect(Collectors.toList()));
        e.setImgUrl(parseUri2Url(e.getImgKey()));
        e.setImgKey(null);
        e.setMediaKey(null);
        return e;
    }

    public Boolean insertOrUpdate(JcHomepage demo) {
        if (demo.getId() == null) {
            return this.insertLang(demo) > 0 ? true : false;
        } else if (demo.getStatus() != null && demo.getStatus().equals(-1)) {
            return deleteLang(demo.getId());
        } else {
            return homepageMapper.updateByPrimaryKeySelective(demo) > 0 ? true : false;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer insertLang(JcHomepage demo) {
        // 这里的逻辑是插入第一条是中文的，同时插一条英文的，这里返回的是英文版的id
        homepageMapper.insertSelective(demo);
        demo.setLang(1);
        if (demo.getId() != null) {
            demo.setId(demo.getId() + 1);
        }
        return homepageMapper.insertSelective(demo);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteLang(Integer id) {
        JcHomepage news = new JcHomepage();
        news.setId(id);
        news.setStatus(-1);
        if (homepageMapper.updateByPrimaryKeySelective(news) <= 0) {
            return false;
        }
        news.setId(id + 1);
        return homepageMapper.updateByPrimaryKeySelective(news) > 0 ? true : false;
    }

    public JcHomepage getFooter(Integer lang) {
        return homepageMapper.getFooter(lang);
    }
}
