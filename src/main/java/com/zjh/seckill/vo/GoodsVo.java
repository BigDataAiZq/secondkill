package com.zjh.seckill.vo;

import java.util.Date;

import com.zjh.seckill.domain.Goods;

public class GoodsVo extends Goods {

    /**
     * 秒杀价格
     */
    private Double skPrice;
    /**
     * 库存数量
     */
    private Integer stockCount;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 数据库版本
     */
    private int version;

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getSkPrice() {
        return skPrice;
    }

    public void setSkPrice(Double skPrice) {
        this.skPrice = skPrice;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
