package com.hanson.soo.user.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getId(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
