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
	  * 根据输入框输入的名字进行查询
	  * @param name
	  * @return
	  */
	 public List<Basic_information> findBasicByNameService(String name){
		 HallDao hallDao=new HallDao();
		 List<Basic_information> basics=hallDao.findBasicByName(name);
		 
		 
		 
		 return basics;
		 
	 }
}
