package com.zjh.seckill.service;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjh.seckill.dao.SkUserDao;
import com.zjh.seckill.domain.SeckillUser;
import com.zjh.seckill.exception.GlobalException;
import com.zjh.seckill.redis.RedisService;
import com.zjh.seckill.redis.SecUserKey;
import com.zjh.seckill.result.CodeMsg;
import com.zjh.seckill.util.MD5Util;
import com.zjh.seckill.util.UUIDUtil;
import com.zjh.seckill.vo.LoginVo;

@Service
public class SkUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    SkUserDao skUserDao;

    @Autowired
    RedisService redisService;

    public SeckillUser getById(long id) {
        // 取缓存
        SeckillUser user = redisService.get(SecUserKey.getById, "" + id, SeckillUser.class);
        if (user != null) {
            return user;
        }
        // 取数据库
        user = skUserDao.getById(id);
        if (user != null) {
            redisService.set(SecUserKey.getById, "" + id, user);
        }
        return user;
    }

    public boolean updatePassword(String token, long id, String formPass) {
        // 取user
        SeckillUser user = getById(id);
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        // 更新数据库
        SeckillUser toBeUpdate = new SeckillUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        skUserDao.updatePwd(toBeUpdate);
        // 处理缓存
        redisService.delete(SecUserKey.getById, "" + id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(SecUserKey.token, token, user);
        return true;
    }

    /**
     * redis缓存中获取用户对象
     * 
     * @param response
     * @param token
     * @return
     */
    public SeckillUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SeckillUser user = redisService.get(SecUserKey.token, token, SeckillUser.class);
        // 延长有效期
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        // 判断手机号是否存在
        SeckillUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        // 验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        System.out.println(formPass);
        System.out.println(saltDB);
        System.out.println(calcPass);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        // 生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        // 更新登录时间
        SeckillUser toBeUpdate = new SeckillUser();
        toBeUpdate.setId(Long.parseLong(loginVo.getMobile()));
        toBeUpdate.setLastLoginDate(new Date());
        skUserDao.updateLoginDate(toBeUpdate);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token, SeckillUser user) {
        redisService.set(SecUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(SecUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
