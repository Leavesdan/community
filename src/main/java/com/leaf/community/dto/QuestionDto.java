package com.leaf.community.dto;

import com.leaf.community.model.Question;
import com.leaf.community.model.User;
import lombok.Data;

@Data
public class QuestionDto extends Question {

    private User user;
}
