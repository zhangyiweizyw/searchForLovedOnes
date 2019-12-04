package search.register.service;

import search.register.dao.RegisterDao;
import search.user.dao.UserDao;

public class RegisterService {
	//插入用户信息（实现注册）
			public void addUserInfo(String user_name,String user_pwd,String user_type,String user_email,String user_tel) {
				RegisterDao registerDao = new RegisterDao();
				registerDao.addUser(user_name,user_pwd,user_type,user_email,user_tel);
			}
}
