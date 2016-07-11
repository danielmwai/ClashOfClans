package com.commonSocket.net.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Logger;

public abstract class BaseConfig
        implements Configurable {

    private Logger logger = Logger.getLogger(getClass());
    protected Properties prop;
    private File configFile;
    private long lastModifyTime;
    private String fileName;
    ClassLoader classLoader = getClass().getClassLoader();

    public void init() {
        load();
    }

    public void load() {
        try {
            this.prop = new Properties();
           this.configFile = new File(getClass().getResource(this.fileName).getFile());
         //  this.configFile = new File(classLoader.getResource(this.fileName).getFile());
            //Load  files from the ClassPath check  File from resources folder with ClassLoader 
          //  this.configFile = new File(getClass().getClassLoader().getResource(this.fileName).getFile());
            System.out.println(" \t\t\n \n\t"+this.configFile  +"\t\n\n"+" Load Properties from  Config" + this.configFile  +"\n\n\n\n\t");
            // this.configFile = new File(System.getProperty("user.dir") + this.fileName);
            this.prop.load(new FileInputStream(this.configFile));
            this.lastModifyTime = this.configFile.lastModified();
        } catch (IOException e) {
            this.logger.error("load properties config file error", e);
        }
    }

    public String getConfig(String key) {
        if ((this.configFile == null) || (this.prop == null)) {
            init();
        }
        if (this.configFile.lastModified() > this.lastModifyTime) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("reload config file for lasttime=" + this.lastModifyTime + " and new time=" + this.configFile.lastModified());
            }
            load();
        }
        return this.prop.getProperty(key);
    }

    public boolean hasModified() {
        return this.configFile.lastModified() > this.lastModifyTime;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        String key;
        String value;
        for (Enumeration e = this.prop.keys(); e.hasMoreElements(); sb.append("{" + key + "=" + value + "}")) {
            key = (String) e.nextElement();
            value = (String) this.prop.get(key);
        }
        return sb.toString();
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
