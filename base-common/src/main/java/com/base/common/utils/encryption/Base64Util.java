package com.base.common.utils.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @description: base64加密
 * @author: dengqx
 * @createTime: 2019-06-03 11:43
 */
public class Base64Util {
    /**
     * @Description: 使用原始的jdk实现base64加密
     * @Author: dengqx
     * @Date: 2019/6/3
     */
    public static String jdkBaseEncode(String pws) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(pws.getBytes());
    }

    /**
     * @Description: 解码
     * @Author: dengqx
     * @Date: 2019/6/3
     */
    public static String jdkBaseDecode(String pws) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            base64Decoder.decodeBuffer(pws);
            return new String(base64Decoder.decodeBuffer(pws));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Base64Util.jdkBaseEncode("123456"));
        String i = Base64Util.jdkBaseEncode("123456");
        System.out.println(Base64Util.jdkBaseDecode(i));
    }
}