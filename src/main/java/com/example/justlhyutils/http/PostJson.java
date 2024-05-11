package com.example.justlhyutils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.justlhyutils.account.UserInfo;
import com.example.justlhyutils.account.UserResponse;
import com.example.justlhyutils.model.GeneralResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * description:http请求-提交json数据
 *
 * @author liuhuayang
 * date: 2023/8/29 14:25
 */
public class PostJson {
    public static void main(String[] args) throws JsonProcessingException {
        String url = "http://o2o-support-dev.o2o-support-idaas-gateway.devgw.yonghui.cn/o2o-support-idaas-application/v1/open/user/queryUsersByRoles"; // 替换为实际的URL

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization","7e9f63f1-5a1d-4773-8862-ba555af32f88");

        String jsonBody = "{\n" +
                "  \"roleCode\": \"bravo-jb-picker\",\n" +
                "  \"size\": 200\n" +
                "}";

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        GeneralResponse<UserResponse> userResponse = JSON.parseObject(response.getBody(),new TypeReference<GeneralResponse<UserResponse>>(){});
        UserResponse result = userResponse.getResult();

        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = mapper.readValue(response.getBody(), JSONObject.class);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("rows");
        Object o = jsonArray.get(0);
        System.out.println(o);
        System.out.println("Response: " + response.getBody());
    }
}
