package com.bw.dao;

import java.util.*;
import com.bw.baseJar.vo.*;

public interface CmTableDao {

    List<CmBuildingVO> initAllBuilding();

    List<CmCropsVO> initAllCrops();

    List<CityServerChannleVO> initAllServerChannle();

    List<CmIslandDataVO> initAllIslandData();

    List<CmSeaDataVO> initAllSeaData();

    List<CmLevelVO> initAllLevel();

    List<CmCastleVO> initAllCastle();

    List<CmPackageVO> initAllPackage();
}
