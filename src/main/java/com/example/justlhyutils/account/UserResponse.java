package com.example.justlhyutils.account;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/11/9 15:17
 */
@Data
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bizContent;

    private Boolean hasNext;

    private List<UserInfo> users;

}
