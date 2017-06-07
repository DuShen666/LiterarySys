package com.ccut.literary.controller;

import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.ccut.literary.domain.Comment;
import com.ccut.literary.domain.Poetry;
import com.ccut.literary.domain.User;
import com.ccut.literary.service.IPoetryService;
import com.ccut.literary.service.IUserService;
import com.ccut.literary.util.SafetyUtil;
import com.ccut.literary.util.Util;

@Controller
@RequestMapping("poetry")
public class PoetryController {
	private static Logger log = Logger.getLogger(PoetryController.class
			.getName());

	@Resource
	private IPoetryService poetryService;

	@Resource
	private IUserService userService;

	@ResponseBody
	@RequestMapping("news")
	public String news(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		int i = data.getInt(0);
		int j = data.getInt(1);
		JSONArray resultArray = new JSONArray();
		List<Poetry> poetries = poetryService.getPoetrys(role, i * j - j, j);
		for (Poetry poetry : poetries) {
			User user = userService.getUserById(poetry.getUserId());
			JSONObject object = JSONObject.fromObject(poetry);
			object.element("nickName", user.getNickName());
			object.element("avatarurl", user.getAvatarurl());
			object.element("createDate",
					Util.formatTime(poetry.getCreateDate()));
			object.element("picUrl", poetry.getPicUrl());
			object.element("goodNum",
					poetryService.getGoodNum(poetry.getPoetryId()));
			object.element("commentNum",
					poetryService.getCommentNum(poetry.getPoetryId()));
			object.element("lookNum",
					poetryService.getLookNum(poetry.getPoetryId()));
			resultArray.add(object.toString());
		}
		log.info("用户" + role + "____获取动态列表");
		return resultArray.toString();
	}

	@ResponseBody
	@RequestMapping("my")
	public String my(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		int i = data.getInt(0);
		int j = data.getInt(1);
		JSONArray resultArray = new JSONArray();
		List<Poetry> poetries = poetryService.getMine(role, i * j - j, j);
		for (Poetry poetry : poetries) {
			User user = userService.getUserById(poetry.getUserId());
			JSONObject object = JSONObject.fromObject(poetry);
			object.element("nickName", user.getNickName());
			object.element("avatarurl", user.getAvatarurl());
			object.element("createDate",
					Util.formatTime(poetry.getCreateDate()));
			object.element("picUrl", poetry.getPicUrl());
			object.element("goodNum",
					poetryService.getGoodNum(poetry.getPoetryId()));
			object.element("commentNum",
					poetryService.getCommentNum(poetry.getPoetryId()));
			object.element("lookNum",
					poetryService.getLookNum(poetry.getPoetryId()));
			resultArray.add(object.toString());
		}
		log.info("用户" + role + "____我的文章列表");
		return resultArray.toString();
	}

	@ResponseBody
	@RequestMapping("others")
	public String others(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		int i = data.getInt(0);
		int j = data.getInt(1);
		int userId = data.getInt(2);
		JSONArray resultArray = new JSONArray();
		List<Poetry> poetries = poetryService.getMine(userId, i * j - j, j);
		for (Poetry poetry : poetries) {
			User user = userService.getUserById(poetry.getUserId());
			JSONObject object = JSONObject.fromObject(poetry);
			object.element("nickName", user.getNickName());
			object.element("avatarurl", user.getAvatarurl());
			object.element("createDate",
					Util.formatTime(poetry.getCreateDate()));
			object.element("picUrl", poetry.getPicUrl());
			object.element("goodNum",
					poetryService.getGoodNum(poetry.getPoetryId()));
			object.element("commentNum",
					poetryService.getCommentNum(poetry.getPoetryId()));
			object.element("lookNum",
					poetryService.getLookNum(poetry.getPoetryId()));
			resultArray.add(object.toString());
		}
		log.info("用户" + role + "____获取用户" + userId + "文章列表");
		return resultArray.toString();
	}

	@ResponseBody
	@RequestMapping("find")
	public String find(HttpServletRequest request) {
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		int i = data.getInt(0);
		int j = data.getInt(1);
		int type = data.getInt(2);
		JSONArray resultArray = new JSONArray();
		List<Poetry> poetries = poetryService.find(i * j - j, j, type);
		for (Poetry poetry : poetries) {
			User user = userService.getUserById(poetry.getUserId());
			JSONObject object = JSONObject.fromObject(poetry);
			object.element("nickName", user.getNickName());
			object.element("avatarurl", user.getAvatarurl());
			object.element("createDate",
					Util.formatTime(poetry.getCreateDate()));
			object.element("picUrl", poetry.getPicUrl());
			object.element("goodNum",
					poetryService.getGoodNum(poetry.getPoetryId()));
			object.element("commentNum",
					poetryService.getCommentNum(poetry.getPoetryId()));
			object.element("lookNum",
					poetryService.getLookNum(poetry.getPoetryId()));
			resultArray.add(object.toString());
		}
		log.info("用户____获取发现列表");
		return resultArray.toString();
	}

