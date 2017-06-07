package com.ccut.literary.IDao;

import org.apache.ibatis.annotations.Param;

import com.ccut.literary.domain.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    int updateByOpenId(User record);
    
    User getUserByOpenId(@Param("openId")String openId);
}