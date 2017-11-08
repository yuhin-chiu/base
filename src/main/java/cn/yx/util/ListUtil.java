package cn.yx.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuxuanjiao
 * @date 2017年9月30日 下午2:54:24
 * @version 1.0
 */

public class ListUtil {

    // 将集合中元素用分隔符链接
    public static String connect(Collection<?> objects, String split) {
        if (objects == null || objects.isEmpty()) {
            return null;
        }
        if (split == null) {
            split = "";
        }
        StringBuilder strObjects = null;

        for (Object tmp : objects) {
            if (tmp == null) {
                continue;
            }
            if (strObjects == null) {
                strObjects = new StringBuilder();
            } else {
                strObjects.append(split);
            }
            strObjects.append(tmp.toString().trim());
        }

        return strObjects == null ? "" : strObjects.toString();
    }

    public static String connect(Collection<?> objects) {
        return connect(objects, ",");
    }

    public static List<String> split(String strs) {
        return split(strs, ",");
    }
    
    public static List<String> split(String strs, String split) {
        return Arrays.asList(strs.split(split));
    }
    public static String parseUri2Url (String str) {
        return str + 1;
    }
    public static void main(String...args) {
        String str = "test,test,test,";
        List<String> list = ListUtil.split(str);
        System.out.println(list.size());
        
        List<String> list2 = list.stream().map(ListUtil::parseUri2Url).collect(Collectors.toList());

        System.out.println(list2);
    }
}
