package com.base.common.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 * Created by Administrator on 2017/2/25.
 */
public final class CollectionUtil {
    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断集合是否为非空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    /**
     * 判断map是否为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断map是否为非空
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty(map);
    }

    /**
            * @param targetList
     *            要排序的实体类List集合
     * @param sortField
     *            排序字段(实体类属性名)
     * @param sortMode
     *            true正序，false逆序
     */

}
