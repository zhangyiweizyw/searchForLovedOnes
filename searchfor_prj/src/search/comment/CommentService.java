package search.comment;

import java.util.List;

import search.entity.PageText;
import search.firstpage.dao.FirstPageDao;
import search.util.Page;

public class CommentService {
	
	public Page<Comment> list(int num,int size){
		Page<Comment> page = new Page<Comment>(num,size);
		CommentDao dao = new CommentDao();
		int count  = dao.count();
		
		List<Comment> list = dao.getJsp(num, size);
		page.setTotalCount(count);
		page.setList(list);
		return page;
	}

}
