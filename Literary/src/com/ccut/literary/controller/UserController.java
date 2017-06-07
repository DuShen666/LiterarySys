package com.ccut.literary.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccut.literary.domain.Message;
import com.ccut.literary.domain.SessionKey;
import com.ccut.literary.domain.User;
import com.ccut.literary.domain.WeiUserInfo;
import com.ccut.literary.service.IUserService;
import com.ccut.literary.util.SafetyUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger log = Logger
			.getLogger(UserController.class.getName());
	@Resource
	private IUserService userService;

	@ResponseBody
	@RequestMapping("/msgs")
	public String msgs(HttpServletRequest request, Model model) {
		int role = SafetyUtil.getRole(request);
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		int i = data.getInt(0);
		int j = data.getInt(1);
		int type = data.getInt(2);
		List<Message> messages = userService.getMsgs(role, i * j - j, j, type);
		JSONArray resultArray = new JSONArray();
		for (Message message : messages) {
			User user = userService.getUserById(message.getFromUserId());
			JSONObject object = JSONObject.fromObject(message);
			object.element("nickName", user.getNickName());
			object.element("avatarurl", user.getAvatarurl());
			resultArray.add(object);
		}
		log.info("用户" + role + "____获取消息列表");
		return resultArray.toString();
	}

	@ResponseBody
	@RequestMapping("/attentions")
	public String attentions(HttpServletRequest request, Model model) {
		int role = SafetyUtil.getRole(request);
		List<User> attentions = userService.getAttentions(role);
		JSONArray resultArray = new JSONArray();
		for (User user : attentions) {
			JSONObject object = JSONObject.fromObject(user);
			resultArray.add(object);
		}
		log.info("用户" + role + "____获取关注列表");
		return resultArray.toString();
	}

	@ResponseBody
	@RequestMapping("/attention")
	public String attention(HttpServletRequest request, Model model) {
		int role = SafetyUtil.getRole(request);
		int bUserId = Integer.parseInt(request.getParameter("bUserId"));
		int n = 0;
		n = userService.attention(role, bUserId);
		if (n > 0) {
			log.info("用户" + role + "____关注" + bUserId + "成功");
			return "success";
		}
		log.info("用户" + role + "____关注" + bUserId + "失败");
		return "fail";
	}

	@ResponseBody
	@RequestMapping("/deAttention")
	public String deAttention(HttpServletRequest request, Model model) {
		int role = SafetyUtil.getRole(request);
		int bUserId = Integer.parseInt(request.getParameter("bUserId"));
		int n = 0;
		n = userService.deAttention(role, bUserId);
		if (n > 0) {
			log.info("用户" + role + "____取消关注" + bUserId + "成功");
			return "success";
		}
		log.info("用户" + role + "____取消关注" + bUserId + "失败");
		return "fail";
	}

	@ResponseBody
	@RequestMapping("/getUser")
	public String getUser(HttpServletRequest request, Model model) {
		int role = SafetyUtil.getRole(request);
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = userService.getUserById(userId);
		JSONObject object = JSONObject.fromObject(user);
		object.element("isAttention", userService.isAttention(role, userId));
		return object.toString();
	}

	@ResponseBody
	@RequestMapping("/oAttentions")
	public String oAttentions(HttpServletRequest request, Model model) {
		int role = SafetyUtil.getRole(request);
		int userId = Integer.parseInt(request.getParameter("userId"));
		List<User> attentions = userService.getAttentions(userId);
		JSONArray resultArray = new JSONArray();
		for (User user : attentions) {
			JSONObject object = JSONObject.fromObject(user);
			resultArray.add(object);
		}
		log.info("用户" + role + "____获取" + userId + "关注列表");
		return resultArray.toString();
	}

	@ResponseBody
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {

		String code = request.getParameter("code");
		String iv = request.getParameter("iv");
		String encryptedData = request.getParameter("encryptedData");
		// String signature = request.getParameter("signature");

		SessionKey sessionKey = null;
		try {
			sessionKey = SafetyUtil.getSessionKey(code);
		} catch (IOException e) {
			e.printStackTrace();
		}
		WeiUserInfo userInfo = null;
		try {
			userInfo = SafetyUtil.decode(encryptedData, sessionKey, iv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(userInfo.toString());
		int userId = -1;
		if (userService.hasUser(userInfo.toUser())) {
			userId = userService.update(userInfo.toUser());
		} else {
			userId = userService.insert(userInfo.toUser());
		}
		if (userId == -1) {
			return "fail";
		}
		String token = SafetyUtil.saveSession(userId);
		return token;
	}

	@ResponseBody
	@RequestMapping("/verify")
	public String verify(HttpServletRequest request, Model model) {
		String token = request.getParameter("session");
		if (SafetyUtil.verifySession(token)) {
			log.info(token + "____验证通过");
			return "success";
		}
		log.info(token + "____验证失败");
		return "fail";
	}

	@ResponseBody
	@RequestMapping("/getOwnId")
	public String getOwnId(HttpServletRequest request, Model model) {
		return SafetyUtil.getRole(request) + "";
	}
}
