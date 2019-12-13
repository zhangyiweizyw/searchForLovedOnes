package search.user.service;

import java.util.List;

import search.entity.User;
import search.user.dao.UserDao;


public class UserService {
		
		//查找用户信息（实现登录）
		public boolean loginUser(String userTel,String password){
			UserDao userDao = new UserDao();
			boolean type = userDao.findUser(userTel,password);
			if(type == true) {
				return true;
			}else {
				return false;
			}
		}
		
		
		public int changeUserService(String tel,String pwd) {
			UserDao userDao = new UserDao();
			int i = userDao.changeUserPwd(tel, pwd);
			
			return i;
		}
}
