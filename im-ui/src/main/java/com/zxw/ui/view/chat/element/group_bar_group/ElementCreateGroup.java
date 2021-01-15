package com.zxw.ui.view.chat.element.group_bar_group;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 组件；好友缘分 | 添加好友
 */
public class ElementCreateGroup {

    private Pane pane;

    private Label head;  // 头像

    private Label name;  // 名称

    private Pane friendLuckPane;
 // 用户面板
    private TextField friendLuckSearch;


    private ListView<Pane> friendLuckListView; // 用户列表[待添加好友用户]

    public ElementCreateGroup() {
        pane = new Pane();
        pane.setId("elementGroupFriendLuck");
        pane.setPrefSize(250, 60);
        pane.getStyleClass().add("elementFriendLuck");
        ObservableList<Node> children = pane.getChildren();

        // 头像区域
        head = new Label();
        head.setPrefSize(40, 40);
        head.setLayoutX(10);
        head.setLayoutY(10);
        head.getStyleClass().add("elementFriendLuck_head");
        children.add(head);

        // 名称区域
        name = new Label();
        name.setPrefSize(180, 40);
        name.setLayoutX(60);
        name.setLayoutY(10);
        name.setText("创建群组");
        name.getStyleClass().add("elementFriendLuck_name");
        children.add(name);

    }

    public Pane pane() {
        return pane;
    }

    public Pane friendLuckPane() {
        return friendLuckPane;
    }

    public TextField friendLuckSearch() {
        return friendLuckSearch;
    }

    public ListView<Pane> friendLuckListView() {
        return friendLuckListView;
    }
}

