package search.user.service;

import java.util.List;

import search.entity.User;
import search.user.dao.UserDao;


public class UserService {
		
		//查找用户信息（实现登录）
		public List<User> loginUser(){
			UserDao userDao = new UserDao();
			List<User> users = userDao.findUser();
			
			return users;
		}
		
		public int changeUserService(String tel,String pwd) {
			UserDao userDao = new UserDao();
			int i = userDao.changeUserPwd(tel, pwd);
			
			return i;
		}
}
