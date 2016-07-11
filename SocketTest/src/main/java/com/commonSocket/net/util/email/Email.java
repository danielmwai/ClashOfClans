package com.commonSocket.net.util.email;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;

import com.dominicsayers.isemail.IsEMail;
import com.dominicsayers.isemail.IsEMailResult;

public class Email {

    private final String Charest = "GB2312";
    private final int timeOut = 30000;
    private final String hostName = "smtp.gmail.com";
    private final String name = "wangchun365@gmail.com";
    private final String passWord = "chentian";
    private final String to = "49046304@qq.com";
    private final String title = "Aplication Server Info";
    private String massage;
    private static Email instance;

    public static synchronized Email getInstance() {
        if (instance == null) {
            instance = new Email();
        }
        return instance;
    }

    public void sendEmail(String massage) {
        SimpleEmail email = new SimpleEmail();
        email.setTLS(true);

        email.setSocketTimeout(timeOut);
        email.setHostName(hostName);
        email.setAuthentication(name, passWord);
        try {
            email.setCharset(Charest);
            email.setFrom(name);
            email.addTo(to);

            email.setSubject(title);
            email.setMsg(massage);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public void sendEmail() {
        SimpleEmail email = new SimpleEmail();
        email.setTLS(true);

        email.setSocketTimeout(30000);
        email.setHostName(hostName);
        email.setAuthentication(name, passWord);
        try {
            email.setCharset(Charest);
            email.setFrom(name);
            email.addTo(to);

            email.setSubject("Aplication Server Info");
            email.setMsg(this.massage);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public boolean checkEmailBySite(String email) throws Exception {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            return false;
        }
        IsEMailResult result = IsEMail.is_email_verbose(email, true);
        switch (com.dominicsayers.isemail.GeneralState.values()[result.getState().ordinal()]) {
            case OK:
                return true;
        }
//		boolean actual_valid = (result.getState() != GeneralState.ERROR);
//		if (actual_valid) {// 警告也可以
//			return true;
//		}
        return false;
    }

    public boolean chechEmailByFormat(String email) {
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean checkEmailByReal(String email) {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            return false;
        }

        String host = "";
        String hostName = email.split("@")[1];
        Record[] result = (Record[]) null;
        SMTPClient client = new SMTPClient();
        try {
            Lookup lookup = new Lookup(hostName, 15);
            lookup.run();
            if (lookup.getResult() != 0) {
                return false;
            }
            result = lookup.getAnswers();

            for (int i = 0; i < result.length; i++) {
                host = result[i].getAdditionalName().toString();
                client.connect(host);
                if (SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                    break;
                }
                client.disconnect();
            }

            client.login("gmail.com");
            client.setSender("mycoolmc@gmail.com");
            client.addRecipient(email);
            if (250 == client.getReplyCode()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException localIOException3) {
            }
        }
        try {
            client.disconnect();
        } catch (IOException localIOException4) {
        }
        return false;
    }

    public String getMassage() {
        return this.massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