	@ResponseBody
	@RequestMapping("newDetail")
	public String newDetail(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		int poetryId = Integer.parseInt(request.getParameter("id"));
		Poetry poetry = poetryService.getPoetryById(poetryId);
		JSONObject object = JSONObject.fromObject(poetry);
		object.element("createDate", Util.formatTime(poetry.getCreateDate()));
		User user = userService.getUserById(poetry.getUserId());
		object.element("userInfo", JSONObject.fromObject(user));
		object.element("isAttention",
				userService.isAttention(role, poetry.getUserId()));
		object.element("isSup", poetryService.isSupport(role, -1, poetryId, 0));
		object.element("goodNum",
				poetryService.getGoodNum(poetry.getPoetryId()));
		object.element("commentNum",
				poetryService.getCommentNum(poetry.getPoetryId()));
		object.element("lookNum", poetryService.getLookNum(poetryId));
		poetryService.look(role, poetryId);
		log.info("用户" + role + "____获取详细信息" + poetryId);
		return object.toString();
	}

	@ResponseBody
	@RequestMapping("comments")
	public String comments(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		int i = data.getInt(0);
		int j = data.getInt(1);
		int poetryId = Integer.parseInt(request.getParameter("id"));
		List<Comment> comments = poetryService.getComments(poetryId, i * j - j,
				j);
		JSONArray resultArray = new JSONArray();
		for (Comment comment : comments) {
			JSONObject object = JSONObject.fromObject(comment);
			object.element("isSup", poetryService.isSupport(role,
					comment.getCommentId(), -1, 1));
			object.element("supNum",
					poetryService.commentSupNum(comment.getCommentId()));
			object.element("date", Util.formatTime(comment.getDate()));
			User user = userService.getUserById(comment.getUserId());
			object.element("userInfo", JSONObject.fromObject(user));
			resultArray.add(object.toString());
		}
		log.info("用户" + role + "____获取" + poetryId + "____评论列表");
		return resultArray.toString();
	}

	@ResponseBody
	@RequestMapping("save")
	public String save(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		JSONObject object = JSONObject.fromObject(request
				.getParameter("poetry"));
		System.out.println(object.toString());
		Poetry poetry = new Poetry(object.getString("title"),
				object.getString("content"), role, object.getString("img"),
				Integer.parseInt(object.getString("type")));
		if (poetryService.insert(poetry) > 0) {
			log.info("用户" + role + "____发表文章成功");
			return "success";
		}
		log.info("用户" + role + "____发表文章失败");
		return "fail";
	}

	@ResponseBody
	@RequestMapping("toComment")
	public String toComment(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		String content = request.getParameter("content");
		int poetryId = Integer.parseInt(request.getParameter("poetryId"));

		if (poetryService.toComment(role, poetryId, content) > 0) {
			log.info("用户" + role + "____评论" + poetryId + "成功");
			return "success";
		}
		log.info("用户" + role + "____评论" + poetryId + "失败");
		return "fail";
	}

	@ResponseBody
	@RequestMapping("reply")
	public String reply(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		int action = Integer.parseInt(request.getParameter("action"));
		int n = 0;
		if (action == 0) {
			int poetryId = Integer.parseInt(request.getParameter("id"));
			n = poetryService.support(role, poetryId);
		} else if (action == 1) {
			int commentId = Integer.parseInt(request.getParameter("id"));
			n = poetryService.supportCom(role, commentId);
		} else if (action == 2) {
			int poetryId = Integer.parseInt(request.getParameter("id"));
			n = poetryService.desupport(role, poetryId);
		} else {
			int commentId = Integer.parseInt(request.getParameter("id"));
			n = poetryService.desupportCom(role, commentId);
		}
		if (n > 0) {
			log.info("用户" + role + "____点赞成功");
			return "success";
		}
		log.info("用户" + role + "____电子失败");
		return "fail";
	}

	@ResponseBody
	@RequestMapping("recent")
	public String recent(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		JSONArray resultArray = new JSONArray();
		List<Poetry> poetries = poetryService.getRecent(role);
		for (Poetry poetry : poetries) {
			User user = userService.getUserById(poetry.getUserId());
			JSONObject object = JSONObject.fromObject(poetry);
			object.element("nickName", user.getNickName());
			object.element("avatarurl", user.getAvatarurl());
			object.element("createDate",
					Util.formatTime(poetry.getCreateDate()));
			object.element("picUrl", poetry.getPicUrl());
			object.element("goodNum",
					poetryService.getGoodNum(poetry.getPoetryId()));
			object.element("commentNum",
					poetryService.getCommentNum(poetry.getPoetryId()));
			object.element("lookNum",
					poetryService.getLookNum(poetry.getPoetryId()));
			resultArray.add(object.toString());
		}
		log.info("用户" + role + "____最近查看列表");
		return resultArray.toString();
	}

	@ResponseBody
	@RequestMapping("delete")
	public String delete(HttpServletRequest request) {
		int role = SafetyUtil.getRole(request);
		int id = Integer.parseInt(request.getParameter("id"));
		int n = poetryService.delete(id, role);
		if (n == 0) {
			log.info("用户" + role + "____删除" + id + "成功");
			return "success";
		}
		log.info("用户" + role + "____删除" + id + "失败");
		return "fail";
	}

	@RequestMapping("uploadPic")
	@ResponseBody
	public String upload(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		System.out.println(657567);
		String path = request.getSession().getServletContext()
				.getRealPath("resource");
		String fileName = file.getOriginalFilename();
		// String fileName = new Date().getTime()+".jpg";
		System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request.getContextPath() + "/resource/"
				+ fileName;
	}

}
