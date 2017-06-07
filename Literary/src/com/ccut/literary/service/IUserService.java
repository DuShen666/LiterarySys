package com.ccut.literary.service;

import java.util.List;

import com.ccut.literary.domain.Message;
import com.ccut.literary.domain.User;

public interface IUserService {
	public User getUserById(int userId);

	public int insert(User user);

	public boolean isAttention(int userId, int bUserId);
	
	public List<Message> getMsgs(int userId,int i,int j,int type);

	public List<User> getAttentions(int userId);
	
	public int attention(int userId,int bUserId);
	
	public int deAttention(int userId,int bUserId);
	
	public int update(User user);
	
	public boolean hasUser(User user);
}