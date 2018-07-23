/*
 * Copyright (C), 2015-2018
 * FileName: ByteUtil
 * Author:   zhao
 * Date:     2018/7/17 17:44
 * Description: 关于字节操作的工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.lizhaoblog.base.util;

import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈关于字节操作的工具类〉
 *
 * @author zhao
 * @date 2018/7/17 17:44
 * @since 1.0.1
 */
public class ByteUtil {
  /**
   * 提供一些数组 操作处理方法，例如添加、复制、转换、删除、插入、计数等
   */
  private ByteUtil() {
  }

  //  public static final int LENGTH_INT = 4;
  public static final int ZERO = 0;//short所占字节
  public static final int PARM_USE_NUM_SHORT = 2;//short所占字节
  public static final int PARM_USE_NUM_INT = 4;//int所占字节
  public static final int PARM_USE_NUM_FLOAT = 4;//float所占字节
  public static final int PARM_USE_NUM_LONG = 8;//long所占字节

  public static int bytes2ToInt(byte[] value) {
    int b0 = (0xFF & value[0]) << 8;
    int b1 = (0xFF & value[1]) << 0;
    return (b0 | b1);
  }

  public static short bytes2ToShort(byte[] value) {
    int b0 = (value[0]) << 8;
    int b1 = (0xFF & value[1]) << 0;
    return (short) (b0 | b1);
  }

  public static int bytes3ToInt(byte[] value) {
    int b0 = (0xFF & value[0]) << 16;
    int b1 = (0xFF & value[1]) << 8;
    int b2 = (0xFF & value[2]) << 0;
    return (b0 | b1 | b2);
  }

  public static int bytes3ToInt(byte value1, byte value2, byte value3) {
    int b0 = (0xFF & value1) << 16;
    int b1 = (0xFF & value2) << 8;
    int b2 = (0xFF & value3) << 0;
    return (b0 | b1 | b2);
  }

  public static int bytes4ToInt(byte value1, byte value2, byte value3, byte value4) {
    int b0 = (value1) << 24;
    int b1 = (0xFF & value2) << 16;
    int b2 = (0xFF & value3) << 8;
    int b3 = (0xFF & value4) << 0;
    return (b0 | b1 | b2 | b3);
  }

  public static int bytes4ToInt(byte[] value) {
    int b0 = (value[0]) << 24;
    int b1 = (0xFF & value[1]) << 16;
    int b2 = (0xFF & value[2]) << 8;
    int b3 = (0xFF & value[3]) << 0;
    return (b0 | b1 | b2 | b3);
  }

  public static long bytesToLong(byte[] value) {
    long b0 = (long) (value[0]) << 56;
    long b1 = (long) (0xFF & value[1]) << 48;
    long b2 = (long) (0xFF & value[2]) << 40;
    long b3 = (long) (0xFF & value[3]) << 32;
    long b4 = (long) (0xFF & value[4]) << 24;
    long b5 = (long) (0xFF & value[5]) << 16;
    long b6 = (long) (0xFF & value[6]) << 8;
    long b7 = (long) (0xFF & value[7]) << 0;
    return (b0 | b1 | b2 | b3 | b4 | b5 | b6 | b7);
  }

  public static short bytesToShort(byte[] value) {
    int b0 = (value[0]) << 8;
    int b1 = (0xFF & value[1]);
    return (short) (b0 | b1);
  }

  public static char bytesToChar(byte[] value) {
    int b0 = (0xFF & value[0]) << 8;
    int b1 = (0xFF & value[1]) << 0;
    return (char) (b0 | b1);
  }

  public static int bytesToUnsignedShort(byte[] value) {
    int b0 = (0xFF & value[0]) << 8;
    int b1 = (0xFF & value[1]) << 0;
    return (b0 | b1);
  }

  public static int bytesToUnsignedShort(byte value1, byte value2) {
    int b0 = (0xFF & value1) << 8;
    int b1 = (0xFF & value2) << 0;
    return (b0 | b1);
  }

  public static void checkBounds(byte[] data, int off, int len) {
    if (data == null) {
      throw new NullPointerException();
    }
    checkBounds(off, len, data.length);
  }

  public static void checkBounds(int off, int len, int size) {
    if (off < 0) {
      throw new IllegalArgumentException("Negative off: " + off);
    }
    if (len < 0) {
      throw new IllegalArgumentException("Negative len: " + len);
    }
    if (off > size || off + len > size) {
      throw new IndexOutOfBoundsException("off:" + off + " + len:" + len + " > size:" + size);
    }
  }

  public static void checkNull(byte[] data) {
    if (data == null) {
      throw new NullPointerException();
    }
  }

