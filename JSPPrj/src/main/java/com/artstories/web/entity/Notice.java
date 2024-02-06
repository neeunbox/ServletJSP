package com.artstories.web.entity;

import java.util.Date;

public class Notice {
	
	private int id; 
	private String title;
	private String content;
	private String files;
	private int hit;	
	private Date regdate;
	private String writerId;
	private boolean pub;
	
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Notice(int id, String title, String content, String files, int hit, Date regdate, String writerId, boolean pub) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.files = files;
		this.hit = hit;
		this.regdate = regdate;
		this.writerId = writerId;
		this.pub = pub;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	

	public boolean getPub() {
		return pub;
	}


	public void setPub(boolean pub) {
		this.pub = pub;
	}


	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", files=" + files + ", hit=" + hit
				+ ", regdate=" + regdate + ", writerId=" + writerId + ", pub=" + pub + "]";
	}

	
}
