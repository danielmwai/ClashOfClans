package com.bw.dao.cachedao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bw.baseJar.common.CommonGameData;
import com.bw.baseJar.vo.StopServerMessageVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.cache.vo.PVPGradeOrderVO;
import com.bw.dao.BwUserDAO;
import com.bw.exception.CacheDaoException;

public class BwUserCacheDAOImpl extends CacheDao implements BwUserDAO {

    public BwUserDAO bwUserDaoImpl;
    private String key_prefix = "bw_user_";
    //农民的数量
    private String key_prefix_work_count = "bw_user_work_count_";
    private String key_server_status = "key_server_status_";
    //pvp 分数排行key 前缀
    private String key_pvp_grade_prefix_200 = "key_pvp_grade_prefix_200_";
    private String key_pvp_grade_prefix_5 = "key_pvp_grade_prefix_5_";
    private static String PVPGRADE_SQL = "select  mail_address,nick_name,level,pvp_mark,area_id from bw_user  order by pvp_mark desc limit 200 ";

    public void delete(BwUserVO bwuservo) throws CacheDaoException {
    }

    @Override
    public BwUserVO queryBwUserVOById(BwUserVO bwuservo)
            throws CacheDaoException {
        String key = key_prefix + bwuservo.getMailaddress();
        if (this.getCache().get(key) != null) {
            return (BwUserVO) this.getCache().get(key);
        } else {
            BwUserVO t = bwUserDaoImpl.queryBwUserVOById(bwuservo);
            if (null != t) {
                this.getCache().put(key, t);
                return t;
            }
        }
        return null;
    }

