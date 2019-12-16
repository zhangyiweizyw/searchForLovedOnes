package search.user.service;

import java.util.List;

import search.entity.User;
import search.user.dao.UserDao;

public class UserService {
		
		//查找用户信息（实现登录）
		public boolean loginUser(String userTel,String password){
			UserDao userDao = new UserDao();
			boolean type = userDao.findUser(userTel,password);
			if(type) {
				return true;
			}else {
				return false;
			}
		}
		
		//传递查询用户电话信息
		public boolean judgeUserTelService(String tel) {
			UserDao userDao = new UserDao();
			boolean type = userDao.judgeUserTel(tel);
			if(type) {
				return true;
			}else {
				return false;
			}
		}
		
		//若查询成功，则根据传入电话进行修改密码操作
		public int changeUserService(String tel,String pwd) {
			UserDao userDao = new UserDao();
			int i = userDao.changeUserPwd(tel, pwd);
			
			return i;
		}
		//传入手机号，将查出的id返回
		public int getUserId(String tel){
			UserDao userdao=new UserDao();
			int user_id=userdao.findIdByPhone(tel);
			return user_id;
		}
}
