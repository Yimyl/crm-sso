package edu.bjtu.crm.sso.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //todo 密码加密保护 token   sha
    String userinfo = "id, username";

    @Insert("INSERT INTO crm_sso_user (username, password) value (#{username}, sha(#{password}))")
    int addUser(@Param("username") String username,@Param("password") String password);


    @Select("SELECT count(1) FROM crm_sso_user WHERE username = #{username} and password = sha(#{password}) and is_delete = false")
    int findUserByIdAndName(@Param("username") String username,@Param("password") String password);
}
