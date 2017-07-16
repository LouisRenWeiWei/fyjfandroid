package com.fyjf.dao.entity;

import java.util.List;

/**
 * Created by czf on 2017/7/15.
 */

public class ReportMessageBean {

    /**
     * messagerName : bj02
     * replys : [{"answerId":"d87be3724280489bbd38838b6c46879a","answerName":"test","replyId":"7338955b8a9747d88b1bb6165c2d6166","replyContent":"232323"},{"answerId":"d87be3724280489bbd38838b6c46879a","answerName":"test","replyId":"a9f69ee4754d425194b6cdf8d32f638c","replyContent":"232323"}]
     * id : 232
     * content : 232323
     * messagerId : 60c3573bcf794fd69823eb0400ca2fba
     * createDate : 1500046996000
     */

    private String messagerName;
    private String id;
    private String content;
    private String messagerId;
    private String createDate;
    private List<ReplysBean> replys;

    public String getMessagerName() {
        return messagerName;
    }

    public void setMessagerName(String messagerName) {
        this.messagerName = messagerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessagerId() {
        return messagerId;
    }

    public void setMessagerId(String messagerId) {
        this.messagerId = messagerId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<ReplysBean> getReplys() {
        return replys;
    }

    public void setReplys(List<ReplysBean> replys) {
        this.replys = replys;
    }

    public static class ReplysBean {
        /**
         * answerId : d87be3724280489bbd38838b6c46879a
         * answerName : test
         * replyId : 7338955b8a9747d88b1bb6165c2d6166
         * replyContent : 232323
         */

        private String answerId;
        private String answerName;
        private String replyId;
        private String replyContent;

        public String getAnswerId() {
            return answerId;
        }

        public void setAnswerId(String answerId) {
            this.answerId = answerId;
        }

        public String getAnswerName() {
            return answerName;
        }

        public void setAnswerName(String answerName) {
            this.answerName = answerName;
        }

        public String getReplyId() {
            return replyId;
        }

        public void setReplyId(String replyId) {
            this.replyId = replyId;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }
    }
}
