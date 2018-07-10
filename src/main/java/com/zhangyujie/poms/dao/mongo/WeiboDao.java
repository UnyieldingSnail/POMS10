package com.zhangyujie.poms.dao.mongo;

import com.zhangyujie.poms.entity.Comment;
import com.zhangyujie.poms.entity.Weibo;

import java.util.List;
import java.util.Map;

public interface WeiboDao {
	public String save(Weibo weibo);
	public List<Weibo> findAll(Integer pageNum);
	public String insertContent(String weiboId, String content);
	public String addComments(String weiboId, Comment comment);

    Weibo findById(String weiboId);

	Map<String,Integer> count();
}
