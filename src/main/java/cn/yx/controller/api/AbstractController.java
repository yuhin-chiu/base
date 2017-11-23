package cn.yx.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;
import cn.yx.service.AboutService;
import cn.yx.service.BannerService;
import cn.yx.service.ContactService;
import cn.yx.service.CultureService;
import cn.yx.service.DemoService;
import cn.yx.service.HomepageService;
import cn.yx.service.NewsService;
import cn.yx.service.ProductService;
import cn.yx.util.FileUtil;

/**
 * @author yuxuanjiao
 * @date 2017年9月30日 下午4:09:59
 * @version 1.0
 */

public class AbstractController {

    @Resource
    protected DemoService demoService;

    @Resource
    protected NewsService newsService;

    @Resource
    protected HomepageService homepageService;

    @Resource
    protected AboutService aboutService;

    @Resource
    protected BannerService bannerService;

    @Resource
    protected ContactService contactService;

    @Resource
    protected CultureService cultureService;

    @Resource
    protected ProductService productService;

    protected ApiResponse uploadFiles(HttpServletRequest request, Class<?> clzss) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files[]");
        MultipartFile file = null;
        List<String> annexs = new ArrayList<>();
        ApiResponse temp, resp = new ApiResponse();

        if (files == null || files.size() == 0) {
            resp.setCode(-1);
            return resp;
        }
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                temp = FileUtil.uploadFile(file, clzss.getSimpleName() + "/" + FileUtil.randomName(fileName), clzss);
                if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                    return temp;
                }

                annexs.add((String) temp.getData());
            } else {
                return ApiResponse.fileSaveEmpty();
            }
        }

        return ApiResponse.successResponse().setData(StringUtils.join(annexs, ","));
    }

    protected ApiResponse uploadFile(HttpServletRequest request, Class<?> clzss, String key) {
        MultipartFile file = null;
        String annex = null;
        ApiResponse temp = null;

        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            file = multiRequest.getFile(key);
        }  catch (Exception e) {
            return null;
        }
        if (file == null) {
            return null;
        }

        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            temp = FileUtil.uploadFile(file, clzss.getSimpleName() + "/" + FileUtil.randomName(fileName), clzss);
            if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                return temp;
            }
            annex = (String) temp.getData();
        } else {
            return ApiResponse.fileSaveEmpty();
        }

        return ApiResponse.successResponse().setData(annex);
    }
}
