package com.cobweb.commons;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: XRom
 * @email: XRom.Zeng@outlook.com
 * @createdTime: 2018-09-25 09:46:25
 * class 工具类
 */
public class ClassUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 利用java反射, 将一个类中的属性赋值到另一个类中(会生成新对象)
     *
     * @param metadata    源对象引用
     * @param targetClass 目标对象class
     * @param <T>         源对象类型
     * @param <S>         目标对象类型
     * @return 赋值后的对象引用
     */
    public static <T, S> T swithClass(S metadata, Class<T> targetClass) {
        if (targetClass == null || metadata == null) {
            return null;
        }

        try {
            T targetEntity = targetClass.newInstance();
            Class<?> metadataClass = metadata.getClass();
            do {
                Field[] metadataFields = metadataClass.getDeclaredFields();
                for (Field metadataField : metadataFields) {
                    metadataField.setAccessible(true);
                    if (Modifier.isStatic(metadataField.getModifiers())) {
                        continue;
                    }
                    Class tc = targetClass;
                    do {
                        assignment(tc, metadataField, metadata, targetEntity);
                        tc = tc.getSuperclass();
                    } while (tc != Object.class);
                }
                metadataClass = metadataClass.getSuperclass();
            } while (metadataClass != Object.class);
            return targetEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 利用java反射, 将一个类中的属性赋值到另一个类中
     *
     * @param metadata     源对象引用
     * @param targetEntity 目标对象引用
     * @param <T>          源对象类型
     * @param <S>          目标对象类型
     */
    public static <T, S> void swithClass(S metadata, T targetEntity) {
        if (targetEntity == null || metadata == null) {
            return;
        }
        try {
            Class<?> metadataClass = metadata.getClass();
            do {
                Field[] metadataFields = metadataClass.getDeclaredFields();
                for (Field metadataField : metadataFields) {
                    metadataField.setAccessible(true);
                    if (Modifier.isStatic(metadataField.getModifiers())) {
                        continue;
                    }
                    Class tc = targetEntity.getClass();
                    do {
                        assignment(tc, metadataField, metadata, targetEntity);
                        tc = tc.getSuperclass();
                    } while (tc != Object.class);
                }
                metadataClass = metadataClass.getSuperclass();
            } while (metadataClass != Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 赋值
     *
     * @param tc            目标对象class
     * @param metadataField 源对象字段
     * @param metadata      源对象引用
     * @param targetEntity  目标对象引用
     * @param <S>           源对象类型
     * @param <T>           目标对象类型
     * @throws IllegalAccessException
     * @throws ParseException
     */
    private static <S, T> void assignment(Class tc, Field metadataField, S metadata, T targetEntity) throws IllegalAccessException, ParseException {
        Field[] targetFields = tc.getDeclaredFields();
        for (Field targetField : targetFields) {
            targetField.setAccessible(true);
            if (StringUtils.equals(metadataField.getName(), targetField.getName())) {
                if (metadataField.get(metadata) == null || StringUtils.isBlank(metadataField.get(metadata).toString())) {

                } else if (metadataField.getType() == Date.class || targetField.getType() == Date.class) {
                    dateTypeHander(metadataField, targetField, metadata, targetEntity);
                } else if (metadataField.getType() != targetField.getType()) {
                    differentTypeHandler(metadataField, targetField, metadata, targetEntity);
                } else {
                    targetField.set(targetEntity, metadataField.get(metadata));
                }
            }
        }
    }

    /**
     * date类型进行反射赋值处理
     *
     * @param metadataField 元数据字段
     * @param targetField   目标字段
     * @param metadata      元数据对象的引用
     * @param targetEntity  目标对象class字节码
     * @param <T>           目标对象类型
     * @param <S>           源对象类型
     * @throws IllegalAccessException
     * @throws ParseException
     */
    private static <T, S> void dateTypeHander(Field metadataField, Field targetField, S metadata, T targetEntity) throws IllegalAccessException, ParseException {
        if (metadataField.getType() == Date.class && targetField.getType() == Date.class) {
            targetField.set(targetEntity, metadataField.get(metadata));
        } else if (metadataField.getType() == Date.class && targetField.getType() == String.class) {
            String date = dateFormat.format(metadataField.get(metadata));
            targetField.set(targetEntity, date);
        } else if (metadataField.getType() == String.class && targetField.getType() == Date.class) {
            String dateString = (String) metadataField.get(metadata);
            Date date;
            if (dateString.length() <= 10) {
                date = dateFormat2.parse(dateString);
            } else {
                date = dateFormat.parse(dateString);
            }
            targetField.set(targetEntity, date);
        }
    }

    /**
     * 处理相同属性名不同类型的数据转换
     *
     * @param metadataField 元数据字段
     * @param targetField   目标字段
     * @param metadata      元数据对象应用
     * @param targetEntity  目标对象class字节码
     * @param <T>           目标对象类型
     * @param <S>           源对象类型
     * @throws IllegalAccessException
     */
    private static <T, S> void differentTypeHandler(Field metadataField, Field targetField, S metadata, T targetEntity) throws IllegalAccessException {
        if (targetField.getType() == String.class) {
            targetField.set(targetEntity, metadataField.get(metadata).toString());
        } else if (targetField.getType() == Integer.class) {
            targetField.set(targetEntity, Integer.parseInt(metadataField.get(metadata).toString()));
        } else if (targetField.getType() == Long.class) {
            targetField.set(targetEntity, Long.parseLong(metadataField.get(metadata).toString()));
        } else if (targetField.getType() == Short.class) {
            targetField.set(targetEntity, Short.parseShort(metadataField.get(metadata).toString()));
        } else if (targetField.getType() == Double.class) {
            targetField.set(targetEntity, Double.parseDouble(metadataField.get(metadata).toString()));
        }
    }

}
