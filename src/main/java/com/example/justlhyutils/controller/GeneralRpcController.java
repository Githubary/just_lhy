package com.example.justlhyutils.controller;

import com.alibaba.fastjson2.JSON;
import com.example.justlhyutils.model.GeneralRpcRequest;
import com.example.justlhyutils.service.rpc.GenericRpcService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/6/16 15:21
 */
@Slf4j
@RequestMapping("/rpc")
@RestController
public class GeneralRpcController {

    private final GenericRpcService genericRpcService;

    public GeneralRpcController(GenericRpcService genericRpcService) {
        this.genericRpcService = genericRpcService;
    }

    @RequestMapping("/general")
    public String generalRpc(@RequestBody GeneralRpcRequest request) throws JsonProcessingException {
        try {
            String group = request.getGroup();
            String interfaceClass = request.getAllPath();
            String version = request.getVersion();
            GenericService genericService = genericRpcService.buildGenericService(interfaceClass,group,version);
            String methodName = request.getMethodName();
            String parameters = request.getParameters();
            String[] parametersSplit = parameters.split(",");
            String parametersValue = request.getParametersValue();
            ObjectMapper objectMapper = new ObjectMapper();
            Object[] parametersValueList = objectMapper.readValue(parametersValue, Object[].class);
            log.info("即将进行泛化调用:methodName:{},parametersSplit:{},value:{}", methodName, JSON.toJSONString(parametersSplit),
                    JSON.toJSONString(parametersValueList));
            Object o = genericService.$invoke(methodName, parametersSplit, parametersValueList);
            log.info("泛化调用结果:{}", JSON.toJSONString(o));
            return JSON.toJSONString(o);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return sw.toString();
        }
    }

}
