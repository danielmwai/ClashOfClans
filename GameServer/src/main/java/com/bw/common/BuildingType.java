package com.bw.common;

/**
 * @author denny_zhao 建筑类型
 */
public enum BuildingType {
    //	0 army  军队
    //	1 town hall 大厅房子
    //	2 resource 资源
    //	3 defense  防御
    //	4 wall  墙
    //	5 worker  工人
    //	6 npc 
    ARMY(0), TOWN_HALL(1), RESOURCE(2), DEFENSE(3),
    WALL(4), WORKER(5), NPC(6);
    private int param;

    private BuildingType(int codeNum) {
        param = codeNum;
    }

    public int value() {
        return param;
    }

    public static BuildingType valueOf(int value) {
        switch (value) {
            case 0:
                return ARMY;//军队
            case 1:
                return TOWN_HALL;//大厅房子
            case 2:
                return RESOURCE;//资源
            case 3:
                return DEFENSE;//防御
            case 4:
                return WALL;//墙
            case 5:
                return WORKER;//工人
            case 6:
                return NPC;//NPC
            default:
                throw new IllegalArgumentException("no matched.");
        }
    }

}
