package com.ccut.literary.IDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ccut.literary.domain.Message;

public interface MessageDao {
    int deleteByPrimaryKey(Integer messageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    
    List<Message> getMsgs(@Param("userId")int userId,@Param("i")int i,@Param("j")int j);
    
    List<Message> getSysMsgs(@Param("userId")int userId,@Param("i")int i,@Param("j")int j);
    
}