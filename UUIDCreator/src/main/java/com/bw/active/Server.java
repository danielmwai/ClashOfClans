package com.bw.active;

import org.apache.log4j.xml.DOMConfigurator;

import com.commonSocket.net.AppContext;

/**
 * @author dennyZhao
 *
 * 2012-4-11 下午04:02:57
 */
public class Server {

    /**
     *
     */
    public Server() {
        // 加载日志配置文件
        //DOMConfigurator.configure("config/log4j.xml");
        DOMConfigurator.configure(Server.class.getResource("/config/log4j.xml"));
        AppContext appContext = AppContext.getInstance();
        MinaServer mina = (MinaServer) appContext.getBean("minaServer");
        mina.start();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Server();
    }

}
