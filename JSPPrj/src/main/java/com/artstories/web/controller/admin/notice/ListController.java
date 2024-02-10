package com.artstories.web.controller.admin.notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.artstories.web.entity.NoticeView;
import com.artstories.web.service.NoticeService;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// 404
	// 405
	// 403
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] openIds = request.getParameterValues("open-id"); // 3,5,8
		String[] delIds = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");
	    String ids_ = request.getParameter("ids");
	    String[] ids = ids_.trim().split(" "); // 1 2 3 4 5 6 7 8 9 10

	    NoticeService service = new NoticeService();
		
		switch (cmd) {
		case "일괄공개":
			for (String openId : openIds) {
				System.out.println("open id : " + openId);
			}
			
			// 배열 값을 List 로  변환 
			List<String> oids = Arrays.asList(openIds);
			// 1,2,3,4,5,6,7,8,9,10 - //3,5,8
			// 1,2,4,6,7,9,10
			List<String> cids = new ArrayList<String>(Arrays.asList(ids));
			cids.removeAll(oids);
			System.out.println(Arrays.asList(ids));
			System.out.println(oids);
			System.out.println(cids);
			
			// Transaction 
			service.pubNoticeAll(oids, cids);
			
			break;
		case "일괄삭제":

			int[] idDels = new int[delIds.length];
			for (int i = 0; i < delIds.length; i++) {
				idDels[i] = Integer.parseInt(delIds[i]);
			}
			int result = service.deleteNOticeAll(idDels);
			
			break;
		}
		
		response.sendRedirect("list");
	}
	
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
		List<NoticeView> list = service.getNoticeList(field, query, page);
		int count = service.getNoticeCount(field, query);
		
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		
		//forward
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);
	}

}
