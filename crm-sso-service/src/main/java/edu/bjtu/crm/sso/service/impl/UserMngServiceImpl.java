package edu.bjtu.crm.sso.service.impl;

import edu.bjtu.crm.sso.dao.mapper.UserInfoMapper;
import edu.bjtu.crm.sso.dao.mapper.UserMapper;
import edu.bjtu.crm.sso.service.UserMngService;
import edu.bjtu.crm.sso.domain.model.User;
import edu.bjtu.crm.sso.domain.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

@Service
public class UserMngServiceImpl implements UserMngService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Transactional
    @Override
    public String addUser(User user, UserInfo userInfo) {
        try {
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
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "";
        }
    }

    @Override
    public int login(User user) {
        return userMapper.findUserByIdAndName(user);
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) {
        return userInfoMapper.findUserInfoByUsername(username);
    }

    @Override
    public UserInfo findUserInfoById(long id) {
        return userInfoMapper.findUserInfoById(id);
    }

    @Override
    public int updatePassword(User user, String password) {
        return userInfoMapper.updatePassword(user, password);
    }

    @Override
    public UserInfo findUserInfoByUserInfo(UserInfo userInfo) {
        return userInfoMapper.findUserInfoByUserInfo(userInfo);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    @Transactional
    @Override
    public int deleteUserInfoByUsername(String username) {
        try {
            userInfoMapper.deleteUserInfoByUsername(username);
            userMapper.deleteUserByUsername(username);
            return 1;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }
}
