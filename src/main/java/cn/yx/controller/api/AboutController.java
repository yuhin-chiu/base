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
import cn.yx.entity.JcAbout;
import cn.yx.entity.JcHomepage;
import cn.yx.model.ApiResponse;
import cn.yx.model.ResponseList;
import cn.yx.util.ListUtil;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:44:55
 * @version 1.0
 */

@RestController
@RequestMapping("/api/about")
public class AboutController extends AbstractController {

    @RequestMapping("/list")
    @ResponseBody
    public ResponseList aboutList(@RequestParam(value = "parent", required = false, defaultValue = "2") Integer parent,
            @RequestParam(value = "lang", required = false, defaultValue = "0") Integer lang,
            @RequestParam(value = "timeRange", required = false) String timeRange,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "1") Integer page) {
        if (pageSize == null || pageSize > 100 || pageSize < 0) {
            pageSize = 15;
        }
        if (page == null || page < 1) {
            page = 1;
        }
        List<JcAbout> list = aboutService.list(parent, timeRange, lang, page, pageSize);
        Long total = ((Page<JcAbout>) list).getTotal();
        return ResponseList.rows(list).total(total);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public JcAbout getById(@RequestParam(value = "id", required = true) Integer id) {
        return aboutService.getById(id);
    }

    @AdminOperation
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(JcAbout about, HttpServletRequest request) {
        ApiResponse temp = this.uploadFile(request, this.getClass(), "image");

        if (about.getId() == null) {
            if (temp == null) {
                return ApiResponse.fileSaveEmpty();
            } else if (temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                about.setImgKey(imgKey);
            } else {
                return temp;
            }
        } else if (about.getId() != null) {
            if (temp != null && temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                about.setImgKey(imgKey);
            } else if (temp != null) {
                return temp;
            }
            if(about.getParent() != null && about.getParent().equals(0)) {
                ApiResponse temp1 = this.uploadFile(request, this.getClass(), "image1");
                ApiResponse temp2 = this.uploadFile(request, this.getClass(), "image2");
                JcAbout e = aboutService.list(0, null, about.getLang(), 1, 1).get(0);
                List<String> list = ListUtil.split(e.getImgKey());
                if (about.getImgKey() != null) {
                    list.set(0, about.getImgKey());
                }
                if (temp1 != null && temp1.isSuccess()) {
                    list.set(1, (String) temp1.getData());
                } else if(temp1 != null) {
                    return temp1;
                }
                if (temp2 != null && temp2.isSuccess()) {
                    list.set(2, (String) temp2.getData());
                } else if(temp2 != null) {
                    return temp2;
                }
                about.setImgKey(ListUtil.connect(list));
            }
        }
        if (aboutService.insertOrUpdate(about)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
