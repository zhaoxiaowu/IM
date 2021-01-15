package com.zxw.ui.view.face;

import com.zxw.ui.view.chat.ChatView;
import javafx.scene.control.TextArea;

/**
 * 微信公众号：bugstack虫洞栈 | 欢迎关注学习专题案例
 * 论坛：http://bugstack.cn
 * Create by 小傅哥 on @2019
 */
public class FaceEventDefine {

    private FaceView faceView;
    private ChatView chatView;

    public FaceEventDefine(FaceView faceView, ChatView chatView) {
        this.faceView = faceView;
        this.chatView = chatView;
        hideFace();
    }

    private void hideFace() {
        chatView.$("txt_input", TextArea.class).setOnMouseClicked(event -> {
            faceView.hide();
        });
        faceView.root.setOnMouseExited(event -> {
            faceView.hide();
        });
    }

}
