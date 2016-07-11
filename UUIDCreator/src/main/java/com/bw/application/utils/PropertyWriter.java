package com.bw.application.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertyWriter {

    private Logger logger = Logger.getLogger(getClass());
    protected Properties prop;
    private FileOutputStream fout;
    private FileInputStream fin;
    private String fileName;
    private File configFile;

    public void init() {
        load();
    }

    public void load() {

        try {
            this.prop = new Properties();
            // configFile = new File(System.getProperty("user.dir") + this.fileName);
            this.configFile = new File(getClass().getResource(this.fileName).getFile());
            this.fin = new FileInputStream(configFile);
            this.prop.load(this.fin);
            System.out.println("source:" + this.prop.getProperty("login_game_uuid"));
        } catch (IOException e) {
            e.printStackTrace();
            this.logger.error("load properties config file error", e);
        }
    }

    public String getConfig(String key) {
        if ((this.prop == null)) {
            init();
        }
        return this.prop.getProperty(key);
    }

    public void wirteUUIDToFile(String key, long uuid) {
        this.prop.setProperty(key, String.valueOf(uuid));
        try {
            this.fout = new FileOutputStream(configFile);
            this.prop.store(this.fout, "reset uuid");
            this.fout.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("重置UUID发生异常", e);
            logger.error("重置UUID发生异常,请联系系统管理员.......");
        }
    }

    public void destory() {
        try {
            if (this.fin != null) {
                this.fin.close();
            }
            //this.fin.close();
            if (this.fout != null) {
                this.fout.close();
            }
            if (this.prop != null) {
                this.prop.clear();
                this.prop = null;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
