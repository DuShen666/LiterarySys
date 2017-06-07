package com.ccut.literary.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ccut.literary.IDao.CommentDao;
import com.ccut.literary.IDao.LookDao;
import com.ccut.literary.IDao.PoetryDao;
import com.ccut.literary.IDao.SupportDao;
import com.ccut.literary.domain.Comment;
import com.ccut.literary.domain.Look;
import com.ccut.literary.domain.Poetry;
import com.ccut.literary.domain.Support;

@Service
public class PoetryServiceImpl implements IPoetryService {

	@Resource
	private PoetryDao poetryDao;
	@Resource
	private SupportDao supportDao;
	@Resource
	private CommentDao commentDao;
	@Resource
	private LookDao lookDao;

	@Override
	public List<Poetry> getPoetrys(int userId, int i, int j) {
		return poetryDao.getPoetries(userId, i, j);
	}

	@Override
	public Poetry getPoetryById(int poetryId) {
		return poetryDao.selectByPrimaryKey(poetryId);
	}

	@Override
	public int getGoodNum(int poetryId) {
		return supportDao.getSupportNumByKey(poetryId);
	}

	@Override
	public int getCommentNum(int poetryId) {
		return commentDao.getCommentNumByKey(poetryId);
	}

	@Override
	public int getLookNum(int poetryId) {
		return lookDao.getNum(poetryId);
	}

	@Override
	public List<Comment> getComments(int poetryId, int i, int j) {
		return commentDao.getCommentsByPoetryId(poetryId, i, j);
	}

	@Override
	public int insert(Poetry poetry) {
		return poetryDao.insert(poetry);
	}

	@Override
	public List<Poetry> getMine(int userId, int i, int j) {
		return poetryDao.getMine(userId, i, j);
	}

	@Override
	public List<Poetry> find(int i, int j, int type) {
		return poetryDao.find(i, j, type);
	}

	@Override
	public int look(int userId, int poetryId) {
		return lookDao.insert(new Look(poetryId, userId));
	}

	@Override
	public int toComment(int userId, int poetryId, String content) {
		return commentDao.insert(new Comment(poetryId, userId, content));
	}

	@Override
	public int support(int userId, int poetryId) {// 赞文章
		return supportDao.insert(new Support(poetryId, userId, 0, 0));
	}

	@Override
	public int supportCom(int userId, int commentId) {// 赞评论
		return supportDao.insert(new Support(userId, 1, commentId));
	}

	@Override
	public int desupport(int userId, int poetryId) {
		return supportDao.delete(poetryId, userId);
	}

	@Override
	public int desupportCom(int userId, int commentId) {
		return supportDao.deleteCom(commentId, userId);
	}

	@Override
	public boolean isSupport(int userId, int commentId, int poetryId, int type) {
		int n = 0;
		if (type == 0) {
			n = supportDao.isSupport(poetryId, userId);
		} else {
			n = supportDao.isSupportCom(commentId, userId);
		}
		if (n > 0) {
			return true;
		}
		return false;
	}

	@Override
	public int commentSupNum(int commentId) {
		return supportDao.getComSupportNum(commentId);
	}

	@Override
	public List<Poetry> getRecent(int role) {
		return poetryDao.getRecent(role);
	}

	@Override
	public int delete(int poetryId, int role) {
		System.out.println(poetryId+",,"+role);
		Poetry poetry = poetryDao.selectByPrimaryKey(poetryId);
		System.out.println(poetry.getUserId());
		if (poetry.getUserId() != role) {
			return -1;
		}
		int n = poetryDao.deleteByPrimaryKey(poetryId);
		if (n > 0) {
			return 0;
		}
		return -1;
	}

}
