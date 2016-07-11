package com.bw.baseJar.common;

public interface CommonGameData {

    /**
     * 大厅的UUID
     */
    public final static int TOWN_HALL_UUID_COMMON_GAME_DATA = 1;
    /**
     * 大厅的ID
     */
    public final static int TOWN_HALL_ID_COMMON_GAME_DATA = 100;
    //战斗列表存储的最大数量
    public final static int BATTLE_LIST_MAX_COUNT = 49;
    //金币storage的buildID
    public final static int GAME_SAVE_GOLD_STORAGE = 203;
    //药水的storage的buildID
    public final static int GAME_SAVE_SLIXIR_STORAGE = 201;
    //Laboratory ID
    public final static int LABORATORY_ID_COMMON_GAME_DATA = 3;
    //兵营ID
    public final static int BARRACK_ID_COMMON_GAME_DATA = 2;
    //营地ID
    public final static int TROOP_HOUSING_COMMON_GAME_DATA = 1;       //mysql truncate
    //魔法工厂
    public final static int SPELL_FACTORY_ID_COMMON_GAME_DATA = 5;

    /**
     * 金币仓库
     */
    public final static int COMMON_GAME_DATA_SAVE_GOLD_STORAGE_ID = 203;
    /**
     * 药水仓库3
     */
    public final static int COMMON_GAME_DATA_SAVE_ELIXIR_STORAGE_ID = 201;
    //200名以外 pvp分数排行 过期时间设置 以分钟为单位
    public final static int EXP_TIME_OF_5 = 5;
    /**
     * 消耗类型: 0,金币; 1, 为药水;
     */
    public final static int COMMON_GAME_DATA_CONSUME_TYPE_ELIXIR_ID = 0;
    /**
     * 消耗类型: 0,金币; 1, 为药水;
     */
    public final static int COMMON_GAME_DATA_CONSUME_TYPE_GOLD_ID = 1;
}
