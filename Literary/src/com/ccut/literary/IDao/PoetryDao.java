package com.ccut.literary.IDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ccut.literary.domain.Poetry;

public interface PoetryDao {
	int deleteByPrimaryKey(Integer poetryId);

	int insert(Poetry record);

	int insertSelective(Poetry record);

	Poetry selectByPrimaryKey(Integer poetryId);

	int updateByPrimaryKeySelective(Poetry record);

	int updateByPrimaryKey(Poetry record);

	List<Poetry> getPoetries(@Param("userId") int userId, @Param("i") int i,
			@Param("j") int j);

	List<Poetry> getMine(@Param("userId") int userId, @Param("i") int i,
			@Param("j") int j);

	List<Poetry> find(@Param("i") int i, @Param("j") int j,
			@Param("type") int type);

	List<Poetry> getRecent(@Param("role") int role);
}