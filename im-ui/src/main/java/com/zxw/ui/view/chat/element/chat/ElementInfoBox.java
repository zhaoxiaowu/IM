package com.zxw.ui.view.chat.element.chat;

import com.zxw.ui.util.AutoSizeTool;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ElementInfoBox {

    private Pane pane;

    private Pane head;              // 头像
    private Label nikeName;         // 昵称区
    private Label infoContentArrow; // 内容箭头
    private TextArea infoContent;   // 内容

    public Pane left(String userNickName, String userHead, String msg, Integer msgType) {

        double autoHeight = AutoSizeTool.getHeight(msg);
        double autoWidth = AutoSizeTool.getWidth(msg);

        pane = new Pane();
        pane.setPrefSize(500, 25 + autoHeight);
        pane.getStyleClass().add("infoBoxElement");
        ObservableList<Node> children = pane.getChildren();

        // 头像
        head = new Pane();
        head.setPrefSize(45, 45);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(head);

        // 昵称
        nikeName = new Label();
        nikeName.setPrefSize(450, 20);
        nikeName.setLayoutX(70);
        nikeName.setLayoutY(10);
        nikeName.setText(userNickName); // "小傅哥 | bugstack.cn"
        nikeName.getStyleClass().add("box_nikeName");
        children.add(nikeName);

        // 箭头
        infoContentArrow = new Label();
        infoContentArrow.setPrefSize(5, 20);
        infoContentArrow.setLayoutX(70);
        infoContentArrow.setLayoutY(30);
        infoContentArrow.getStyleClass().add("box_infoContent_arrow");
        children.add(infoContentArrow);

        switch (msgType) {
            case 0:
                // 内容
                infoContent = new TextArea();
                infoContent.setPrefWidth(autoWidth);
                infoContent.setPrefHeight(autoHeight);
                infoContent.setLayoutX(75);
                infoContent.setLayoutY(30);
                infoContent.setWrapText(true);
                infoContent.setEditable(false);
                infoContent.setText(msg);
                infoContent.getStyleClass().add("box_infoContent_left");
                children.add(infoContent);
                break;
            case 1:
                Label infoContentFace = new Label();
                infoContentFace.setPrefWidth(60);
                infoContentFace.setPrefHeight(40);
                infoContentFace.setLayoutX(75);
                infoContentFace.setLayoutY(30);
                infoContentFace.setStyle(String.format("-fx-background-image: url('/fxml/face/img/%s.png');-fx-background-position: center center;-fx-background-repeat: no-repeat;-fx-background-color: #ffffff;-fx-border-width: 0 1px 1px 0;-fx-border-radius: 2px;-fx-background-radius: 2px;", msg));
                children.add(infoContentFace);
                break;
        }
        return pane;
    }

    public Pane right(String userNickName, String userHead, String msg, Integer msgType) {

        double autoHeight = AutoSizeTool.getHeight(msg);
        double autoWidth = AutoSizeTool.getWidth(msg);

        AnchorPane pane = new AnchorPane();
        pane.setPrefSize(500, 25 + autoHeight);
        pane.setLayoutX(640);
        pane.setLayoutY(0);
        pane.getStyleClass().add("infoBoxElement");
        ObservableList<Node> children = pane.getChildren();

        // 头像
        head = new Pane();
        head.setPrefSize(45, 45);
//        head.setLayoutX(640);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(head);
        AnchorPane.setRightAnchor(head, Double.valueOf(15));

        // 箭头
        infoContentArrow = new Label();
        infoContentArrow.setPrefSize(5, 20);
//        infoContentArrow.setLayoutX(640);
        infoContentArrow.setLayoutY(15);
        infoContentArrow.getStyleClass().add("box_infoContent_arrow");
        children.add(infoContentArrow);
        AnchorPane.setRightAnchor(infoContentArrow, Double.valueOf(70));

        switch (msgType) {
            case 0:
                // 内容
                infoContent = new TextArea();
                infoContent.setPrefWidth(autoWidth);
                infoContent.setPrefHeight(autoHeight);
                infoContent.setLayoutY(15);
                infoContent.setWrapText(true);
                infoContent.setEditable(false);
                infoContent.setText(msg);
                infoContent.getStyleClass().add("box_infoContent_right");
                children.add(infoContent);
                AnchorPane.setRightAnchor(infoContent, Double.valueOf(75));
                break;
            case 1:
                Label infoContentFace = new Label();
                infoContentFace.setPrefWidth(60);
                infoContentFace.setPrefHeight(40);
                infoContentFace.setLayoutY(15);
                infoContentFace.setStyle(String.format("-fx-background-image: url('/fxml/face/img/%s.png');-fx-background-position: center center;-fx-background-repeat: no-repeat;-fx-background-color: #9eea6a;-fx-border-width: 0 1px 1px 0;-fx-border-radius: 2px;-fx-background-radius: 2px;", msg));
                children.add(infoContentFace);
                AnchorPane.setRightAnchor(infoContentFace, Double.valueOf(75));
                break;
        }
        return pane;
    }

}
