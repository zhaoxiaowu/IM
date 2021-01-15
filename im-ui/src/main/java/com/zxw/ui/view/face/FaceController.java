package com.zxw.ui.view.face;

import com.zxw.ui.view.UIObject;
import com.zxw.ui.view.chat.ChatView;
import com.zxw.ui.view.chat.IChatEvent;
import com.zxw.ui.view.chat.IChatMethod;

/**
 * 微信公众号：bugstack虫洞栈 | 欢迎关注学习专题案例
 * 论坛：http://bugstack.cn
 * Create by 小傅哥 on @2019
 */
public class FaceController extends FaceView implements IFaceMethod {

    private FaceViewInit faceView;

    public FaceController(UIObject obj, ChatView chatView, IChatEvent chatEvent, IChatMethod chatMethod) {
        super(obj,chatView);
//        this.chatView = chatView;
        this.chatEvent = chatEvent;
        this.chatMethod = chatMethod;
    }

    @Override
    public void initView() {
        faceView = new FaceViewInit(this);
    }

    @Override
    public void initEventDefine() {
        new FaceEventDefine(this,chatView);
    }

    @Override
    public void doShowFace(Double x, Double y) {
        setX(x);  // 设置位置X
        setY(y);                      // 设置位置Y
        show();                             // 展示窗口
    }

}
