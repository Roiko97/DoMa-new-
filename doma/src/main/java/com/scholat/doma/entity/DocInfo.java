package com.scholat.doma.entity;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class DocInfo {

    private String userId;
    private String docId;
    private String pubTime;
    private String docName;
    private Integer recommend;
    private String style;
    private String address;
    private Integer existAnn;
    private String content;

    public DocInfo() {

    }

    public DocInfo(String userId, String docId, String pubTime, String docName, Integer recommend, String style, String address, Integer exist_ann, String content) {
        this.userId = userId;
        this.docId = docId;
        this.pubTime = pubTime;
        this.docName = docName;
        this.recommend = recommend;
        this.style = style;
        this.address = address;
        this.existAnn = exist_ann;
        this.content = content;
    }

    @Override
    public String toString() {
        return "DocInfo{" +
                "userId='" + userId + '\'' +
                ", docId='" + docId + '\'' +
                ", pubTime=" + pubTime +
                ", docName='" + docName + '\'' +
                ", recommend='" + recommend + '\'' +
                ", style='" + style + '\'' +
                ", address='" + address + '\'' +
                ", exist_ann='" + existAnn + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getExistAnn() {
        return existAnn;
    }

    public void setExistAnn(Integer exist_ann) {
        this.existAnn = exist_ann;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
