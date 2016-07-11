package com.bw.cache.main;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;

import com.bw.cache.utils.IocUtils;

public class CityCacheMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //DOMConfigurator.configure("config/log4j.xml");//src/main/resources/log4j.xml
        DOMConfigurator.configure(CityCacheMain.class.getResource("/log4j.xml"));
        ApplicationContext spring = IocUtils.getClassPathXmlApplicationContext();

        //CGUserIslandInfoDAOImpl bean = (CGUserIslandInfoDAOImpl) spring.getBean("islandDao");
        //List<CGUserIslandInfoVO> list = bean.queryInfoByMail("aaa");
        //System.out.println(list.size());
//		CGUserVO vo = new CGUserVO();
//
//		for (int i = 0; i < 50; i++) {
//
//			vo.setCmIslandDataId(i + 1);
//			vo.setExp(new Random().nextLong());
//			vo.setGoldenCount(new Random().nextInt(5524));
//			vo.setImageName("abc");
//			vo.setLevels(new Random().nextInt(100));
//			vo.setLocalArea("ch");
//			vo.setMachineNum("sdaoijbosdi");
//			vo.setMailAddress(UUID.randomUUID().toString() + "@164.com");
//			vo.setNickName("gaofushuai");
//			vo.setPraisesCount(new Random().nextInt(2316341));
//			vo.setScreenHeight(new Random().nextInt(900));
//			vo.setScreenWidth(new Random().nextInt(800));
//			vo.setSex(new Random().nextInt(1));
//
//			bean.register(vo);
//			bean.initUserInfo(vo);
//
//		}
        // GlobalCmDict.buildingMap
        // CGFriendRelationCacheDAOimpl bean = (CGFriendRelationCacheDAOimpl)
        // spring
        // .getBean("cGFriendRelationCacheDAOimpl");
        // CGFriendRelationVO vo = new CGFriendRelationVO();
        // vo.setUserid(3L);
        // vo.setFriendid(5L);
        // System.out.println("------------------------[" + vo.getUserid() +
        // "]开始添加["
        // + vo.getFriendid() + "]为好友-------------------------");
        // bean.save(vo);
        // System.out.println("------------------------好友添加完成-------------------------");
        // long count = bean.queryCGFriendRelationVOCount(vo);
        // System.out.println("------------------------[" + vo.getUserid() +
        // "]好友数量为[" + count
        // + "]-------------------------");
        // List<CGFriendRelationVO> list = bean.queryCGFriendRelationVO(vo);
        // System.out.println("------------------------[" + vo.getUserid() +
        // "]好友列表为-------------------------");
        // for (CGFriendRelationVO cg : list) {
        // System.out.print(cg.getFriendid()+"  ");
        // }
        // System.out.println("------------------------[" + vo.getUserid() +
        // "]开始删除好友["
        // + vo.getFriendid() + "]-------------------------");
        // bean.delete(vo);
        // System.out.println("------------------------删除好友完成-------------------------");
        // count = bean.queryCGFriendRelationVOCount(vo);
        // System.out.println("------------------------[" + vo.getUserid() +
        // "]好友数量为[" + count
        // + "]-------------------------");
        // FarmUserinfoDAO farmUserinfoCacheDAO = (FarmUserinfoDAO)
        // IocUtils.getBeanFromJar("farmUserinfoCacheDAO");
        // FarmDepotDAO farmDepotCacheDAO = (FarmDepotDAO)
        // IocUtils.getBeanFromJar("farmDepotCacheDAO");
        // System.out.println("********************************************************************************************************");
        // System.out.println("******************************************开始载入数据库信息****************************************************");
        // System.out.println("********************************************************************************************************");
        // System.out.println("*******************************************load 载入道具信息开始 from db start*********************************************");
        // farmDepotCacheDAO.loadDeportInfor();
        // System.out.println("*******************************************load 载入道具信息结束 from db end*********************************************");
        // System.out.println("*******************************************load 载入土地信息开始 from db start*********************************************");
        // System.out.println("*******************************************load 载入土地信息结束 from db end*********************************************");
        // System.out.println("*******************************************load 载入用户信息开始 from db start*********************************************");
        // farmUserinfoCacheDAO.queryAllUserInfor();
        // System.out.println("*******************************************load 载入用户信息结束 from db end*********************************************");
        // System.out.println("******************************************结束载入数据库信息****************************************************");
    }

}
