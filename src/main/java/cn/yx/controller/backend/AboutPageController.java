package cn.yx.controller.backend;

import cn.yx.enums.DialogItemEnum;
import cn.yx.model.DialogItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
        setPresentItems(model, 2);
        return "/about/jcgk";
    }

    @RequestMapping({ "dszzc" })
    public String dszzc(Model model) {
        setPresentItems(model, 1);
        return "/about/dszzc";
    }

    @RequestMapping({ "qyys" })
    public String qyys(Model model) {
        setPresentItems(model, 3);
        return "/about/qyys";
    }

    /**
     * Implement Methods
     */
    private static volatile List<DialogItem> dialogItems = new ArrayList<DialogItem>();

    @Override
    protected synchronized void initDialogItems() {
        if (dialogItems.size() == 0) {
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.EDITOR.getStr());
            content.setLength(1, 5000);
            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());
            image.setPlaceholder("推荐尺寸284*364");

            dialogItems.add(content);
            dialogItems.add(image);
        }
    }

    @Override
    protected List<DialogItem> getDailogItems() {
        return dialogItems;
    }

    private static volatile List<DialogItem> dszzcItems = new ArrayList<DialogItem>();
    private static volatile List<DialogItem> qyysItems = new ArrayList<DialogItem>();
    private static volatile List<DialogItem> jcgkItems = new ArrayList<DialogItem>();

    @Override
    void initPresentItems() {
        if (dszzcItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "1", DialogItemEnum.HIDDEN.getStr());
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.EDITOR.getStr());
            content.setLength(1, 5000);

            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());
            image.setPlaceholder("推荐尺寸(284*364)像素");

            dszzcItems.add(parent);
            dszzcItems.add(lang);
            dszzcItems.add(content);
            dszzcItems.add(image);
        }
        if (qyysItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "3", DialogItemEnum.HIDDEN.getStr());
            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.EDITOR.getStr());

            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());
            image.setPlaceholder("推荐尺寸(380*290)像素");

            qyysItems.add(parent);
            qyysItems.add(lang);
            qyysItems.add(content);
            qyysItems.add(image);
        }
        if (jcgkItems.size() == 0) {
            DialogItem parent = new DialogItem("parent", "0", DialogItemEnum.HIDDEN.getStr());

            DialogItem lang = new DialogItem("lang", "语言", DialogItemEnum.SELECT.getStr());
            lang.addSelectItem(0, "中文");
            lang.addSelectItem(1, "英文");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.EDITOR.getStr());
            content.setLength(1, 5000);

            DialogItem image = new DialogItem("image", "大图", DialogItemEnum.IMAGE.getStr());
            image.setPlaceholder("推荐尺寸(790*290)像素");
            DialogItem image1 = new DialogItem("image1", "小图1", DialogItemEnum.IMAGE.getStr());
            image1.setPlaceholder("推荐尺寸(380*290)像素");
            DialogItem image2 = new DialogItem("image2", "小图2", DialogItemEnum.IMAGE.getStr());
            image2.setPlaceholder("推荐尺寸(380*290)像素");

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
        if (type.equals(1)) {
            return dszzcItems;
        } else if (type.equals(3)) {
            return qyysItems;
        }
        return jcgkItems;
    }

}
