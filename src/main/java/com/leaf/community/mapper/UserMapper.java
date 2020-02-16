package com.leaf.community.mapper;

import com.leaf.community.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_update) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtUpdate})")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
