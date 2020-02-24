package com.leaf.community.service;

import com.leaf.community.dto.QuestionDto;
import com.leaf.community.mapper.QuestionMapper;
import com.leaf.community.mapper.UserMapper;
import com.leaf.community.model.Question;
import com.leaf.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> findQuestionList() {
        List<Question> questionList = questionMapper.selectList();
        List<QuestionDto> questionDtoList = new ArrayList<QuestionDto>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreatorId());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }
}
