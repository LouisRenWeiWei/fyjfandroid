package com.gooda.push.message;

/**
 * Created by 任伟伟
 * Datetime: 2017/2/28-18:45
 * Email: renweiwei@ufashion.com
 */

public class PushMsgUserData {
    private String topic;
    private String msg;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
