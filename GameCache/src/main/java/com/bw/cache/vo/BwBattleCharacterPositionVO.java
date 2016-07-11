package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 战斗中兵的位置
 */
public class BwBattleCharacterPositionVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5829046893165435005L;
    /**
     * battle_id
     *
     *
     */
    private long battleId;
    /**
     * character_id
     *
     *
     */
    private int characterId;
    /**
     * count
     *
     * 士兵数量
     */
    private int count;
    /**
     * id
     *
     * 战斗位置表
     */
    private long id;
    /**
     * level
     *
     * 士兵等级
     */
    private int level;
    /**
     * map_index
     *
     * 士兵所在的格子
     */
    private int mapIndex;

    public long getBattleid() {
        return battleId;
    }

    public void setBattleid(long battleId) {
        this.battleId = battleId;
    }

    public int getCharacterid() {
        return characterId;
    }

    public void setCharacterid(int characterId) {
        this.characterId = characterId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMapindex() {
        return mapIndex;
    }

    public void setMapindex(int mapIndex) {
        this.mapIndex = mapIndex;
    }
}
