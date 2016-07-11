package com.test.ThreadTest;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwBarrackVO;
import com.bw.dao.BwBarrackDAO;
import com.bw.exception.CacheDaoException;
import com.commonSocket.net.AppContext;

public class Insert100Curr implements Runnable {
    //private static Logger log=Logger.getLogger(Insert100Curr.class);

    private Logger logger = Logger.getLogger(getClass());
    BwBarrackDAO bwBarrackLibCacheDAOImpl = null;

    public Insert100Curr(AppContext appContextx) {
        bwBarrackLibCacheDAOImpl = (BwBarrackDAO) appContextx.getBean("BwBarrackCacheDAOImpl");
    }

    @Override
    public void run() {
        int count = 5;
        for (int m = 0; m < count; m++) {
            insertDB();
        }
        System.out.println("结束时间:" + System.currentTimeMillis() + "thread-id:" + Thread.currentThread().getName());
        logger.info("结束时间:" + System.currentTimeMillis());
    }

    public void insertDB() {

        BwBarrackVO bwbarracklibvo = new BwBarrackVO();
//		bwbarracklibvo.setCharacterid(10);
//		bwbarracklibvo.setCharacterlevel(1);
//		bwbarracklibvo.setDeleteflag(0);
        bwbarracklibvo.setEndtime("2012-11-1 08:20:30");
        bwbarracklibvo.setProducecount(50);
//		bwbarracklibvo.setUserbuildingdataid(10);
        try {
            bwBarrackLibCacheDAOImpl.save(bwbarracklibvo);
        } catch (CacheDaoException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
            e.printStackTrace();

        }
        bwbarracklibvo = null;
    }

}
