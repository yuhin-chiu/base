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
@RequestMapping("/backend/about")
public class AboutPageController extends DialogPageController {
    
    
    @RequestMapping({ "/", "", "list" })
    public String index(Model model) {
        setDailogItems(model);
        return "/about/list";
    }

    @RequestMapping({ "jcgk" })
    public String jcgk(Model model) {
        setPresentItems(model, 1);
        return "/about/jcgk";
    }
    
    @RequestMapping({ "other" })
    public String other(Model model) {
        setPresentItems(model, 2);
        return "/about/other";
    }
    /**
     * Implement Methods
     */
    private static volatile List<DialogItem> dialogItems = new ArrayList<DialogItem>();

    @Override
    protected synchronized void initDialogItems() {
        if(dialogItems.size() == 0) {
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.EDITOR.getStr());
            content.setLength(1, 500);
            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());

            dialogItems.add(content);
            dialogItems.add(image);
        }
    }

    @Override
    protected List<DialogItem> getDailogItems() {
        return dialogItems;
    }

    
    private static volatile List<DialogItem> whlnItems = new ArrayList<DialogItem>();
    private static volatile List<DialogItem> jcgkItems = new ArrayList<DialogItem>();

    @Override
    void initPresentItems() {
        if(whlnItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "所属栏目", DialogItemEnum.SELECT.getStr());
            parent.addSelectItem(1, "董事长致辞");
            parent.addSelectItem(3, "企业优势");
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.TEXTAREA.getStr());

            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());

            whlnItems.add(parent);
            whlnItems.add(lang);
            whlnItems.add(content);
            whlnItems.add(image);
        }
        if(jcgkItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "0", DialogItemEnum.HIDDEN.getStr());

            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.TEXTAREA.getStr());

            DialogItem image = new DialogItem("image", "大图", DialogItemEnum.IMAGE.getStr());
            DialogItem image1 = new DialogItem("image1", "小图1", DialogItemEnum.IMAGE.getStr());
            DialogItem image2 = new DialogItem("image2", "小图2", DialogItemEnum.IMAGE.getStr());

            jcgkItems.add(parent);
            jcgkItems.add(lang);
            jcgkItems.add(content);
            jcgkItems.add(image);
            jcgkItems.add(image1);
            jcgkItems.add(image2);
        }
    }

    @Override
    List<DialogItem> getPresentItems(Integer type) {
        if(type.equals(2)) {
            return whlnItems;
        }
        return jcgkItems;
    }

}
