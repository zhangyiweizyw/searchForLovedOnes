package search.hall.service;

import java.util.List;

import search.entity.Basic_information;
import search.entity.PageText;
import search.firstpage.dao.FirstPageDao;
import search.hall.dao.HallDao;
import search.util.Page;
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
	/**
	 * 从数据库中取全部数据传给网页端
	 * @return
	 */
	public List<Basic_information> findAllDataService(){
		HallDao hallDao=new HallDao();
		List<Basic_information> basics=hallDao.findBasicAll();



		return basics;

	}
	/**
	 * 分页专用
	 * @param id
	 * @param num
	 * @param size
	 * @return
	 */
	public Page<Basic_information> list(int num,int size){
		Page<Basic_information> page = new Page<Basic_information>(num,size);
		HallDao hallDao=new HallDao();
		int count  = hallDao.findBasicCount();//得到总数
		
		List<Basic_information> list = hallDao.getJspCounrt(num, size);
		page.setTotalCount(count);
		page.setList(list);
		return page;
	}
}
