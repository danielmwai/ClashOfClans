package com.bw.action;

import org.apache.log4j.xml.DOMConfigurator;

import com.bw.cache.utils.IocUtils;

public class UpdateMain {

    public static void main(String[] args) {
        //DOMConfigurator.configure("log4j.xml");
        DOMConfigurator.configure(UpdateMain.class.getResource("/log4j.xml"));
        IocUtils.getClassPathXmlApplicationContext();
        System.out.println("update spring 启动完成");

    }
}
