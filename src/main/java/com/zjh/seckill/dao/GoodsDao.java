package com.zjh.seckill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zjh.seckill.domain.SeckillGoods;
import com.zjh.seckill.vo.GoodsVo;

@Mapper
public interface GoodsDao {

    @Select("select g.*,sg.stock_count, sg.start_date, sg.end_date,sg.sk_price from sk_goods_seckill sg left join sk_goods g on sg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,sg.stock_count, sg.start_date, sg.end_date,sg.sk_price from sk_goods_seckill sg left join sk_goods g on sg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    // stock_count > 0 和 版本号实现乐观锁 防止超卖
    @Update("update sk_goods_seckill set stock_count = stock_count - 1, version= version + 1 where goods_id = #{goodsId} and stock_count > 0 and version = #{version}")
    public int reduceStockByVersion(SeckillGoods g);

    @Update("update sk_goods_seckill set stock_count = #{stockCount} where goods_id = #{goodsId}")
    public int resetStock(SeckillGoods g);

}
