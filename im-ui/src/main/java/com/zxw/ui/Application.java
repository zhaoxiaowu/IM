package com.zxw.ui;

import com.zxw.ui.view.chat.ChatController;
import com.zxw.ui.view.chat.IChatMethod;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        IChatMethod chat = new ChatController();
        chat.doShow();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
