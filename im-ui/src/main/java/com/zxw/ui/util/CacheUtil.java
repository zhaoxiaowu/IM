package com.zxw.ui.util;

import com.zxw.ui.view.chat.element.group_bar_chat.ElementTalk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtil {

    // 对话框组
    public static Map<String, ElementTalk> talkMap = new ConcurrentHashMap<String, ElementTalk>(16);

}
