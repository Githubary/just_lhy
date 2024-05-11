package com.example.justlhyutils.model;

import lombok.Data;

import java.io.Serializable;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/12/6 18:15
 */
@Data
public class ReturnShelfDto {


    /**
     * 是否开启返架按钮.true启用
     */
    private Boolean returnShelfEnable;

    /**
     * 是否开启返架扫码
     */
    private Boolean returnShelfScanCode;

}

