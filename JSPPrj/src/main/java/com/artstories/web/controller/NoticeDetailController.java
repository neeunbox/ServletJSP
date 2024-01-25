package com.artstories.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artstories.web.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

		String url = "jdbc:mariadb://localhost:3306/studies";
		String sql = "SELECT * FROM TB_NOTICE WHERE ID = ?";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "study", "qwer1234");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			
			rs.next();

			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			Date regdate = rs.getDate("REGDATE");
			String writerId = rs.getString("WRITER_ID");
			
			
			Notice notice = new Notice(id, title, content, hit, files, regdate, writerId);
			
			request.setAttribute("n", notice);
			
			/*
			request.setAttribute("title", title);
			request.setAttribute("content", content);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("regdate", regdate);
			request.setAttribute("writerId", writerId);
			*/

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
		
		//redirect
		
		
		//forward
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);

		
	}
}
