package com.bw.application.action;

import org.apache.log4j.Logger;

import com.bw.application.message.UUIDsSelfBuilder;
import com.bw.application.utils.PropertyWriter;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

public class CreateUUIDAction implements Action {

    private Logger logger = Logger.getLogger(getClass());
    private PropertyWriter propertyWriter;
    private long currentUUID = 10000;

    @Override
    public String execute(Request request, Response paramResponse)
            throws Exception {

        UUIDsSelfBuilder.CreateUUIDRequest reqMsg = UUIDsSelfBuilder.CreateUUIDRequest
                .parseFrom(request.getMessage());
        UUIDsSelfBuilder.CreateUUIDResponse.Builder builder = UUIDsSelfBuilder.CreateUUIDResponse
                .newBuilder();
        try {
            String serverNumber = reqMsg.getServerNumber();
            String macAddress = reqMsg.getMacAddress();
            int areaId = reqMsg.getAreaId();
            long newUUID = this.getNewUUID();
            builder.setUuid(newUUID);
            builder.setMacAddress(macAddress);
            builder.setAreaId(areaId);
            builder.setResult(0);

        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(1);
            builder.setInfo(e.getMessage());
            logger.error(e);
            System.out.println("获取UUID失败!");
        } finally {
            paramResponse.write(builder.build());
        }
        return null;
    }

    public PropertyWriter getPropertyWriter() {
        return propertyWriter;
    }

    public void setPropertyWriter(PropertyWriter propertyWriter) {
        this.propertyWriter = propertyWriter;
        this.currentUUID = Long.parseLong(this.propertyWriter
                .getConfig("login_game_uuid"));
    }

    public synchronized long getNewUUID() {
        long recordMaxUUID = Long.parseLong(propertyWriter
                .getConfig("login_game_uuid"));
        long result = 0;
        if (this.currentUUID + 10 >= recordMaxUUID) {
            propertyWriter.wirteUUIDToFile("login_game_uuid",
                    this.currentUUID + 100);
        }
        result = this.currentUUID++;
        if (logger.isDebugEnabled()) {
            logger.debug("产生新的UUID:" + this.currentUUID);
        }
        return result;
    }
}
