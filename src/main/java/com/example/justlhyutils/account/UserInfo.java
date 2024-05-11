package com.example.justlhyutils.account;

import lombok.Data;

import java.io.Serializable;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/11/9 15:14
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer isDisable;
    /**
     * 电话
     */
    private String phoneNum;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 员工工号
     */
    private String userCode;
    /**
     * 员工类型
     */
    private String userType;

}
