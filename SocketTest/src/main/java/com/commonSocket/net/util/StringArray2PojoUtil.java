package com.commonSocket.net.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class StringArray2PojoUtil {

    private HashMap<String, Field> fieldMap = new HashMap();

    public Object stringArr2Pojo(Object t, String[] att)
            throws Exception {
        Class tc = null;
        if ((t instanceof Class)) {
            tc = Class.forName(t.toString().substring(6));
        }
        if ((t instanceof String)) {
            tc = Class.forName(t.toString());
        }
        Object o = tc.newInstance();
        buildClass(o, att);
        return o;
    }

    private Field[] getClassAllField(Object o) throws Exception {
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

    private void buildClass(Object o, String[] att)
            throws Exception {
        Field[] field = getClassAllField(o);
        for (int i = 0; i < att.length; i++) {
            String value = att[i];
            if ((value == null) || (value.length() <= 0)) {
                value = "";
            } else {
                value = value.trim();
            }
            Field currentField = field[i];
            String propertyName = currentField.getName();
            PropertyDescriptor prop = new PropertyDescriptor(propertyName, o.getClass());
            String type = prop.getPropertyType().getSimpleName();
            if (type.equals("int")) {
                prop.getWriteMethod().invoke(o, new Object[]{new Integer(value)});
            } else if (type.equals("long")) {
                prop.getWriteMethod().invoke(o, new Object[]{new Long(value)});
            } else if (type.equals("String")) {
                prop.getWriteMethod().invoke(o, new Object[]{value});
            } else if (type.equals("short")) {
                prop.getWriteMethod().invoke(o, new Object[]{new Short(value)});
            } else if (type.equals("byte")) {
                prop.getWriteMethod().invoke(o, new Object[]{new Byte(value)});
            } else if (type.equals("boolean")) {
                prop.getWriteMethod().invoke(o, new Object[]{new Boolean(value)});
            } else if (type.equals("double")) {
                prop.getWriteMethod().invoke(o, new Object[]{new Double(value)});
            } else if (type.equals("float")) {
                prop.getWriteMethod().invoke(o, new Object[]{new Float(value)});
            }
        }
    }

    public void setField(Object clas, String fieldName, Object value)
            throws Exception {
        Field[] fields = getClassAllField(clas);
        for (Field field : fields) {
            this.fieldMap.put(field.getName(), field);
        }

        String propertyName = fields[0].getName();
        PropertyDescriptor prop = new PropertyDescriptor(propertyName, clas.getClass());
        String type = prop.getPropertyType().getSimpleName();
        String tempValue = String.valueOf(value);
        if (type.equals("int")) {
            prop.getWriteMethod().invoke(clas, new Object[]{new Integer(tempValue)});
        } else if (type.equals("long")) {
            prop.getWriteMethod().invoke(clas, new Object[]{new Long(tempValue)});
        } else if (type.equals("String")) {
            prop.getWriteMethod().invoke(clas, new Object[]{value});
        } else if (type.equals("short")) {
            prop.getWriteMethod().invoke(clas, new Object[]{new Short(tempValue)});
        } else if (type.equals("byte")) {
            prop.getWriteMethod().invoke(clas, new Object[]{new Byte(tempValue)});
        } else if (type.equals("boolean")) {
            prop.getWriteMethod().invoke(clas, new Object[]{new Boolean(tempValue)});
        } else if (type.equals("double")) {
            prop.getWriteMethod().invoke(clas, new Object[]{new Double(tempValue)});
        } else if (type.equals("float")) {
            prop.getWriteMethod().invoke(clas, new Object[]{new Float(tempValue)});
        }
    }
}
