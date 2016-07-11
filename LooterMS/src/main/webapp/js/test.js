function createGridPanel(tabId) {
    var tar;
    switch (tabId) {
        case 101:
            tar = create1001();
            break;
        case 102:
            tar = create1002();
            break;
        case 211:
            tar = create2101();
            break;
        case 212:
            tar = create2102();
            break;
        case 213:
            tar = create2103();
            break;
        case 214:
            tar = create2104();
            break;
        case 221:
            tar = create2201();
            break;
    }
    return tar;
}


function create101() {
    var grid = new Ext.grid.GridPanel({
        id: 'tab101',
        title: '平台 - 用户'
    });
    return grid;
}
function create102() {
    var grid = new Ext.grid.GridPanel({
        id: 'tab102',
        title: '平台 - 权限'
    });
    return grid;
}
function create211() {
    var cm = new Ext.grid.ColumnModel({defaults: {sortable: true}, columns: [
            {header: '编号', dataIndex: 'buildingid'},
            {header: '建筑名称', dataIndex: 'buildingname', editor: new Ext.form.TextField()},
            {header: '对空攻击', dataIndex: 'airtargets', editor: new Ext.form.NumberField()},
            {header: '攻击范围', dataIndex: 'attackrange', editor: new Ext.form.NumberField()},
            {header: '攻击速度', dataIndex: 'attackspeed', editor: new Ext.form.NumberField()},
            {header: '建筑消耗资源类型', dataIndex: 'buildresourcetype', editor: new Ext.form.NumberField()},
            {header: '建筑时间-日期', dataIndex: 'buildtimedate', editor: new Ext.form.NumberField()},
            {header: '建筑时间-小时', dataIndex: 'buildtimehour', editor: new Ext.form.NumberField()},
            {header: '建筑时间-分钟', dataIndex: 'buildtimeminutes', editor: new Ext.form.NumberField()},
            {header: '建筑的碰撞范围 - 高', dataIndex: 'buildingcrashh', editor: new Ext.form.NumberField()},
            {header: '建筑的碰撞范围 - 宽', dataIndex: 'buildingcrashw', editor: new Ext.form.NumberField()},
            {header: '建筑类型', dataIndex: 'buildingtype', editor: new Ext.form.NumberField()},
            {header: '溅射范围', dataIndex: 'damageradius', editor: new Ext.form.NumberField()},
            {header: '对地攻击', dataIndex: 'groundtargets', editor: new Ext.form.NumberField()},
            {header: '占地范围高', dataIndex: 'height', editor: new Ext.form.NumberField()},
            {header: '是否是堡垒', dataIndex: 'isbunker', editor: new Ext.form.NumberField()},
            {header: '是否需要隐身', dataIndex: 'ishidden', editor: new Ext.form.NumberField()},
            {header: '是否需要解锁', dataIndex: 'islocked', editor: new Ext.form.NumberField()},
            {header: '建筑是否可以出售', dataIndex: 'issell', editor: new Ext.form.NumberField()},
            {header: '最小攻击范围', dataIndex: 'minattackrange', editor: new Ext.form.NumberField()},
            {header: '生成资源的种类', dataIndex: 'producesresourcetype', editor: new Ext.form.NumberField()},
            {header: '攻击附带击退效果', dataIndex: 'pushback', editor: new Ext.form.NumberField()},
            {header: '触发范围', dataIndex: 'triggerradius', editor: new Ext.form.NumberField()},
            {header: '是否可以升级士兵', dataIndex: 'upgradeunits', editor: new Ext.form.NumberField()},
            {header: '占地范围宽', dataIndex: 'width', editor: new Ext.form.NumberField()},
            {header: '建造中动画', dataIndex: 'buildinganimation', editor: new Ext.form.TextField()},
            {header: '建筑动画', dataIndex: 'swf', editor: new Ext.form.TextField()}
        ]});

    var proxy = new Ext.data.HttpProxy({
        url: '/looters/cfg/building/all'
    });

    var reader = new Ext.data.JsonReader({
        root: 'data',
        idProperty: 'buildingId'
    }, [
        {name: 'buildingid'},
        {name: 'airtargets'},
        {name: 'attackrange'},
        {name: 'attackspeed'},
        {name: 'buildresourcetype'},
        {name: 'buildtimedate'},
        {name: 'buildtimehour'},
        {name: 'buildtimeminutes'},
        {name: 'buildinganimation'},
        {name: 'buildingcrashh'},
        {name: 'buildingcrashw'},
        {name: 'buildingname'},
        {name: 'buildingtype'},
        {name: 'damageradius'},
        {name: 'groundtargets'},
        {name: 'height'},
        {name: 'isbunker'},
        {name: 'ishidden'},
        {name: 'islocked'},
        {name: 'issell'},
        {name: 'minattackrange'},
        {name: 'producesresourcetype'},
        {name: 'pushback'},
        {name: 'swf'},
        {name: 'triggerradius'},
        {name: 'upgradeunits'},
        {name: 'width'}
    ]);

    var store = new Ext.data.Store({
        proxy: proxy,
        reader: reader
    });
    store.load();

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab211',
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
function create212() {
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
        id: 'tab212',
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
function create213() {
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
        id: 'tab213',
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
function create214() {
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
        id: 'tab214',
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

function create221() {
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
            {header: 'mac地址', dataIndex: 'macAddress', editor: new Ext.form.TextField()}
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
        {name: 'macAddress'}
    ]);
    var store = new Ext.data.Store({
        proxy: new Ext.data.MemoryProxy(),
        reader: reader
    });

    var grid = new Ext.grid.EditorGridPanel({
        id: 'tab221',
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
        title: '首页'
    });

    tabPanel.add(tabMain);
    tabPanel.setActiveTab(tabMain);
});