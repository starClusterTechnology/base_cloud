package com.base.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 字符串工具类
 * Created by Administrator on 2017/2/25.
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null) return true;
        str = str.trim();
        return str.length() == 0;
    }

    /**
     * 判断字符串是否为非空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static boolean notNullOrEmpty(Object obj) {
        return !isNullOrEmpty(obj);
    }



}
