package com.zhangyujie.poms.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "weibo")
public class Weibo implements Serializable {
    @Id
    private String id;
    private String userId;
    private String userName;
    private String userGender;
    private String content;
    private String createdAt;
    private List<Comment> comments;
    private List<Reposter> reposters;
    private Integer attitudesCount;
    private Integer commentsCount;
    private Integer repostsCount;
    private List<Integer> truth;
    private List<Integer> pre;

    @PersistenceConstructor
    public Weibo(String id, String userId, String userName, String userGender, String content, String createdAt, List<Comment> comments, List<Reposter> reposters, Integer attitudesCount, Integer commentsCount, Integer repostsCount, List<Integer> truth, List<Integer> pre) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userGender = userGender;
        this.content = content;
        this.createdAt = createdAt;
        this.comments = comments;
        this.reposters = reposters;
        this.attitudesCount = attitudesCount;
        this.commentsCount = commentsCount;
        this.repostsCount = repostsCount;
        this.truth = truth;
        this.pre = pre;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Reposter> getReposters() {
        return reposters;
    }

    public void setReposters(List<Reposter> reposters) {
        this.reposters = reposters;
    }

    public Integer getAttitudesCount() {
        return attitudesCount;
    }

    public void setAttitudesCount(Integer attitudesCount) {
        this.attitudesCount = attitudesCount;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Integer getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(Integer repostsCount) {
        this.repostsCount = repostsCount;
    }

    public List<Integer> getTruth() {
        return truth;
    }

    public void setTruth(List<Integer> truth) {
        this.truth = truth;
    }

    public List<Integer> getPre() {
        return pre;
    }

    public void setPre(List<Integer> pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "Weibo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", comments=" + comments +
                ", reposters=" + reposters +
                ", attitudesCount=" + attitudesCount +
                ", commentsCount=" + commentsCount +
                ", repostsCount=" + repostsCount +
                ", truth=" + truth +
                ", pre=" + pre +
                '}';
    }
}
