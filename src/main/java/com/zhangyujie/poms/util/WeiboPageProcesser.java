package com.zhangyujie.poms.util;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

@Component("weiboPageProcesser")
public class WeiboPageProcesser implements PageProcessor {
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setTimeOut(3000);
	//存储cookie信息
	private Set<Cookie> cookies;
	
	private int pageNum = 1;
	
	private void collectSpeeches(Page page) {
		
		page.putField("type", "speech");
		
		String ok = new JsonPathSelector("$.ok").select(page.getRawText()).trim();
		List<String> ids = new JsonPathSelector("$..cards[*].mblog.id").selectList(page.getRawText());
		System.out.println("ids:"+ids.size());
		
		if ("1".equals(ok) && CollectionUtils.isNotEmpty(ids)) {
			for (String id : ids) {
				//添加微博内容url
				page.addTargetRequest("https://m.weibo.cn/statuses/extend?id=" + id);
				//添加评论url
				page.addTargetRequest("https://m.weibo.cn/api/comments/show?id=" + id + "&page=1");
			}
		
			page.putField("weiboId", ids);
			page.putField("userId", new JsonPathSelector("$..cards[*].mblog.user.id").selectList(page.getRawText()));
			page.putField("userName", new JsonPathSelector("$..cards[*].mblog.user.screen_name").selectList(page.getRawText()));
			page.putField("userGender", new JsonPathSelector("$..cards[*].mblog.user.gender").selectList(page.getRawText()));
			page.putField("text", new JsonPathSelector("$..cards[*].mblog.text").selectList(page.getRawText()));
			page.putField("createdAt", new JsonPathSelector("$..cards[*].mblog.created_at").selectList(page.getRawText()));
			page.putField("attitudesCount", new JsonPathSelector("$..cards[*].mblog.attitudes_count").selectList(page.getRawText()));
			page.putField("commentsCount", new JsonPathSelector("$..cards[*].mblog.comments_count").selectList(page.getRawText()));
			page.putField("repostsCount", new JsonPathSelector("$..cards[*].mblog.reposts_count").selectList(page.getRawText()));
			if (pageNum > 1) {
				return;
			}
			page.addTargetRequest("https://m.weibo.cn/api/container/getIndex?containerid=102803&since_id=" + (pageNum++));
		} else {
			page.setSkip(true);
		}
	}
	private void collectContents(Page page) {
		page.putField("type", "content");
		
		String ok = new JsonPathSelector("$.ok").select(page.getRawText()).trim();
		if (!"1".equals(ok)) {
			page.setSkip(true);
			return;
		}
		page.putField("weiboId", page.getUrl().regex("(?<=id=)[0-9]+").get().trim());
		page.putField("content", new JsonPathSelector("$.longTextContent").select(page.getRawText()));
	}
	// 采集评论信息
	private void collectComments(Page page) {
		page.putField("type", "comment");
		
		String ok = new JsonPathSelector("$.ok").select(page.getRawText()).trim();
		if (!"1".equals(ok)) {
			page.setSkip(true);
			return;
		}
		String weiboId = page.getUrl().regex("[0-9]+(?=&)").get();
		
		page.putField("weiboId", weiboId);
		page.putField("commentId", new JsonPathSelector("$.data[*].id").selectList(page.getRawText()));
		page.putField("userId", new JsonPathSelector("$.data[*].user.id").selectList(page.getRawText()));
		page.putField("userName", new JsonPathSelector("$.data[*].user.screen_name").selectList(page.getRawText()));
		page.putField("text", new JsonPathSelector("$.data[*].text").selectList(page.getRawText()));
		page.putField("createdAt", new JsonPathSelector("$.data[*].created_at").selectList(page.getRawText()));
		
		int n = Integer.parseInt(page.getUrl().regex("(?<=&page=)[0-9]+").get());
		page.addTargetRequest("https://m.weibo.cn/api/comments/show?id=" + weiboId + "&page=" + (n + 1));
	}
	
	public void process(Page page) {
		
		if (page.getUrl().regex("https://m\\.weibo\\.cn/api/container/getIndex\\?containerid=102803(&since_id=[0-9]+)?").match()) {
			//如果是列表页面，则采集微博信息并获取微博详细内容链接
			collectSpeeches(page);
			System.out.println("collectSpeeches......");
		} else if (page.getUrl().regex("https://m\\.weibo\\.cn/statuses/extend\\?id=[0-9]+").match()){
			//如果是微博详细信息页面，则采集微博内容
			collectContents(page);
			System.out.println("collectContents......");
		} else if (page.getUrl().regex("https://m\\.weibo\\.cn/api/comments/show\\?id=[0-9]+&page=[0-9+]").match()){
			//微博评论页面
			collectComments(page);
			System.out.println("collectComments......");
		} else {
			page.setSkip(true);
		}
	}

	public Site getSite() {
		return site;
	}
	//使用 selenium 来模拟用户的登录获取cookie信息
    public void login() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://passport.weibo.cn/signin/login");
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        driver.findElement(By.id("loginName")).clear();

        //填用户名
        driver.findElement(By.id("loginName")).sendKeys("15545544769");

        driver.findElement(By.id("loginPassword")).clear();
        //填密码
        driver.findElement(By.id("loginPassword")).sendKeys("123210");

        //模拟点击登录按钮
       // driver.findElement(By.cssSelector(".W_login_form .login_btn a")).click();
        Actions action = new Actions(driver);
        action.click(driver.findElement(By.id("loginAction")));
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        //获取cookie信息
        cookies = driver.manage().getCookies();
        driver.close(); 

        //将获取到的cookie信息添加到webmagic中
        for (Cookie cookie : cookies) { 
            site.addCookie(cookie.getName().toString(),cookie.getValue().toString());
            System.out.println(cookie.getName());
        }
    }

}
