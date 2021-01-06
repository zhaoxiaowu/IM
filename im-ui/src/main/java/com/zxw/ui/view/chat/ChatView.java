package com.zxw.ui.view.chat;

import com.zxw.ui.view.UIObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;

public abstract class ChatView extends UIObject {

    private static final String RESOURCE_NAME = "/fxml/chat/chat.fxml";

    ChatView(){
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
        initView();
        initEventDefine();
    }

}
