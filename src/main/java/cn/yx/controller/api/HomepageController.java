package cn.yx.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年11月7日 上午9:53:40 
 * @version 1.0
 */

@RestController
@RequestMapping("/api/homepage")
public class HomepageController extends AbstractController {

    @RequestMapping("/all")
    public ApiResponse all(@RequestParam(value="lang",required=false) Integer lang) {
        return ApiResponse.successResponse().setData(homepageService.getLastOne(lang));
    }
}
