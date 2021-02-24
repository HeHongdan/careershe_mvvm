package com.careershe.careershe.model.main;

/**
 * 类描述：首页底部导航按钮。
 *
 * @author HeHongdan
 * @date 2/24/21
 * @since v2/24/21
 */
public class TabEntity {
    private String tabName;
    private int tabIcon;
    private int msgCount;

    public TabEntity(String tabName, int tabIcon, int msgCount) {
        this.tabName = tabName;
        this.tabIcon = tabIcon;
        this.msgCount = msgCount;
    }

    public String getTabName() {
        return tabName;
    }

    public int getTabIcon() {
        return tabIcon;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }
}
