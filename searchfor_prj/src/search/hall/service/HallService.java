package search.hall.service;

import java.util.List;

import search.entity.Basic_information;
import search.hall.dao.HallDao;
public class HallService {
	
	//查询用户类型，让客户端获得基本数据信息
	 public List<Basic_information> basicService(int type){
		 HallDao hallDao=new HallDao();
		 List<Basic_information> basics=hallDao.findBasic(type);
		 
		 return basics;
		 
	 }
}
