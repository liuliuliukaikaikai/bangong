package org.hdxy.bangong.util;

import org.apache.ibatis.parsing.TokenHandler;

/**
 * 配置中常用的属性
 */


public class UntilFinal {
    //jwt配置
    public static final String TokenHandler = "Authorization"; //jwt存储的请求头
    public static final String SECRETKEY = "jwtsecretykey"; //jwt秘钥
    public static final int EXPIRETOKEN = 60 * 60 * 24; //token过期时间
    public static final String TOKENHEADER = "Bearer"; //token中的开头
}