    @Override
    public long queryBwUserVOCount(BwUserVO bwuservo) throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwUserVOIds(BwUserVO bwuservo)
            throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwUserVO bwuservo) throws CacheDaoException {
        bwUserDaoImpl.save(bwuservo);
        String key = key_prefix + bwuservo.getMailaddress();
        this.getCache().put(key, bwuservo);
    }

    @Override
    public void update(BwUserVO bwuservo) throws CacheDaoException {
        String key = key_prefix + bwuservo.getMailaddress();
        this.getCache().put(key, bwuservo);
//             bwUserDaoImpl.update(bwuservo);
    }

    @Override
    public List<BwUserVO> queryBwUserVO(BwUserVO bwuservo)
            throws CacheDaoException {
        return null;
    }

    public BwUserDAO getBwUserDaoImpl() {
        return bwUserDaoImpl;
    }

    public void setBwUserDaoImpl(BwUserDAO bwUserDaoImpl) {
        this.bwUserDaoImpl = bwUserDaoImpl;
    }

    @Override
    public void batchUpdate(List<BwUserVO> listtime1) throws CacheDaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public StopServerMessageVO getServerStatus() throws CacheDaoException {
        String key = key_server_status;
        Object o = this.getCache().get(key);

        if (null != o) {
            return (StopServerMessageVO) o;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PVPGradeOrderVO> getPVPGradeOrder(PVPGradeOrderVO pVPGradeOrderVO)
            throws CacheDaoException {
        //如果为null直接从数据库获取并且找出前200 名 和于用户相近的5名  同时put入cache 否则 直接读取

        String key = key_pvp_grade_prefix_200;
        String key5 = key_pvp_grade_prefix_5;
        Object o200 = this.getCache().get(key);
        boolean in200flag = false;
        List<PVPGradeOrderVO> result = new ArrayList<PVPGradeOrderVO>();
        BwUserVO temUservo = new BwUserVO();
        if (o200 == null) {//从数据库查询

            temUservo.setPvpGradeOrderSQL(PVPGRADE_SQL);
            List<BwUserVO> buservoList = bwUserDaoImpl.queryBwUserVO(temUservo);
            for (int i = 0; i < buservoList.size(); i++) {
                BwUserVO buvo = buservoList.get(i);
                PVPGradeOrderVO temp = new PVPGradeOrderVO();
                temp.setBoweiId(buvo.getMailaddress());
                temp.setPvpGradeOrder(i + 1);
                temp.setPvpOrderChangeFlag(0);
                temp.setPvpOrderChangeCount(0);
                temp.setLevel(buvo.getLevel());
                temp.setNickname(buvo.getNickname());
                temp.setPvpGrade((int) buvo.getPvpmark());
                result.add(temp);
                if (pVPGradeOrderVO.getBoweiId().equalsIgnoreCase(buvo.getMailaddress())) {
                    in200flag = true;
                }
            }
            if (result.size() > 0) {//写入cache
                this.getCache().put(key, result);
            }

        } else {
            result = (List<PVPGradeOrderVO>) o200;
            for (PVPGradeOrderVO resultPVPGVO : result) {
                if (pVPGradeOrderVO.getBoweiId().equalsIgnoreCase(resultPVPGVO.getBoweiId())) {
                    in200flag = true;
                    break;
                }
            }
        }
        //200外并列
        if (!in200flag) {//200名以外 
            Object o5 = this.getCache().get(key5);
            List<PVPGradeOrderVO> result5 = new ArrayList<PVPGradeOrderVO>();
            if (null == o5) {

                String sql5 = "select  mail_address,nick_name,level,pvp_mark,area_id from bw_user where pvp_mark=" + pVPGradeOrderVO.getPvpGrade() + "  limit 5 ";
                temUservo.setPvpGradeOrderSQL(sql5);
                //获取pvpgrade相同的成员
                List<BwUserVO> buservoList5 = bwUserDaoImpl.queryBwUserVO(temUservo);
                //获取排行
                long order = bwUserDaoImpl.getPVPGradeOrderByGrade(pVPGradeOrderVO.getPvpGrade());
                for (BwUserVO bwUserVO : buservoList5) {
                    PVPGradeOrderVO temp = new PVPGradeOrderVO();
                    temp.setBoweiId(bwUserVO.getMailaddress());
                    temp.setPvpGradeOrder((int) order);
                    temp.setPvpOrderChangeFlag(0);
                    temp.setPvpOrderChangeCount(0);
                    temp.setLevel(bwUserVO.getLevel());
                    temp.setNickname(bwUserVO.getNickname());
                    temp.setPvpGrade((int) bwUserVO.getPvpmark());
                    temp.setOrderTime(new Date());
                    result5.add(temp);
                    result.add(temp);
                }
                buservoList5.clear();
                buservoList5 = null;
                if (result5.size() > 0) {
                    this.getCache().put(key5, result5, CommonGameData.EXP_TIME_OF_5 * 60);
                }
                result5.clear();
                result5 = null;
            } else {
                //需要判断一下是否需要检查
                result5 = (List<PVPGradeOrderVO>) o5;

                if (result5.size() > 0) {
                    List<PVPGradeOrderVO> result5New = new ArrayList<PVPGradeOrderVO>();
//					Data nowTime=new Date();
                    Calendar now = Calendar.getInstance();
                    now.setTime(new Date());
                    Calendar orderTime = Calendar.getInstance();
                    orderTime.setTime(result5.get(0).getOrderTime());
                    orderTime.add(Calendar.MINUTE, CommonGameData.EXP_TIME_OF_5);
                    if (orderTime.before(now)) {//重新进行排序
                        String sql5 = "select  mail_address,nick_name,level,pvp_mark,area_id from bw_user where pvp_mark=" + pVPGradeOrderVO.getPvpGrade() + "  limit 5 ";
                        temUservo.setPvpGradeOrderSQL(sql5);
                        //获取pvpgrade相同的成员
                        List<BwUserVO> buservoList5 = bwUserDaoImpl.queryBwUserVO(temUservo);
                        //获取排行
                        long order = bwUserDaoImpl.getPVPGradeOrderByGrade(pVPGradeOrderVO.getPvpGrade());
                        for (BwUserVO bwUserVO : buservoList5) {
                            String boweiId = bwUserVO.getMailaddress();
                            PVPGradeOrderVO temp = new PVPGradeOrderVO();
                            temp.setBoweiId(bwUserVO.getMailaddress());
                            temp.setPvpGradeOrder((int) order);
                            temp.setLevel(bwUserVO.getLevel());
                            temp.setPvpOrderChangeFlag(0);
                            temp.setPvpOrderChangeCount(0);
                            temp.setNickname(bwUserVO.getNickname());
                            temp.setPvpGrade((int) bwUserVO.getPvpmark());
                            temp.setOrderTime(new Date());
                            for (PVPGradeOrderVO pgovo : result5) {
                                if (boweiId.equalsIgnoreCase(pgovo.getBoweiId())) {
                                    int orderCount = temp.getPvpGradeOrder() - pgovo.getPvpGradeOrder();
                                    if (orderCount > 0) {
                                        temp.setPvpOrderChangeFlag(-1);
                                        temp.setPvpOrderChangeCount(orderCount);
                                    } else if (orderCount < 0) {
                                        temp.setPvpOrderChangeFlag(1);
                                        temp.setPvpOrderChangeCount(Math.abs(orderCount));
                                    }
                                    break;
                                }

                            }
                            result5New.add(temp);
                            result.add(temp);
                        }
                        if (result5New.size() > 0) {
                            this.getCache().put(key5, result5New, CommonGameData.EXP_TIME_OF_5 * 60);
                            result5New.clear();
                            result5New = null;
                        }

                    } else {
                        result.addAll(result5);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public long getPVPGradeOrderByGrade(long pvpgrade) throws CacheDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setPVPGradeOrder() throws CacheDaoException {
        String key = key_pvp_grade_prefix_200;
        List<PVPGradeOrderVO> result = new ArrayList<PVPGradeOrderVO>();

        BwUserVO temUservo = new BwUserVO();
        temUservo.setPvpGradeOrderSQL(PVPGRADE_SQL);
        List<BwUserVO> buservoList = bwUserDaoImpl.queryBwUserVO(temUservo);
        //获取老的200名排行
        Object resultOld = this.getCache().get(key);
        List<PVPGradeOrderVO> tempOld = new ArrayList<PVPGradeOrderVO>();
        if (null != resultOld) {
            tempOld = (List<PVPGradeOrderVO>) tempOld;
        }
        for (int i = 0; i < buservoList.size(); i++) {
            BwUserVO buvo = buservoList.get(i);
            PVPGradeOrderVO temp = new PVPGradeOrderVO();
            temp.setBoweiId(buvo.getMailaddress());
            temp.setPvpGradeOrder(i + 1);
            temp.setPvpOrderChangeFlag(0);
            temp.setPvpOrderChangeCount(0);
            temp.setLevel(buvo.getLevel());
            temp.setNickname(buvo.getNickname());
            temp.setPvpGrade((int) buvo.getPvpmark());
            for (PVPGradeOrderVO t : tempOld) {
                if (buvo.getMailaddress().equalsIgnoreCase(t.getBoweiId())) {
                    int orderCount = temp.getPvpGradeOrder() - t.getPvpGradeOrder();
                    if (orderCount > 0) {
                        temp.setPvpOrderChangeFlag(-1);
                        temp.setPvpOrderChangeCount(orderCount);
                    } else if (orderCount < 0) {
                        temp.setPvpOrderChangeFlag(1);
                        temp.setPvpOrderChangeCount(Math.abs(orderCount));
                    }
                    break;
                }
            }
            result.add(temp);
        }
        if (result.size() > 0) {//写入cache
            this.getCache().put(key, result);
            buservoList.clear();
            buservoList = null;
        }

    }

//	@Override
//	public int getWorkCount(BwUserVO bwuservo) throws CacheDaoException {
//		String key=key_prefix_work_count+bwuservo.getMailaddress();
//		return (Integer)this.getCache().get(key);
//	}
//
//	@Override
//	public void updateWorkCount(BwUserVO bwuservo) throws CacheDaoException {
//		String key=key_prefix_work_count+bwuservo.getMailaddress();
//		this.getCache().put(key, bwuservo.getWorkCount());
//		
//	}
}
