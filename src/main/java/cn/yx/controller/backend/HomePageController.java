package cn.yx.controller.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yx.enums.DialogItemEnum;
import cn.yx.model.DialogItem;

/**
 * @author yuxuanjiao
 * @date 2017年10月24日 下午2:49:17
 * @version 1.0
 */

@Controller
@RequestMapping("/backend/homepage")
public class HomePageController extends DialogPageController {

    @RequestMapping({ "/", "", "list" })
    public String index(Model model) {
        setDailogItems(model);
        return "/homepage/list";
    }

    @RequestMapping({ "base" })
    public String whln(Model model) {
        setPresentItems(model, 1);
        return "/homepage/base";
    }

    /**
     * Implement Methods
     */
    private static volatile List<DialogItem> dialogItems = new ArrayList<DialogItem>();

    @Override
    protected synchronized void initDialogItems() {
        if (dialogItems.size() == 0) {
            DialogItem title = new DialogItem("title", "标题");
            DialogItem url = new DialogItem("url", "跳转地址");
            url.setLength(1, 200);
            DialogItem content = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());
            
            dialogItems.add(title);
            dialogItems.add(url);
            dialogItems.add(content);
        }
    }

    @Override
    protected List<DialogItem> getDailogItems() {
        return dialogItems;
    }

    private static volatile List<DialogItem> whlnItems = new ArrayList<DialogItem>();

    @Override
    void initPresentItems() {
        if (whlnItems.size() == 0) {
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");

            DialogItem address = new DialogItem("address", "地址");
            DialogItem phone = new DialogItem("phone", "电话");
            DialogItem mail = new DialogItem("mail", "邮箱");
            DialogItem videoUrl = new DialogItem("videoUrl", "宣传片地址");
            videoUrl.setLength(1, 300);
            DialogItem introduction = new DialogItem("introduction", "关于津弛", DialogItemEnum.TEXTAREA.getStr());
            introduction.setLength(1, 500);
            DialogItem image = new DialogItem("image", "关于图片", DialogItemEnum.IMAGE.getStr());
            DialogItem image1 = new DialogItem("image1", "媒体图片1", DialogItemEnum.IMAGE.getStr());
            DialogItem image2 = new DialogItem("image2", "媒体图片2", DialogItemEnum.IMAGE.getStr());
            DialogItem image3 = new DialogItem("image3", "媒体图片3", DialogItemEnum.IMAGE.getStr());
            whlnItems.add(lang);
            whlnItems.add(address);
            whlnItems.add(phone);
            whlnItems.add(mail);
            whlnItems.add(videoUrl);
            whlnItems.add(introduction);
            whlnItems.add(image);
            whlnItems.add(image1);
            whlnItems.add(image2);
            whlnItems.add(image3);
        }
    }

    @Override
    List<DialogItem> getPresentItems(Integer type) {
        return whlnItems;
    }

}
