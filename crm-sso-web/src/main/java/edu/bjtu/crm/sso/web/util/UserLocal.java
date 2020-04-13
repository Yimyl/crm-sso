package edu.bjtu.crm.sso.web.util;

import edu.bjtu.crm.sso.domain.model.UserInfo;

public class UserLocal {

    private UserLocal(){ }

    private static final ThreadLocal<UserInfo> USER_INFO = new ThreadLocal<UserInfo>();

    public static void set(UserInfo userInfo) {
        USER_INFO.set(userInfo);
    }

    public static UserInfo get() {
        return USER_INFO.get();
    }

    public static void remove() {
        USER_INFO.remove();
    }

    public static String getName() {
        return USER_INFO.get().getName();
    }

    public static String getUsername() {
        return USER_INFO.get().getUsername();
    }

    public static int getIsMng() {
        return USER_INFO.get().getIsMng();
    }

    public static String getPhone() {
        return USER_INFO.get().getPhone();
    }

    public static String getEmail() {
        return USER_INFO.get().getEmail();
    }

    public static String getToken() {
        return USER_INFO.get().getToken();
    }
}
