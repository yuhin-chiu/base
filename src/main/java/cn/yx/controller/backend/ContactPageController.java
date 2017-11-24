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

    @RequestMapping({ "/", "", "lxfs" })
    public String other(Model model) {
        setPresentItems(model, 0);
        return "/contact/lxfs";
    }

    @RequestMapping({ "rlzy" })
    public String rlzy(Model model) {
        setPresentItems(model, 1);
        return "/contact/rlzy";
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

    private static volatile List<DialogItem> lxfsItems = new ArrayList<DialogItem>();
    private static volatile List<DialogItem> rlzyItems = new ArrayList<DialogItem>();

    @Override
    void initPresentItems() {
        if (lxfsItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "0", DialogItemEnum.HIDDEN.getStr());
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.TEXTAREA.getStr());

            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());
            image.setPlaceholder("建议宽度880px");

            lxfsItems.add(parent);
            lxfsItems.add(lang);
            lxfsItems.add(content);
            lxfsItems.add(image);
        }
        if (rlzyItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "1", DialogItemEnum.HIDDEN.getStr());
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.TEXTAREA.getStr());

            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());
            image.setPlaceholder("建议宽度1200px");

            rlzyItems.add(parent);
            rlzyItems.add(lang);
            rlzyItems.add(content);
            rlzyItems.add(image);
        }
    }

    @Override
    List<DialogItem> getPresentItems(Integer type) {
        if (type.equals(0)) {
            return lxfsItems;
        }
        return rlzyItems;
    }

}
