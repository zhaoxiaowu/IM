package com.zxw.ui.view.chat;

import com.zxw.ui.view.login.ILoginEvent;

public class ChatController extends ChatView implements IChatMethod {

    private ChatEventDefine chatEventDefine;

    @Override
    public void initView() {

    }

    @Override
    public void initEventDefine() {
        chatEventDefine = new ChatEventDefine(this);
    }

    @Override
    public void doShow() {
        super.show();
    }

}
