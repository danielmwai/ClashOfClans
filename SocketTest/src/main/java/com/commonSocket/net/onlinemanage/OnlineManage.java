package com.commonSocket.net.onlinemanage;

public abstract interface OnlineManage {

    public abstract void removeOnlineRole(String paramLong);

    public abstract void addOnlineRole(String paramLong, Object paramObject);

    public abstract void updataOnlineRole(String paramLong);

    public abstract Object getOnlineRole(String paramLong);

    public abstract int getOnlineUserCount();

    public abstract String[] onlineIdSet();
}
