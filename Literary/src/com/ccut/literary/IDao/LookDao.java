package com.ccut.literary.IDao;

import org.apache.ibatis.annotations.Param;

import com.ccut.literary.domain.Look;

public interface LookDao {
    int deleteByPrimaryKey(Integer lookid);

    int insert(Look record);

    int insertSelective(Look record);

    Look selectByPrimaryKey(Integer lookid);

    int updateByPrimaryKeySelective(Look record);

    int updateByPrimaryKey(Look record);
    
    int getNum(@Param("poetryId")int poetryId);
}