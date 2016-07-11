function createGridPanel(tabId) {
    var tar;
    switch (tabId) {
        case 1001:
            tar = create1001();
            break;
        case 1002:
            tar = create1002();
            break;
        case 2101:
            tar = create2101();
            break;
        case 2102:
            tar = create2102();
            break;
        case 2103:
            tar = create2103();
            break;
        case 2104:
            tar = create2104();
            break;
        case 2105:
            tar = create2105();
            break;
        case 2106:
            tar = create2106();
            break;
        case 2107:
            tar = create2107();
            break;
        case 2108:
            tar = create2108();
            break;
        case 2109:
            tar = create2109();
            break;
        case 2110:
            tar = create2110();
            break;
        case 2201:
            tar = create2201();
            break;
        case 2202:
            tar = create2202();
            break;
        case 2203:
            tar = create2203();
            break;
        case 2204:
            tar = create2204();
            break;
    }
    return tar;
}


function create1001() {
    var grid = new Ext.grid.GridPanel({
        id: 'tab1001',
        title: 'Platform - User'
    });
    return grid;
}
function create1002() {
    var grid = new Ext.grid.GridPanel({
        id: 'tab1002',
        title: 'Platform - authority'
    });
    return grid;
}
function create2101() {
    var cm = new Ext.grid.ColumnModel({defaults: {sortable: true}, columns: [
            {header: '编号', dataIndex: 'buildingId'},
            {header: '建筑名称', dataIndex: 'buildingName', editor: new Ext.form.TextField()},
            {header: '对空攻击', dataIndex: 'airTargets', editor: new Ext.form.NumberField()},
            {header: '攻击范围', dataIndex: 'attackRange', editor: new Ext.form.NumberField()},
            {header: '攻击速度', dataIndex: 'attackSpeed', editor: new Ext.form.NumberField()},
            {header: '建筑消耗资源类型', dataIndex: 'buildResourceType', editor: new Ext.form.NumberField()},
            {header: '建筑时间-日期', dataIndex: 'buildTimeDate', editor: new Ext.form.NumberField()},
            {header: '建筑时间-小时', dataIndex: 'buildTimeHour', editor: new Ext.form.NumberField()},
            {header: '建筑时间-分钟', dataIndex: 'buildTimeMinutes', editor: new Ext.form.NumberField()},
            {header: '建筑的碰撞范围 - 高', dataIndex: 'buildingCrashH', editor: new Ext.form.NumberField()},
            {header: '建筑的碰撞范围 - 宽', dataIndex: 'buildingCrashW', editor: new Ext.form.NumberField()},
            {header: '建筑类型', dataIndex: 'buildingType', editor: new Ext.form.NumberField()},
            {header: '溅射范围', dataIndex: 'damageRadius', editor: new Ext.form.NumberField()},
            {header: '对地攻击', dataIndex: 'groundTargets', editor: new Ext.form.NumberField()},
            {header: '占地范围高', dataIndex: 'height', editor: new Ext.form.NumberField()},
            {header: '是否是堡垒', dataIndex: 'isBunker', editor: new Ext.form.NumberField()},
            {header: '是否需要隐身', dataIndex: 'isHidden', editor: new Ext.form.NumberField()},
            {header: '是否需要解锁', dataIndex: 'isLocked', editor: new Ext.form.NumberField()},
            {header: '建筑是否可以出售', dataIndex: 'isSell', editor: new Ext.form.NumberField()},
            {header: '最小攻击范围', dataIndex: 'minAttackRange', editor: new Ext.form.NumberField()},
            {header: '生成资源的种类', dataIndex: 'producesResourceType', editor: new Ext.form.NumberField()},
            {header: '攻击附带击退效果', dataIndex: 'pushBack', editor: new Ext.form.NumberField()},
            {header: '触发范围', dataIndex: 'triggerRadius', editor: new Ext.form.NumberField()},
            {header: '是否可以升级士兵', dataIndex: 'upgradeUnits', editor: new Ext.form.NumberField()},
            {header: '占地范围宽', dataIndex: 'width', editor: new Ext.form.NumberField()},
            {header: '建造中动画', dataIndex: 'buildingAnimation', editor: new Ext.form.TextField()},
            {header: '建筑动画', dataIndex: 'swf', editor: new Ext.form.TextField()}
        ]});

    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'buildingId'
    }, [
        {name: 'buildingId'},
        {name: 'airTargets'},
        {name: 'attackRange'},
        {name: 'attackSpeed'},
        {name: 'buildResourceType'},
        {name: 'buildTimeDate'},
        {name: 'buildTimeHour'},
        {name: 'buildTimeMinutes'},
        {name: 'buildingAnimation'},
        {name: 'buildingCrashH'},
        {name: 'buildingCrashW'},
        {name: 'buildingName'},
        {name: 'buildingType'},
        {name: 'damageRadius'},
        {name: 'groundTargets'},
        {name: 'height'},
        {name: 'isBunker'},
        {name: 'isHidden'},
        {name: 'isLocked'},
        {name: 'isSell'},
        {name: 'minAttackRange'},
        {name: 'producesResourceType'},
        {name: 'pushBack'},
        {name: 'swf'},
        {name: 'triggerRadius'},
        {name: 'upgradeUnits'},
        {name: 'width'}
    ]);

    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/building/all'
    });

    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2101',
        title: '配置 - 建筑信息',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/building/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });

    return grid;
}
function create2102() {
    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/buildingpropertieslevel/all'
    });
    var cm = new Ext.grid.ColumnModel({defaults: {sortable: true}, columns: [
            {header: '编号', dataIndex: 'id'},
            {header: '建造消耗的资源数量', dataIndex: 'buildcostcount', editor: new Ext.form.NumberField()},
            {header: '建筑升级对应的图片', dataIndex: 'buildexportname', editor: new Ext.form.TextField()},
            {header: '建筑等级', dataIndex: 'buildlevel', editor: new Ext.form.NumberField()},
            {header: '建筑时间-日期', dataIndex: 'buildtimedate', editor: new Ext.form.NumberField()},
            {header: '建筑时间-小时', dataIndex: 'buildtimehour', editor: new Ext.form.NumberField()},
            {header: '建筑时间-分钟', dataIndex: 'buildtimeminutes', editor: new Ext.form.NumberField()},
            {header: '建筑ID', dataIndex: 'buildingid', editor: new Ext.form.NumberField()},
            {header: '防御塔上站着的士兵id', dataIndex: 'bwcharacterspropertieslevelid', editor: new Ext.form.NumberField()},
            {header: '防御塔上站得士兵数量', dataIndex: 'characterscount', editor: new Ext.form.TextField()},
            {header: '攻击力', dataIndex: 'damage', editor: new Ext.form.NumberField()},
            {header: '破坏后获取的经验', dataIndex: 'destructionxp', editor: new Ext.form.NumberField()},
            {header: '电磁塔触发器名称', dataIndex: 'exportnametriggered', editor: new Ext.form.TextField()},
            {header: '生命值', dataIndex: 'hitpoint', editor: new Ext.form.NumberField()},
            {header: '提供存兵的人口数', dataIndex: 'houseingspace', editor: new Ext.form.NumberField()},
            {header: '存储药水上限', dataIndex: 'maxstoredelixir', editor: new Ext.form.NumberField()},
            {header: '存储金钱上线', dataIndex: 'maxstoredgold', editor: new Ext.form.NumberField()},
            {header: '每小时生成资源的数量', dataIndex: 'produceresourceperhour', editor: new Ext.form.NumberField()},
            {header: '对应的子弹类型', dataIndex: 'projectilename', editor: new Ext.form.TextField()},
            {header: '被拆除后恢复需要的时间', dataIndex: 'regentime', editor: new Ext.form.NumberField()},
            {header: '生产后存储资源的上限', dataIndex: 'resourcemax', editor: new Ext.form.NumberField()},
            {header: '允许制造该建筑的主基地等级', dataIndex: 'townhalllevel', editor: new Ext.form.NumberField()},
            {header: '兵营中最多可以放多少人口', dataIndex: 'unitproduction', editor: new Ext.form.NumberField()}
        ]});
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'id'
    }, [
        {name: 'buildcostcount'},
        {name: 'buildexportname'},
        {name: 'buildlevel'},
        {name: 'buildtimedate'},
        {name: 'buildtimehour'},
        {name: 'buildtimeminutes'},
        {name: 'buildingid'},
        {name: 'bwcharacterspropertieslevelid'},
        {name: 'characterscount'},
        {name: 'damage'},
        {name: 'destructionxp'},
        {name: 'exportnametriggered'},
        {name: 'hitpoint'},
        {name: 'houseingspace'},
        {name: 'id'},
        {name: 'maxstoredelixir'},
        {name: 'maxstoredgold'},
        {name: 'produceresourceperhour'},
        {name: 'projectilename'},
        {name: 'regentime'},
        {name: 'resourcemax'},
        {name: 'townhalllevel'},
        {name: 'unitproduction'}
    ]);
    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2102',
        title: '配置 - 建筑等级',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/buildingproperties/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });
    return grid;
}
function create2103() {
    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/character/all'
    });
    var cm = new Ext.grid.ColumnModel({defaults: {sortable: true}, columns: [
            {header: '升级消耗资源的类型', dataIndex: 'upgraderesource', editor: new Ext.form.NumberField()},
            {header: '是否可以对空中单位进行攻击', dataIndex: 'airtarget', editor: new Ext.form.NumberField()},
            {header: '攻击几次后死亡', dataIndex: 'attackcount', editor: new Ext.form.NumberField()},
            {header: '优先攻击的目标', dataIndex: 'attackpreferedtarget', editor: new Ext.form.NumberField()},
            {header: '攻击范围', dataIndex: 'attackrang', editor: new Ext.form.NumberField()},
            {header: '攻击间隔', dataIndex: 'attackspeed', editor: new Ext.form.NumberField()},
            {header: '解锁兵种需要的兵营等级', dataIndex: 'barracklevel', editor: new Ext.form.NumberField()},
            {header: '对应大尺寸图片', dataIndex: 'bigpicture', editor: new Ext.form.TextField()},
            {header: '角色ID', dataIndex: 'characterid', editor: new Ext.form.NumberField()},
            {header: '角色名称', dataIndex: 'charactername', editor: new Ext.form.TextField()},
            {header: '对特定目标的攻击倍率', dataIndex: 'damagemod', editor: new Ext.form.NumberField()},
            {header: '兵放入地图时的特效', dataIndex: 'deployeffect', editor: new Ext.form.TextField()},
            {header: '死亡效果', dataIndex: 'dieeffect', editor: new Ext.form.TextField()},
            {header: '是否可以攻击地面单位', dataIndex: 'groundtargets', editor: new Ext.form.NumberField()},
            {header: '所占人口', dataIndex: 'housingspace', editor: new Ext.form.NumberField()},
            {header: '对应的图标名称（造兵时显示的）', dataIndex: 'iconname', editor: new Ext.form.TextField()},
            {header: '是否可飞行', dataIndex: 'isflying', editor: new Ext.form.NumberField()},
            {header: '造兵需要消耗的资源类型', dataIndex: 'resourcetype', editor: new Ext.form.NumberField()},
            {header: '移动速度', dataIndex: 'speed', editor: new Ext.form.NumberField()},
            {header: '动画文件名称', dataIndex: 'swf', editor: new Ext.form.TextField()},
            {header: '造兵需要的时间', dataIndex: 'trainingtime', editor: new Ext.form.NumberField()},
            {header: '对应UI图标', dataIndex: 'uiname', editor: new Ext.form.TextField()}
        ]});
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'characterid'
    }, [
        {name: 'characterid'},
        {name: 'upgraderesource'},
        {name: 'airtarget'},
        {name: 'attackcount'},
        {name: 'attackpreferedtarget'},
        {name: 'attackrang'},
        {name: 'attackspeed'},
        {name: 'barracklevel'},
        {name: 'bigpicture'},
        {name: 'charactername'},
        {name: 'damagemod'},
        {name: 'deployeffect'},
        {name: 'dieeffect'},
        {name: 'groundtargets'},
        {name: 'housingspace'},
        {name: 'iconname'},
        {name: 'isflying'},
        {name: 'resourcetype'},
        {name: 'speed'},
        {name: 'swf'},
        {name: 'trainingtime'},
        {name: 'uiname'}
    ]);
    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2103',
        title: '配置 - 角色信息',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/character/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });
    return grid;
}
function create2104() {
    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/characterpropertieslevel/all'
    });
    var cm = new Ext.grid.ColumnModel({defaults: {sortable: true}, columns: [
            {header: '编号', dataIndex: 'id', editor: new Ext.form.NumberField()},
            {header: '动画名称', dataIndex: 'animation', editor: new Ext.form.TextField()},
            {header: '士兵攻击特效', dataIndex: 'attackeffect', editor: new Ext.form.TextField()},
            {header: '角色ID', dataIndex: 'characterid', editor: new Ext.form.NumberField()},
            {header: '角色等级', dataIndex: 'characterlevel', editor: new Ext.form.NumberField()},
            {header: '攻击力', dataIndex: 'damage', editor: new Ext.form.NumberField()},
            {header: '攻击的溅射范围', dataIndex: 'damageradius', editor: new Ext.form.NumberField()},
            {header: '生命值', dataIndex: 'hitpoints', editor: new Ext.form.NumberField()},
            {header: '被攻击效果', dataIndex: 'hitedeffect', editor: new Ext.form.TextField()},
            {header: '升级需要的科技建筑等级', dataIndex: 'laboratorylevel', editor: new Ext.form.NumberField()},
            {header: '子弹名称', dataIndex: 'projectilename', editor: new Ext.form.TextField()},
            {header: '消耗的资源数量', dataIndex: 'trainingresourcecost', editor: new Ext.form.NumberField()},
            {header: '升级需要消耗的资源数量', dataIndex: 'upgraderesourcecose', editor: new Ext.form.NumberField()},
            {header: '升级需要的时间', dataIndex: 'upgradetime', editor: new Ext.form.NumberField()}
        ]});
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'id'
    }, [
        {name: 'animation'},
        {name: 'attackeffect'},
        {name: 'characterid'},
        {name: 'characterlevel'},
        {name: 'damage'},
        {name: 'damageradius'},
        {name: 'hitpoints'},
        {name: 'hitedeffect'},
        {name: 'id'},
        {name: 'laboratorylevel'},
        {name: 'projectilename'},
        {name: 'trainingresourcecost'},
        {name: 'upgraderesourcecose'},
        {name: 'upgradetime'}
    ]);
    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();
    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2104',
        title: '配置 - 角色等级',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/characterpropertieslevel/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });
    return grid;
}

