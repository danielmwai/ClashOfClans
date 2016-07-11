package com.bw.application.action;

import com.bw.application.manager.IBattleManager;
import com.bw.application.message.BattleDataCheck;
import com.bw.application.message.BattleDataCheck.BattleDataCheckRequest;
import com.bw.application.message.BattleDataCheck.BattleDataCheckResponse;
import com.bw.application.message.BattleDataCheck.BattleDataCheckResponse.Builder;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

/**
 * @author denny zhao
 *
 */
public class BattleDataCheckAction implements Action {

    public IBattleManager battleManagerImpl;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        BattleDataCheckRequest request = BattleDataCheckRequest.parseFrom(paramRequest.getMessage());
        BattleDataCheckResponse.Builder builder = BattleDataCheckResponse.newBuilder();
        try {

            builder.setResult(0);
        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(1);
            builder.setInfo(e.getMessage());
        } finally {

            paramResponse.write(builder.build());
        }
        return null;
    }

    public IBattleManager getBattleManagerImpl() {
        return battleManagerImpl;
    }

    public void setBattleManagerImpl(IBattleManager battleManagerImpl) {
        this.battleManagerImpl = battleManagerImpl;
    }

}
