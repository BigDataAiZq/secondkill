package com.zjh.seckill.vo;

import com.zjh.seckill.domain.SeckillUser;

/**
 * 商品详情值对象
 * 
 * @author ztq
 * @date 2018年10月26日
 */
public class GoodsDetailVo {

    /**
     * 秒杀状态 0 未开始 1进行中 2已结束
     */
    private int seckillStatus = 0;
    /**
     * 剩余秒杀
     */
    private int remainSeconds = 0;
    /**
     * 商品
     */
    private GoodsVo goods;
    /**
     * 用户
     */
    private SeckillUser user;

    public int getSeckillStatus() {
        return seckillStatus;
    }

    public void setSeckillStatus(int seckillStatus) {
        this.seckillStatus = seckillStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    public SeckillUser getUser() {
        return user;
    }

    public void setUser(SeckillUser user) {
        this.user = user;
    }
}
