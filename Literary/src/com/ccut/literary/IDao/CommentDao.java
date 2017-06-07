package com.ccut.literary.IDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.ccut.literary.domain.Comment;

public interface CommentDao {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    int getCommentNumByKey(@Param("poetryId")int poetryId);
    
    List<Comment> getCommentsByPoetryId(@Param("poetryId")int poetryId,@Param("i")int i,@Param("j")int j);
}