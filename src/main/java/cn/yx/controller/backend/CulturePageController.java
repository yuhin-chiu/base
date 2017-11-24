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

    @RequestMapping({ "ygsh" })
    public String ygsh(Model model) {
        setPresentItems(model, 2);
        return "/culture/ygsh";
    }

    /**
     * Implement Methods
     */
    private static volatile List<DialogItem> dialogItems = new ArrayList<DialogItem>();

    @Override
    protected synchronized void initDialogItems() {
        if (dialogItems.size() == 0) {
            DialogItem title = new DialogItem("title", "标题");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.EDITOR.getStr());
            content.setLength(1, 500);
            DialogItem parent = new DialogItem("parent", "0", DialogItemEnum.HIDDEN.getStr());

            dialogItems.add(title);
            dialogItems.add(content);
            dialogItems.add(parent);
        }
    }

    @Override
    protected List<DialogItem> getDailogItems() {
        return dialogItems;
    }

    private static volatile List<DialogItem> whlnItems = new ArrayList<DialogItem>();
    private static volatile List<DialogItem> ygshItems = new ArrayList<DialogItem>();

    @Override
    void initPresentItems() {
        if (whlnItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "1", DialogItemEnum.HIDDEN.getStr());
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());
            image.setPlaceholder("建议宽度1200px");

            whlnItems.add(parent);
            whlnItems.add(lang);
            whlnItems.add(image);
        }
        if (ygshItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "2", DialogItemEnum.HIDDEN.getStr());
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());
            image.setPlaceholder("建议宽度1200px");

            ygshItems.add(parent);
            ygshItems.add(lang);
            ygshItems.add(image);
        }
    }

    @Override
    List<DialogItem> getPresentItems(Integer type) {
        if (type.equals(1)) {
            return whlnItems;
        }
        return ygshItems;
    }

}
