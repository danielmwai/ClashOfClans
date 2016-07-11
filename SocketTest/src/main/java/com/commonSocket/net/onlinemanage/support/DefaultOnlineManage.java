package com.commonSocket.net.onlinemanage.support;

import com.commonSocket.net.Service;
import com.commonSocket.net.onlinemanage.OnlineManage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class DefaultOnlineManage
        implements OnlineManage, Service {

    private AtomicBoolean started = new AtomicBoolean(false);
    protected ConcurrentHashMap<String, Object> onlineMap = null;

    public void start() {
        if (this.started.compareAndSet(false, true)) {
            this.onlineMap = new ConcurrentHashMap<String, Object>();
        }
    }

    public void stop() {
        if (this.started.compareAndSet(true, false)) {
            if (this.onlineMap != null) {
                this.onlineMap.clear();
                this.onlineMap = null;
            }

        }
    }

    public void removeOnlineRole(String roleId) {
        this.onlineMap.remove(roleId);
    }

    public void addOnlineRole(String roleId, Object role) {
        this.onlineMap.put(roleId, role);
    }

    public void updataOnlineRole(String roleId) {
    }

    public int getOnlineUserCount() {
        return this.onlineMap.size();
    }

    public Object getOnlineRole(String roleId) {
        return this.onlineMap.get(roleId);
    }

    public String[] onlineIdSet() {
        return (String[]) this.onlineMap.keySet().toArray(new String[0]);
    }
}
