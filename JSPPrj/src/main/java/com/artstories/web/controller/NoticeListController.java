package com.artstories.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artstories.web.entity.Notice;
import com.artstories.web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		
		String field = "title";
		if (field_ != null) {
			field = field_;
		}
		
		String query = "";
		if (query_ != null) {
			query = query_;
		}
			
				
		
		NoticeService service = new NoticeService();
		List<Notice> list = service.getNoticeList(field, query, 1);
		
		
		// 저장소에 넣기 
		request.setAttribute("list", list);
		
		// 전달하기 
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
		
	}

}
