package cn.yx.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;

/**
 * Json字符串相关操作工具类
 *
 * @author Wang Baomin
 * @since 2016/9/14
 */
public class JsonUtil {

    public static Object parse(String json) {
        return JSON.parse(json);
    }

    public static String toJson(Object object) {
        return JSON.toJSONString(object,
                SerializerFeature.WriteNonStringKeyAsString);
    }

    /**
     * json 字符串转List
     *
     * @param json   json字符串
     * @param prefix 前缀
     * @return List
     */
    public static List<String> toList(String json, String prefix) {
        if (json == null) {
            return null;
        }
        List<String> pathList = new ArrayList<>();
        JSONArray pathArr = (JSONArray) JsonUtil.parse(json);
        for (Object pathObj : pathArr) {
            String strPath = pathObj.toString();

            String absolutePath = prefix == null ? strPath : prefix + strPath;
            pathList.add(absolutePath);
        }

        return pathList;
    }

    public static JSONArray toJsonArray(String json) {
        return JSON.parseArray(json);
    }
    
    public static boolean jsonArrayIsEmpty(JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.size() == 0) {
            return true;
        }
        return false;
    }
}
