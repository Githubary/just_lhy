package com.example.justlhyutils.onebook.login;

import com.alibaba.fastjson.JSON;
import com.example.justlhyutils.onebook.model.LoginRequest;
import com.example.justlhyutils.onebook.model.MeResponse;
import com.example.justlhyutils.onebook.model.UserToken;
import com.example.justlhyutils.onebook.model.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/7/14 11:23
 */
@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080", methods = {RequestMethod.GET, RequestMethod.POST})
public class LoginController {

    @PostMapping("/login")
    public WebResult<UserToken> login(@RequestBody LoginRequest loginRequest){
        log.info("一本糊涂账：登录参数:{}", JSON.toJSONString(loginRequest));
        UserToken userToken = new UserToken();
        userToken.setToken("token");
        return WebResult.success(userToken);
    }

    @RequestMapping("/me")
    public WebResult<MeResponse> me(){
        log.info("一本糊涂账：me");
        MeResponse meResponse = new MeResponse();
        meResponse.setDefault_cashbook("未知");
        meResponse.setUnread_count(10);
        return WebResult.success(meResponse);
    }

}
