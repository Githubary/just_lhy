package com.example.justlhyutils.check;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description:
 * date 2022-02-08 17:47
 */
public class PropertiesDiffExecute {
    /**
     * 对比接口结果
     *
     * @param sourceObj
     * @param popObj
     * @return
     */
    public static Boolean diffObject(String diffName, Object sourceObj, Object popObj, List<PropertiesDiffRecord> diffRecords, List<String> ignoreNames) {
        if (ignoreNames.contains(diffName)) {
            return true;
        }

        if (sourceObj == null && popObj == null) {
            return true;
        }
        if (sourceObj.getClass().isPrimitive()) {
            if (sourceObj != popObj) {
                addPropertiesDiffRecord(sourceObj, popObj, diffName, diffRecords);
                return false;
            } else {
                return true;
            }
        }
        if (isBaseType(sourceObj, popObj)) {
            if (sourceObj != null && !sourceObj.equals(popObj)) {
                addPropertiesDiffRecord(sourceObj, popObj, diffName, diffRecords);
                return false;
            } else if (popObj != null && !popObj.equals(sourceObj)) {
                addPropertiesDiffRecord(sourceObj, popObj, diffName, diffRecords);
                return false;
            } else {
                return true;
            }
        }
        // 旧结果是list
        if (sourceObj instanceof List) {
            // 新结果不是list报错
            if (!(popObj instanceof List)) {
                addPropertiesDiffRecord(sourceObj, popObj, diffName + "的类型不同" + "<oldClass>=[" + sourceObj.getClass() + "]<newClass>=[" + popObj.getClass() + "]", diffRecords);
                return false;
            }
            Boolean result = true;
            List sourceList = (List) sourceObj;
            List popList = (List) popObj;
            if (sourceList.size() != popList.size()) {
                addPropertiesDiffRecord(sourceObj, popObj, diffName + "的集合长度不同", diffRecords);
                return false;
            }
            for (int i = 0; i < sourceList.size(); i++) {
                result = diffObject("list", sourceList.get(i), popList.get(i), diffRecords, ignoreNames) && result;
            }
            return result;
        }
        // 旧的不是list,新结果如果是list那么就报错
        if (popObj instanceof List) {
            addPropertiesDiffRecord(sourceObj, popObj, diffName + "的类型不同" + "<oldClass>=[" + sourceObj.getClass() + "]<newClass>=[" + popObj.getClass() + "]", diffRecords);
            return false;
        }

        // 旧结果是数组
        if (sourceObj.getClass().isArray()) {
            // 新结果不是数组报错
            if (!(popObj.getClass().isArray())) {
                addPropertiesDiffRecord(sourceObj, popObj, diffName + "的类型不同" + "<oldClass>=[" + sourceObj.getClass() + "]<newClass>=[" + popObj.getClass() + "]", diffRecords);
                return false;
            }
            Boolean result = true;
            Object[] sourceList = (Object[]) sourceObj;
            Object[] popList = (Object[]) popObj;
            if (sourceList.length != popList.length) {
                addPropertiesDiffRecord(sourceObj, popObj, diffName + "的数组长度不同", diffRecords);
                return false;
            }
            for (int i = 0; i < sourceList.length; i++) {
                result = diffObject("list", sourceList[i], popList[i], diffRecords, ignoreNames) && result;
            }
            return result;
        }
        // 旧的不是数组,新结果如果是数组那么就报错
        if (popObj.getClass().isArray()) {
            addPropertiesDiffRecord(sourceObj, popObj, diffName + "的类型不同" + "<oldClass>=[" + sourceObj.getClass() + "]<newClass>=[" + popObj.getClass() + "]", diffRecords);
            return false;
        }

        Boolean result = true;
        Map<String, Object> sourceMap = JSONObject.parseObject(JSONObject.toJSONString(sourceObj));
        Map<String, Object> popMap = JSONObject.parseObject(JSONObject.toJSONString(popObj));
//        if (sourceMap.size() != popMap.size()) {
//            addPropertiesDiffRecord(sourceObj, popObj, diffName + "对象属性数量不同", diffRecords);
//            return false;
//        }
        Set<String> keySet = sourceMap.keySet();
        for (String sourceKey : keySet) {
            // 属性缺失的情况
            if (!popMap.containsKey(sourceKey)) {
                addPropertiesDiffRecord(sourceMap.get(sourceKey), null, "新结果没有属性为[" + sourceKey + "]的值", diffRecords);
                continue;
            }
            result = diffObject(sourceKey, sourceMap.get(sourceKey), popMap.get(sourceKey), diffRecords, ignoreNames) && result;
        }
        return result;
    }

    private static boolean isBaseType(Object sourceObj, Object popObj) {
        if (sourceObj instanceof String) {
            return true;
        } else if (sourceObj instanceof Integer) {
            return true;
        } else if (sourceObj instanceof Character) {
            return true;
        } else if (sourceObj instanceof Boolean) {
            return true;
        } else if (sourceObj instanceof Long) {
            return true;
        } else if (sourceObj instanceof Byte) {
            return true;
        } else if (sourceObj instanceof Double) {
            return true;
        } else if (sourceObj instanceof Float) {
            return true;
        } else if (sourceObj instanceof Date) {
            return true;
        } else if (sourceObj instanceof BigDecimal) {
            return true;
        } else if (sourceObj instanceof Short) {
            return true;
        } else {
            return false;
        }
    }

    private static void addPropertiesDiffRecord(Object sourceObj, Object popObj, String diffName, List<PropertiesDiffRecord> diffRecords) {
        PropertiesDiffRecord record = new PropertiesDiffRecord();
        record.setExpectValue(JSONObject.toJSONString(sourceObj));
        record.setActualValue(JSONObject.toJSONString(popObj));
        record.setNewDiffItemName(diffName);
        record.setOldDiffItemName(diffName);
        diffRecords.add(record);
    }
}
