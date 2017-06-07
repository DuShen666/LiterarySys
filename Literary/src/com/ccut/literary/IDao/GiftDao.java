package com.ccut.literary.IDao;

import com.ccut.literary.domain.Gift;

public interface GiftDao {
    int deleteByPrimaryKey(Integer giftId);

    int insert(Gift record);

    int insertSelective(Gift record);

    Gift selectByPrimaryKey(Integer giftId);

    int updateByPrimaryKeySelective(Gift record);

    int updateByPrimaryKey(Gift record);
}