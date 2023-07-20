package com.example.justlhyutils.onebook.model;

import lombok.Data;

import java.io.Serializable;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/7/14 17:08
 */
@Data
public class MeResponse implements Serializable {

    private String default_cashbook;
    private Integer unread_count;
}
