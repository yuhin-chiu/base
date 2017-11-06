package cn.yx.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;

import cn.yx.annotation.AdminOperation;
import cn.yx.entity.JcNews;
import cn.yx.model.ApiResponse;
import cn.yx.model.ResponseList;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:44:55
 * @version 1.0
 */

@RestController
@RequestMapping("/api/news")
public class NewsController extends AbstractController {

    @RequestMapping("/list")
    @ResponseBody
    public ResponseList newsList(@RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "lang", required = false) Integer lang,
            @RequestParam(value = "timeRange", required = false) String timeRange,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "page", defaultValue = "1") Integer page) {
        if (pageSize == null || pageSize > 100 || pageSize < 0) {
            pageSize = 15;
        }
        if (page == null || page < 1) {
            page = 1;
        }
        List<JcNews> list = newsService.list(title, timeRange, lang, page, pageSize);
        Long total = ((Page<JcNews>) list).getTotal();
        return ResponseList.rows(list).total(total);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public JcNews getById(@RequestParam(value = "id", required = true) Integer id) {
        return newsService.getById(id);
    }

    @AdminOperation
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(JcNews news, HttpServletRequest request) {
        ApiResponse temp = this.uploadFile(request, this.getClass(), "image");

        if (news.getId() == null) {
            if (temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                news.setImgKey(imgKey);
            } else {
                return temp;
            }
        } else if (news.getId() != null) {
            if (temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                news.setImgKey(imgKey);
            }
        }
        if (newsService.insertOrUpdate(news)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
