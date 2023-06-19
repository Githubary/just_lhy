package com.example.justlhyutils.service.rpc;

import org.apache.dubbo.rpc.service.GenericService;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/17 13:56
 */
public interface GenericRpcService {

    GenericService buildGenericService(String interfaceClass, String group, String version);

}
