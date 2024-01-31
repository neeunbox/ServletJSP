package com.artstories.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.artstories.web.entity.Notice;

public class NoticeService {
	
	/*
	 * 공지목록 전체 가져오
	 **/
	public List<Notice> getNoticeList() {
		
		return getNoticeList("title", "", 1);
		
	}
	
	/*
	 * 공지목록 특정 페이지 가져오기 
	 * */
	public List<Notice> getNoticeList(int page) {
		
		return getNoticeList("title", "", page);
		
	}

	/*
	 * 공지목록 조회 조건에 포함되는 특정 페이지 가져오기 
	 * */
	public List<Notice> getNoticeList(String field, String query, int page) {
		
		List<Notice> list = new ArrayList<>();
		
		String sql = " SELECT ID                         "
				+    "      , TITLE                      "
				+    "      , CONTENT                    "
				+    "      , FILES                      "
				+    "      , HIT                        "
				+    "      , REGDATE                    "
				+    "      , WRITER_ID                  "
				+    "   FROM TB_NOTICE                  "
				+    "  WHERE 1 = 1                      "
				+    "    AND " +field+ " LIKE ?         "
				+    "  ORDER BY REGDATE DESC LIMIT ?, ? ";
		
		// 1,  11, 21, 31 -> an = 1 + (page-1) * 10
		// 10, 20, 30, 40 -> page*10
		
		String url = "jdbc:mariadb://localhost:3306/studies";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "study", "qwer1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			st.setInt(2, 0 + ((page - 1) * 10));
			st.setInt(3, (page * 10));
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("ID");
				String title  = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				Date regdate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");

				Notice notice = new Notice(id, title, content, hit, files, regdate, writerId);
				
				// ArrayList에 넣기  
				list.add(notice);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	
	}
	
	/*
	 * 공지목록 전체 건수 가져오기
	 * */
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
		
	}
	
	/*
	 * 공지목록 조회 조건에 포함되는 건수 가져오기
	 * */
	public int getNoticeCount(String field, String query) {
		
		int count = 0;
		
		String sql = " SELECT COUNT(ID) AS COUNT         "
				+    "   FROM TB_NOTICE                  "
				+    "  WHERE 1 = 1                      "
		        +    "    AND " +field+ " LIKE ?         ";
		
		String url = "jdbc:mariadb://localhost:3306/studies";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "study", "qwer1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery();
			
			count = rs.getInt("count");
			
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return count;
	}
	
	
	/*
	 * 공지목록 조회ID 항목 가져오기 
	 * */
	public Notice getNotice(int id) {
		
		Notice notice = null;
		
		String sql = " SELECT ID                         "
				+    "      , TITLE                      "
				+    "      , CONTENT                    "
				+    "      , FILES                      "
				+    "      , HIT                        "
				+    "      , REGDATE                    "
				+    "      , WRITER_ID                  "
				+    "   FROM TB_NOTICE                  "
				+    "  WHERE 1 = 1                      "
		        +    "    AND ID = ?                     ";
		
		String url = "jdbc:mariadb://localhost:3306/studies";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "study", "qwer1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				
				int nid = rs.getInt("ID");
				String title  = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				Date regdate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");

				notice = new Notice(nid, title, content, hit, files, regdate, writerId);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
	/*
	 * 공지목록 조회ID 항목의 다음 항목 가져오기 
	 * */
	public Notice getNextNotice(int id) {
		
		Notice notice = null;

		String sql = " SELECT A.ID                                                                  "
				+    "      , A.TITLE                                                               "
				+    "      , A.CONTENT                                                             "
				+    "      , A.FILES                                                               "
				+    "      , A.HIT                                                                 "
				+    "      , A.REGDATE                                                             "
				+    "      , A.REGDATE                                                             "
				+    "      , A.WRITER_ID                                                           "
				+    "   FROM TB_NOTICE A                                                           "
				+    "  WHERE 1 = 1                                                                 "
				+    "    AND A.ID = (SELECT ID                                                     "
				+    "                  FROM TB_NOTICE                                              "
				+    "                 WHERE 1 = 1                                                  "
				+    "                   AND REGDATE > (SELECT REGDATE FROM TB_NOTICE WHERE ID = ?) "
				+    "                 ORDER BY ID ASC LIMIT 0, 1)                                  ";
		
		String url = "jdbc:mariadb://localhost:3306/studies";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "study", "qwer1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				
				int nid = rs.getInt("ID");
				String title  = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				Date regdate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");

				notice = new Notice(nid, title, content, hit, files, regdate, writerId);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
		
	}
	
	/*
	 * 공지목록 조회ID 항목의 이전 항목 가져오기
	 * */
	public Notice getPrevNotice(int id) {
		
		Notice notice = null;
		
		String sql = " SELECT A.ID                                                                  "
				+    "      , A.TITLE                                                               "
				+    "      , A.CONTENT                                                             "
				+    "      , A.FILES                                                               "
				+    "      , A.HIT                                                                 "
				+    "      , A.REGDATE                                                             "
				+    "      , A.REGDATE                                                             "
				+    "      , A.WRITER_ID                                                           "
				+    "   FROM TB_NOTICE A                                                           "
				+    "  WHERE 1 = 1                                                                 "
				+    "    AND A.ID = (SELECT ID                                                     "
				+    "                  FROM TB_NOTICE                                              "
				+    "                 WHERE 1 = 1                                                  "
				+    "                   AND REGDATE < (SELECT REGDATE FROM TB_NOTICE WHERE ID = ?) "
				+    "                 ORDER BY ID DESC LIMIT 0, 1)                                 ";
		
		
		String url = "jdbc:mariadb://localhost:3306/studies";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "study", "qwer1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				
				int nid = rs.getInt("ID");
				String title  = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				Date regdate = rs.getDate("REGDATE");
				String writerId = rs.getString("WRITER_ID");

				notice = new Notice(nid, title, content, hit, files, regdate, writerId);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
		
	}

}

