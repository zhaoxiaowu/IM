package com.zxw.ui.view.chat;

import com.zxw.ui.util.CacheUtil;
import com.zxw.ui.util.Ids;
import com.zxw.ui.view.chat.data.GroupsData;
import com.zxw.ui.view.chat.data.RemindCount;
import com.zxw.ui.view.chat.data.TalkData;
import com.zxw.ui.view.chat.element.chat.ElementInfoBox;
import com.zxw.ui.view.chat.element.chat.ElementTalk;
import com.zxw.ui.view.chat.element.group_bar_group.ElementFriendGroup;
import com.zxw.ui.view.chat.element.group_bar_friend.ElementFriendLuckUser;
import com.zxw.ui.view.chat.element.group_bar_friend.ElementFriendUser;
import com.zxw.ui.view.chat.element.group_bar_group.ElementFriendGroupUser;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.Date;

public class ChatController extends ChatView implements IChatMethod {

    private ChatEventDefine chatEventDefine;

    private ChatViewInit chatView;

    public ChatController(IChatEvent chatEvent) {
        super(chatEvent);
    }

    @Override
    public void initView() {
        chatView = new ChatViewInit(this, chatEvent);
    }

    @Override
    public void initEventDefine() {
        chatEventDefine = new ChatEventDefine(this, chatEvent, this);
    }

    @Override
    public void doShow() {
        super.show();
    }

    @Override
    public void setUserInfo(String userId, String userNickName, String userHead) {
        super.userId = userId;
        super.userNickName = userNickName;
        super.userHead = userHead;
        Button button = $("bar_portrait", Button.class);
        button.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
    }

