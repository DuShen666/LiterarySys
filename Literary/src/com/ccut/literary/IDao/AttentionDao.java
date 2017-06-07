package com.ccut.literary.IDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ccut.literary.domain.Attention;

public interface AttentionDao {
	int deleteByPrimaryKey(Integer attentionId);

	int insert(Attention record);

	int insertSelective(Attention record);

	Attention selectByPrimaryKey(Integer attentionId);

	int updateByPrimaryKeySelective(Attention record);

	int updateByPrimaryKey(Attention record);

	int isAttention(@Param("userId")int userId, @Param("bUserId")int bUserId);
	
	List<Integer> attentions(@Param("userId")int userId);
	
	int remove(@Param("userId")int userId, @Param("bUserId")int bUserId);
}