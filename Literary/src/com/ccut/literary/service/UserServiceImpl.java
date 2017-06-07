package com.ccut.literary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccut.literary.IDao.AttentionDao;
import com.ccut.literary.IDao.MessageDao;
import com.ccut.literary.IDao.UserDao;
import com.ccut.literary.domain.Attention;
import com.ccut.literary.domain.Message;
import com.ccut.literary.domain.User;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserDao userDao;

	@Resource
	private AttentionDao attentionDao;

	@Resource
	private MessageDao messageDao;

	@Override
	public User getUserById(int userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public int insert(User user) {// 返回userid
		userDao.insert(user);
		int userId = -1;
		userId = userDao.getUserByOpenId(user.getOpenId()).getUserId();
		return userId;
	}

	@Override
	public boolean isAttention(int userId, int bUserId) {
		int n = attentionDao.isAttention(userId, bUserId);
		if (n == 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<Message> getMsgs(int userId, int i, int j, int k) {
		if (k == 0) {
			return messageDao.getSysMsgs(userId, i, j);
		}
		return messageDao.getMsgs(userId, i, j);
	}

	@Override
	public List<User> getAttentions(int userId) {
		List<Integer> userIds = attentionDao.attentions(userId);
		List<User> users = new ArrayList<>();
		for (Integer id : userIds) {
			User user = userDao.selectByPrimaryKey(id);
			users.add(user);
		}
		return users;
	}

	@Override
	public int attention(int userId, int bUserId) {
		return attentionDao.insert(new Attention(userId, bUserId));
	}

	@Override
	public int deAttention(int userId, int bUserId) {
		return attentionDao.remove(userId, bUserId);
	}

	@Override
	public int update(User user) {
		int n = 0;
		n = userDao.updateByOpenId(user);
		if (n > 0) {
			return userDao.getUserByOpenId(user.getOpenId()).getUserId();
		}
		return -1;
	}

	@Override
	public boolean hasUser(User user) {
		User user2 = null;
		user2 = userDao.getUserByOpenId(user.getOpenId());
		if (user2 == null) {
			return false;
		}
		return true;
	}
}
