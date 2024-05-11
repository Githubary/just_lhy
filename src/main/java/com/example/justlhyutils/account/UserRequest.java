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
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bizContent;
    private String productCode;
    private String roleCode;
    private Integer size;

}
