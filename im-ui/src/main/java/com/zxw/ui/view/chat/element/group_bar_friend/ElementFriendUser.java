package com.zxw.ui.view.chat.element.group_bar_friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class ElementFriendUser {

    private Pane pane;    // 用户底板(存储用户ID)

    private Label headLabel;  // 头像区域
    private Label nameLabel;  // 名称区域

    public ElementFriendUser(String userId, String userNickName, String userHead){
        // 用户底板(存储用户ID)
        pane = new Pane();
        pane.setId(userId);
        pane.setPrefWidth(250);
        pane.setPrefHeight(60);
        pane.getStyleClass().add("elementFriendUser");
        ObservableList<Node> children = pane.getChildren();
        // 头像区域
        headLabel = new Label();
        headLabel.setPrefSize(40, 40);
        headLabel.setLayoutX(10);
        headLabel.setLayoutY(10);
        headLabel.getStyleClass().add("elementFriendUser_head");
        headLabel.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(headLabel);
        // 名称区域
        nameLabel = new Label();
        nameLabel.setPrefSize(180, 40);
        nameLabel.setLayoutX(60);
        nameLabel.setLayoutY(10);
        nameLabel.setText(userNickName);
        nameLabel.getStyleClass().add("elementFriendUser_name");
        children.add(nameLabel);
    }

    public Pane pane() {
        return pane;
    }

}
