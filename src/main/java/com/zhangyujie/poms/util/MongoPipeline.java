package com.zhangyujie.poms.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zhangyujie.poms.dao.mongo.WeiboDao;
import com.zhangyujie.poms.entity.Comment;
import com.zhangyujie.poms.entity.Weibo;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
@Component
public class MongoPipeline implements Pipeline {
	
	@Resource
	private WeiboDao dao;
	
	private static int num = 1;
	
	private void saveSpeech(ResultItems resultItems) {

		List<String> weiboIds = resultItems.get("weiboId");
		List<String> userIds = resultItems.get("userId");
		List<String> userNames = resultItems.get("userName");
		List<String> userGenders = resultItems.get("userGender");
		List<String> createdAts = resultItems.get("createdAt");
		List<String> attitudesCounts = resultItems.get("attitudesCount");
		List<String> commentsCounts = resultItems.get("commentsCount");
		List<String> repostsCounts = resultItems.get("repostsCount");

		for (int i = 0; i < weiboIds.size(); i++) {
			dao.save(new Weibo(null, weiboIds.get(i).trim(), userIds.get(i).trim(), userNames.get(i).trim(), 
					userGenders.get(i).trim(), null, createdAts.get(i).trim(), null,
					Integer.parseInt(attitudesCounts.get(i).trim()),
					Integer.parseInt(commentsCounts.get(i).trim()),
					Integer.parseInt(repostsCounts.get(i).trim())));
		}
		
	}
	
	private void saveContent(ResultItems resultItems) {
		dao.insertContent(resultItems.get("weiboId").toString(), resultItems.get("content").toString());
	}
	
	private void saveComment(ResultItems resultItems) {
		
		String weiboId = resultItems.get("weiboId");
		List<String> userIds = resultItems.get("userId");
		List<String> commentIds = resultItems.get("commentId");
		List<String> userNames = resultItems.get("userName");
		List<String> createdAts = resultItems.get("createdAt");
		List<String> texts = resultItems.get("text");
		
		for (int i = 0; i < commentIds.size(); i++) {
			dao.addComments(weiboId, new Comment(userIds.get(i).trim(), userNames.get(i).trim(), commentIds.get(i).trim(), texts.get(i).trim(), createdAts.get(i).trim()));
		}
		
	}
	
	public void process(ResultItems resultItems, Task task) {
	
		String type = resultItems.get("type");
		if ("speech".equals(type)) {
			saveSpeech(resultItems);
			System.out.println("<Saved " + (num++) + " speech>");
		} else if ("content".equals(type)) {
			saveContent(resultItems);
		} else {
			saveComment(resultItems);
		}
		
	}

}
