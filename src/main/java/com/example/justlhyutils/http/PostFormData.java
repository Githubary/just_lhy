package com.example.justlhyutils.http;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * description: http请求-提交表单数据
 *
 * @author liuhuayang
 * date: 2023/8/29 14:04
 */
public class PostFormData {

    public static void main(String[] args) throws IOException {
        String url = "http://api.itwork.yonghui.cn/oauth/oauth/token"; // 替换为实际的URL

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("username", "81115356");
        map.add("password", "TEhZc2hpemh1MTIz");
        map.add("originPassword", "U2FsdGVkX18sTxekTsrxCfv8QCrzNajXymt1Lp4Wu14=");
        map.add("grant_type", "password");
        map.add("client_id", "oneplatform");
        map.add("client_secret", "123456");
        map.add("authType", "value2");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class);
        ObjectMapper mapper = new ObjectMapper();
        Map responseMap =  mapper.convertValue(response.getBody(), Map.class);
        Object o = responseMap.get("access_token");
        System.out.println(o);
        System.out.println("Response: " + response.getBody());
    }
}
