package com.zhangyujie.poms.entity;

import java.io.Serializable;

public class Reposter implements Serializable{
    private String id;
    private String createdAt;
    private String text;

    public Reposter() {
    }

    public Reposter(String id, String createdAt, String text) {
        this.id = id;
        this.createdAt = createdAt;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Reposter{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
