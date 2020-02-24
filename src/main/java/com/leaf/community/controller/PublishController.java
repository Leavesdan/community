package com.leaf.community.controller;

import com.leaf.community.model.Question;
import com.leaf.community.model.User;
import com.leaf.community.mapper.QuestionMapper;
import com.leaf.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tags" , required = false) String tags,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title", title);
        model.addAttribute("description",description);
        model.addAttribute("tags", tags);
        if(title == null || title == ""){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if(description == null || description == ""){
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if(tags == null || tags == ""){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }


        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTags(tags);
        question.setCreatorId(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtUpdate(System.currentTimeMillis());
        questionMapper.create(question);

        return "redirect:/";
    }
}
