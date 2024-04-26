package com.tjj.zj.tjjwork.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * @author sxz
 *
 */
@Component
public class JwtUtil {

    private final static String SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";

    public static String createToken(String userId, int seconds) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                //设置字符串过期时间
                .setExpiration(new Date(System.currentTimeMillis() + seconds * 1000))
                //私有部分
                .claim("userId", userId)
                //设置秘钥
                .signWith(signatureAlgorithm, signingKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)){
            return false;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(jwtToken).getBody();
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取用户id
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Claims claims = parseJWT(token);
        if (null != claims) {
            return (String) claims.get("userId");
        }
        return null;
    }

    private static Claims parseJWT(String jsonWebToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jsonWebToken).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        String token = createToken("zhaoxiaofeng", 2 * 60 * 60);
        System.out.println("token: " + token);
        System.out.println(getUserId(token));
        System.out.println(checkToken(token));
    }
}
