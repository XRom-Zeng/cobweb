package com.cobweb.commons;

import java.math.BigDecimal;

/**
 * Created by XRog
 * on 2/28/2017.
 * 数学运算工具类
 */
public class MathUtils {

    private static final int SCALE = 2;

    /**
     * 提供精确加法(addition)运算方法
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static double add(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确减法(subtraction)运算方法
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确乘法(multiplication)运算方法
     * @param value1 乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确乘法(multiplication)运算方法（乘100）
     * @param value1 乘数
     * @return 两个参数的积
     */
    public static double mul100(double value1){
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(100.00);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的除法(division)运算方法
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     */
    public static double div(double value1,double value2,int scale){
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.divide(b2, scale < 0 ? SCALE : scale,BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    /**
     * 提供精确的除法(division)运算方法
     * @param value1 被除数
     * @param value2 除数
     * @return 两个参数的商
     */
    public static double div(double value1,double value2){
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.divide(b2, SCALE,BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }



    /**
     * 提供精确的除法(division)运算方法（除以100）
     * @param value 被除数
     * @return 两个参数的商
     */
    public static double div100(double value){
        BigDecimal b1 = new BigDecimal(Double.toString(value));
        BigDecimal b2 = new BigDecimal("100.00");
        return b1.divide(b2, SCALE,BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }
}