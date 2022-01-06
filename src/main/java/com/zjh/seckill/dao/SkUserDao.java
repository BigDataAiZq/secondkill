package com.zjh.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zjh.seckill.domain.SeckillUser;

@Mapper
public interface SkUserDao {

    @Select("select * from sk_user where id = #{id}")
    public SeckillUser getById(@Param("id") long id);

    @Update("update sk_user set password = #{password} where id = #{id}")
    public void updatePwd(SeckillUser toBeUpdate);

    @Update("update sk_user set last_login_date = #{lastLoginDate} where id = #{id}")
    public void updateLoginDate(SeckillUser toBeUpdate);
}
