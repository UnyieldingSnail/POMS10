package com.zhangyujie.poms.service;

import javax.annotation.Resource;

import com.zhangyujie.poms.entity.Result;
import org.springframework.stereotype.Service;

import com.zhangyujie.poms.util.MongoPipeline;
import com.zhangyujie.poms.util.WeiboPageProcesser;

import us.codecraft.webmagic.Spider;

@Service("weiboCrawler")
public class WeiboCrawlerImpl implements WeiboCrawler {
    @Resource(name = "weiboPageProcesser")
    private WeiboPageProcesser processer;
    @Resource(name = "mongoPipeline")
    private MongoPipeline pipeline;

    public static int schedule = 0;

    public Result run() {
//		processer.login();
    	Spider.create(processer).addUrl("https://m.weibo.cn/api/container/getIndex?containerid=102803").addPipeline(pipeline).run();
        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Result(0, "更新完毕！", null);
    }


    public Result schedule() {
        return new Result(0, "成功！", 50);
    }

}
