package com.bjhl.plugins.android.util;

/**
 * Created by XIAS on 2018/6/27.
 */

public class MessageEvent {

    private int eventTyoe;

    public MessageEvent(int eventTyoe) {
        this.eventTyoe = eventTyoe;
    }

    public int getEventTyoe() {
        return eventTyoe;
    }
}
