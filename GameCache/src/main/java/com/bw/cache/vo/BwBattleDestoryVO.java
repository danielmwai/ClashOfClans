package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @author Administrator 战斗摧毁情况vo
 */
public class BwBattleDestoryVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5781457348255525616L;
    public int building_id;
    public int uuid;
    public int map_index_x;
    public int map_index_y;

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int buildingId) {
        building_id = buildingId;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public int getMap_index_x() {
        return map_index_x;
    }

    public void setMap_index_x(int mapIndexX) {
        map_index_x = mapIndexX;
    }

    public int getMap_index_y() {
        return map_index_y;
    }

    public void setMap_index_y(int mapIndexY) {
        map_index_y = mapIndexY;
    }

}
