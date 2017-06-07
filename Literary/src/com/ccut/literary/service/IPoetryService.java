package com.ccut.literary.service;

import java.util.List;

import com.ccut.literary.domain.Comment;
import com.ccut.literary.domain.Poetry;

public interface IPoetryService {

	public List<Poetry> getPoetrys(int userId, int i, int j);

	public Poetry getPoetryById(int poetryId);

	public int getGoodNum(int poetryId);

	public int getCommentNum(int poetryId);

	public int getLookNum(int poetryId);

	public List<Comment> getComments(int poetryId,int i,int j);
	
	public int insert(Poetry poetry);
	
	public List<Poetry> getMine(int userId, int i, int j);

	public List<Poetry> find(int i, int j,int type);
	
	public int look(int userId,int poetryId);
	
	public int toComment(int userId,int poetryId,String content);
	
	public int support(int userId,int poetryId);
	
	public int supportCom(int userId,int commentId);
	
	public int desupport(int userId,int poetryId);
	
	public int desupportCom(int userId,int commentId);
	
	public boolean isSupport(int userId,int commentId,int poetryId,int type);
	
	public int commentSupNum(int commentId);
	
	public List<Poetry> getRecent(int role);
	
	public int delete(int poetryId,int role);
}
