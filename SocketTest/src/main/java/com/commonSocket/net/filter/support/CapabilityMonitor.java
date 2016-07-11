package com.commonSocket.net.filter.support;

import com.commonSocket.net.Application;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;
import com.commonSocket.net.filter.Filter;
import com.commonSocket.net.filter.FilterChain;
import com.commonSocket.net.notice.Session;
import com.commonSocket.net.onlinemanage.OnlineManage;
import com.google.protobuf.ByteString;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.log4j.Logger;

public class CapabilityMonitor
        implements Filter {

    private Logger logger = Logger.getLogger(getClass());
    private Map<String, Tag> map = new Hashtable();
    private Timer timer = new Timer();
    private int monitor;
    private int monitorType;
    private long interval = 5000L;
    private MonitorData data;
    private OnlineManage onlineManage;

    public int getMonitor() {
        return this.monitor;
    }

    public void setMonitor(int monitor) {
        this.monitor = monitor;
    }

    public int getMonitorType() {
        return this.monitorType;
    }

    public void setMonitorType(int monitorType) {
        this.monitorType = monitorType;
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void init() {
        this.data = new MonitorData();
        if (this.monitor == 1) {
            this.timer.schedule(new Task(), this.interval, this.interval);
        }
    }

    public void doFilter(Request request, Response response, FilterChain chain)
            throws Exception {
        this.data.addCount();
        Tag tag = new Tag();
        tag.setCreateTime(new Date());
        tag.setMessage(request.getMessage());
        tag.setSession(request.getSession());
        tag.setThreadId(Thread.currentThread().getId());
        this.map.put(String.valueOf(Thread.currentThread().getId()), tag);
        chain.doFilter(request, response);
        this.map.remove(String.valueOf(Thread.currentThread().getId()));
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Monitor:action 执行时间：" + (System.currentTimeMillis() - tag.getCreateTime().getTime()) + "毫秒；消息："
                    + tag.getMessage().toStringUtf8());
        }
    }

    public void doFilter(Session session, FilterChain filterchain)
            throws Exception {
        filterchain.doFilter(session);
    }

    public void destroy() {
        this.timer.cancel();
    }

    public OnlineManage getOnlineManage() {
        return this.onlineManage;
    }

    public void setOnlineManage(OnlineManage onlineManage) {
        this.onlineManage = onlineManage;
    }

    private class Task extends TimerTask {

        private Task() {
        }

        public void run() {
            try {
                switch (CapabilityMonitor.this.monitorType) {
                    case 0:
                        if (null != Application.getInstance().getAcceptor()) {
                            CapabilityMonitor.this.logger.info("Monitor:stat " + Application.getInstance().getSessionCount() + "/" + CapabilityMonitor.this.onlineManage.getOnlineUserCount()
                                    + "(session/onlinerole) " + CapabilityMonitor.this.data.getList().toString());
                        }
                        break;
                    case 1:
                        CapabilityMonitor.this.logger.info("Monitor:stat " + Application.getInstance().getSessionCount() + "/(session) " + CapabilityMonitor.this.data.getList().toString());
                        break;
                    case 2:
                        CapabilityMonitor.this.logger.info("Monitor:stat " + CapabilityMonitor.this.onlineManage.getOnlineUserCount() + "/(onlinerole) " + CapabilityMonitor.this.data.getList().toString());
                        break;
                }

                while (CapabilityMonitor.this.data.getList().size() > 0) {
                    CapabilityMonitor.this.data.getList().remove(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
