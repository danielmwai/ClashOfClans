package com.bw.baseJar.vo;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class StopServerMessageVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7684601351937085982L;
    //服务器维护代号
    private int maintainCode;
    private String message;

    public int getMaintainCode() {
        return maintainCode;
    }

    public void setMaintainCode(int maintainCode) {
        this.maintainCode = maintainCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
