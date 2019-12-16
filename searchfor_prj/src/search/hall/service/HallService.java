package search.hall.service;

import java.util.List;

import search.entity.Basic_information;
import search.hall.dao.HallDao;
public class HallService {
	
	
	/**
	 * 根据单选框选项或输入框输入的数字，让客户端获得基本数据信息
	 * @param type
	 * @return
	 */
	 public List<Basic_information> findBasicByIDService(int type){
		 HallDao hallDao=new HallDao();
		 List<Basic_information> basics=hallDao.findBasicByID(type);
		 
		 
		 
		 return basics;
		 
	 }
	 /**
	  *  根据名字搜寻
	  * @param name
	  * @return
	  */
	 public List<Basic_information> findBasicByNameService(String name){
		 HallDao hallDao=new HallDao();
		 List<Basic_information> basics=hallDao.findBasicByName(name);
		 
		 
		 
		 return basics;
		 
	 }
	 /**
	  * 从数据库中任选数据传给客户端用于展示
	  * @return
	  */
	 public List<Basic_information> findSomeRandomDataService(){
		 HallDao hallDao=new HallDao();
		 List<Basic_information> basics=hallDao.findBasicRandom();
		 
		 
		 
		 return basics;
		 
	 }
}
