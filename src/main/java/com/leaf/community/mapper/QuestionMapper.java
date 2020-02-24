package com.leaf.community.mapper;

import com.leaf.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description, gmt_create, gmt_update, creator_id, tags) values (#{title}, #{description}, #{gmtCreate}, #{gmtUpdate}, #{creatorId}, #{tags})")
    void create(Question question);

    @Select("select * from question")
    List<Question> selectList();
}
