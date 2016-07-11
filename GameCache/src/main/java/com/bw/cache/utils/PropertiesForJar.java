package com.bw.cache.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @creator:赵清有 2012-4-20下午03:12:25 用于从jar包同级目录下获取
 */
public class PropertiesForJar implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0)
            throws BeansException {
        Resource[] r = new Resource[1];
        // r[0]=new FileSystemResource("/config/jdbc.properties");
        r[0] = new ClassPathResource("/config/jdbc.properties");
//        r[0] =  new ClassPathResource(getClass().getResource("/config/jdbc.properties").getFile());
        System.out.println("\n\n\n\t ****ClassPath Resource " + r[0] + "\t \n\n\n\n");
//			 r[1]=new FileSystemResource("resCources/config/properties/logicConfig.properties");
        PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
        cfg.setLocations(r);
        cfg.postProcessBeanFactory(arg0);
    }

    /**
     * @param argsz
     */
    public static void main(String[] args) {
        Resource r = new ClassPathResource("/config/jdbc.properties");//resources/jdbc.properties
        PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
        cfg.setLocation(r);
        System.out.println(cfg.toString());
    }

}
