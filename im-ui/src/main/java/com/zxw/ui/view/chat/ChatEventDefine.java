package com.zxw.ui.view.chat;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ChatEventDefine {

    private ChatView chatView;

    Button bar_chat;
    Pane group_bar_chat;
    Button bar_friend;
    Pane group_bar_friend;
    Button bar_group;
    Pane group_bar_group;

    public ChatEventDefine(ChatView chatView) {
        this.chatView = chatView;

        chatView.move();
        this.obtain();
        this.barChat();
        this.barFriend();
        this.barGroup();
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

}
