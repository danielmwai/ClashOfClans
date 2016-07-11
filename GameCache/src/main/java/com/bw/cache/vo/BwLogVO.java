package com.bw.cache.vo;

import java.io.Serializable;

import com.bw.log.Action;

/**
 * 日志实体对象
 *
 * @author zhYou
 */
public class BwLogVO implements Serializable {

    private static final long serialVersionUID = -1667994304914588291L;
    private long roleId; // 角色编号
    private long time; // 时间
    private Action action; // 操作类型
    private String args; // 其他类型参数

    /**
     * 初始化Log对象, 时间使用当前系统时间 args则遍历params进行拼接
     *
     * @see #Log(long, long, Action, String)
     * @param roleId
     * @param action
     * @param params
     */
    public BwLogVO(long roleId, Action action, Object... params) {
        this.roleId = roleId;
        this.time = System.currentTimeMillis();
        this.action = action;

        if (params != null && params.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object param : params) {
                if (param != null) {
                    sb.append(param.toString());
                }
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            this.args = sb.toString();
        }
    }

    public BwLogVO(long roleId, long time, Action action, String args) {
        this.roleId = roleId;
        this.time = time;
        this.action = action;
        this.args = args;
    }

    public long getRoleId() {
        return roleId;
    }

    public long getTime() {
        return time;
    }

    public Action getAction() {
        return action;
    }

    public String getArgs() {
        return args;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Log [roleId=");
        builder.append(roleId);
        builder.append(", time=");
        builder.append(time);
        builder.append(", action=");
        builder.append(action);
        builder.append(", args=");
        builder.append(args);
        builder.append("]");
        return builder.toString();
    }
}
