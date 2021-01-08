package com.zxw.ui.view.login;

import com.zxw.ui.view.chat.IChatMethod;

/**
 * 窗口控制器   管理视图  事件（第三方事件  窗口事件） 窗口方法
 */
public class LoginController extends LoginView implements ILoginMethod {

    private LoginEventDefine loginEventDefine;


    public LoginController(ILoginEvent ILoginEven) {
        super(ILoginEven);
    }

    @Override
    public void initView() {
        //无需初始化
    }

    @Override
    public void initEventDefine() {
        loginEventDefine = new LoginEventDefine(this, ILoginEvent);
    }

    @Override
    public void doShow() {
        super.show();
    }

    @Override
    public void doLoginError() {
        System.out.println("登陆失败，执行提示操作");
    }

    @Override
    public void doLoginSuccess() {
        System.out.println("登陆成功，执行跳转操作");
        // 关闭原窗口
        close();
    }

}
