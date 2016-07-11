package com.bw.active;

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
//		DOMConfigurator.configure("resources/log4j.xml");
//
//		ApplicationContext spring = IocUtils.getClassPathXmlApplicationContext();

        // AppContext appContext = AppContext.getInstance();
        // MinaServer mina = (MinaServer) appContext.getBean("minaServer");
        // mina.start();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Server();
    }

}
