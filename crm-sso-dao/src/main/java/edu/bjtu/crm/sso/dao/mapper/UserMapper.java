package edu.bjtu.crm.sso.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
//    String s = "SELECT count(1) FROM";
//    @Select("SELECT count(1) FROM crm_sso_user WHERE username = #{username} and password = #{password}")
    int findUserByIdAndName(@Param("username") String username,@Param("password") String password);
}
