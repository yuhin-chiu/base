package cn.yx.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.annotation.AdminOperation;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年9月30日 下午4:10:58
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class IndexController extends AbstractController {

    @RequestMapping({ "/", "" })
    public ApiResponse index() {
        return ApiResponse.successResponse().setData("nihao");
    }

    /**
     * 一个需要验证登陆的接口
     * 
     * @return
     */
    @AdminOperation
    @RequestMapping({ "/test" })
    public ApiResponse test() {
        return ApiResponse.successResponse().setData("test");
    }

    /**
     * 不需要验证登陆的接口
     * 
     * @return
     */
    @RequestMapping({ "/test2" })
    public ApiResponse test2() {
        return ApiResponse.successResponse().setData("test2");
    }

    /**
     * 不存在的接口
     * 
     * @return
     */
    @RequestMapping({ "/*" })
    public ApiResponse other() {
        return ApiResponse.noMapping();
    }
}
