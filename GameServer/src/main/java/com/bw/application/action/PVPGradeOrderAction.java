package com.bw.application.action;

import org.apache.log4j.Logger;

import com.bw.application.manager.IPVPOrderManager;
import com.bw.application.message.PVPGradeOrder.PVPGradeOrderRequest;
import com.bw.application.message.PVPGradeOrder.PVPGradeOrderResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

/**
 * @author denny zhao pvp分数排行
 *
 */
public class PVPGradeOrderAction implements Action {

    private static Logger logger = Logger.getLogger(PVPGradeOrderAction.class);
    private IPVPOrderManager pVPOrderManagerImpl;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        PVPGradeOrderRequest request = PVPGradeOrderRequest.parseFrom(paramRequest.getMessage());
        PVPGradeOrderResponse.Builder builder = PVPGradeOrderResponse.newBuilder();
        try {
            int result = pVPOrderManagerImpl.getPVPGradeOrder(request.getMailAddress(), builder);
            builder.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(1);
            builder.setInfo(e.getMessage());
            logger.error("获取pvp分数排行发生错误", e);
        } finally {
            paramResponse.write(builder.build());
        }
        return null;
    }

    public IPVPOrderManager getpVPOrderManagerImpl() {
        return pVPOrderManagerImpl;
    }

    public void setpVPOrderManagerImpl(IPVPOrderManager pVPOrderManagerImpl) {
        this.pVPOrderManagerImpl = pVPOrderManagerImpl;
    }

}
