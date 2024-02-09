package com.artstories.web.controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artstories.web.entity.NoticeView;
import com.artstories.web.service.NoticeService;

@WebServlet("/notice/list")
public class ListController extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_  = request.getParameter("p");
		
		String field = "title";
		if (field_ != null && !"".equals(field_)) {
			field = field_;
		}
		
		String query = "";
		if (query_ != null && !"".equals(query_)) {
			query = query_;
		}
		
		int page = 1;
		if (page_ != null && !"".equals(page_)) {
			page = Integer.parseInt(page_);
		}
		
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticePubList(field, query, page);
		int count = service.getNoticeCount(field, query);
		
		
		// 저장소에 넣기 
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		// 전달하기 
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
		
	}

}
