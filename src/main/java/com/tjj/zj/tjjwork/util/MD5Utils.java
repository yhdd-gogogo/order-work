package com.tjj.zj.tjjwork.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

/**
 * @parma
 */
@Component
public class MD5Utils {


    // MD5加密
    public static String encrpty(String str) {
        StringBuffer finalPassword = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 执行加密操作
            byte[] messageDigest = md.digest(str.getBytes());

            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    finalPassword.append('0');
                }
                finalPassword.append(hex);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return finalPassword.toString();
    }
}
