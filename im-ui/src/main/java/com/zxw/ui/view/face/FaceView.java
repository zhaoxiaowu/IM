package com.zxw.ui.view.face;

import com.zxw.ui.view.UIObject;
import com.zxw.ui.view.chat.ChatView;
import com.zxw.ui.view.chat.IChatEvent;
import com.zxw.ui.view.chat.IChatMethod;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;


import java.io.IOException;

/**
 * 微信公众号：bugstack虫洞栈 | 欢迎关注学习专题案例
 * 论坛：http://bugstack.cn
 * Create by 小傅哥 on @2019
 */
public abstract class FaceView extends UIObject {

    private static final String RESOURCE_NAME = "/fxml/face/face.fxml";

    public Pane rootPane;

    public ChatView chatView;
    public IChatEvent chatEvent;
    public IChatMethod chatMethod;

    FaceView(final UIObject obj,ChatView chatView) {
        this.chatView = chatView;
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
        // 模态窗口
//        initModality(Modality.APPLICATION_MODAL);
        initOwner(obj);
        // 初始化页面&事件
        obtain();
        initView();
        initEventDefine();
    }

    private void obtain() {
        rootPane = $("face", Pane.class);
    }

    public Parent root(){
        return super.root;
    }

}
