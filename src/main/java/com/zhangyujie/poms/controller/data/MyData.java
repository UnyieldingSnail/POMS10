package com.zhangyujie.poms.controller.data;

import com.zhangyujie.poms.entity.Result;
import com.zhangyujie.poms.service.WeiboCrawler;
import com.zhangyujie.poms.service.WeiboService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/weibo")
public class MyData {
    @Resource
    private WeiboService service;

    @ResponseBody
    @RequestMapping("/all")
    public Result select(@Param(value = "pageNum") Integer pageNum) {
        System.out.println("done");
        return service.selectAll(pageNum);
    }

    @ResponseBody
    @RequestMapping("/predict")
    public Result predict(@Param(value = "weiboId") String weiboId) {
        //http://localhost:8080/poms/weibo/predict?weiboId=4241132072076706
        System.out.println("done");
        return service.predict(weiboId);
    }
    @ResponseBody
    @RequestMapping("/describe")
    public Result describe() {
        System.out.println("done");
        return service.describe();
    }
}
