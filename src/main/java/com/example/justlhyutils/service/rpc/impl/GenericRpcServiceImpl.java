
package com.example.justlhyutils.service.rpc.impl;

import com.alibaba.fastjson.JSON;
import com.example.justlhyutils.service.rpc.GenericRpcService;
import org.apache.dubbo.common.config.ReferenceCache;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.SimpleReferenceCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/17 13:56
 */
@Service
public class GenericRpcServiceImpl implements GenericRpcService {

    @Override
    public GenericService buildGenericService(String interfaceClass, String group, String version) {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface(interfaceClass);
        reference.setVersion(version);
        //开启泛化调用
        reference.setGeneric("true");
        reference.setTimeout(30000);
        reference.setGroup(group);
        ReferenceCache cache = SimpleReferenceCache.getCache();
        try {
            return cache.get(reference);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