  public static void checkValue(byte[] value, int minLen) {
    if (value == null) {
      throw new NullPointerException();
    }
    if (value.length < minLen) {
      throw new IndexOutOfBoundsException(value.length + " < " + minLen);
    }
  }

  public static byte[] copyOf(byte[] original, int newLength) {
    if (newLength < 0) {
      throw new IllegalArgumentException("Negative newLength: " + newLength);
    }
    byte[] copy = new byte[newLength];
    if (original == null) {
      return copy;
    }
    System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
    return copy;
  }

  public static byte[] copyOfRange(byte[] original, int from, int to) {
    int newLength = to - from;
    if (newLength < 0) {
      throw new IllegalArgumentException("Negative newLength: " + from + " > " + to);
    }
    byte[] copy = new byte[newLength];
    if (original == null) {
      return copy;
    }
    System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
    return copy;
  }

  public static int[] copyOf(int[] original, int newLength) {
    if (newLength < 0) {
      throw new IllegalArgumentException("Negative newLength: " + newLength);
    }
    int[] copy = new int[newLength];
    if (original == null) {
      return copy;
    }
    System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
    return copy;
  }

  public static String decodeUTF(byte[] utf) throws UTFDataFormatException {
    int utflen = utf.length;
    byte[] bytearr = utf;
    char[] chararr = new char[utflen];

    int c;
    int char2;
    int char3;
    int count = 0;
    int chararrCount = 0;
    while (count < utflen) {
      c = bytearr[count] & 0xFF;
      if (c > 127) {
        break;
      }
      count++;
      chararr[chararrCount++] = (char) c;
    }

    while (count < utflen) {
      c = bytearr[count] & 0xFF;
      switch (c >> 4) {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
          /* 0xxxxxxx */
          count++;
          chararr[chararrCount++] = (char) c;
          break;
        case 12:
        case 13:
          /* 110x xxxx 10xx xxxx */
          count += 2;
          if (count > utflen) {
            throw new UTFDataFormatException("malformed input: partial character at end");
          }
          char2 = bytearr[count - 1];
          if ((char2 & 0xC0) != 0x80) {
            throw new UTFDataFormatException("malformed input around byte " + count);
          }
          chararr[chararrCount++] = (char) (((c & 0x1F) << 6) | (char2 & 0x3F));
          break;
        case 14:
          /* 1110 xxxx 10xx xxxx 10xx xxxx */
          count += 3;
          if (count > utflen) {
            throw new UTFDataFormatException("malformed input: partial character at end");
          }
          char2 = bytearr[count - 2];
          char3 = bytearr[count - 1];
          if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80)) {
            throw new UTFDataFormatException("malformed input around byte " + (count - 1));
          }
          chararr[chararrCount++] = (char) (((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0));
          break;
        default:
          /* 10xx xxxx, 1111 xxxx */
          throw new UTFDataFormatException("malformed input around byte " + count);
      }
    }
    // The number of chars produced may be less than utflen
    return new String(chararr, 0, chararrCount);
  }

  public static byte[] encodedUTF(String str) {
    int strlen = str.length();
    int utflen = 0;
    int c = 0;
    int count = 0;
    /* use charAt instead of copying String to char array */
    for (int i = 0; i < strlen; i++) {
      c = str.charAt(i);
      if ((c >= 0x0001) && (c <= 0x007F)) {
        utflen++;
      } else if (c > 0x07FF) {
        utflen += 3;
      } else {
        utflen += 2;
      }
    }
    if (utflen > 0xFFFF) {
      throw new IllegalArgumentException("encoded string too long: " + utflen + " bytes");
    }

    byte[] bytearr = new byte[utflen + 2];

    bytearr[count++] = (byte) ((utflen >>> 8) & 0xFF);
    bytearr[count++] = (byte) ((utflen >>> 0) & 0xFF);

    int i = 0;
    for (i = 0; i < strlen; i++) {
      c = str.charAt(i);
      if (!((c >= 0x0001) && (c <= 0x007F))) {
        break;
      }
      bytearr[count++] = (byte) c;
    }

    for (; i < strlen; i++) {
      c = str.charAt(i);
      if ((c >= 0x0001) && (c <= 0x007F)) {
        bytearr[count++] = (byte) c;

      } else if (c > 0x07FF) {
        bytearr[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
        bytearr[count++] = (byte) (0x80 | ((c >> 6) & 0x3F));
        bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
      } else {
        bytearr[count++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
        bytearr[count++] = (byte) (0x80 | ((c >> 0) & 0x3F));
      }
    }
    return bytearr;
  }

  public static int indexOf(byte[] src, byte taget) {
    return indexOf(src, taget, 0);
  }

  public static int indexOf(byte[] src, byte taget, int fromIndex) {
    if (src == null || src.length == 0) {
      return -1;
    }
    for (int i = fromIndex; i < src.length; i++) {
      if (src[i] == taget) {
        return i;
      }
    }
    return -1;
  }

  public static int indexOf(int[] src, int taget) {
    return indexOf(src, taget, 0);
  }

  public static int indexOf(int[] src, int taget, int fromIndex) {
    if (src == null || src.length == 0) {
      return -1;
    }
    for (int i = fromIndex; i < src.length; i++) {
      if (src[i] == taget) {
        return i;
      }
    }
    return -1;
  }

  /**
   * short到字节数组的转换.
   */
  public static byte[] shortToByte(short number) {
    int temp = number;
    byte[] b = new byte[2];
    for (int i = 0; i < b.length; i++) {
      b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
      temp = temp >> 8;// 向右移8位
    }

    byte[] b2 = new byte[2];
    b2[0] = b[1];
    b2[1] = b[0];
    return b2;
  }

  public static byte[] boolToBytes(boolean bool) {
    byte[] write = new byte[1];
    write[0] = (byte) (bool ? 1 : 0);
    return write;
  }

  public static boolean bytesToBoolean(byte[] bytes) {
    return bytes[0] != 0;
  }

  public static byte[] stringToBytes(String str) {
    byte[] result = str.getBytes();
    return result;
  }

  public static String bytesToString(byte[] bytes) {
    String result = new String(bytes);
    return result;
  }

  public static byte[] intToBytes4(int value) {
    byte[] write = new byte[4];
    write[0] = (byte) ((value >>> 24) & 0xFF);
    write[1] = (byte) ((value >>> 16) & 0xFF);
    write[2] = (byte) ((value >>> 8) & 0xFF);
    write[3] = (byte) ((value >>> 0) & 0xFF);
    return write;
  }

  public static byte[] intToBytes3(int value) {
    byte[] write = new byte[3];
    write[0] = (byte) ((value >>> 16) & 0xFF);
    write[1] = (byte) ((value >>> 8) & 0xFF);
    write[2] = (byte) ((value >>> 0) & 0xFF);
    return write;
  }

  public static byte[] longToBytes(long value) {
    byte[] write = new byte[8];
    write[0] = (byte) ((value >>> 56) & 0xFF);
    write[1] = (byte) ((value >>> 48) & 0xFF);
    write[2] = (byte) ((value >>> 40) & 0xFF);
    write[3] = (byte) ((value >>> 32) & 0xFF);
    write[4] = (byte) ((value >>> 24) & 0xFF);
    write[5] = (byte) ((value >>> 16) & 0xFF);
    write[6] = (byte) ((value >>> 8) & 0xFF);
    write[7] = (byte) ((value >>> 0) & 0xFF);
    return write;
  }

  public static byte[] intToBytes2(int value) {
    byte[] write = new byte[2];
    write[0] = (byte) ((value >>> 8) & 0xFF);
    write[1] = (byte) ((value >>> 0) & 0xFF);
    return write;
  }

  /**
   * 排序，按升序排
   *
   * @param src byte[] 字节
   */
  public static void sort(byte[] src) {
    if (src == null) {
      return;
    }
    int len = src.length;
    for (int i = 0; i < len - 1; i++) {
      for (int j = (i + 1); j < len; j++) {
        if (src[i] > src[j]) {
          byte temp = src[i];
          src[i] = src[j];
          src[j] = temp;
        }
      }
    }
  }

  /**
   * 排序，按降序排列
   *
   * @param src byte[]
   */
  public static void sortDesc(byte[] src) {
    if (src == null) {
      return;
    }
    int len = src.length;
    for (int i = 0; i < len - 1; i++) {
      for (int j = (i + 1); j < len; j++) {
        if (src[i] < src[j]) {
          byte temp = src[i];
          src[i] = src[j];
          src[j] = temp;
        }
      }
    }
  }

  public static byte[][] split(byte[] source, byte regex) {
    if (source == null || source.length == 0) {
      return null;
    }
    int len = source.length;
    List<Integer> regexList = new ArrayList<Integer>();
    for (int i = 0; i < len; i++) {
      if (source[i] == regex) {
        regexList.add(i);
      }
    }
    if (regexList.size() == 0) {
      return new byte[][] { source };
    } else {
      byte[][] bytesArrays = new byte[regexList.size() + 1][];
      int nextIndex = 0;
      int index = 0;
      for (int regexIndex : regexList) {
        bytesArrays[index] = new byte[regexIndex - nextIndex];
        System.arraycopy(source, nextIndex, bytesArrays[index], 0, bytesArrays[index].length);
        nextIndex = regexIndex + 1;
        index++;
      }
      // 添加最后一个属性
      bytesArrays[index] = new byte[source.length - nextIndex];
      System.arraycopy(source, nextIndex, bytesArrays[index], 0, bytesArrays[index].length);
      return bytesArrays;
    }
  }

  public static String toHexString(byte[] data) { // 二行制转字符串
    return data == null ? "null" : toHexString(data, 0, data.length);
  }

  public static String toHexString(byte[] data, int maxLen) {
    return toHexString(data, 0, data.length > maxLen ? maxLen : data.length);
  }

  public static String toHexString(byte[] data, int off, int len) { // 二行制转字符串
    if (data == null) {
      return "null";
    }
    int iMax = off + len - 1;
    if (iMax == -1 || len == 0) {
      return "[]";
    }
    StringBuilder b = new StringBuilder(3 * len);
    for (int i = off; i <= iMax; i++) {
      b.append(Integer.toHexString(data[i] & 0xFF));
      if (i != iMax) {
        b.append(':');
      }
    }
    return b.toString();
  }

  public static String toString(final byte[] data) {
    return toString(data, 0, data == null ? 0 : data.length);
  }

  public static String toString(final int[] data) {
    return toString(data, 0, data == null ? 0 : data.length);
  }

  public static String toString(final String[] data) {
    return toString(data, 0, data == null ? 0 : data.length);
  }

  public static String toString(byte[] data, int off, int len) {
    if (data == null) {
      return "null";
    }
    int iMax = off + len - 1;
    if (iMax == -1 || len == 0) {
      return "[]";
    }

    StringBuilder b = new StringBuilder(2 * len + 2);
    b.append('[');
    for (int i = 0; ; i++) {
      b.append(data[i]);
      if (i == iMax) {
        return b.append(']').toString();
      }
      b.append(", ");
    }
  }

  public static String toString(int[] data, int off, int len) {
    if (data == null) {
      return "null";
    }
    int iMax = off + len - 1;
    if (iMax == -1 || len == 0) {
      return "[]";
    }

    StringBuilder b = new StringBuilder(4 * len + 2);
    b.append('[');
    for (int i = 0; ; i++) {
      b.append(data[i]);
      if (i == iMax) {
        return b.append(']').toString();
      }
      b.append(", ");
    }
  }

  public static String toString(String[] data, int off, int len) {
    if (data == null) {
      return "null";
    }
    int iMax = off + len - 1;
    if (iMax == -1 || len == 0) {
      return "[]";
    }

    StringBuilder b = new StringBuilder(8 * len + 2);
    b.append('[');
    for (int i = 0; ; i++) {
      b.append(data[i]);
      if (i == iMax) {
        return b.append(']').toString();
      }
      b.append(", ");
    }
  }

  /**
   * 转换成string类型
   */
  public static String toStr(List<? extends Object> data) {
    if (data == null) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < data.size(); i++) {
      sb.append(data.get(i));
      if (i < data.size() - 1) {
        sb.append(",");
      }
    }
    return sb.toString();
  }

  public static String toStr(byte[] data) {
    if (data == null) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    if (data != null) {
      for (int i = 0; i < data.length; i++) {
        sb.append((int) data[i]);
        if (i < data.length - 1) {
          sb.append(",");
        }
      }
    }
    return sb.toString();
  }

  public static String toStr(int[] data) {
    if (data == null) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    if (data != null) {
      for (int i = 0; i < data.length; i++) {
        sb.append(data[i]);
        if (i < data.length - 1) {
          sb.append(",");
        }
      }
    }
    return sb.toString();
  }

  public static String toStr(Long[] data) {
    if (data == null) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < data.length; i++) {
      sb.append(data[i]);
      if (i < data.length - 1) {
        sb.append(",");
      }
    }
    return sb.toString();
  }

  public static String toStr(String[] data, String split) {
    if (data == null) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < data.length; i++) {
      sb.append(data[i]);
      if (i < data.length - 1) {
        sb.append(split);
      }
    }
    return sb.toString();
  }

  public static List<Long> formatLong(String str) {
    List<Long> list = new ArrayList<>();
    if ("".equals(str)) {
      return list;
    }
    String[] strs = str.split(",");
    for (int i = 0; i < strs.length; i++) {
      list.add(Long.parseLong(strs[i]));
    }
    return list;
  }

  public static List<Integer> formatInt(String str) {

    List<Integer> list = new ArrayList<>();
    if ("".equals(str)) {
      return list;
    }
    String[] strs = str.split(",");
    for (int i = 0; i < strs.length; i++) {
      list.add(Integer.parseInt(strs[i]));
    }
    return list;
  }

  public static String[] formatStr(String str, String split) {
    if (str == null) {
      return null;
    }
    String[] strs = str.split(split);
    return strs;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
  }

  /**
   * 从ByteArr的index起截取数据
   * int为四位
   *
   * @param bytes 字节数组
   * @param index 起始位置
   */
  protected static int getIntFromArr(byte[] bytes, int index) {
    byte[] msgArr = new byte[PARM_USE_NUM_INT];
    System.arraycopy(bytes, index, msgArr, ZERO, PARM_USE_NUM_INT);
    int result = bytes4ToInt(msgArr);
    return result;
  }

  /**
   * 从ByteArr的index起截取数据
   * float为四位
   *
   * @param bytes 字节数组
   * @param index 起始位置
   */
  protected static float getFloatFromArr(byte[] bytes, int index) {
    float result = byte2float(bytes, index);
    return result;
  }

  /**
   * 从ByteArr的index起截取数据
   * int为四位
   *
   * @param bytes 字节数组
   * @param index 起始位置
   */
  protected static long getLongFromArr(byte[] bytes, int index) {
    byte[] msgArr = new byte[PARM_USE_NUM_LONG];
    System.arraycopy(bytes, index, msgArr, ZERO, PARM_USE_NUM_LONG);
    long result = bytesToLong(msgArr);
    return result;
  }

  /**
   * 从ByteArr的index起截取数据
   * int为四位
   *
   * @param bytes 字节数组
   * @param index 起始位置
   */
  protected static String getStringFromArr(byte[] bytes, int index) {
    int strLength = getIntFromArr(bytes, index);
    byte[] msgArr = new byte[strLength];
    System.arraycopy(bytes, index + PARM_USE_NUM_INT, msgArr, ZERO, strLength);
    String result = bytesToString(msgArr);
    return result;
  }

  /**
   * 从ByteArr的index起截取数据
   * int为四位
   *
   * @param bytes 字节数组
   * @param index 起始位置
   */
  protected static short getShortFromArr(byte[] bytes, int index) {
    byte[] msgArr = new byte[PARM_USE_NUM_SHORT];
    System.arraycopy(bytes, index, msgArr, ZERO, PARM_USE_NUM_SHORT);
    short result = bytesToShort(msgArr);
    return result;
  }

  /**
   * 浮点转换为字节
   *
   * @param f
   * @return
   */
  public static byte[] float2byte(float f) {

    // 把float转换为byte[]
    int fbit = Float.floatToIntBits(f);

    byte[] b = new byte[4];
    for (int i = 0; i < 4; i++) {
      b[i] = (byte) (fbit >> (24 - i * 8));
    }

    // 翻转数组
    int len = b.length;
    // 建立一个与源数组元素类型相同的数组
    byte[] dest = new byte[len];
    // 为了防止修改源数组，将源数组拷贝一份副本
    System.arraycopy(b, 0, dest, 0, len);
    //    byte temp;
    //    // 将顺位第i个与倒数第i个交换
    //    for (int i = 0; i < len / 2; ++i) {
    //      temp = dest[i];
    //      dest[i] = dest[len - i - 1];
    //      dest[len - i - 1] = temp;
    //    }

    return dest;

  }

  /**
   * 字节转换为浮点
   *
   * @param b     字节（至少4个字节）
   * @param index 开始位置
   * @return
   */
  public static float byte2float(byte[] b, int index) {
    int l;
    //低位
    //    l = b[index + 0];
    //    l &= 0xff;
    //    l |= ((long) b[index + 1] << 8);
    //    l &= 0xffff;
    //    l |= ((long) b[index + 2] << 16);
    //    l &= 0xffffff;
    //    l |= ((long) b[index + 3] << 24);

    //高位
    l = b[index + 3];
    l &= 0xff;
    l |= ((long) b[index + 2] << 8);
    l &= 0xffff;
    l |= ((long) b[index + 1] << 16);
    l &= 0xffffff;
    l |= ((long) b[index + 0] << 24);

    return Float.intBitsToFloat(l);
  }

  public static byte[] concat(byte[] first, byte[] second) {
    byte[] result = Arrays.copyOf(first, first.length + second.length);
    System.arraycopy(second, 0, result, first.length, second.length);
    return result;
  }

  public static <T> T[] concat(T[] first, T[] second) {
    T[] result = Arrays.copyOf(first, first.length + second.length);
    System.arraycopy(second, 0, result, first.length, second.length);
    return result;
  }

}
