package cn.yx.controller.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;
import cn.yx.util.FileUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午4:37:01
 * @version 1.0
 */

@Controller
@RequestMapping("/api")
public class FileController extends AbstractController {

    // 图片展示相关代码  
    @RequestMapping("/image")
    public void imageFile(@RequestParam(defaultValue = "test.txt") String fileName, HttpServletResponse response)
            throws IOException {
        if (fileName != null) {
            // 当前是从该工程目录的File文件夹中获取文件(该目录在常量中配置了)
//            response.setContentType("application/force-download");// 设置强制下载不打开
//            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            response.setDateHeader("expires", System.currentTimeMillis()+ 30*24*3600*1000L); // 这个单位特么是毫秒？
            FileUtil.downloadFile(fileName, new BufferedOutputStream(response.getOutputStream()), this.getClass());
        }
    }
    
//    // 文件下载相关代码
//    @RequestMapping("/download")
//    public void downloadFile(String fileName,
//            Integer fileId,
//            @RequestParam(name = "originName", defaultValue = "download", required = false) String originName,
//            HttpServletResponse response) throws IOException {
//        if (fileId != null) {
//            WhsDownloads down = downloadsService.getById(fileId);
//            String name = down.getAnnex();
//            downloadsService.increase(fileId, 1);
//            response.setContentType("application/force-download");// 设置强制下载不打开
//            response.addHeader("Content-Disposition",
//                    "attachment;fileName*=UTF-8''" + URLEncoder.encode(originName, "UTF-8"));// 设置文件名 可以显示中文
//            FileUtil.downloadFile(name, new BufferedOutputStream(response.getOutputStream()), this.getClass());
//        } else if (fileName != null) {
//            // 当前是从该工程目录的File文件夹中获取文件(该目录在常量中配置了)
//            response.setContentType("application/force-download");// 设置强制下载不打开
//            response.addHeader("Content-Disposition",
//                    "attachment;fileName*=UTF-8''" + URLEncoder.encode(originName, "UTF-8"));// 设置文件名 可以显示中文
//            FileUtil.downloadFile(fileName, new BufferedOutputStream(response.getOutputStream()), this.getClass());
//        }
//    }

    // 多文件上传
    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse uploadFiles(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        String[] fileNames = new String[files.size()];
        ApiResponse temp, resp = new ApiResponse();

        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                temp = FileUtil.uploadFile(file);
                if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                    return temp;
                }
                fileNames[i] = (String) temp.getData();
            } else {
                resp.setDescription("某些上传文件为空，请检查！");
            }
        }

        resp.setMsg("上传文件成功");
        resp.setData(fileNames);
        return resp;
    }

}
