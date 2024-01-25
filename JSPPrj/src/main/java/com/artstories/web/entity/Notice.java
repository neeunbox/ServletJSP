package com.artstories.web.entity;

import java.util.Date;

public class Notice {
	
	private int id; 
	private String title;
	private String content;
	private int hit;
	private String files;
	private Date regdate;
	private String writerId;
	
	// 기본 생성자 
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	// 오버로드 생성자  
	public Notice(int id, String title, String content, int hit, String files, Date regdate, String writerId) {
		
		this.id = id;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.files = files;
		this.regdate = regdate;
		this.writerId = writerId;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
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

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", hit=" + hit + ", files=" + files
				+ ", regdate=" + regdate + ", writerId=" + writerId + "]";
	}

}
