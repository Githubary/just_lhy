package com.example.justlhyutils.model;

import lombok.Data;

import java.io.Serializable;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/17 11:19
 */
@Data
public class GeneralRpcRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String allPath;
    private String methodName;
    private String group;
    private String version;
    private String ipAddress;
    private String parameters;
    private String parametersValue;
}
