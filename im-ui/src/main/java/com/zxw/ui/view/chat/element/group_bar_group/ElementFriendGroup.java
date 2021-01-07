package com.zxw.ui.view.chat.element.group_bar_group;

import com.zxw.ui.util.Ids;
import com.zxw.ui.view.chat.data.GroupsData;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class ElementFriendGroup {

    private Pane groupPane;

    public ElementFriendGroup(String groupId, String groupName, String groupHead) {
        // 群组底板(存储群ID)
        groupPane = new Pane();
        groupPane.setId(Ids.ElementTalkId.createFriendGroupId(groupId));
        groupPane.setUserData(new GroupsData(groupId, groupName, groupHead));
        groupPane.setPrefWidth(250);
        groupPane.setPrefHeight(60);
        groupPane.getStyleClass().add("elementFriendGroup");
        ObservableList<Node> children = groupPane.getChildren();
        // 头像区域
        Label groupHeadLabel = new Label();
        groupHeadLabel.setPrefSize(40, 40);
        groupHeadLabel.setLayoutX(10);
        groupHeadLabel.setLayoutY(10);
        groupHeadLabel.getStyleClass().add("elementFriendGroup_head");
        groupHeadLabel.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", groupHead));
        children.add(groupHeadLabel);
        // 名称区域
        Label groupNameLabel = new Label();
        groupNameLabel.setPrefSize(180, 40);
        groupNameLabel.setLayoutX(60);
        groupNameLabel.setLayoutY(10);
        groupNameLabel.setText(groupName);
        groupNameLabel.getStyleClass().add("elementFriendGroup_name");
        children.add(groupNameLabel);
    }

    public Pane pane() {
        return groupPane;
    }
}
