package cn.yx.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;

import cn.yx.annotation.AdminOperation;
import cn.yx.entity.JcProduct;
import cn.yx.model.ApiResponse;
import cn.yx.model.ResponseList;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:44:55
 * @version 1.0
 */

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractController {

    @RequestMapping("/list")
    @ResponseBody
    public ResponseList productList(@RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "lang", required = false) Integer lang,
            @RequestParam(value = "timeRange", required = false) String timeRange,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "currentPage", defaultValue = "1") Integer page) {
        if (pageSize == null || pageSize > 100 || pageSize < 0) {
            pageSize = 15;
        }
        if (page == null || page < 1) {
            page = 1;
        }
        List<JcProduct> list = productService.list(title, timeRange, lang, page, pageSize);
        Long total = ((Page<JcProduct>) list).getTotal();
        return ResponseList.rows(list).total(total);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public JcProduct getById(@RequestParam(value = "id", required = true) Integer id) {
        return productService.getById(id);
    }

    @AdminOperation
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(JcProduct product) {
        if (productService.insertOrUpdate(product)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
