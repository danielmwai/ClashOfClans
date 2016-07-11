package com.bw.application.util;

import org.apache.log4j.*;
import com.bw.dao.*;
import com.bw.baseJar.vo.*;
import java.io.*;
import java.util.*;

public class GlobalCmDict {

    private final Logger logger;
    private CmTableDao cmTableDao;
    private String filePath;
    public static Map<String, CmBuildingVO> buildingMap;
    public static Map<String, CmCropsVO> cropsMap;
    public static Map<String, CmIslandDataVO> islandDataMap;
    public static Map<String, CmSeaDataVO> seaDataMap;
    public static Map<Integer, Long> levelMap;
    public static Map<Integer, CmCastleVO> castleMap;
    public static Map<Integer, CmPackageVO> packageMap;

    public GlobalCmDict() {
        this.logger = Logger.getLogger(GlobalCmDict.class);
    }

    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    public void setCmTableDao(final CmTableDao cmTableDao) {
        this.cmTableDao = cmTableDao;
    }

    public void init() {
        if (GlobalCmDict.buildingMap == null) {
            GlobalCmDict.buildingMap = new HashMap<String, CmBuildingVO>();
            final List<CmBuildingVO> list = this.cmTableDao.initAllBuilding();
            if (list != null && list.size() > 0) {
                for (final CmBuildingVO cmBuilding : list) {
                    GlobalCmDict.buildingMap.put(String.valueOf(cmBuilding.getBuildingId()), cmBuilding);
                }
            } else {
                this.logger.error("\u9519\u8bef\u4fe1\u606f:\u57fa\u7840\u5efa\u7b51\u8868\u4e3a\u7a7a");
            }
        }
        if (GlobalCmDict.cropsMap == null) {
            GlobalCmDict.cropsMap = new HashMap<String, CmCropsVO>();
            final List<CmCropsVO> list2 = this.cmTableDao.initAllCrops();
            if (list2 != null && list2.size() > 0) {
                for (final CmCropsVO cmCrops : list2) {
                    GlobalCmDict.cropsMap.put(String.valueOf(cmCrops.getId()), cmCrops);
                }
            } else {
                this.logger.error("\u9519\u8bef\u4fe1\u606f:\u519c\u4f5c\u7269\u8868\u4e3a\u7a7a");
            }
        }
        if (GlobalCmDict.islandDataMap == null) {
            GlobalCmDict.islandDataMap = new HashMap<String, CmIslandDataVO>();
            final List<CmIslandDataVO> list3 = this.cmTableDao.initAllIslandData();
            if (list3 != null && list3.size() > 0) {
                for (final CmIslandDataVO cmIslandDataVO : list3) {
                    try {
                        this.initLoadPoint(cmIslandDataVO);
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.logger.error("\u9519\u8bef\u4fe1\u606f:\u5c9b\u5c7f\u5750\u6807\u521d\u59cb\u5316\u5931\u8d25");
                    }
                    GlobalCmDict.islandDataMap.put(String.valueOf(cmIslandDataVO.getId()), cmIslandDataVO);
                }
            } else {
                this.logger.error("\u9519\u8bef\u4fe1\u606f:\u5c9b\u5c7f\u6570\u636e\u8868\u4e3a\u7a7a");
            }
        }
        if (GlobalCmDict.seaDataMap == null) {
            GlobalCmDict.seaDataMap = new HashMap<String, CmSeaDataVO>();
            final List<CmSeaDataVO> list4 = this.cmTableDao.initAllSeaData();
            if (list4 != null && list4.size() > 0) {
                for (final CmSeaDataVO cmSeaDataVO : list4) {
                    GlobalCmDict.seaDataMap.put(String.valueOf(cmSeaDataVO.getId()), cmSeaDataVO);
                }
            } else {
                this.logger.error("\u9519\u8bef\u4fe1\u606f:\u6d77\u6d0b\u6570\u636e\u8868\u4e3a\u7a7a");
            }
        }
        if (GlobalCmDict.levelMap == null) {
            GlobalCmDict.levelMap = new HashMap<Integer, Long>();
            final List<CmLevelVO> list5 = this.cmTableDao.initAllLevel();
            if (list5 != null && list5.size() > 0) {
                for (final CmLevelVO cmLevelVO : list5) {
                    GlobalCmDict.levelMap.put(cmLevelVO.getUserLevel(), cmLevelVO.getTotal_exp());
                }
            } else {
                this.logger.error("\u9519\u8bef\u4fe1\u606f:\u7b49\u7ea7\u533a\u95f4\u8868\u4e3a\u7a7a");
            }
        }
        if (GlobalCmDict.castleMap == null) {
            GlobalCmDict.castleMap = new HashMap<Integer, CmCastleVO>();
            final List<CmCastleVO> list6 = this.cmTableDao.initAllCastle();
            if (list6 != null && list6.size() > 0) {
                for (final CmCastleVO cmCastleVO : list6) {
                    GlobalCmDict.castleMap.put(cmCastleVO.getId(), cmCastleVO);
                }
            } else {
                this.logger.error("\u9519\u8bef\u4fe1\u606f:\u57ce\u5821\u6570\u636e\u4e3a\u7a7a");
            }
        }
        if (GlobalCmDict.packageMap == null) {
            GlobalCmDict.packageMap = new HashMap<Integer, CmPackageVO>();
            final List<CmPackageVO> list7 = this.cmTableDao.initAllPackage();
            if (list7 != null && list7.size() > 0) {
                for (final CmPackageVO cmPackageVO : list7) {
                    GlobalCmDict.packageMap.put(cmPackageVO.getId(), cmPackageVO);
                }
            } else {
                this.logger.error("\u9519\u8bef\u4fe1\u606f:\u4ed3\u5e93\u6570\u636e\u4e3a\u7a7a");
            }
        }
    }

    private void initLoadPoint(final CmIslandDataVO vo) throws Exception {
        final int id = vo.getId();
        final File file = new File(String.valueOf(this.filePath) + String.valueOf(id) + ".txt");
        final Scanner scanner = new Scanner(file);
        final String[] split = scanner.nextLine().trim().split(", ");
        final List<Integer> xList = new ArrayList<Integer>();
        final List<Integer> yList = new ArrayList<Integer>();
        for (int i = 0; i < split.length; i += 100) {
            final int k = i / 100 + 1;
            for (int j = 1; j < 101; ++j) {
                final String string = split[i + j - 1];
                if (!"36".equals(string)) {
                    xList.add(j);
                    yList.add(k);
                }
            }
        }
        vo.setPointX(xList);
        vo.setPointY(yList);
    }
}
