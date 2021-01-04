package com.zxw.ui;

import com.zxw.ui.view.login.ILoginMethod;
import com.zxw.ui.view.login.ILoginController;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ILoginMethod login = new ILoginController((userId, userPassword) -> {
            System.out.println("登陆 userId：" + userId + "userPassword：" + userPassword);
        });

        login.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