function create2201() {
    var cm = new Ext.grid.ColumnModel({defaults: {sortable: true}, columns: [
            {header: '邮件地址', dataIndex: 'mailaddress'},
            {header: '主键自动增长ID', dataIndex: 'id', editor: new Ext.form.NumberField()},
            {header: '药水数量', dataIndex: 'elixircount', editor: new Ext.form.NumberField()},
            {header: '经验', dataIndex: 'exp', editor: new Ext.form.NumberField()},
            {header: '金币数量', dataIndex: 'goldencount', editor: new Ext.form.NumberField()},
            {header: '最好一次登录时间', dataIndex: 'lastlogintime', editor: new Ext.form.TextField()},
            {header: '用户等级', dataIndex: 'level', editor: new Ext.form.NumberField()},
            {header: '用户昵称', dataIndex: 'nickname', editor: new Ext.form.TextField()},
            {header: ' pvp分数', dataIndex: 'pvpmark', editor: new Ext.form.NumberField()},
            {header: '保护结束时间', dataIndex: 'shieldEndtime', editor: new Ext.form.TextField()},
            {header: '战斗状态', dataIndex: 'battleStatus', editor: new Ext.form.NumberField()},
            {header: '农民数量', dataIndex: 'workCount', editor: new Ext.form.NumberField()},
            {header: 'mac地址', dataIndex: 'macAddress', editor: new Ext.form.TextField()},
            {header: '最大金币数量', dataIndex: 'maxGoldenCount', editor: new Ext.form.NumberField()},
            {header: '最大药水数量', dataIndex: 'maxElixirCount', editor: new Ext.form.NumberField()}
        ]});
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'mailaddress'
    }, [
        {name: 'mailaddress'},
        {name: 'elixircount'},
        {name: 'exp'},
        {name: 'goldencount'},
        {name: 'id'},
        {name: 'lastlogintime'},
        {name: 'level'},
        {name: 'nickname'},
        {name: 'pvpmark'},
        {name: 'shieldEndtime'},
        {name: 'battleStatus'},
        {name: 'workCount'},
        {name: 'macAddress'},
        {name: 'maxGoldenCount'},
        {name: 'maxElixirCount'}
    ]);
    var store = new Ext.data.Store({
        proxy: new Ext.data.MemoryProxy(),
        reader: reader
    });

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2201',
        title: '数据 - 角色',
        layout: 'border',
        cm: cm,
        store: store,
        tbar: [
            {id: 'txtEmailAddress', xtype: 'textfield', blankText: '请输入登录邮箱', allowBlank: false},
            {
                xtype: 'button',
                text: '查询',
                listeners: {
                    'click': function () {
                        var emailAddress = Ext.getCmp('txtEmailAddress').getValue();
                        var obj = {mailaddress: emailAddress};

                        Ext.Ajax.request({
                            url: '/looters/obj/user/getByMail',
                            headers: {'Content-Type': 'application/json'},
                            jsonData: obj,
                            callback: function (options, success, response) {
                                if (success) {
                                    var jsonResp = Ext.decode(response.responseText);
                                    store.loadData(jsonResp);
                                    grid.refresh();
                                } else {
                                    Ext.Msg.alert("提示", "没有发现该玩家!");
                                }
                            }
                        });
                    }
                }
            }
        ],
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/obj/user/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });
    return grid;
}

