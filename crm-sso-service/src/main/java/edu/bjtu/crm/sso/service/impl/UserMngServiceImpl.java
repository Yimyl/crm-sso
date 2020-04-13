package edu.bjtu.crm.sso.service.impl;

import edu.bjtu.crm.sso.dao.mapper.UserInfoMapper;
import edu.bjtu.crm.sso.dao.mapper.UserMapper;
import edu.bjtu.crm.sso.service.UserMngService;
import edu.bjtu.crm.sso.domain.model.User;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserMngServiceImpl implements UserMngService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public String addUser(User user, UserInfo userInfo) {
        int num = userInfoMapper.findNumOfPinyin(userInfo.getPinyin());
        if (num == 0) {
            userInfo.setUsername(userInfo.getPinyin());
        } else {
            userInfo.setUsername(userInfo.getPinyin() + (num + 1));
        }
        user.setUsername(userInfo.getUsername());
        userMapper.addUser(user);
        userInfoMapper.addUserInfo(userInfo);
        return userInfo.getUsername();
    }

    @Override
    public int login(User user) {
        return userMapper.findUserByIdAndName(user);
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) {
        return userInfoMapper.findUserInfoByUsername(username);
    }

}
