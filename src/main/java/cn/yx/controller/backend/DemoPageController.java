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
@RequestMapping("/backend/demo")
public class DemoPageController extends DialogPageController {
    
    
    @RequestMapping({ "/", "", "list" })
    public String index(Model model) {
        setDailogItems(model);
        return "/demo/list";
    }


    /**
     * Implement Methods
     */
    private static volatile List<DialogItem> dialogItems = new ArrayList<DialogItem>();

    @Override
    protected synchronized void initDialogItems() {
        if(dialogItems.size() == 0) {
            DialogItem title = new DialogItem("title", "标题");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.TEXTAREA.getStr());
            dialogItems.add(title);
            dialogItems.add(content);
        }
    }

    @Override
    protected List<DialogItem> getDailogItems() {
        return dialogItems;
    }

}
