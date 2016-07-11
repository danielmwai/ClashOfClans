package com.bw.cache.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 *
 * @creator:赵清有 2012-4-20下午04:07:16 spring 常用方法
 */
public class IocUtils {
    // static String SPRINGSTR="com/zxwx/springxml/applicationContext.xml";
    //static String SPRINGSTR = "file:"+System.getProperty("user.dir")+"/applicationContext.xml";///src/main/resources/applicationContext.xml

    static String SPRINGSTR = "applicationContext.xml";
    private static Map<String, ApplicationContext> contextMap = new ConcurrentHashMap<String, ApplicationContext>();
    private static final Logger log = Logger.getLogger(IocUtils.class);

    /**
     * 从Srping的IOC容器中查找Bean，找不到时返回null
     *
     * @param beanName
     * @return
     */
    public static Object getBeanFromWar(String beanName) {
        ApplicationContext context = getWebApplicationContext();
        if (context == null) {
            return null;
        } else {
            try {
                return context.getBean(beanName);
            } catch (BeansException e) {
                log.debug(e);
                return null;
            }
        }
    }

    public static Object getBeanFromJar(String beanName) {
        ApplicationContext context = contextMap.get(SPRINGSTR);
        if (context == null) {
            context = getClassPathXmlApplicationContext();
            return context.getBean(beanName);
        } else {
            try {
                return context.getBean(beanName);
            } catch (BeansException e) {
                log.error("获取bean:发生异常==", e);
                return null;
            }
        }
    }

    public static WebApplicationContext getWebApplicationContext() {
        return ContextLoader.getCurrentWebApplicationContext();
    }

    public static Object getBeanFromClssPathFile(String contextFile, String beanName) {
        ApplicationContext context = getClassPathXmlApplicationContext(contextFile);
        if (context == null) {
            return null;
        } else {
            try {
                return context.getBean(beanName);
            } catch (BeansException e) {
                log.debug(e);
                return null;
            }
        }
    }

    public static ApplicationContext getClassPathXmlApplicationContext(String contextFile) {
        ApplicationContext context = contextMap.get(contextFile);
        if (context == null) {
            try {
                context = new ClassPathXmlApplicationContext(contextFile);
            } catch (BeansException e) {
                log.warn("Read spring context file[" + contextFile + "] error", e);
            }
            contextMap.put(contextFile, context);
        }
        return context;
    }

    public static ApplicationContext getClassPathXmlApplicationContext() {
        ApplicationContext context = contextMap.get(SPRINGSTR);
        System.out.println(SPRINGSTR);
        if (context == null) {
            try {
                context = new ClassPathXmlApplicationContext(SPRINGSTR);
            } catch (BeansException e) {
                e.printStackTrace();
                log.error("Read spring context file[" + SPRINGSTR + "] error", e);
            }
            contextMap.put(SPRINGSTR, context);
        }
        return context;
    }

    /**
     * @param repeatList
     * @return 去除list中相同的元素但返回结果是没有顺序的
     */
    @SuppressWarnings("unchecked")
    public static List<Long> removeRepeatValue(List<Long> repeatList) {
        if (null == repeatList || repeatList.size() == 0) {
            return null;
        } else {
            HashSet h = new HashSet(repeatList);
            repeatList.clear();
            repeatList.addAll(h);
            return repeatList;
        }

    }

    /**
     * @param arlList
     * @return 有顺序
     */
    @SuppressWarnings("unchecked")
    public static List<Long> removeDuplicateWithOrder(List<Long> arlList) {
        Set set = new HashSet();
        List<Long> newList = new ArrayList<Long>();
        for (Iterator iter = arlList.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element)) {
                newList.add((Long) element);
            }
        }
        arlList.clear();
        arlList.addAll(newList);
        return arlList;
    }

    public static List<String> removeDuplicateWithOrderStr(List<String> arlList) {
        Set set = new HashSet();
        List<String> newList = new ArrayList<String>();
        for (Iterator iter = arlList.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element)) {
                newList.add((String) element);
            }
        }
        arlList.clear();
        arlList.addAll(newList);
        return arlList;
    }
}
