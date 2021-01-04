package com.zxw.ui.view.login;

/**
 * 窗口事件定义
 */
public class LoginEventDefine {

    private LoginView loginView;
    private ILoginEvent ILoginEvent;

    public LoginEventDefine(LoginView loginView, ILoginEvent ILoginEvent) {
        this.loginView = loginView;
        this.ILoginEvent = ILoginEvent;

        loginView.move();
        min();
        quit();
        doEventLogin();
    }

    // 事件；最小化
    private void min() {
        loginView.login_min.setOnAction(event -> {
            System.out.println("最小化");
            loginView.setIconified(true);
        });
    }

    // 事件；退出
    private void quit() {
        loginView.login_close.setOnAction(event -> {
            System.out.println("退出");
            loginView.close();
            System.exit(0);
        });
    }

    // 事件；登陆
    private void doEventLogin() {
        loginView.login_button.setOnAction(event -> {
            ILoginEvent.doLoginCheck(loginView.userId.getText(), loginView.userPassword.getText());
        });
    }
}