function create2105() {
    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/gamechannel/all'
    });
    var cm = new Ext.grid.ColumnModel({
        columns: [
            {header: '编号', dataIndex: 'id'},
            {header: '分区编号', dataIndex: 'areaId'},
            {header: '分区名称', dataIndex: 'channlename'},
            {header: '状态', dataIndex: 'status', editor: new Ext.form.NumberField()},
            {header: '最大用户数', dataIndex: 'maxusercount', editor: new Ext.form.NumberField()},
            {header: '游戏服务器地址', dataIndex: 'address', editor: new Ext.form.TextField()},
            {header: 'RMI地址', dataIndex: 'serviceurl', editor: new Ext.form.TextField()},
            {header: 'RMI接口', dataIndex: 'serviceinterface', editor: new Ext.form.TextField()}
        ]
    });
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'id'
    }, [
        {name: 'id'},
        {name: 'address'},
        {name: 'status'},
        {name: 'channlename'},
        {name: 'serviceurl'},
        {name: 'serviceinterface'},
        {name: 'maxusercount'},
        {name: 'areaId'}
    ]);
    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2105',
        title: '配置 - 服务器',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/gamechannel/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });

    store.load();
    return grid;
}

function create2106() {
    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/args/all'
    });
    var cm = new Ext.grid.ColumnModel({
        columns: [
            {header: '编号', dataIndex: 'id'},
            {header: '获取pvp分数的最大K', dataIndex: 'pvpMaxK', editor: new Ext.form.NumberField()},
            {header: '获取pvp分数的最大N', dataIndex: 'pvpN', editor: new Ext.form.NumberField()}
        ]
    });
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'id'
    }, [
        {name: 'id'},
        {name: 'pvpMaxK'},
        {name: 'pvpN'}
    ]);
    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2106',
        title: '配置 - 服务器常数',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/args/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });
    return grid;
}

