package edu.bjtu.crm.sso.dao.mapper;

public interface UserMapper {
    int findUserByIdAndName(String username, String password);
}
