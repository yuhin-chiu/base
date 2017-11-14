package cn.yx.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.annotation.AdminOperation;
import cn.yx.entity.JcHomepage;
import cn.yx.model.ApiResponse;
import cn.yx.util.ListUtil;

/**
 * @author yuxuanjiao
 * @date 2017年11月7日 上午9:53:40
 * @version 1.0
 */

@RestController
@RequestMapping("/api/homepage")
public class HomepageController extends AbstractController {

    @RequestMapping("/all")
    public ApiResponse all(@RequestParam(value = "lang", required = false) Integer lang) {
        return ApiResponse.successResponse().setData(homepageService.getLastOne(lang));
    }

    @AdminOperation
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(JcHomepage homepage, HttpServletRequest request) {
        ApiResponse temp = this.uploadFile(request, this.getClass(), "image");
        ApiResponse temp1 = this.uploadFile(request, this.getClass(), "image1");
        ApiResponse temp2 = this.uploadFile(request, this.getClass(), "image2");
        ApiResponse temp3 = this.uploadFile(request, this.getClass(), "image3");

        if (homepage.getId() != null) {
            if (temp != null && temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                homepage.setImgKey(imgKey);
            } else if(temp != null) {
                return temp;
            }
            if (temp1 != null || temp2 != null || temp3 != null) {
                JcHomepage e = homepageService.getLastOne(homepage.getLang());
                List<String> list = ListUtil.split(e.getMediaKey());
                if (temp1 != null && temp1.isSuccess()) {
                    list.set(0, (String) temp1.getData());
                } else if(temp1 != null) {
                    return temp1;
                }
                if (temp2 != null && temp1.isSuccess()) {
                    list.set(1, (String) temp2.getData());
                } else if(temp2 != null) {
                    return temp2;
                }
                if (temp3 != null && temp1.isSuccess()) {
                    list.set(2, (String) temp3.getData());
                } else if(temp3 !=null) {
                    return temp3;
                }
                homepage.setMediaKey(ListUtil.connect(list));
            }
        }
        if (homepageService.insertOrUpdate(homepage)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
