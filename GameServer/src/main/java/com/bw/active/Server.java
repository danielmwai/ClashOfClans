package com.bw.active;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.bw.application.resourceManager.ResGlobal;
import com.commonSocket.net.AppContext;

/**
 * @author dennyZhao
 *
 * 2012-4-11 下午04:02:57
 */
public class Server {

    public static Logger logger = Logger.getLogger(Server.class);

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
        ResGlobal.getInstance();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Server();

//		DOMConfigurator.configure("/config/log4j.xml");
//		AppContext appContext = AppContext.getInstance();
//		//一百个多线程并发insert测试mysql的性能
//		int threadCount=550;
//		System.out.println("开始时间:"+System.currentTimeMillis());
//		logger.info("开始时间:"+System.currentTimeMillis());
//		for(int x=0;x<threadCount;x++){
//			Thread t=new Thread(new Insert100Curr(appContext));
//			t.start();
//		}
    }

}
