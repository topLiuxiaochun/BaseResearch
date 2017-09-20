package com.liuxc.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * des对称加密算法实现
 * @author wisdom
 *
 */
public class DESUtil {

	public static void main(String[] args) {
		// 待加密内容
		String str = "10086";
		// 密码，长度要是8的倍数
		String password = "12345678";
		try {
			byte[] result = DESUtil.encode(str.getBytes("UTF-8"), password);
			System.out.println("加密后内容为：" + new String(result,"UTF-8"));

			// 直接将如上内容解密
			byte[] decryResult = DESUtil.decode(result, password);
			System.out.println("解密后内容为：" + new String(decryResult));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * 加密算法存在中文乱码问题
	 * @param datasource
	 * @param password
	 * @return
	 */
	public static byte[] encode(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			System.err.println((new String(datasource)));
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decode(byte[] src, String password) throws Exception {

		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
}