function create2107() {
    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/inituser/all'
    });
    var cm = new Ext.grid.ColumnModel({
        columns: [
            {header: '编号', dataIndex: 'id'},
            {header: '金币', dataIndex: 'goldenCount', editor: new Ext.form.NumberField()},
            {header: '药水', dataIndex: 'elixirCount', editor: new Ext.form.NumberField()},
            {header: '宝石', dataIndex: 'gemCount', editor: new Ext.form.NumberField()},
            {header: '经验', dataIndex: 'exp', editor: new Ext.form.NumberField()},
            {header: '加速一分钟消耗宝石', dataIndex: 'oneMinuteCost', editor: new Ext.form.NumberField()},
            {header: '加速一小时消耗宝石', dataIndex: 'oneHourCost', editor: new Ext.form.NumberField()},
            {header: '加速一天消耗宝石', dataIndex: 'oneDayCost', editor: new Ext.form.NumberField()},
            {header: '加速一周消耗宝石', dataIndex: 'oneWeekCost', editor: new Ext.form.NumberField()}
        ]
    });
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'id'
    }, [
        {name: 'id'},
        {name: 'goldenCount'},
        {name: 'elixirCount'},
        {name: 'gemCount'},
        {name: 'exp'},
        {name: 'oneMinuteCost'},
        {name: 'oneHourCost'},
        {name: 'oneDayCost'},
        {name: 'oneWeekCost'}
    ]);
    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2107',
        title: '配置 - 用户初始化',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/inituser/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });
    return grid;
}

