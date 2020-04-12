package edu.bjtu.crm.sso.dao.mapper;

import edu.bjtu.crm.sso.domain.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    //todo 密码加密保护 token   sha
    String userinfo = "id, username";

    @Insert("INSERT INTO crm_sso_userinfo (username, password) value (#{username}, sha(#{password}))")
    int addUserInfo(UserInfo userInfo);


    @Select("SELECT count(1) FROM crm_sso_userinfo WHERE username = #{username} AND is_delete = false")
    UserInfo findUserInfoByUsername(@Param("username") String username);

    @Select("SELECT count(1) FROM crm_sso_userinfo WHERE pinyin = #{pinyin}")
    int findNumOfPinyin(String pinyin);
}
