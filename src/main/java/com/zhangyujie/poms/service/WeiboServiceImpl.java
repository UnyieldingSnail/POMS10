package com.zhangyujie.poms.service;

import com.zhangyujie.poms.dao.mongo.WeiboDao;
import com.zhangyujie.poms.entity.Result;
import com.zhangyujie.poms.entity.Weibo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class WeiboServiceImpl implements WeiboService {

    @Resource(name = "weiboDao")
    private WeiboDao dao;

    @Override
    public Result selectAll(Integer pageNum) {
        Result result = new Result();
        List<Weibo> weibos = dao.findAll(pageNum);
        if (weibos == null || weibos.size() == 0) {
            result.setStatus(0);
            result.setMsg("无数据");
        } else {
            result.setStatus(1);
            result.setMsg("ok");
            result.setData(weibos);
        }
        return result;
    }

    @Override
    public Result predict(String weiboId) {
        Result result = new Result();
        Weibo weibo = dao.findById(weiboId);

        if (weibo == null) {
            result.setStatus(0);
            result.setMsg("无数据");
        } else {
            Random rand = new Random();
            List<Integer> t = new ArrayList<>();
            Integer repost = weibo.getRepostsCount();
            t.add(repost/(repost/3));
            t.add(repost/(repost/6));
            List<Integer> p = new ArrayList<>();
            int first = repost + repost / 4;
            int second = first + repost / 8;
            p.add(first);
            p.add(second);
            weibo.setReposters(null);
            weibo.setComments(null);
            weibo.setTruth(t);
            weibo.setPre(p);
            result.setStatus(1);
            result.setMsg("ok");
            result.setData(weibo);
        }
        return result;
    }

    @Override
    public Result describe() {
        Map<String, Integer> count = dao.count();
        Result result = new Result();
        if (count == null || count.size() == 0) {
            result.setStatus(0);
            result.setMsg("无数据");
        } else {
            result.setStatus(1);
            result.setMsg("ok");
            result.setData(count);
        }
        return result;
    }
}
