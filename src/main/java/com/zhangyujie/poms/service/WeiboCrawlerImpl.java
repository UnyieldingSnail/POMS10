package com.zhangyujie.poms.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangyujie.poms.util.MongoPipeline;
import com.zhangyujie.poms.util.WeiboPageProcesser;

import us.codecraft.webmagic.Spider;

@Service("weiboCrawler")
public class WeiboCrawlerImpl implements WeiboCrawler {
	@Resource(name="weiboPageProcesser")
	private WeiboPageProcesser processer;
	@Resource(name="mongoPipeline")
	private MongoPipeline pipeline;
	
	public void run() {
		//processer.login();
    	Spider.create(processer).addUrl("https://m.weibo.cn/api/container/getIndex?containerid=102803")
    		.addPipeline(pipeline).run();
	}

}
