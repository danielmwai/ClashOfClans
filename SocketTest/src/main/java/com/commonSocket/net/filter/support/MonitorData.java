package com.commonSocket.net.filter.support;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonitorData {

    private String currTime;
    private int currCount;
    private List<String> list;

    public MonitorData() {
        this.currTime = "";
        this.currCount = 0;
        this.list = new ArrayList();
    }

    public synchronized void addCount() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        if (df.format(date).equals(this.currTime)) {
            this.currCount += 1;
        } else {
            String text = "时间：" + this.currTime + ";并发数：" + this.currCount;
            this.list.add(text);
            this.currTime = df.format(date);
            this.currCount = 1;
        }
    }

    public List<String> getList() {
        return this.list;
    }
}
