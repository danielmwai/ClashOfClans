package com.bw.application.manager.impl;

import java.util.List;

import com.bw.application.exception.ManagerServerException;
import com.bw.application.manager.IPVPOrderManager;
import com.bw.application.message.PVPGradeOrder;
import com.bw.application.message.PVPGradeOrder.PVPGradeOrderResponse.Builder;
import com.bw.cache.vo.BwUserVO;
import com.bw.cache.vo.PVPGradeOrderVO;
import com.bw.dao.BwUserDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author denny zhao 用户 公会排行
 *
 */
public class PVPOrderManagerImpl implements IPVPOrderManager {

    public BwUserDAO bwUserCacheDAOImpl;

    @Override
    public int getPVPGradeOrder(String boweiId, Builder builder)
            throws ManagerServerException {
        //根据boweiId获取PVP分数
        BwUserVO bwuservo = new BwUserVO();
        bwuservo.setMailaddress(boweiId);
        try {
            bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(bwuservo);
            PVPGradeOrderVO pVPGradeOrderVO = new PVPGradeOrderVO();
            pVPGradeOrderVO.setBoweiId(boweiId);
            pVPGradeOrderVO.setPvpGrade((int) bwuservo.getPvpmark());
            List<PVPGradeOrderVO> resultList = bwUserCacheDAOImpl.getPVPGradeOrder(pVPGradeOrderVO);
            if (null != resultList) {
                for (PVPGradeOrderVO govo : resultList) {
                    PVPGradeOrder.PVPGradeOrderResponse.PVPGradeOrderVO.Builder b = PVPGradeOrder.PVPGradeOrderResponse.PVPGradeOrderVO.newBuilder();
                    b.setLevel(govo.getLevel());
                    b.setMailAddress(govo.getBoweiId());
                    b.setNickname(govo.getNickname());
                    b.setPvpGrade(govo.getPvpGrade());
                    b.setPvpGradeOrder(govo.getPvpGradeOrder());
                    b.setPvpOrderChangeCount(govo.getPvpOrderChangeCount());
                    b.setPvpOrderChangeFlag(govo.getPvpOrderChangeFlag());
                    builder.addPvpGradeOrderVOList(b.build());
                }
            }
        } catch (CacheDaoException e) {
            e.printStackTrace();
            throw new ManagerServerException(e);
        }
        //直接遍历返回
        return 0;
    }

    public BwUserDAO getBwUserCacheDAOImpl() {
        return bwUserCacheDAOImpl;
    }

    public void setBwUserCacheDAOImpl(BwUserDAO bwUserCacheDAOImpl) {
        this.bwUserCacheDAOImpl = bwUserCacheDAOImpl;
    }

}
