package com.commonSocket.net.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public abstract class BaseConfig
        implements Configurable {

    private Logger logger = Logger.getLogger(getClass());
    protected Properties prop;
    private Path configFile;
    private long lastModifyTime;
    private String fileName;
    private FileTime fileTime;
    private static final String FORMAT_STRING = "HH:mm:ss"; 
    private SimpleDateFormat df = new SimpleDateFormat(FORMAT_STRING);
    private ClassLoader classLoader = getClass().getClassLoader();
    private BasicFileAttributes attr;
    private long fileLastModified;
    private  long fileModified ;

    public void init() {
        load();
    }

    public void load() {
        try {
            this.prop = new Properties();
            this.configFile = Files.createTempFile("resource-", ".ext");
            Files.copy(BaseConfig.class.getResourceAsStream(this.fileName), this.configFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(" \t\t\n \n\t" + this.configFile + "\t\n\n" + " Load Properties from  Config" + this.configFile + "\n\n\n\n\t");
            // this.configFile = new File(System.getProperty("user.dir") + this.fileName);
            this.prop.load(new FileInputStream(this.configFile.toFile()));
            //this.lastModifyTime = this.configFile.lastModified();
            attr = Files.readAttributes(this.configFile, BasicFileAttributes.class);
            fileTime = attr.creationTime();
            fileLastModified = fileTime.toMillis();
            System.out.println(" \t\t\n \n\t" + fileLastModified + "\t\n\n" + "File Last Modified" + fileLastModified + "\n\n\n\n\t");

            this.lastModifyTime =fileLastModified;
            //Files.getLastModifiedTime(this.configFile);
        } catch (IOException e) {
            this.logger.error("load properties config file error", e);
        }
    }

    public String getConfig(String key) {
        if ((this.configFile == null) || (this.prop == null)) {
            init();
        }
        if (fileLastModified > this.lastModifyTime) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("reload config file for lasttime=" + this.lastModifyTime + " and new time=" + fileLastModified);
            }
            load();
        }
        return this.prop.getProperty(key);
    }

    public boolean hasModified() {
        return fileLastModified> this.lastModifyTime;
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
