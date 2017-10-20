package cn.yx.controller.api;

import javax.annotation.Resource;

import cn.yx.service.DemoService;

/**
 * @author yuxuanjiao
 * @date 2017年9月30日 下午4:09:59
 * @version 1.0
 */

public class AbstractController {

    @Resource
    protected DemoService demoService;

}
