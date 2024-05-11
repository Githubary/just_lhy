package com.example.justlhyutils.design.createobject.original;

import lombok.Data;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/10/24 22:41
 */
@Data
public class BigModel implements Cloneable{

    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;
    private String a6;
    private String a7;
    private String a8;
    private String a9;
    private String a10;
    private String a11;
    private String a12;
    private String a13;
    private String a14;
    private String a15;
    private String a16;
    private String a17;
    private String a18;
    private String a19;
    private String a20;
    private String a21;
    private String a22;
    private String a23;
    private String a24;
    private String a25;
    private String a26;
    private String a27;
    private String a28;
    private String a29;
    private String a30;
    private String a31;
    private String a32;
    private SmallModel small;


    public BigModel() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        int loop = 1;
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if ("class".equals(propertyDescriptor.getName())) {
                continue;
            }
            if ("small".equals(propertyDescriptor.getName())) {
                continue;
            }
            propertyDescriptor.getWriteMethod().invoke(this, String.valueOf(loop++));
        }
    }

    @Override
    public BigModel clone() {
        try {
            BigModel bigModel = (BigModel) super.clone();
            bigModel.small = this.small.clone();
            return bigModel;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
