package com.commonSocket.net.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;

public class ClonePojoAttUtil {

    Logger logger = Logger.getLogger(ClonePojoAttUtil.class);

    public void clonePojoAtt(Object from, Object to) throws Exception {
        Field[] field = getClassAllField(from);
        for (int i = 0; i < field.length; i++) {
            String propertyName = field[i].getName();
            PropertyDescriptor prop = new PropertyDescriptor(propertyName, from.getClass());

            Object[] objs = (Object[]) null;
            Object obj = prop.getReadMethod().invoke(from, objs);
            prop = new PropertyDescriptor(propertyName, to.getClass());
            prop.getWriteMethod().invoke(to, new Object[]{obj});
        }
    }

    public Field[] getClassAllField(Object o)
            throws Exception {
        String superClassName = o.getClass().getSuperclass().getName();
        Field[] superField = new Field[0];
        if (!superClassName.equals("java.lang.Object")) {
            Class cla = o.getClass().getSuperclass();
            superField = getClassAllField(cla.newInstance());
        }
        Field[] field = o.getClass().getDeclaredFields();
        Field[] ret = new Field[superField.length + field.length];
        System.arraycopy(superField, 0, ret, 0, superField.length);
        System.arraycopy(field, 0, ret, superField.length, field.length);
        return ret;
    }
}
