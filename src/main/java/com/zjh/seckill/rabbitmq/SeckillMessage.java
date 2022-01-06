package com.zjh.seckill.rabbitmq;

import com.zjh.seckill.domain.SeckillUser;

/**
 * 秒杀消息类
 * 
 * @author ztq
 * @date 2018年10月26日
 */
public class SeckillMessage {

    /**
     * 秒杀用户
     */
    private SeckillUser user;
    /**
     * 秒杀商品id
     */
    private long goodsId;

    public SeckillUser getUser() {
        return user;
    }

    public void setUser(SeckillUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