    @Override
    public void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected) {
        // 填充到对话框
        ListView<Pane> talkList = $("talkList", ListView.class);
        // 判断会话框是否有该对象
        ElementTalk elementTalk = CacheUtil.talkMap.get(talkId);
        if (null != elementTalk) {
            Node talkNode = talkList.lookup("#" + Ids.ElementTalkId.createTalkPaneId(talkId));
            if (null == talkNode) {
                talkList.getItems().add(talkIdx, elementTalk.pane());
            }
            if (selected) {
                // 设置选中
                talkList.getSelectionModel().select(elementTalk.pane());
            }
            return;
        }
        // 初始化对话框元素
        ElementTalk talkElement = new ElementTalk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);
        CacheUtil.talkMap.put(talkId, talkElement);
        // 填充到对话框
        ObservableList<Pane> items = talkList.getItems();
        Pane talkElementPane = talkElement.pane();
        if (talkIdx >= 0) {
            items.add(talkIdx, talkElementPane);  // 添加到第一个位置
        } else {
            items.add(talkElementPane);           // 顺序添加
        }
        if (selected) {
            talkList.getSelectionModel().select(talkElementPane);
        }
        // 对话框元素点击事件
        talkElementPane.setOnMousePressed(event -> {
            // 填充消息栏
            fillInfoBox(talkElement, talkName);
            // 清除消息提醒
            Label msgRemind = talkElement.msgRemind();
            msgRemind.setUserData(new RemindCount(0));
            msgRemind.setVisible(false);
        });
        // 鼠标事件[移入/移出]
        talkElementPane.setOnMouseEntered(event -> {
            talkElement.delete().setVisible(true);
        });
        talkElementPane.setOnMouseExited(event -> {
            talkElement.delete().setVisible(false);
        });
        // 填充对话框消息栏
        fillInfoBox(talkElement, talkName);
        // 从对话框中删除
        talkElement.delete().setOnMouseClicked(event -> {
            System.out.println("删除对话框：" + talkName);
            talkList.getItems().remove(talkElementPane);
            $("info_pane_box", Pane.class).getChildren().clear();
            $("info_pane_box", Pane.class).setUserData(null);
            $("info_name", Label.class).setText("");
            talkElement.infoBoxList().getItems().clear();
            talkElement.clearMsgSketch();
        });
    }

    /**
     * 私有方法
     * 填充对话框消息内容
     *
     * @param talkElement 对话框元素
     * @param talkName    对话框名称
     */
    private void fillInfoBox(ElementTalk talkElement, String talkName) {
        String talkId = talkElement.pane().getUserData().toString();
        // 填充对话列表
        Pane info_pane_box = $("info_pane_box", Pane.class);
        String boxUserId = (String) info_pane_box.getUserData();
        // 判断是否已经填充[talkId]，当前已填充则返回
        if (talkId.equals(boxUserId)) return;
        ListView<Pane> listView = talkElement.infoBoxList();
        info_pane_box.setUserData(talkId);
        info_pane_box.getChildren().clear();
        info_pane_box.getChildren().add(listView);
        // 对话框名称
        Label info_name = $("info_name", Label.class);
        info_name.setText(talkName);
    }

    @Override
    public void addTalkMsgUserLeft(String talkId, String msg, Integer msgType, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = talkElement.infoBoxList();
        TalkData talkUserData = (TalkData) listView.getUserData();
        Pane left = new ElementInfoBox().left(talkUserData.getTalkName(), talkUserData.getTalkHead(), msg, msgType);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);
        talkElement.fillMsgSketch(0 == msgType ? msg : "[表情]", msgDate);
        // 设置位置&选中
        chatView.updateTalkListIdxAndSelected(0, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);
    }

    @Override
    public void addTalkMsgGroupLeft(String talkId, String userId, String userNickName, String userHead, String msg, Integer msgType, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        // 自己的消息抛弃
        if (super.userId.equals(userId)) return;
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        if (null == talkElement) {
            GroupsData groupsData = (GroupsData) $(Ids.ElementTalkId.createFriendGroupId(talkId), Pane.class).getUserData();
            if (null == groupsData) return;
            addTalkBox(0, 1, talkId, groupsData.getGroupName(), groupsData.getGroupHead(), userNickName + "：" + msg, msgDate, false);
            talkElement = CacheUtil.talkMap.get(talkId);
            // 事件通知(开启与群组发送消息)
            chatEvent.doEventAddTalkGroup(super.userId, talkId);
        }
        ListView<Pane> listView = talkElement.infoBoxList();
        Pane left = new ElementInfoBox().left(userNickName, userHead, msg, msgType);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);
        talkElement.fillMsgSketch(0 == msgType ? userNickName + "：" + msg : userNickName + "：[表情]", msgDate);
        // 设置位置&选中
        chatView.updateTalkListIdxAndSelected(1, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);
    }


    @Override
    public void addTalkMsgRight(String talkId, String msg, Integer msgType, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElementTalk talkElement = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = talkElement.infoBoxList();
        Pane right = new ElementInfoBox().right(userNickName, userHead, msg, msgType);
        // 消息填充
        listView.getItems().add(right);
        // 滚动条
        listView.scrollTo(right);
        talkElement.fillMsgSketch(0 == msgType ? msg : "[表情]", msgData);
        // 设置位置&选中
        chatView.updateTalkListIdxAndSelected(0, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);
    }

    @Override
    public void addFriendGroup(boolean selected, String groupId, String groupName, String groupHead) {
        ElementFriendGroup elementFriendGroup = new ElementFriendGroup(groupId, groupName, groupHead);
        Pane pane = elementFriendGroup.pane();
        // 添加到群组列表
        ListView<Pane> groupListView = $("groupListView", ListView.class);
        ObservableList<Pane> items = groupListView.getItems();
        items.add(pane);
        groupListView.setPrefHeight(80 * items.size());
        $("friendGroupList", Pane.class).setPrefHeight(80 * items.size());

        // 选中
        if (selected) {
            groupListView.getSelectionModel().select(pane);
        }

        // 群组，内容框[初始化，未装载]，承载群组信息内容，点击按钮时候填充
        Pane detailContent = new Pane();
        detailContent.setPrefSize(665, 730);
        detailContent.getStyleClass().add("friendGroupDetailContent");
        ObservableList<Node> children = detailContent.getChildren();

        Button sendMsgButton = new Button();
        sendMsgButton.setId(groupId);
        sendMsgButton.getStyleClass().add("friendGroupSendMsgButton");
        sendMsgButton.setPrefSize(135, 35);
        sendMsgButton.setLayoutX(265);
        sendMsgButton.setLayoutY(335);
        sendMsgButton.setText("发送消息");
        chatEventDefine.doEventOpenFriendGroupSendMsg(sendMsgButton, groupId, groupName, groupHead);
        children.add(sendMsgButton);

        // 添加监听事件
        pane.setOnMousePressed(event -> {
            Pane createGroup = $("create_group", Pane.class);
            createGroup.setVisible(false);
            clearViewListSelectedAll($("groupList", ListView.class));
            chatView.setGroupContentPaneBox(groupId, groupName, detailContent);
        });
        chatView.setGroupContentPaneBox(groupId, groupName, detailContent);
    }

    @Override
    public void addFriendUser(boolean selected, String userFriendId, String userFriendNickName, String userFriendHead) {
        ElementFriendUser friendUser = new ElementFriendUser(userFriendId, userFriendNickName, userFriendHead);
        Pane pane = friendUser.pane();
        // 添加到好友列表
        ListView<Pane> userListView = $("userListView", ListView.class);
        ObservableList<Pane> items = userListView.getItems();
        items.add(pane);
        userListView.setPrefHeight(80 * items.size());
        $("friendUserList", Pane.class).setPrefHeight(80 * items.size());

        //添加到群聊列表
        addGroupFriend(userFriendId, userFriendNickName, userFriendHead);


        // 选中
        if (selected) {
            userListView.getSelectionModel().select(pane);
        }
        // 好友，内容框[初始化，未装载]，承载好友信息内容，点击按钮时候填充
        Pane detailContent = new Pane();
        detailContent.setPrefSize(665, 730);
        detailContent.getStyleClass().add("friendUserDetailContent");
        ObservableList<Node> children = detailContent.getChildren();

        Button sendMsgButton = new Button();
        sendMsgButton.setId(userFriendId);
        sendMsgButton.getStyleClass().add("friendUserSendMsgButton");
        sendMsgButton.setPrefSize(135, 35);
        sendMsgButton.setLayoutX(265);
        sendMsgButton.setLayoutY(335);
        sendMsgButton.setText("发送消息");
        chatEventDefine.doEventOpenFriendUserSendMsg(sendMsgButton, userFriendId, userFriendNickName, userFriendHead);
        children.add(sendMsgButton);
        // 添加监听事件
        pane.setOnMousePressed(event -> {
            clearViewListSelectedAll($("friendList", ListView.class));
            chatView.setContentPaneBox(userFriendId, userFriendNickName, detailContent);
        });
        chatView.setContentPaneBox(userFriendId, userFriendNickName, detailContent);
    }

    @Override
    public void addLuckFriend(String userId, String userNickName, String userHead, Integer status) {
        // 添加到好友列表
        ListView<Pane> friendLuckListView = $("friendLuckListView", ListView.class);
        System.out.println(friendLuckListView.getEditingIndex());
        friendLuckListView.getItems().add(new ElementFriendLuckUser(userId, userNickName, userHead, status).pane());
//        // 点击事件
//        friendLuckUser.statusLabel().setOnMousePressed(event -> {
//            System.out.println("添加好友");
//        });
    }

    /**
     * 添加群组成员    
     * @param userId
     * @param userNickName
     * @param userHead
     */
    public void addGroupFriend(String userId, String userNickName, String userHead) {
        ElementFriendGroupUser groupFriend = new ElementFriendGroupUser(userId, userNickName, userHead);
        Pane pane = groupFriend.pane();


//        ElementCreateGroup element = new ElementCreateGroup();
//
//        Pane friendLuckPane = element.friendLuckPane();
//        chatView.setContentPaneBox("itstack-naive-chat-ui-chat-group-friend-luck", "新的群组", friendLuckPane);
//        // 搜索清空元素
//        element.friendLuckListView().getItems().clear();
//        // 添加朋友
//        element.friendLuckListView().getItems().add(new ElementFriendGroupUser("1000005", "比丘卡", "05_50").pane());


        // 添加到好友列表
        ListView<Pane> selectList = $("create_group_list", ListView.class);
        ObservableList<Pane> items = selectList.getItems();
        items.add(pane);

        ListView<Node> selectedList = $("selected_group_list", ListView.class);
        ObservableList<Node> selectedItems = selectedList.getItems();

        pane.setOnMousePressed(event -> {
            if(items.contains(pane)){
                selectedItems.add(pane);
                items.remove(pane);
            }else {
                items.add(pane);
                selectedItems.remove(pane);
            }

        });
//        // 点击事件
//        groupFriend.statusLabel().setOnMousePressed(event -> {
//            System.out.println("添加好友");
//        });
    }

    @Override
    public double getToolFaceX() {
        return x() + width() - 665;
    }

    @Override
    public double getToolFaceY() {
        return y() + height() - 310;
    }

}
