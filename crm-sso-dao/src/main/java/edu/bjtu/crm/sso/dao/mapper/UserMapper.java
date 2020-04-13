package edu.bjtu.crm.sso.dao.mapper;

import edu.bjtu.crm.sso.domain.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //todo 密码加密保护 token   sha
    String userinfo = "id, username";

    @Insert("INSERT INTO crm_sso_user (username, password) value (#{username}, sha(#{password}))")
    int addUser(User user);


    @Select("SELECT count(1) FROM crm_sso_user WHERE username = #{username} and password = sha(#{password}) and is_delete = false")
    int findUserByIdAndName(User user);


}
