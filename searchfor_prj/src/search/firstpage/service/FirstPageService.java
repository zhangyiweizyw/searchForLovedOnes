package search.firstpage.service;

import java.util.List;

import search.entity.PageText;
import search.firstpage.dao.FirstPageDao;
import search.util.Page;

public class FirstPageService {
	public Page<PageText> list(int id,int num,int size){
		Page<PageText> page = new Page<PageText>(num,size);
		FirstPageDao dao = new FirstPageDao();
		int count  = dao.count(id);
		
		List<PageText> list = dao.getJsp(id, num, size);
		page.setTotalCount(count);
		page.setList(list);
		return page;
	}
}
