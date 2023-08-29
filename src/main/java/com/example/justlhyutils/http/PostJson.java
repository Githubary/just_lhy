package com.example.justlhyutils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/8/29 14:25
 */
public class PostJson {
    public static void main(String[] args) throws JsonProcessingException {
        String url = "http://public-service.sqldbms-front.gw.yonghui.cn/api/sql-workflow-dbms/v1/projects/227/sql/query"; // 替换为实际的URL

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization","7e9f63f1-5a1d-4773-8862-ba555af32f88");

        String jsonBody = "{\n" +
                "    \"env\": 441,\n" +
                "    \"limitNum\": 1000,\n" +
                "    \"sqlContent\": \"select * from shop_base_config\\nwhere config_key = 'CFG_PICK_AND_PACK_NEW'\",\n" +
                "    \"instanceId\": 1796,\n" +
                "    \"dbName\": \"ofs_shop_center\",\n" +
                "    \"list_structure\": \"shop_base_config\",\n" +
                "    \"explain\": false\n" +
                "}";

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = mapper.readValue(response.getBody(), JSONObject.class);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("rows");
        Object o = jsonArray.get(0);
        System.out.println(o);
        System.out.println("Response: " + response.getBody());
    }
}
