package edu.bjtu.crm.sso.dao.mapper;

import edu.bjtu.crm.sso.domain.model.User;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoMapper {
    //todo 密码加密保护 token   sha
    String userinfo = "id, username";

    @Insert("INSERT INTO crm_sso_userinfo (username,name,pinyin,position,is_mng,email,phone) " +
            "VALUE (#{username}, #{name}, #{pinyin}, #{position}, #{isMng}, #{email}, #{phone})")
    int addUserInfo(UserInfo userInfo);

    @Select("SELECT id,username,name,pinyin,position,is_mng as isMng,email,phone,token " +
            "FROM crm_sso_userinfo " +
            "WHERE username = #{username} AND " +
            "is_delete = false")
    UserInfo findUserInfoByUsername(String username);

    @Select("SELECT id,username,name,pinyin,position,is_mng as isMng,email,phone,token " +
            "FROM crm_sso_userinfo " +
            "WHERE id = #{id} AND " +
            "is_delete = false")
    UserInfo findUserInfoById(long id);

    @Select("SELECT count(1) FROM crm_sso_userinfo WHERE pinyin = #{pinyin}")
    int findNumOfPinyin(String pinyin);

    @Select("<script>SELECT id,username,name,pinyin,position,is_mng as isMng,email,phone,token" +
            " FROM crm_sso_userinfo " +
            "<where>" +
            "        <if test=\"id != '-1'\">" +
            "            AND id = #{id}" +
            "        </if>" +
            "        <if test=\"username != null and username != ''\">" +
            "            AND username = #{username}" +
            "        </if>" +
            "        <if test=\"phone != null and phone != ''\">" +
            "            AND phone = #{phone}" +
            "        </if>" +
            " AND is_delete = false" +
            "    </where></script>")
    UserInfo findUserInfoByUserInfo(UserInfo userInfo);

    @Update("UPDATE crm_sso_user set password = sha(#{password}) " +
            "WHERE username = #{user.username) and password = sha(#{password}) and is_delete = false")
    int updatePassword(@Param("User") User user, @Param("password") String password);

    @Update("UPDATE crm_sso_userinfo set name = #{name}, position = #{position},is_mng = #{isMng}, email = #{email},phone = #{phone} " +
            "WHERE username = #{username} and is_delete = false")
    int updateUserInfo(UserInfo userInfo);

    @Update("UPDATE crm_sso_userinfo set is_delete = true " +
            "WHERE username = #{username} and is_delete = false")
    int deleteUserInfoByUsername(String username);
}
