package com.leaf.community.model;

import lombok.Data;

@Data
public class Question {

    private Integer id;
    private String title;
    private String description;
    private String tags;
    private Long gmtCreate;
    private Long gmtUpdate;
    private Integer creatorId;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;


}
