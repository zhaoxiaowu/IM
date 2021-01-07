package com.zxw.ui.view.chat.element.group_bar_group;

import javafx.collections.ObservableList;
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

    private Pane friendLuckPane;               // 用户面板
    private TextField friendLuckSearch;        // 用户搜索
    private ListView<Pane> friendLuckListView; // 用户列表[待添加好友用户]

    public ElementCreateGroup() {
        pane = new Pane();
        pane.setId("elementFriendLuck");
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

//        // 初始化框体区域[搜索好友框、展现框]
//        friendLuckPane = new Pane();
//        friendLuckPane.setPrefSize(665, 670);
//        friendLuckPane.getStyleClass().add("friendLuckPane");
//        ObservableList<Node> friendLuckPaneChildren = friendLuckPane.getChildren();
//
//        friendLuckSearch = new TextField();
//        friendLuckSearch.setPrefSize(545,35);
//        friendLuckSearch.setLayoutX(60);
//        friendLuckSearch.setLayoutY(25);
//        friendLuckSearch.setPromptText("搜一搜");
//        friendLuckSearch.setPadding(new Insets(10));
//        friendLuckSearch.getStyleClass().add("friendLuckSearch");
//        friendLuckPaneChildren.add(friendLuckSearch);
//
//        // 用户列表框[初始化，未装载]
//        friendLuckListView = new ListView<>();
//        friendLuckListView.setId("friendLuckListView");
//        friendLuckListView.setPrefSize(665, 460);
//        friendLuckListView.setLayoutY(75);
//        friendLuckListView.getStyleClass().add("friendLuckListView");
//        friendLuckPaneChildren.add(friendLuckListView);

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

