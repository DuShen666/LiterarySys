package com.ccut.literary.IDao;

import org.apache.ibatis.annotations.Param;

import com.ccut.literary.domain.Support;

public interface SupportDao {
	int deleteByPrimaryKey(Integer supportId);

	int insert(Support record);

	int insertSelective(Support record);

	Support selectByPrimaryKey(Integer supportId);

	int updateByPrimaryKeySelective(Support record);

	int updateByPrimaryKey(Support record);

	int getSupportNumByKey(@Param("poetryId")int poetryId);

	int delete(@Param("poetryId")int poetryId, @Param("userId")int userId);

	int deleteCom(@Param("commentId")int commentId, @Param("userId")int userId);

	int isSupport(@Param("poetryId")int poetryId, @Param("userId")int userId);

	int isSupportCom(@Param("commentId")int commentId, @Param("userId")int userId);

	int getComSupportNum(@Param("commentId")int commentId);
}