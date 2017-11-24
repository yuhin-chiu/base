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
import cn.yx.entity.JcBanner;
import cn.yx.model.ApiResponse;
import cn.yx.model.ResponseList;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:44:55
 * @version 1.0
 */

@RestController
@RequestMapping("/api/banner")
public class BannerController extends AbstractController {

    @RequestMapping("/list")
    @ResponseBody
    public ResponseList bannerList(@RequestParam(value = "lang", required = false) Integer lang,
            @RequestParam(value = "timeRange", required = false) String timeRange,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "1") Integer page) {
        if (pageSize == null || pageSize > 100 || pageSize < 0) {
            pageSize = 15;
        }
        if (page == null || page < 1) {
            page = 1;
        }
        List<JcBanner> list = bannerService.list(timeRange, title, lang, page, pageSize);
        Long total = ((Page<JcBanner>) list).getTotal();
        return ResponseList.rows(list).total(total);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public JcBanner getById(@RequestParam(value = "id", required = true) Integer id) {
        return bannerService.getById(id);
    }

    @AdminOperation
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(JcBanner banner, HttpServletRequest request) {
        ApiResponse temp = this.uploadFile(request, this.getClass(), "image");

        if (banner.getId() == null) {
            if (temp == null) {
                return ApiResponse.fileSaveEmpty();
            } else if (temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                banner.setImgKey(imgKey);
            } else {
                return temp;
            }
        } else if (banner.getId() != null) {
            if (temp != null && temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                banner.setImgKey(imgKey);
            }
        }
        if (bannerService.insertOrUpdate(banner)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
