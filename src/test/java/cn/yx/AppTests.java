package cn.yx;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import cn.yx.entity.JcNews;
import cn.yx.service.NewsService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class AppTests {

    @Resource
    private NewsService newsService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testInsert() {
        JcNews demo = new JcNews();
        demo.setTitle("title");
        demo.setAbstr("abstr");
        demo.setContent("content");
        newsService.insertLang(demo);
    }
    
    @Test
    public void testDelete() {
        newsService.deleteLang(2);
    }
    
    @Test
    public void testList() {
        List<JcNews> list = newsService.list(null, null, null, 0, 1, 20);
        list.forEach(e -> System.out.println(e.getCreateTime() + " == " + e.getContent()));
    }
}
