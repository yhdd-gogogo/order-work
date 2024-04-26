package com.tjj.zj.tjjwork.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

/**
 * @author zxf
 */
public class SimpleKeyUtil {

    private static final ThreadLocal<String> SIMPLE_ID_POOL = new ThreadLocal<>();

    private static final String[] chars = new String[]{
            "a","b","c","d","e","f","g","h","i","j","k",
            "l","m","n","o","p","q","r","s","t","u","v",
            "w","x","y","z","0","1","2","3","4","5","6",
            "7","8","9","A","B","C","D","E","F","G",
            "H","I","J","K","L","M","N","O","P","Q","R",
            "S","T","U","V","W","X","Y","Z"
    };

    public static String genShortUuId(){
        return genShortUuId(8);
    }

    public static String genShortUuId(int bit){
        StringBuffer buffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        for (int i = 0; i < bit; i++) {
            String str = uuid.substring(i*4, i*4 + 4);
            int x = Integer.parseInt(str, 16);
            buffer.append(chars[x% 0x3E]);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(genShortUuId());
        System.out.println(Base64.getEncoder().encodeToString("zhaoxiaofeng".getBytes(StandardCharsets.UTF_8)));
    }
}
