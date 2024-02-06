package com.artstories.web.entity;

import java.util.Date;

public class NoticeView extends Notice {
	
	
	private int cmtCount;
	

	public int getCmtCount() {
		return cmtCount;
	}

	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}

	public NoticeView() {
		// TODO Auto-generated constructor stub
	}

	public NoticeView(int id, String title,  String files, int hit, int cmtCount, Date regdate, String writerId, boolean pub) {
		
		super(id, title, "", files, hit, regdate, writerId, pub);
		
		this.cmtCount = cmtCount; 
	}
	
	

}
