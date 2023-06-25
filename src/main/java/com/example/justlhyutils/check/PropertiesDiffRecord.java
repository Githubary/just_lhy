package com.example.justlhyutils.check;

import lombok.Data;

/**
 * description: 属性差异记录
 * date 2022-02-08 17:33
 * @author liuhuayang
 */
@Data
public class PropertiesDiffRecord {

    private String newDiffItemName;
    private String oldDiffItemName;
    private String expectValue;
    private String actualValue;
}
