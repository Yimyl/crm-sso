package edu.bjtu.crm.sso.service;

import edu.bjtu.crm.sso.domain.model.User;
import edu.bjtu.crm.sso.domain.model.UserInfo;

public interface UserMngService {
    String addUser(User user, UserInfo userInfo);

    int login(User user);

    UserInfo findUserInfoByUsername(String username);

    UserInfo findUserInfoById(long id);

    int updatePassword(User user, String password);

    UserInfo findUserInfoByUserInfo(UserInfo userInfo);

    int updateUserInfo(UserInfo userInfo);

    int deleteUserInfoByUsername(String username);
}
