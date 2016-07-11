package com.bw.application.manager;

import com.bw.application.exception.ManagerServerException;
import com.bw.application.message.BattleMatching.BattleMatchingResponse;
import com.bw.application.message.BattleStart.BattleStartRequest;
import com.bw.application.message.BattleStart.BattleStartResponse;
import com.bw.application.message.CancleBattle.CancleBattleRequest;
import com.bw.application.message.DownloadBattleInfor.DownloadBattleInforResponse;
import com.bw.application.message.RevengeBattle.RevengeBattleResponse;
import com.bw.application.message.UploadBattleResult.UploadBattleResultRequest;
import com.bw.application.message.UploadBattleResultPVE.UploadBattleResultPVERequest;
import com.bw.cache.vo.BwBattleVO;

public interface IBattleManager {

    public boolean createBattle(BwBattleVO bwBattleVO) throws ManagerServerException;

    /**
     * @param builder
     * @param mailAddress
     * @param previousMailAddress 前一个对手
     * @return
     * @throws ManagerServerException 根据已经传入的成员查找和自己想匹配的对手
     */
    public int battleMatching(BattleMatchingResponse.Builder builder, String mailAddress, String previousMailAddress, int battleStatus) throws ManagerServerException;

    /**
     * @return @throws ManagerServerException 检测战斗过程数据
     */
    public boolean checkBattleInfor() throws ManagerServerException;

    /**
     * @return @throws ManagerServerException 上传战斗结果
     */
    public boolean uploadBattleResult() throws ManagerServerException;

    /**
     * @return @throws ManagerServerException 上传战斗经过
     */
    public boolean uplaodBattleAnimation() throws ManagerServerException;

    /**
     * @param request
     * @return
     * @throws ManagerServerException 保存战斗信息
     */
    public int saveBattleResult(UploadBattleResultRequest request) throws ManagerServerException;

    /**
     * @param request
     * @return 0成功 1失败
     * @throws ManagerServerException 开始战斗
     */
    public int startBattle(BattleStartRequest request, BattleStartResponse.Builder build) throws ManagerServerException;

    /**
     * @param request
     * @return
     * @throws ManagerServerException 取消战斗
     */
    public int cancleBattle(CancleBattleRequest request) throws ManagerServerException;

    /**
     * @param request
     * @return
     * @throws ManagerServerException 上传pve战斗结果
     */
    public int uploadBattleResultPVE(UploadBattleResultPVERequest request) throws ManagerServerException;

    /**
     * @param request
     * @return
     * @throws ManagerServerException 下载战斗信息
     */
    public void downloadBattleInforList(String mailAddress, DownloadBattleInforResponse.Builder builder, int countFlag) throws ManagerServerException;

    /**
     * @param builder
     * @param mailAddress
     * @param defenceMailAddress 复仇业务逻辑
     * @return
     * @throws ManagerServerException
     */
    public int revengeBattle(RevengeBattleResponse.Builder builder, String mailAddress, String defenceMailAddress) throws ManagerServerException;
}
