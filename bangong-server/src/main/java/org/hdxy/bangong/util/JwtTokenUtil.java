package org.hdxy.bangong.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * jwt的工具类
 */
@Component
public class JwtTokenUtil {

    //根据用户信息生成token
    public static String generateToken(UserDetails userDetails) {
        HashMap<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("type", "jwt");
        JwtBuilder builder = Jwts.builder()
                .setHeader(header)
                .setId("lk")
                .setSubject("sub")
                .setIssuedAt(new Date())
                .claim("username", userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + UntilFinal.EXPIRETOKEN * 1000))
                .signWith(SignatureAlgorithm.HS256, UntilFinal.SECRETKEY);
        return builder.compact();
    }

    //解析token
    private Claims getClaims(String token) {
        Claims claims = (Claims) Jwts.parser()
                .setSigningKey(UntilFinal.SECRETKEY)
                .parse(token).getBody();
        if (claims == null) return null;
        return claims;
    }

    //根据token获取用户名
    public static String getUserNameFromToken(String token) {
        Claims claims = new JwtTokenUtil().getClaims(token);
        String username = (String) claims.get("username");
        if (username == null || username.equals("")) return null;
        return username;
    }

    //判断token是否有效 --- token中的用户信息和登录的信息相同且 有效期存在
    public static boolean validateToken(String token, UserDetails details) {
        Claims claims = new JwtTokenUtil().getClaims(token);
        //判断token是否失效
        boolean before = claims.getExpiration().before(new Date());
        return getUserNameFromToken(token).equals(details.getUsername()) && !before;
    }

    //判断token是否要进行刷新----就是token是否还有效
    public static boolean canRefresh(String token) {
        Claims claims = new JwtTokenUtil().getClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    //刷新token
    public static String refreshToken(String token) {
        Claims claims = new JwtTokenUtil().getClaims(token);
        claims.setExpiration(new Date(System.currentTimeMillis() + UntilFinal.EXPIRETOKEN * 1000));
        return Jwts.builder().setClaims(claims).compact();
    }
}
