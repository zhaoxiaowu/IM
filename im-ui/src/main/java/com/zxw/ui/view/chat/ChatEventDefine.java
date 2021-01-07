package com.zxw.ui.view.chat;

import com.zxw.ui.view.chat.data.TalkBoxData;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.Date;

public class ChatEventDefine {

    private ChatView chatView;
    private IChatMethod chatMethod;

    Button bar_chat;
    Pane group_bar_chat;
    Button bar_friend;
    Pane group_bar_friend;
    Button bar_group;
    Pane group_bar_group;

    public ChatEventDefine(ChatView chatView,IChatMethod chatMethod) {
        this.chatView = chatView;
        this.chatMethod = chatMethod;

        chatView.move();
        this.obtain();
        this.barChat();
        this.barFriend();
        this.barGroup();
        min();               // 最小化
        quit();              // 退出
        barChat();           // 聊天
        barFriend();         // 好友
        doEventTextSend();   // 发送消息事件[键盘]
        doEventTouchSend();  // 发送消息事件[按钮]
    }


    private void obtain() {
        bar_chat = chatView.$("bar_chat", Button.class);
        group_bar_chat = chatView.$("group_bar_chat", Pane.class);

        bar_friend = chatView.$("bar_friend", Button.class);
        group_bar_friend = chatView.$("group_bar_friend", Pane.class);

        bar_group = chatView.$("bar_group", Button.class);
        group_bar_group = chatView.$("group_bar_group", Pane.class);
    }

    // 聊天
    private void barChat() {
        bar_chat.setOnAction(event -> {
            switchBarChat(bar_chat, group_bar_chat, true);
            switchBarFriend(bar_friend, group_bar_friend, false);
            switchBarGroup(bar_group, group_bar_group, false);
        });
    }

    // 好友
    private void barFriend() {
        bar_friend.setOnAction(event -> {
            switchBarChat(bar_chat, group_bar_chat, false);
            switchBarFriend(bar_friend, group_bar_friend, true);
            switchBarGroup(bar_group, group_bar_group, false);
        });
    }

    // 群组
    private void barGroup() { ;
        bar_group.setOnAction(event -> {
            switchBarChat(bar_chat, group_bar_chat, false);
            switchBarFriend(bar_friend, group_bar_friend, false);
            switchBarGroup(bar_group, group_bar_group, true);
        });
    }


    // 切换：bar_chat
    private void switchBarChat(Button bar_chat, Pane group_bar_chat, boolean toggle) {
        if (toggle) {
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/chat-2.png')");
            group_bar_chat.setVisible(true);
        } else {
            bar_chat.setStyle("-fx-background-image: url('/fxml/chat/img/chat.png')");
            group_bar_chat.setVisible(false);
        }
    }

    // 切换：bar_friend
    private void switchBarFriend(Button bar_friend, Pane group_bar_friend, boolean toggle) {
        if (toggle) {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/friend-2.png')");
            group_bar_friend.setVisible(true);
        } else {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/friend.png')");
            group_bar_friend.setVisible(false);
        }
    }

    // 切换：bar_group
    private void switchBarGroup(Button bar_friend, Pane group_bar_friend, boolean toggle) {
        if (toggle) {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/group-2.png')");
            group_bar_friend.setVisible(true);
        } else {
            bar_friend.setStyle("-fx-background-image: url('/fxml/chat/img/group.png')");
            group_bar_friend.setVisible(false);
        }
    }

    // 最小化
    private void min() {
        chatView.$("group_bar_chat_min", Button.class).setOnAction(event -> {
            System.out.println("最小化");
            chatView.setIconified(true);
        });
        chatView.$("group_bar_friend_min", Button.class).setOnAction(event -> {
            System.out.println("最小化");
            chatView.setIconified(true);
        });
    }

    // 退出
    private void quit() {
        chatView.$("group_bar_chat_close", Button.class).setOnAction(event -> {
            chatView.close();
            System.exit(0);
            System.out.println("退出");
        });
        chatView.$("group_bar_friend_close", Button.class).setOnAction(event -> {
            System.out.println("最小化");
            chatView.setIconified(true);
        });
    }

    // 发送消息事件[键盘]
    private void doEventTextSend() {
        TextArea txt_input = chatView.$("txt_input", TextArea.class);
        txt_input.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                doEventSendMsg();
            }
        });
    }

    // 发送消息事件[按钮]
    private void doEventTouchSend() {
        Label touch_send = chatView.$("touch_send", Label.class);
        touch_send.setOnMousePressed(event -> {
            doEventSendMsg();
        });
    }

    private void doEventSendMsg() {
        TextArea txt_input = chatView.$("txt_input", TextArea.class);
        MultipleSelectionModel selectionModel = chatView.$("talkList", ListView.class).getSelectionModel();
        Pane selectedItem = (Pane) selectionModel.getSelectedItem();
        // 对话信息
        TalkBoxData talkBoxData = (TalkBoxData) selectedItem.getUserData();
        String msg = txt_input.getText();
        if (null == msg || "".equals(msg) || "".equals(msg.trim())) {
            return;
        }
        Date msgDate = new Date();
        // 发送消息
        System.out.println("发送消息：" + msg);
        // 发送事件给自己添加消息
        chatMethod.addTalkMsgRight(talkBoxData.getTalkId(), msg, msgDate, true, true, false);
        //清楚内容 鼠标重新定位
        txt_input.clear();
    }
}
