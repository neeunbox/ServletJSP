package com.artstories.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artstories.web.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Notice> list = new ArrayList<>();
		
		String url = "jdbc:mariadb://localhost:3306/studies";
		String sql = "SELECT * FROM TB_NOTICE";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "study", "qwer1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
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
		
		// 저장소에 넣기 
		request.setAttribute("list", list);
		
		// 전달하기 
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
		
	}

}
