package com.artstories.web.service;

import java.util.List;

import com.artstories.web.entity.Notice;

public class NoticeService {
	
	public List<Notice> getNoticeList() {
		
		return getNoticeList("title", "", 1);
		
	}
	
	public List<Notice> getNoticeList(int page) {
		
		return getNoticeList("title", "", page);
		
	}

	public List<Notice> getNoticeList(String field, String query, int page) {
		
		String sql = "SELECT ID\n"
				+ "     , TITLE\n"
				+ "     , CONTENT\n"
				+ "     , FILES\n"
				+ "     , HIT\n"
				+ "     , REGDATE\n"
				+ "     , WRITER_ID\n"
				+ "  FROM TB_NOTICE\n"
				+ " WHERE 1 = 1\n"
				+ " ORDER BY REGDATE ASC LIMIT 0, 5\n";
			
	
		return null;
	
	}
	
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
	}
	
	
	public int getNoticeCount(String field, String query) {
		
		return getNoticeCount("title", "query");
	}
	
	
	public Notice getNotice(int id) {
		return null;
	}
	
	public Notice getNextNotice(int id) {
		return null;
	}
	
	public Notice getPrevNotice(int id) {
		return null;
	}
	
	

}
