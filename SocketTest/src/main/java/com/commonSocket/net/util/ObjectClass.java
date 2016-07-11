package com.commonSocket.net.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObjectClass {

    private static ObjectClass instance;

    public static synchronized ObjectClass getInstance() {
        if (instance == null) {
            instance = new ObjectClass();
        }
        return instance;
    }

    public Object setField(Object clas, String fieldName, Object value)
            throws Exception {
        Field field = clas.getClass().getDeclaredField(fieldName);
        PropertyDescriptor prop = new PropertyDescriptor(field.getName(), clas.getClass());
        StringBuffer type = new StringBuffer(prop.getPropertyType().getSimpleName());
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        Object valueObject = null;
        if (type.toString().equals("int")) {
            valueObject = new Integer(sb.toString());
        } else if (type.toString().equals("long")) {
            valueObject = new Long(sb.toString());
        } else if (type.toString().equals("String")) {
            valueObject = sb.toString();
        } else if (type.toString().equals("short")) {
            valueObject = new Short(sb.toString());
        } else if (type.toString().equals("byte")) {
            valueObject = new Byte(sb.toString());
        } else if (type.toString().equals("boolean")) {
            valueObject = new Boolean(sb.toString());
        } else if (type.toString().equals("double")) {
            valueObject = new Double(sb.toString());
        } else if (type.toString().equals("float")) {
            valueObject = new Float(sb.toString());
        }
        prop.getWriteMethod().invoke(clas, new Object[]{valueObject});
        return clas;
    }

    public Object getField(Object clas, String fieldName)
            throws Exception {
        Field field = clas.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(clas);
    }
}
