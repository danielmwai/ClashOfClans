package com.bw.application.action;

import com.bw.application.manager.IBattleManager;
import com.bw.application.message.BattleMatching.BattleMatchingRequest;
import com.bw.application.message.BattleMatching.BattleMatchingResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

/**
 * @author denny zhao 战斗匹配
 */
public class BattleMatchingAction implements Action {

    public IBattleManager battleManagerImpl;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        BattleMatchingRequest request = BattleMatchingRequest.parseFrom(paramRequest.getMessage());
        String mailAddress = request.getMailAddress();
        String previousMailAddress = request.getPreviousMailAddress();
        BattleMatchingResponse.Builder builder = BattleMatchingResponse.newBuilder();
        int battleStatus = request.getBattleStatus();
        try {
            int result = battleManagerImpl.battleMatching(builder, mailAddress, previousMailAddress, battleStatus);
            builder.setResult(result);

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
