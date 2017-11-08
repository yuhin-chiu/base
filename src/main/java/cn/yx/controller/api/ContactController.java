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
import cn.yx.entity.JcContact;
import cn.yx.model.ApiResponse;
import cn.yx.model.ResponseList;

/**
 * @author yuxuanjiao
 * @date 2017年10月19日 下午8:44:55
 * @version 1.0
 */

@RestController
@RequestMapping("/api/contact")
public class ContactController extends AbstractController {

    @RequestMapping("/list")
    @ResponseBody
    public ResponseList contactList(@RequestParam(value = "parent", defaultValue = "0") Integer parent,
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
        List<JcContact> list = contactService.list(parent, timeRange, lang, page, pageSize);
        Long total = ((Page<JcContact>) list).getTotal();
        return ResponseList.rows(list).total(total);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public JcContact getById(@RequestParam(value = "id", required = true) Integer id) {
        return contactService.getById(id);
    }

    @AdminOperation
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(JcContact contact, HttpServletRequest request) {
        ApiResponse temp = this.uploadFile(request, this.getClass(), "image");

        if (contact.getId() == null) {
            if (temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                contact.setImgKey(imgKey);
            } else {
                return temp;
            }
        } else if (contact.getId() != null) {
            if (temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                contact.setImgKey(imgKey);
            }
        }
        if (contactService.insertOrUpdate(contact)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
