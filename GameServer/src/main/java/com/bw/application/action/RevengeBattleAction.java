package com.bw.application.action;

import org.apache.log4j.Logger;

import com.bw.application.manager.IBattleManager;
import com.bw.application.message.RevengeBattle.RevengeBattleRequest;
import com.bw.application.message.RevengeBattle.RevengeBattleResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

/**
 * @author denny zhao 复仇action
 */
public class RevengeBattleAction implements Action {

    public IBattleManager battleManagerImpl;
    private static Logger logger = Logger.getLogger(BattleStartAction.class);

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        RevengeBattleRequest request = RevengeBattleRequest.parseFrom(paramRequest.getMessage());
        RevengeBattleResponse.Builder builder = RevengeBattleResponse.newBuilder();
        // TODO Auto-generated method stub 
        try {
            String attacker = request.getMailAddress();
            String defencer = request.getDefenceMailAddress();
//			String defenceMailAddress=
            int result = battleManagerImpl.revengeBattle(builder, attacker, defencer);
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
