package com.bean.sell.utils;

import java.util.UUID;

public class KeyUtil {

    public static String genUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
