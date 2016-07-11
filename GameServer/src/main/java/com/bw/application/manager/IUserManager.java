package com.bw.application.manager;

import com.bw.application.exception.ManagerServerException;
import com.bw.application.message.DownloadMapInfor.DownloadMapInforResponse;
import com.bw.application.message.OperateSequence.OperateSequenceRequest;
import com.bw.application.message.OperateSequence.OperateSequenceRequest.Operate;
import com.bw.application.message.ProduceSoldierMessage.ProduceSoldiersRequest;
import com.bw.cache.vo.BwBarrackVO;
import com.bw.cache.vo.BwPlantUserVO;
import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserVO;

import java.util.List;

/**
 * 用户业务逻辑接口
 *
 * @author zhYou
 */
public interface IUserManager {

    /**
     * 构建玩家的地图和个人数据
     *
     * @param builder message builder对象, 用于构建返回给客户端的消息
     * @param mailAddress 玩家的邮箱地址, 后期改为boweiId
     * @throws ManagerServerException
     */
    public void getUserAllInfor(DownloadMapInforResponse.Builder builder, String mailAddress) throws ManagerServerException;

    /**
     * 处理用户上传数据<br/>
     * 由于角色的基地的建设不会涉及到交互内容, 所以客户端不会实时的传递建设数据<br/>
     * 定时上传的时间在客户端配置 暂定为2min reqMsg:{ type: 0-升级建筑; 1-移动建筑; 2-造兵; 3-升级兵种; 4-造魔法;
     * 5-升级魔法; 6-购买绿宝石; 7-购买金币; 8-购买药水; 9-建造; 10-收获药水; 11-收获金币; 12-拆除; 13-清除墓碑
     * operationTime: 操作时间 uuid: 建筑唯一编号 buildingId: 建筑编号 endTime: 升级结束时间
     * moveToMapIndexX: 移动目标点x }
     *
     * @param reqMsg 请求信息
     * @return 消息码
     * @throws ManagerServerException 出来客户端传上来的流水账异常
     */
    public int ProcessInforOfUpLoadFromClient(OperateSequenceRequest reqMsg) throws ManagerServerException;

    /**
     * 兵营制造士兵
     *
     * @param reqMsg 造兵请求
     * @return 消息码
     */
    public int createSoldiersFromClient(ProduceSoldiersRequest reqMsg) throws ManagerServerException;

    /**
     * 收货金币或者药水
     *
     * @param type 收货类型
     * @param operate 客户端操作
     * @param bwuservo 用户数据
     * @param bwusermapdatavo 用户地图数据
     * @return 消息码
     * @throws ManagerServerException
     */
    public int harvestElixirOrGolden(byte type, Operate operate, BwUserVO bwuservo, BwUserMapDataVO bwusermapdatavo, long attackAddCount) throws ManagerServerException;

    /**
     * 博为用户在游戏中进行注册
     *
     * @param bwPlantUserVO 博为平台用户
     * @return 状态码
     * @throws ManagerServerException 用户注册
     */
    public int userRegister(BwPlantUserVO bwPlantUserVO) throws ManagerServerException;

    public void barrackProduce(BwUserVO user, List<BwBarrackVO> barracks, long opTime) throws ManagerServerException;

    /**
     * 判断建筑是否升级, 并对建筑升级后的功能效果进行处理
     *
     * @param user 用户数据
     * @param map 建筑信息
     * @param opTime 操作时间
     */
    public void checkUpgrade(BwUserVO user, BwUserMapDataVO map, long opTime);
}
