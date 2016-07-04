package com.dc.sfs.service.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 用户服务
 * 
 * @author Daemon
 *
 */
public class UserService {

	/**
	 * 创建用于测试的用户账号（用户名对应用户Id）（仅测试用）
	 */
	private static final HashMap<String, Integer> userNameMapUserId = new HashMap<String, Integer>();
	static {
		
		for( int i=1; i<1000000; i++ )
			userNameMapUserId.put(String.valueOf(i), i);
	}
	
	/**
	 * 
	 * 用户登录检查（仅测试用）
	 * 
	 * @param userName
	 * @param sessionId
	 * @param password
	 * @return
	 */
	public Integer userLoginCheck(String userName, String sessionId, String password) {
		
		//故意延迟，以增加用户在登录时候断线的几率(这段时间越长，给测试用户的断线事件就越多，断线测试代码详见：LoginLogoutDisconetPressTest 160117_177)
		try {
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//模拟判断用户名密码是否正确（用户名在userNameMapUserId中，而且密码是"888888"）
		Integer userId = userNameMapUserId.get(userName);
		if( userId != null && getHash( (new StringBuilder(sessionId).append("888888").toString()) ).equals(password) ) {
			
			return userId;
			
		} else {
			
			return null;
		}
		
	}
	
	/**
	 * md5加密
	 * 
	 * @param s
	 * @return
	 */
	private final String getHash(String s) {
		
		try {
			
			byte[] data = s.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(data);
			return toHexString(messageDigest.digest());
			
		} catch (NoSuchAlgorithmException e) {
			
			System.out.println("ACCIDENT:MessageDigest MD5 Erro !");
			return "";
		}
		
	}
	
	/**
	 * md5加密
	 * 
	 * @param byteData
	 * @return
	 */
	private final String toHexString(byte[] byteData) {
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(byteData[i] & 0xFF);
			if (hex.length() == 1) {
				hex = "0" + hex;
			}
			sb.append(hex);
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 用户登录到服务（仅测试用）
	 * 
	 * @param userId
	 */
	public void userLoginToServer(int userId) {
		
		// TODO login log to db
	}
	
	/**
	 * 用户从服务上退出（仅测试用）
	 * 
	 * @param userId
	 * @param isDisconect
	 */
	public void userLogout(int userId, boolean isDisconect) {
		
		//故意延迟
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// TODO logout log to db
	}
}





