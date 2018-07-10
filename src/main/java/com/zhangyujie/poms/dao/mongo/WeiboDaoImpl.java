package com.zhangyujie.poms.dao.mongo;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.zhangyujie.poms.entity.Comment;
import com.zhangyujie.poms.entity.Weibo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("weiboDao")
public class WeiboDaoImpl implements WeiboDao {

	@Resource(name="mongoTemplate")
	private MongoOperations mo;
	
	public String save(Weibo weibo) {
		mo.save(weibo);
		return weibo.getId();
	}

	public List<Weibo> findAll(Integer pageNum) {
		List<Weibo> weibos = mo.find(Query.query(Criteria.where("repostsCount").exists(true)).skip(pageNum * 10).limit(10), Weibo.class);
		return weibos;
	}

	public String insertContent(String weiboId, String content) {
		mo.updateFirst(Query.query(Criteria.where("weiboId").is(weiboId)), Update.update("content", content), "weibo");
		return weiboId;
	}

	public String addComments(String weiboId, Comment comment) {
		mo.updateFirst(Query.query(Criteria.where("weiboId").is(weiboId)), new Update().addToSet("comments", comment), Weibo.class);
		return weiboId;
	}

	@Override
	public Weibo findById(String weiboId) {
		return mo.findById(weiboId, Weibo.class);
	}

	@Override
	public Map<String, Integer> count() {
		List<Weibo> weibos = mo.find(Query.query(Criteria.where("repostsCount").exists(true)),Weibo.class);
		Integer weiboNum = weibos.size();
		Integer repostNum = 0;
		for (int i = 0; i < weiboNum; i++) {
			repostNum += weibos.get(i).getRepostsCount();
		}
		Map<String, Integer> map = new HashMap<>();
		map.put("weiboNum", weiboNum);
		map.put("repostNum", repostNum);
		return map;
	}

}
