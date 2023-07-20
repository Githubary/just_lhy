package com.example.justlhyutils.onebook.model;

import lombok.Data;

import java.io.Serializable;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/7/14 11:39
 */
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;
    private String password;

}
