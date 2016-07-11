package com.bw.baseJar.errorCode;

public interface ErrorCodeInterface {

    // 成功
    public static final int SUCESS = 0;
    // 失败
    public static final int ERROR = 1;
    // 所有服务器停服维护
    public static final int SERVER_MAINTENACE = 2;
    // 邮箱地址已经存在
    public static final int MAIL_ADDRESS_EXIST = 3;
    // 游戏数据已经迁移，无法通过该设备连接
    public static final int GAME_DATA_MIGRATED = 4;
    // 该用户不存在请重新注册
    public static final int EXIST_NOT_FOR_USER = 5;
    // 6 overfflow_friends_count 超出好友最大数量限制
    public static final int OVERFFLOW_FRIENDS_COUNT = 6;
    // 7 EXIST_THIS_FRIENDS 已经存在改好友
    public static final int EXIST_THIS_FRIENDS = 7;
    // 8 NO_result_for_request 查无结果
    public static final int NO_RESULT_FOR_REQUEST = 8;
    // 9 NO_ENOUGH_GOLDEN 没有充足的金币
    public static final int NO_ENOUGH_GOLDEN = 9;
    // 10 NO_ENOUGH_ELIXIR 没有充足的药水
    public static final int NO_ENOUGH_ELIXIR = 10;
    // 此建筑正在升级
    public static final int BUILDING_UPGRADING = 11;
    // 没有空闲的农民
    public static final int NO_HAVE_FREE_WORKER = 12;
    // 大厅等级不够
    public static final int NO_ENOUGH_LEVEL_OF_HALL = 13;
    // 实验室等级不够
    public static final int NO_ENOUGH_LEVEL_OF_LIBORATORY = 14;
    // 没有找到匹配的玩家
    public static final int NO_MATCHING_USER = 15;
    /**
     * 玩家在线,或者战斗状态 无法复仇
     */
    public static final int USER_IS_ONLINE_NOT_REVENGE = 16;
    /**
     * 用户正在保护期
     */
    public static final int USER_IS_IN_SHIELD_TIME = 17;
    /**
     * 兵营等级不够
     */
    public static final int NO_ENOUGH_LEVEL_OF_BARRACK = 18;
    /**
     * 魔法工厂等级不够
     */
    public static final int NO_ENOUGH_LEVEL_OF_SPELL_FACTORY = 19;
    /**
     * 用户名或者密码不对
     */
    public static final int USER_NAME_OR_PASSWORD_NO_RIGHT = 20;
    /**
     * 没有足够的绿宝石可消费
     */
    public static final int NO_ENOUGH_GEM_CONSUME = 21;
    /**
     * 营地没有足够人口位置
     */
    public static final int NO_ENOUGH_HOUSE_SPACE = 22;
    /**
     * 魔法工厂没有足够的空闲位置造魔法
     */
    public static final int NO_ENOUGH_PELL_Space_OF_FACTORY = 23;
    /**
     * 未发现购买的资源
     */
    public static final int BUY_RESOURCE_NOT_FOUND = 24;
    /**
     * 未发现该建筑
     */
    public static final int BUILDING_NOT_FOUND = 25;
    /**
     * 未发现该建筑类型
     */
    public static final int BUILDING_TYPE_NOT_FOUND = 26;
    /**
     * 建筑建造/升级未完成
     */
    public static final int BUILDING_UNFINISHED = 27;
    /**
     * 建筑已经满级
     */
    public static final int FULL_LEVEL = 28;
    /**
     * 数据库中未发现兵种
     */
    public static final int CHARACTER_NOT_FOUND = 29;
    /**
     * 未发现该技能
     */
    public static final int SPELL_NOT_FOUND = 30;
    /**
     * 容量已满
     */
    public static final int CAPACITY_FULL = 31;
    /**
     * 拒绝登陆
     */
    public static final int DENY_LOGIN = 32;
    /**
     * 玩家正在战斗
     */
    public static final int USER_IS_BATTLEING = 33;
    /**
     * 无法找到匹配的文件
     */
    public static final int NOT_FIND_REPLY_FILE = 34;
    /**
     * 未发现用户技能
     */
    public static final int USER_SPELL_NOT_FOUND = 35;
    /**
     * 未发现用户士兵
     */
    public static final int USER_CHARACTER_NOT_FOUND = 36;
    /**
     * 该建筑/士兵/魔法已经完成, 加速失败
     */
    public static final int ALREADY_COMPLETE = 37;
    /**
     * 未发现该用户
     */
    public static final int USER_NOT_FOUND = 38;
    /**
     * 操作类型错误
     */
    public static final int OPERATE_TYPE_ERROR = 39;
    /**
     * 加速类型错误
     */
    public static final int ACCELERATE_TYPE_ERROR = 40;
    /**
     * 造兵已经完成
     */
    public static final int PRODUCE_CHARACTER_FINISHED = 41;
    /**
     * 军营不存在
     */
    public static final int BARRACK_NOT_FOUND = 42;
    /**
     * 消费类型错误
     */
    public static final int COST_TYPE_ERROR = 43;
    /**
     * 未发现用户银行数据
     */
    public static final int BANK_NOT_FOUND = 44;
    /**
     * 建筑已经存在
     */
    public static int BUILDING_EXISTS = 45;
    /**
     * 已经是最大等级
     */
    public static final int ALREADY_MAX_LEVEL = 46;
    /**
     * 兵营建造队列已满
     */
    public static final int BARRACK_PRODUCE_QUEUE_FULL = 47;
    /**
     * 没有足够的士兵
     */
    public static final int NO_ENOUGH_SOLDIER = 48;
    /**
     * 建筑类型错误
     */
    public static final int BUILDING_TYPE_ERROR = 49;
    /**
     * 没有发现收集器
     */
    int COLLECTOR_NOT_FOUND = 50;
    //该用户已经存在 
    public static final int USER_IS_EXIST = 51;
    //本服务器停服维护
    public static final int SERVER_MAINTENACE_PART = 52;
    //服务器已满请稍后重试
    public static final int SERVER_IS_OVER_FLOW = 53;

}