function create2108() {
    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/quicken/all'
    });
    var cm = new Ext.grid.ColumnModel({
        columns: [
            {header: '编号', dataIndex: 'id', editor: new Ext.form.NumberField()},
            {header: '加速类型', dataIndex: 'quickenType', editor: new Ext.form.NumberField()},
            {header: '加速时间', dataIndex: 'quickenTime', editor: new Ext.form.NumberField()},
            {header: '价格', dataIndex: 'price', editor: new Ext.form.NumberField()}
        ]
    });
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'id'
    }, [
        {name: 'id'},
        {name: 'quickenType'},
        {name: 'quickenTime'},
        {name: 'price'}
    ]);
    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2108',
        title: '配置 - 加速消费',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/quicken/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });
    return grid;
}

function create2109() {
    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/exchange/all'
    });
    var cm = new Ext.grid.ColumnModel({
        columns: [
            {header: '数量', dataIndex: 'count'},
            {header: '宝石数量', dataIndex: 'gem', editor: new Ext.form.NumberField()}
        ]
    });
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'count'
    }, [
        {name: 'count'},
        {name: 'gem'}
    ]);
    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2109',
        title: '配置 - 商店兑换',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/exchange/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });
    return grid;
}

function create2110() {
    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/userlevel/all'
    });
    var cm = new Ext.grid.ColumnModel({
        columns: [
            {header: '等级', dataIndex: 'levelid'},
            {header: '经验', dataIndex: 'expreq', editor: new Ext.form.NumberField()}
        ]
    });
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'levelId'
    }, [
        {name: 'levelid'},
        {name: 'expreq'}
    ]);
    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2110',
        title: '配置 - 角色等级',
        layout: 'border',
        cm: cm,
        store: store,
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    Ext.Ajax.request({
                        url: '/looters/cfg/userlevel/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: e.record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });
    return grid;
}

function create2202() {
    var Blacklist = Ext.data.Record.create([
        {name: 'id', type: 'long'},
        {name: 'boweiId', type: 'long'},
        {name: 'operator', type: 'string'},
        {name: 'startTime', type: 'date'},
        {name: 'endTime', type: 'date'},
        {name: 'reason', type: 'string'}
    ]);
    var editor = new Ext.ux.grid.RowEditor({
        saveText: '保存',
        cancelText: '取消',
        clicksToEdit: 2,
        listeners: {
            'afteredit': function (rowEditor, changes, record, rowIdx) {
                if (record.dirty) {
                    var data = record.data;
                    data.startTime = new Date(data.startTime).getTime();
                    data.endTime = new Date(data.endTime).getTime();
                    Ext.Ajax.request({
                        url: '/looters/obj/blacklist/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: record.data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                var jsonResp = Ext.decode(response.responseText);
                                record.data.id = jsonResp.data.id;
                                record.dirty = false;
                                record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }

            },
            'canceledit': function (e) {
                e.grid.getStore().removeAll();
            }
        }
    });
    var cm = new Ext.grid.ColumnModel({
        columns: [
            {header: '编号', dataIndex: 'id'},
            {header: '博为编号', dataIndex: 'boweiId', editor: new Ext.form.TextField()},
            {header: '操作人', dataIndex: 'operator', editor: new Ext.form.TextField()},
            {header: '开始时间', dataIndex: 'startTime', editor: new Ext.form.DateField({format: 'Y-m-d H:i:s', width: 100}), renderer: function (value) {
                    return value ? new Date(value).format('Y-m-d H:i:s') : '';
                }
            },
            {header: '结束时间', dataIndex: 'endTime', editor: new Ext.form.DateField({format: 'Y-m-d H:i:s', width: 100}), renderer: function (value) {
                    return value ? new Date(value).format('Y-m-d H:i:s') : '';
                }},
            {header: '原因', dataIndex: 'reason', editor: new Ext.form.TextField()}
        ]
    });
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'id'
    }, [
        {name: 'id'},
        {name: 'boweiId'},
        {name: 'operator'},
        {name: 'startTime'},
        {name: 'endTime'},
        {name: 'reason'}
    ]);
    var store = new Ext.data.Store({
        proxy: new Ext.data.MemoryProxy(),
        reader: reader
    });
    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2202',
        title: '数据 - 黑名单',
        layout: 'border',
        cm: cm,
        store: store,
        plugins: [editor],
        tbar: [
            {id: 'txtBoweiId', xtype: 'textfield', blankText: '请输入boweiId', allowBlank: false},
            {
                xtype: 'button',
                text: '查询',
                listeners: {
                    'click': function () {
                        var boweiId = Ext.getCmp('txtBoweiId').getValue();
                        var obj = {boweiId: boweiId};
                        Ext.Ajax.request({
                            url: '/looters/obj/blacklist/get',
                            headers: {'Content-Type': 'application/json'},
                            jsonData: obj,
                            callback: function (options, success, response) {
                                if (success) {
                                    var jsonResp = Ext.decode(response.responseText);
                                    store.loadData(jsonResp);
                                    grid.refresh();
                                } else {
                                    Ext.Msg.alert("提示", "没有发现该玩家!");
                                }
                            }
                        });
                    }
                }
            },
            {
                text: '添加',
                handler: function () {
                    var bl = new Blacklist({
                        id: 0,
                        boweiId: 0,
                        operator: '',
                        startTime: new Date().getTime(),
                        endTime: new Date().getTime(),
                        reason: ''
                    });
                    store.removeAll();
                    editor.stopEditing();
                    store.insert(0, bl);
                    grid.getView().refresh();
                    grid.getSelectionModel().selectRow(0);
                    editor.startEditing(0);
                }
            },
            {
                text: '删除',
                handler: function () {
                    var grid = Ext.getCmp("tab2202");
                    var boweiId = grid.store.data.items[0].data.boweiId;
                    if (boweiId) {
                        Ext.Ajax.request({
                            url: '/looters/obj/blacklist/del',
                            headers: {'Content-Type': 'application/json'},
                            jsonData: boweiId,
                            callback: function (options, success, response) {
                                if (success) {
                                    Ext.Msg.alert("提示", "删除成功!");
                                    grid.getStore().removeAll();
                                } else {
                                    Ext.Msg.alert("提示", "没有发现该玩家!");
                                }
                            }
                        });
                    }
                }
            }
        ]
    });
    return grid;
}

