package com.zjh.seckill.redis;

/**
 * 键
 * 
 * @author ztq
 * @date 2018年10月26日
 */
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
