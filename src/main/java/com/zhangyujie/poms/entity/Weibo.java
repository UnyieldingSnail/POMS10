package com.zhangyujie.poms.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="weibo")
public class Weibo implements Serializable {
	@Id
	private String id;
	private String weiboId;
	private String userId;
	private String userName;
	private String userGender;
	private String text;
	private String createdAt;
	private List<Comment> comments;
	private Integer attitudesCount;
	private Integer commentsCount;
	private Integer repostsCount;
	
	@PersistenceConstructor
	public Weibo(String id, String weiboId, String userId, String userName, String userGender, String text,
			String createdAt, List<Comment> comments, Integer attitudesCount, Integer commentsCount,
			Integer repostsCount) {
		super();
		this.id = id;
		this.weiboId = weiboId;
		this.userId = userId;
		this.userName = userName;
		this.userGender = userGender;
		this.text = text;
		this.createdAt = createdAt;
		this.comments = comments;
		this.attitudesCount = attitudesCount;
		this.commentsCount = commentsCount;
		this.repostsCount = repostsCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWeiboId() {
		return weiboId;
	}
	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	@Override
	public String toString() {
		return "Weibo [id=" + id + ", weiboId=" + weiboId + ", userId=" + userId + ", userName=" + userName
				+ ", userGender=" + userGender + ", text=" + text + ", createdAt=" + createdAt + ", comments="
				+ comments + ", attitudesCount=" + attitudesCount + ", commentsCount=" + commentsCount
				+ ", repostsCount=" + repostsCount + "]";
	}
	
}
