package com.lzjf.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
/**
 * Java消息摘要算法 MD5 工具类,其实其他摘要算法的实现也类似
 */
public class MD5Util {
  public static String md5(String plainText) {
    if (null == plainText) {
      plainText = "";
    }
    String MD5Str = "";
    try {
      // JDK 6 支持以下6种消息摘要算法，不区分大小写
      // md5,sha(sha-1),md2,sha-256,sha-384,sha-512
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(plainText.getBytes());
      byte b[] = md.digest();
 
      int i;
 
      StringBuilder builder = new StringBuilder(32);
      for (int offset = 0; offset < b.length; offset++) {
        i = b[offset];
        if (i < 0)
          i += 256;
        if (i < 16)
          builder.append("0");
        builder.append(Integer.toHexString(i));
      }
      MD5Str = builder.toString();
      // LogUtil.println("result: " + buf.toString());// 32位的加密
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return MD5Str;
  }
  public static void main(String[] args) {
    String m1 = md5("1");
    String m2 = md5(m1);
    System.out.println("m1="+m1);
    System.out.println("m2="+m2);
  }
}