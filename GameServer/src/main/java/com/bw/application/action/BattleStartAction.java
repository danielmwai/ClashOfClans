package com.bw.application.action;

import org.apache.log4j.Logger;

import com.bw.application.manager.IBattleManager;
import com.bw.application.message.BattleStart.BattleStartRequest;
import com.bw.application.message.BattleStart.BattleStartResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

/**
 * @author denny zhao
 *
 */
public class BattleStartAction implements Action {

    public IBattleManager battleManagerImpl;
    private static Logger logger = Logger.getLogger(BattleStartAction.class);

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {

        BattleStartRequest request = BattleStartRequest.parseFrom(paramRequest.getMessage());
        BattleStartResponse.Builder builder = BattleStartResponse.newBuilder();
        // TODO Auto-generated method stub 
        try {
            int result = battleManagerImpl.startBattle(request, builder);
            builder.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(1);
            builder.setInfo(e.getMessage());
            logger.error("开始战斗产生异常", e);
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
