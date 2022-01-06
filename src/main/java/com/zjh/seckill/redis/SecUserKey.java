package com.zjh.seckill.redis;

public class SecUserKey extends BasePrefix{

	public static final int TOKEN_EXPIRE = 3600*24 * 2;
	private SecUserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static SecUserKey token = new SecUserKey(TOKEN_EXPIRE, "tk");
	public static SecUserKey getById = new SecUserKey(0, "id");
}
