package com.bw.application.resourceManager;

import com.bw.baseJar.vo.BwArgsVO;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwCharactersPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwExchangeVO;
import com.bw.baseJar.vo.BwInitUserVO;
import com.bw.baseJar.vo.BwQuickenVO;
import com.bw.baseJar.vo.BwUserLevelReqVO;

import java.util.List;

/**
 * @author zhouyou
 */
public class ResManagerImpl implements ResManager {

    @Override
    public void reload() {
        ResGlobal.getInstance().init();
    }

    @Override
    public boolean updateConfig(Object configObj) {
        boolean tag = false;

        if (configObj instanceof BwBuildingVO) {
            BwBuildingVO vo = (BwBuildingVO) configObj;
            ResGlobal.getInstance().bwBuildingVOMap.put(vo.getBuildingId(), vo);
            tag = true;
        } else if (configObj instanceof BwBuildingPropertiesLevelVO) {
            BwBuildingPropertiesLevelVO vo = (BwBuildingPropertiesLevelVO) configObj;
            ResGlobal.getInstance().bwBuildingPropertiesLevelVOMap.put(vo.getBuildingid() + "_" + vo.getBuildlevel(), vo);
            tag = true;
        } else if (configObj instanceof BwCharactersVO) {
            BwCharactersVO vo = (BwCharactersVO) configObj;
            ResGlobal.getInstance().bwCharactersVOMap.put(vo.getCharacterid(), vo);
            tag = true;
        } else if (configObj instanceof BwCharactersPropertiesLevelVO) {
            BwCharactersPropertiesLevelVO vo = (BwCharactersPropertiesLevelVO) configObj;
            ResGlobal.getInstance().bwCharactersPropertiesLevelVOMap.put(vo.getCharacterid() + "_" + vo.getCharacterlevel(), vo);
            tag = true;
        } else if (configObj instanceof BwArgsVO) {
            ResGlobal.getInstance().bwArgsVO = (BwArgsVO) configObj;
            tag = true;
        } else if (configObj instanceof BwUserLevelReqVO) {
            BwUserLevelReqVO vo = (BwUserLevelReqVO) configObj;
            ResGlobal.getInstance().bwUserLevelReqVOMap.put(vo.getLevelid(), vo);
            tag = true;
        } else if (configObj instanceof BwExchangeVO) {
            BwExchangeVO vo = (BwExchangeVO) configObj;
            List<BwExchangeVO> exchanges = ResGlobal.getInstance().bwExchanges;
            for (int i = 0; i < exchanges.size(); i++) {
                if (exchanges.get(i).getCount() == vo.getCount()) {
                    exchanges.set(i, vo);
                    break;
                }
            }
            tag = true;
        } else if (configObj instanceof BwQuickenVO) {
            // TODO - zhYou: ResGlobal中没有使用到该配置?
        } else if (configObj instanceof BwInitUserVO) {
            ResGlobal.getInstance().bwInitUserVO = (BwInitUserVO) configObj;
            tag = true;
        }
        return tag;
    }

}
