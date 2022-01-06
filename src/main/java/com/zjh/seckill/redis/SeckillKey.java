package com.zjh.seckill.redis;

public class SeckillKey extends BasePrefix {

    private SeckillKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillKey isGoodsOver = new SeckillKey(0, "go"); // 商品卖完
    public static SeckillKey getSeckillPath = new SeckillKey(60, "sp"); // 秒杀路径
    public static SeckillKey getSeckillVerifyCode = new SeckillKey(300, "vc"); // 秒杀验证码
}
