package com.zhangyujie.poms.dao.mongo;

import com.zhangyujie.poms.entity.Comment;
import com.zhangyujie.poms.entity.Weibo;

public interface WeiboDao {
	public String save(Weibo weibo);
	public Weibo findAll();
	public String insertContent(String weiboId, String content);
	public String addComments(String weiboId, Comment comment);
}
