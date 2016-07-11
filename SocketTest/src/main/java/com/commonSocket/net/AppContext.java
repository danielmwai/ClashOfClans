package com.commonSocket.net;

import com.commonSocket.net.exception.InitializeException;
import com.commonSocket.net.util.Report;

import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AppContext {
    //private FileSystemXmlApplicationContext context;  

    private ClassPathXmlApplicationContext context;
    private AtomicBoolean started = new AtomicBoolean(false);
    private Logger logger = Logger.getLogger(getClass());
    private static AppContext instance;

    public static synchronized AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
            instance.init();
        }
        return instance;
    }

    public ApplicationContext getApplicationContext() {
        return this.context;
    }

    public Object getBean(String beanName) {
        if (beanName.equals("minaServer")) {
            //new Report();
        }
        return this.context.getBean(beanName);
    }

    public void init() {
        try {
            if (this.started.compareAndSet(false, true)) {
                String contextFilePath = "serverCfg";
                try {
                    contextFilePath = ResourceBundle.getBundle("config").getString("spring");
                    System.out.println("\t \n \n Print  the  following \t \n\n  " + contextFilePath);
                } catch (Throwable e) {
                    e.printStackTrace();
                }

                if (this.logger.isDebugEnabled()) {
                    this.logger.debug(contextFilePath + "/*.xml");
                }
//        this.context = new FileSystemXmlApplicationContext(System.getProperty("user.dir") + new StringBuilder(String.valueOf(contextFilePath)).append("/*.xml").toString());

                // this.context = new FileSystemXmlApplicationContext("File:" + System.getProperty("user.dir") + new StringBuilder(String.valueOf(contextFilePath)).append("/spring_context_all.xml").toString());  //append("/*.xml").toString());
                //     this.context = new ClassPathXmlApplicationContext("config/serverCfg/*.xml");
                this.context = new ClassPathXmlApplicationContext("config/serverCfg/*.xml");

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitializeException(e);
        }
    }

    public void destroy() {
        if (this.started.compareAndSet(true, false)) {
            this.context.close();
            this.context.destroy();
        }
    }
}
