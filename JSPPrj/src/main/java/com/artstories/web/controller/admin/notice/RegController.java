package com.artstories.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.artstories.web.entity.Notice;
import com.artstories.web.service.NoticeService;


@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*50*5
)

@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		//forward
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		// pub
		boolean pub = false;
		if (isOpen != null) {
			pub = true;
		}
		
		//files
		Collection<Part> parts = request.getParts();
		StringBuilder builder = new StringBuilder();
		
		for (Part part : parts) {
			// 파일 체크, 첨부 없는지 체크 
			if(!part.getName().equals("file")) continue;
			if(part.getSize() == 0) continue;
			
			Part filePart = part;
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			
			InputStream fis = filePart.getInputStream();
			
			String realPath = request.getServletContext().getRealPath("/upload");
			System.out.println(realPath);
			
			File path = new File(realPath);
			if(!path.exists()) {
				path.mkdirs();
			}
			
			// 같은 파일이이 있을 경우 파일명(1) 처리를 해야 한다.
			String filePath = realPath + File.separator + fileName;
			FileOutputStream fos = new FileOutputStream(filePath);
			
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size=fis.read(buf)) != -1) {
				fos.write(buf,0,size);
			}
			
			fos.close();
			fis.close();
		}
		// file name last ',' remove
		builder.delete(builder.length()-1, builder.length());
		
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setFiles(builder.toString());
		notice.setPub(pub);
		notice.setWriterId("study");
		
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
				
		
		response.sendRedirect("list");
	}

}
