package search.firstpage.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import search.entity.PageText;
import search.util.DBUtil;


public class FirstPageDao {

	public List<PageText> getAvoids() {
		List<PageText> texts =new ArrayList<>();
		Connection con=null;
		try {
			con = DBUtil.getCon();
			Statement statement = con.createStatement();
			String sql = "select * from detail where tp_id=1";
			ResultSet rs = statement.executeQuery(sql);
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
	
	public List<PageText> getFindWays() {
		List<PageText> texts =new ArrayList<>();
		Connection con=null;
		try {
			con = DBUtil.getCon();
			Statement statement = con.createStatement();
			String sql = "select * from detail where tp_id=2";
			ResultSet rs = statement.executeQuery(sql);
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
	
	public List<PageText> getLaws() {
		List<PageText> texts =new ArrayList<>();
		Connection con=null;
		try {
			con = DBUtil.getCon();
			Statement statement = con.createStatement();
			String sql = "select * from detail where tp_id=3";
			ResultSet rs = statement.executeQuery(sql);
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
}
