package com.bw.download.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
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
        r[0] = new FileSystemResource("config/jdbc.properties");
//			 r[1]=new FileSystemResource("config/properties/logicConfig.properties");
        PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
        cfg.setLocations(r);
        cfg.postProcessBeanFactory(arg0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Resource r = new FileSystemResource("jdbc.properties");
        PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
        cfg.setLocation(r);
        System.out.println(cfg.toString());
    }

}
