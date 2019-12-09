package search.firstpage.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import search.entity.PageText;
import search.util.DBUtil;


public class FirstPageDao {

	public List<PageText> getInit(int id) {
		List<PageText> texts =new ArrayList<>();
		Connection con=null;
		PreparedStatement pstm = null;
		try {
			con = DBUtil.getCon();
			String sql = "select * from detail where tp_id = ? order by rand() limit 5";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				PageText pt=new PageText();
				pt.setImgName(rs.getString("dt_img"));
				pt.setTitle(rs.getString("dt_title"));
				pt.setContent(rs.getString("dt_content"));				
				texts.add(pt);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return texts;
	}
	
	public List<PageText> getJsp(int id,int num,int size) {
		List<PageText> texts =new ArrayList<>();
		Connection con=null;
		PreparedStatement pstm = null;
		try {
			con = DBUtil.getCon();
			String sql = "select * from detail where tp_id = ? limit ?,?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setInt(2, num);
			pstm.setInt(3, size);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				PageText pt=new PageText();
				pt.setImgName(rs.getString("dt_img"));
				pt.setTitle(rs.getString("dt_title"));
				pt.setContent(rs.getString("dt_content"));				
				texts.add(pt);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return texts;
	}
	
	
	public int count(int id) {
		int count = 0;
		Connection con=null;
		PreparedStatement pstm = null;
		try {
			con = DBUtil.getCon();
			String sql = "select * from detail where tp_id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				count++;
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}