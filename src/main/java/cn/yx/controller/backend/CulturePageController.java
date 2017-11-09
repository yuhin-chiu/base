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
@RequestMapping("/backend/culture")
public class CulturePageController extends DialogPageController {
    
    
    @RequestMapping({ "/", "", "list" })
    public String index(Model model) {
        setDailogItems(model);
        return "/culture/list";
    }

    @RequestMapping({ "whln" })
    public String whln(Model model) {
        setPresentItems(model, 1);
        return "/culture/whln";
    }

    /**
     * Implement Methods
     */
    private static volatile List<DialogItem> dialogItems = new ArrayList<DialogItem>();

    @Override
    protected synchronized void initDialogItems() {
        if(dialogItems.size() == 0) {
            DialogItem title = new DialogItem("title", "标题");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.EDITOR.getStr());
            content.setLength(1, 500);

            dialogItems.add(title);
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
        if(whlnItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "所属栏目", DialogItemEnum.SELECT.getStr());
            parent.addSelectItem(1, "文化理念");
            parent.addSelectItem(2, "员工生活");
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());

            whlnItems.add(parent);
            whlnItems.add(lang);
            whlnItems.add(image);
        }
    }

    @Override
    List<DialogItem> getPresentItems(Integer type) {
        return whlnItems;
    }

}
