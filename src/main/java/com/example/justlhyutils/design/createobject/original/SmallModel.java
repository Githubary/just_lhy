package com.example.justlhyutils.design.createobject.original;

import lombok.Data;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/10/24 22:43
 */
@Data
public class SmallModel implements Cloneable{

    private String name;
    private Integer age;
    private Long number;

    @Override
    public SmallModel clone() {
        try {
            return (SmallModel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
