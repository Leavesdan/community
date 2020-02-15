package com.leaf.community.provider;

import com.alibaba.fastjson.JSON;
import com.leaf.community.dto.AccessTokenDto;
import com.leaf.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import org.thymeleaf.standard.expression.GreaterThanExpression;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto ));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            String token = str.split("&")[0].split("=")[1];
            System.out.println(str);
            System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            System.out.println(resStr);
            GithubUser githubUser = JSON.parseObject(resStr, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
