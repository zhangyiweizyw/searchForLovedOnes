package search.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDisgest {
		public String secretPassword(String password) {
			
			
			try {
				if(password != null && !password.equals("")) {
					MessageDigest md5 = MessageDigest.getInstance("MD5");
					byte[] pwdb = password.getBytes();
					byte[] digest = md5.digest(pwdb);
					String str = "";
					char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
					for(byte b:digest) {
						str+=HEX_DIGITS[(b & 0xf0) >> 4] + "" + HEX_DIGITS[b & 0xf];
					}
					System.out.println("用户密码加密前:"+password);
					System.out.println("用户密码加密:"+str);
					return str;
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
}
