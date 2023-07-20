package com.example.justlhyutils.onebook.model;

import lombok.Data;

import java.io.Serializable;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/7/14 17:06
 */
@Data
public class UserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

}
