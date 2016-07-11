package com.bw.util;

/**
 * @author zhaoqingyou 判断当前操作系统类型
 */
public class OSValidator {

    /**
     * 操作系统名
     */
    private String os = System.getProperty("os.name").toLowerCase();

    private static OSValidator instance;

    public static OSValidator getInstance() {
        if (instance == null) {
            instance = new OSValidator();
        }
        return instance;
    }

    public boolean isWindows() {
        // windows
        return (os.indexOf("win") >= 0);

    }

    public boolean isMac() {
        // Mac
        return (os.indexOf("mac") >= 0);

    }

    public boolean isUnix() {
        // linux or unix
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);

    }

    public boolean isSolaris() {
        // Solaris
        return (os.indexOf("sunos") >= 0);
    }

    public static void main(String[] args) {
        if (OSValidator.getInstance().isWindows()) {
            System.out.println("This is Windows");
        } else if (OSValidator.getInstance().isMac()) {
            System.out.println("This is Mac");
        } else if (OSValidator.getInstance().isUnix()) {
            System.out.println("This is Unix or Linux");
        } else if (OSValidator.getInstance().isSolaris()) {
            System.out.println("This is Solaris");
        } else {
            System.out.println("Your OS is not support!!");
        }
    }
}
