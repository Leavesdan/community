package com.leaf.community.mapper;

import com.leaf.community.dto.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description, gmt_create, gmt_update, creator_id, tags) values (#{title}, #{description}, #{gmtCreate}, #{gmtUpdate}, #{creatorId}, #{tags})")
    void create(Question question);
}
