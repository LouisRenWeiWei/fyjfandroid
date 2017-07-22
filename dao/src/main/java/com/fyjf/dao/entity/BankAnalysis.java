package com.fyjf.dao.entity;

import java.io.Serializable;

/**
 * Created by czf on 2017/7/16.
 */

public class BankAnalysis implements Serializable{

    /**
     * bankId : 185414d99b044aaf92ae18eab52c29d2
     * pdfFile : b1bfb343-ece1-4e79-b30e-24afe92d3444.pdf
     * id : 0575df8a12c34c33ab74f70c28867a20
     * title : 211
     * content : 212121
     * createDate : 1500198765000
     */

    private String bankId;
    private String pdfFile;
    private String id;
    private String title;
    private String content;
    private String createDate;
    private String msgCount;
    private String bankName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }
}
