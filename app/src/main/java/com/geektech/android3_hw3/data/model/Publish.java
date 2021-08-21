package com.geektech.android3_hw3.data.model;

import com.google.gson.annotations.SerializedName;

public class Publish {

    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("group")
    private Integer group;

    public Publish( String title, String content, Integer userId, Integer group) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.group = group;
    }

    public Publish(Integer id, String title, String content, Integer userId, Integer group) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.group = group;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Publish{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", group=" + group +
                '}';
    }
}
