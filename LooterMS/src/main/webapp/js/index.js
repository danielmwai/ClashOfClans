Ext.onReady(function () {
    var myLoginForm = new Ext.FormPanel({//定义一个FormPanel对象
        width: 350,
        frame: true, //设置窗体为圆角
        renderTo: "div1", //设置渲染的Div
        title: "log in", //"登录",
        method: "POST",
        //以下两个为Submit的主要方法
        onSubmit: Ext.emptyFn,
        submit: function () {
            this.getEl().dom.action = "login/";
            this.getEl().dom.submit()
            //disable this  
            Ext.MessageBox.alert("Form submit", "Submit SUCCESS!!!");
        },
        items: [
            //设置FormPanel的子对象
            {
                fieldLabel: "Username/ Account Number", //"账号", //标签内容
                xtype: "textfield", //对象的类型,默认为 textfield
                allowBlank: false, //是否允许为空,默认为 true
                blankText: "Account can not be empty !", //"账号不能为空!", //显示错误提示信息
                name: "username",
                id: "username"
            },
            {
                fieldLabel: "password", //"密码",
                xtype: "textfield",
                allowBlank: false,
                blankText: "password can not be blank", //"密码不能为空",
                inputType: "password", //输入类型为 password
                name: "password",
                id: "password"
            }
        ],
        buttons: [
            {
                text: "log in", //"登录",
                handler: function () {
                    if (myLoginForm.form.isValid()) {    //判断是否通过验证
                        myLoginForm.form.submit();    //提交表单
                    }
                }}
//            ,
//            {
//                text:"重置",
//                handler:function () {
//                    myLoginForm.form.reset();   //重置表单
//                }
//            }
        ]
    });
    //以下为显示验证提示的主要设置
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
});