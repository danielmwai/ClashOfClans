package com.commonSocket.net.util;

import com.commonSocket.net.Application;
import com.commonSocket.net.util.email.Email;

import java.net.SocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.mina.core.service.IoAcceptor;

public class Report {

    private ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();

    private void sendEmail() {
        Email email = new Email();
        StringBuffer sb = new StringBuffer();
        for (SocketAddress socketAddress : Application.getInstance().getAcceptor().getLocalAddresses()) {
            sb.append("\n");
            sb.append("<");
            sb.append(socketAddress);
            sb.append(">");
        }
        email.setMassage("Aplication id:\n" + Application.getInstance().getAppId() + "\nAplication ip: " + sb.toString());
        email.sendEmail();
    }

    public Report() {
        this.es.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                if (Application.getInstance().canStart()) {
                    System.out.println("发mail.............");
//            Report.this.sendEmail();
                }
                Report.this.es.shutdown();
                Report.this.es = null;
                System.gc();
            }
        }, 10L, 1L, TimeUnit.MINUTES);
    }
}
