package com.tribesbackend.tribes.configurations.security;

//Added by Jirina coz JWT token
public class SecurityConstants {
    public static final String SECRET = "123456";
            //System.getenv("SECRET");
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Tribes ";
    public static final String HEADER_STRING = "X-Tribes-Token";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
