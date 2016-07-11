package com.commonSocket.net.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NodeUtil {

    static String nodeId = null;

    public static String getLocalNodeId() {
        if (nodeId == null) {
            String hostName = "hostname";
            String userDir = "userdir";
            try {
                InetAddress addr = InetAddress.getLocalHost();
                hostName = addr.getHostName();
                userDir = System.getProperty("user.dir").replace("\\", "/");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            nodeId = hostName + "@" + userDir;
        }
        return nodeId;
    }
}
