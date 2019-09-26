package com.yuw.dao;

import com.yuw.bean.UserInfoBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoBeanMapper {


    int insert(UserInfoBean record);

    int insertSelective(UserInfoBean record);

    List<UserInfoBean> selectUserInfoByParams(@Param("record") UserInfoBean record);
    int deleteByPrimaryKey( UserInfoBean record );

    int updateByPrimaryKey(UserInfoBean record);
    List<UserInfoBean> selectByPrimaryKey(@Param("id") Integer id);
}