function create2203() {

    var cm = new Ext.grid.ColumnModel({defaults: {sortable: true}, columns: [
            {dataIndex: 'id', header: '编号'},
            {dataIndex: 'boweiId', header: '博为编号'},
            {dataIndex: 'mailaddress', header: '邮箱'},
            {dataIndex: 'gemtotalcount', header: '宝石数量', editor: new Ext.form.NumberField()},
            {dataIndex: 'lastupdatetime', header: '最后更新时间'},
            {dataIndex: 'payTotalMoney', header: '花费RMB数量'}
        ]});
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'boweiId'
    }, [
        {name: 'id'},
        {name: 'boweiId'},
        {name: 'mailaddress'},
        {name: 'gemtotalcount'},
        {name: 'lastupdatetime'},
        {name: 'payTotalMoney'}
    ]);
    var store = new Ext.data.Store({
        proxy: new Ext.data.MemoryProxy(),
        reader: reader
    });

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2203',
        title: '数据 - 宝石',
        layout: 'border',
        cm: cm,
        store: store,
        tbar: [
            {id: 'txtBoweiId', xtype: 'textfield', blankText: '请输入博为编号', allowBlank: false},
            {
                xtype: 'button',
                text: '查询',
                listeners: {
                    'click': function () {
                        var boweiId = Ext.getCmp('txtBoweiId').getValue();
                        var obj = boweiId;

                        Ext.Ajax.request({
                            url: '/looters/obj/userbank/get',
                            headers: {'Content-Type': 'application/json'},
                            jsonData: obj,
                            callback: function (options, success, response) {
                                if (success) {
                                    var jsonResp = Ext.decode(response.responseText);
                                    store.loadData(jsonResp);
                                    grid.refresh();
                                } else {
                                    Ext.Msg.alert("提示", "没有发现该玩家!");
                                }
                            }
                        });
                    }
                }
            }
        ],
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    var data = e.record.data;
                    data.lastupdatetime = Ext.util.Format.date(new Date(), 'Y-m-d H:i:s');
                    Ext.Ajax.request({
                        url: '/looters/obj/userbank/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });

    return grid;
}

function create2204() {
    var cm = new Ext.grid.ColumnModel({defaults: {sortable: true}, columns: [
            {dataIndex: 'boweiid', header: '博为编号'},
            {dateIndex: 'mailaddress', header: '邮箱'},
            {dateIndex: 'nickname', header: '昵称'},
            {dateIndex: 'platformtype', header: '平台类型'},
            {dateIndex: 'macaddress', header: 'MAC地址'}
        ]});
    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'boweiid'
    }, [
        {name: 'boweiid'},
        {name: 'mailaddress'},
        {name: 'nickname'},
        {name: 'platformtype'},
        {name: 'macaddress'}
    ]);
    var store = new Ext.data.Store({
        proxy: new Ext.data.MemoryProxy(),
        reader: reader
    });

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab2204',
        title: '数据 - 平台',
        layout: 'border',
        cm: cm,
        store: store,
        tbar: [
            {id: 'txtBoweiId', xtype: 'textfield', blankText: '请输入博为编号', allowBlank: false},
            {
                xtype: 'button',
                text: '查询',
                listeners: {
                    'click': function () {
                        var boweiId = Ext.getCmp('txtBoweiId').getValue();
                        var obj = boweiId;

                        Ext.Ajax.request({
                            url: '/looters/obj/plantuser/get',
                            headers: {'Content-Type': 'application/json'},
                            jsonData: obj,
                            callback: function (options, success, response) {
                                if (success) {
                                    var jsonResp = Ext.decode(response.responseText);
                                    store.loadData(jsonResp);
                                    grid.refresh();
                                } else {
                                    Ext.Msg.alert("提示", "没有发现该玩家!");
                                }
                            }
                        });
                    }
                }
            },
            {
                text: '删除',
                handler: function () {
                    var grid = Ext.getCmp("tab2204");
                    var boweiId = grid.store.data.items[0].data.boweiid;
                    if (boweiId) {
                        Ext.Ajax.request({
                            url: '/looters/obj/plantuser/del',
                            headers: {'Content-Type': 'application/json'},
                            jsonData: boweiId,
                            callback: function (options, success, response) {
                                if (success) {
                                    Ext.Msg.alert("提示", "删除成功!");
                                    grid.getStore().removeAll();
                                } else {
                                    Ext.Msg.alert("提示", "没有发现该玩家!");
                                }
                            }
                        });
                    }
                }
            }
        ],
        listeners: {
            'afteredit': function (e) {
                if (e.record.dirty) {
                    var data = e.record.data;
                    Ext.Ajax.request({
                        url: '/looters/obj/plantuser/save',
                        headers: {'Content-Type': 'application/json'},
                        jsonData: data,
                        callback: function (options, success, response) {
                            if (success) {
                                Ext.Msg.alert("提示", "修改成功!");
                                e.record.dirty = false;
                                e.record.commit();
                            } else {
                                Ext.Msg.alert("提示", "服务器错误!");
                            }
                        }
                    });
                }
            }
        }
    });

    return grid;
}

