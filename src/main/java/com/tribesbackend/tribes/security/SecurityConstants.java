package com.tribesbackend.tribes.security;

//Added by Jirina coz JWT token
public class SecurityConstants {
    public static final String SECRET = "123456";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String HEADER_STRING = "X-Tribes-Token";
}
