package com.leaf.community.controller;


import com.leaf.community.dto.AccessTokenDto;
import com.leaf.community.dto.GithubUser;
import com.leaf.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        System.out.println(code+"==="+state);
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("5be521d6be25d28ae4ab");
        accessTokenDto.setClient_secret("5d3ce2b9b581826691b60bf1a8200aa80d6ad12d");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setState(state);
        String token = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(token);
        System.out.println(githubUser.getName());
        return "index";
    }
}
