package com.zhangyujie.poms.service;

import com.zhangyujie.poms.entity.Result;

public interface WeiboCrawler {
	public Result run();
	public Result schedule();
}
