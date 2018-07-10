package com.zhangyujie.poms.service;

import com.zhangyujie.poms.entity.Result;

public interface WeiboService {
    public Result selectAll(Integer pageNum);

    Result predict(String weiboId);

    Result describe();
}
