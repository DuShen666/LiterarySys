package com.ccut.literary.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.ccut.literary.domain.SessionKey;
import com.ccut.literary.domain.WeiUserInfo;

import redis.clients.jedis.Jedis;

public class SafetyUtil {
	private static Logger log = Logger.getLogger(SafetyUtil.class.getName());
	private static Jedis jedis;
	static {
		jedis = new Jedis("127.0.0.1", 6379);
	}

	public static WeiUserInfo decode(String encryptedData,
			SessionKey sessionKey, String iv) throws Exception {

		final String KEY_ALGORITHM = "AES";
		final String algorithmStr = "AES/CBC/PKCS7Padding";
		//
		Key key;
		Cipher cipher = null;
		byte[] keyBytes = Base64.decodeBase64(sessionKey.getSession_key());
		int base = 16;
		if (keyBytes.length % base != 0) {
			int groups = keyBytes.length / base
					+ (keyBytes.length % base != 0 ? 1 : 0);
			byte[] temp = new byte[groups * base];
			Arrays.fill(temp, (byte) 0);
			System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
			keyBytes = temp;
		}
		Security.addProvider(new BouncyCastleProvider());
		key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
		try {
			cipher = Cipher.getInstance(algorithmStr, "BC");
		} catch (Exception e) {
			e.printStackTrace();
		}

		cipher.init(Cipher.DECRYPT_MODE, key,
				new IvParameterSpec(Base64.decodeBase64(iv)));
		byte[] result = cipher.doFinal(Base64
				.decodeBase64(encryptedData));
		String json = new String(result,"UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(json);
		WeiUserInfo weiUserInfo = new WeiUserInfo(jsonObject);
		return weiUserInfo;
	}

	public static SessionKey getSessionKey(String code) throws IOException {
		String urlpath = "https://api.weixin.qq.com/sns/jscode2session?appid="
				+ Config.APP_ID + "&secret=" + Config.SECRET + "&js_code="
				+ code + "&grant_type=authorization_code";
		URL url = new URL(urlpath);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String jsonStr = "";
		jsonStr += br.readLine();
		JSONObject json = JSONObject.fromObject(jsonStr);
		SessionKey sessionKey = new SessionKey();
		System.out.println(jsonStr);
		sessionKey.setOpenId(json.getString("openid"));
		sessionKey.setSession_key(json.getString("session_key"));
		return sessionKey;
	}

	public static boolean verifySession(String session) {
		if (getUserId(session) == null) {
			log.info("会话过期" + session);
			return false;
		}
		return true;
	}

	public static String getUserId(String session) {
		String data = jedis.get(session);
		return data;
	}

	public static String saveSession(int userId) {
		String session_id = RandomStringUtils.randomAlphanumeric(16);
		jedis.setex(session_id, Config.SESSION_VALID_TIME, userId + "");
		return session_id;
	}

	public static int getRole(HttpServletRequest request) {
		String token = request.getHeader("session");
		int role = -1;
		if (getUserId(token) != null) {
			role = Integer.parseInt(getUserId(token));
		}
		log.info("身份认证："+token+",,,,"+role);
		return role;
	}

}
