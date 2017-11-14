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
@RequestMapping("/backend/contact")
public class ContactPageController extends DialogPageController {
    
    @RequestMapping({ "/", "", "other" })
    public String other(Model model) {
        setPresentItems(model, 1);
        return "/contact/other";
    }
    /**
     * Implement Methods
     */

    @Override
    protected synchronized void initDialogItems() {
    }

    @Override
    protected List<DialogItem> getDailogItems() {
        return null;
    }

    
    private static volatile List<DialogItem> otherItems = new ArrayList<DialogItem>();

    @Override
    void initPresentItems() {
        if(otherItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "所属栏目", DialogItemEnum.SELECT.getStr());
            parent.addSelectItem(0, "联系方式");
            parent.addSelectItem(1, "人力资源");
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.TEXTAREA.getStr());

            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());

            otherItems.add(parent);
            otherItems.add(lang);
            otherItems.add(content);
            otherItems.add(image);
        }
    }

    @Override
    List<DialogItem> getPresentItems(Integer type) {
        return otherItems;
    }

}
