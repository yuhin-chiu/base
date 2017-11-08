package cn.yx.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;

import cn.yx.annotation.AdminOperation;
import cn.yx.entity.Demo;
import cn.yx.model.ApiResponse;
import cn.yx.model.ResponseList;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:44:55
 * @version 1.0
 */

@RestController
@RequestMapping("/api/demo")
public class DemoController extends AbstractController {

    @RequestMapping("/list")
    @ResponseBody
    public ResponseList demoList(@RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "timeRange", required = false) String timeRange,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "1") Integer page) {
        if (pageSize == null || pageSize > 100 || pageSize < 0) {
            pageSize = 15;
        }
        if (page == null || page < 1) {
            page = 1;
        }
        List<Demo> list = demoService.list(title, timeRange, page, pageSize);
        Long total = ((Page<Demo>) list).getTotal();
        return ResponseList.rows(list).total(total);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public Demo getById(@RequestParam(value = "id", required = true) Long id) {
        return demoService.getById(id);
    }

    @AdminOperation
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(Demo demo) {
        if (demoService.insertOrUpdate(demo)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
