package com.bw.log;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户操作类型
 *
 * @author zhYou
 */
public enum Action {
    ACCELERATE(1, "加速"),
    BUG_RESOURCE(2, "购买资源");

    private int id; // 编号
    private String desc; // 描述

    private Action(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    /**
     * @return 返回枚举编号
     */
    public int getId() {
        return id;
    }

    /**
     * @return 返回该枚举的描述
     */
    public String getDesc() {
        return desc;
    }

    private static final Map<Integer, Action> actionMap = new HashMap<Integer, Action>();

    static {
        for (Action act : Action.values()) {
            actionMap.put(act.getId(), act);
        }
    }

    /**
     * 根据定义的id获得Action实例
     *
     * @param id action中定义的id
     * @return action实例
     */
    public static final Action valueOf(Integer id) {
        if (!actionMap.containsKey(id)) {
            throw new IllegalArgumentException("action not found: " + id);
        }
        return actionMap.get(id);
    }
}
