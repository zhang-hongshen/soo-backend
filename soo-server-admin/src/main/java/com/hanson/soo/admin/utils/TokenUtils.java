package com.hanson.soo.admin.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Random;

public class TokenUtils {

    static class TokenProcessor{
        private static volatile TokenProcessor tokenProcessor;
        private TokenProcessor(){}

        public static TokenProcessor getInstance(){
            if(tokenProcessor == null){
                synchronized (TokenProcessor.class){
                    if(tokenProcessor == null){
                        tokenProcessor = new TokenProcessor();
                    }
                }
            }
            return tokenProcessor;
        }

        public String createToken() {
            String token = String.valueOf((System.currentTimeMillis() + new Random().nextInt(Integer.MAX_VALUE)));
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("md5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte md5[] =  md.digest(token.getBytes());
            Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(md5);
        }
    }

    public static String createToken(){
        return TokenProcessor.getInstance().createToken();
    }
}