Ext.onReady(function () {
    Ext.QuickTips.init();

    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    var tree = new Ext.tree.TreePanel({
        id: 'tree',
        region: 'west',
        title: '',
        split: true,
        width: 200,
        collapsible: true,
        rootVisible: false,
        lines: false,
        autoScroll: true,
        root: new Ext.tree.AsyncTreeNode({
            text: '',
            expanded: true
        }),
        loader: new Ext.tree.TreeLoader({
            dataUrl: '/ms/mainTreeNodes'
        }),
        listeners: {
            click: function (node) {
                if (node.leaf) {
                    var tabId = node.id;
                    var tarTab = Ext.getCmp('tab' + tabId);
                    if (!tarTab) {
                        tarTab = createGridPanel(tabId);
                        tabPanel.add(tarTab);
                    }
                    tabPanel.setActiveTab(tarTab);
                }
            }
        }
    });

    var tabPanel = new Ext.TabPanel({
        id: 'tabPanel',
        activeTab: 0,
        region: 'center',
        resizeTabs: true
    });

    var viewport = new Ext.Viewport({
        layout: 'border',
        items: [tree, tabPanel]
    });

    var tabMain = new Ext.Panel({
        id: 'tabMain',
        layout: 'border',
        title: 'Home'
    });

    tabPanel.add(tabMain);
    tabPanel.setActiveTab(tabMain);
});