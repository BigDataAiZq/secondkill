package com.zjh.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjh.seckill.domain.SeckillUser;
import com.zjh.seckill.redis.RedisService;
import com.zjh.seckill.result.Result;
import com.zjh.seckill.service.SkUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	SkUserService userService;
	
	@Autowired
	RedisService redisService;
	
    @RequestMapping("/info")
    @ResponseBody
    public Result<SeckillUser> info(Model model,SeckillUser user) {
        return Result.success(user);
    }
    
}
