package com.zxw.ui.view.login;

import com.zxw.ui.view.UIObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * 窗口视图
 *
 * -窗口    Stage
 * -场景    Scene
 * -布局    StackPane
 * -控件    ButtonS
 */
public abstract class LoginView extends UIObject {

    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";

    protected ILoginEvent ILoginEvent;

    public Button login_min;          // 登陆窗口最小化
    public Button login_close;        // 登陆窗口退出
    public Button login_button;       // 登陆按钮
    public TextField userId;          // 用户账户窗口
    public PasswordField userPassword;// 用户密码窗口

    LoginView(ILoginEvent ILoginEvent) {
        this.ILoginEvent = ILoginEvent;
        try {
            root = FXMLLoader.load(getClass().getResource(RESOURCE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/common/img/logo.png"));
        obtain();
        initView();
        initEventDefine();
    }

    private void obtain() {
        login_min = $("login_min", Button.class);
        login_close = $("login_close", Button.class);
        login_button = $("login_button", Button.class);
        userId = $("userId", TextField.class);
        userPassword = $("userPassword", PasswordField.class);
    }

}
