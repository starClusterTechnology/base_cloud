package com.base.common.utils.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @description: aes加密
 * @author: dengqx
 * @createTime: 2019-06-03 11:59
 */
public class AesUtil {
    public static void jdkAES (String pws){
        try {
            //生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            //keyGenerator.init(128, new SecureRandom("seedseedseed".getBytes()));
            //使用上面这种初始化方法可以特定种子来生成密钥，这样加密后的密文是唯一固定的。
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            //Key转换
            Key key = new SecretKeySpec(keyBytes, "AES");
            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(pws.getBytes());
            System.out.println(new String(encodeResult));
            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(encodeResult);
            System.out.println("AESdecode : " + new String (decodeResult));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AesUtil.jdkAES("1234");
    }
}