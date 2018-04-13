package vo;

import java.sql.Date;

public class BoardBean {
	
	private int FB_NUM;
	private String FB_NAME;
	private String FB_PASS;
	private String FB_SUBJECT;
	private String FB_CONTENT;
	private String FB_FILE;
	private String FB_HEAD;
	private int FB_READCOUNT;
	private Date FB_DATE;
	
	public int getFB_NUM() {
		return FB_NUM;
	}
	public void setFB_NUM(int fB_NUM) {
		FB_NUM = fB_NUM;
	}
	public String getFB_NAME() {
		return FB_NAME;
	}
	public void setFB_NAME(String fB_NAME) {
		FB_NAME = fB_NAME;
	}
	public String getFB_PASS() {
		return FB_PASS;
	}
	public void setFB_PASS(String fB_PASS) {
		FB_PASS = fB_PASS;
	}
	public String getFB_SUBJECT() {
		return FB_SUBJECT;
	}
	public void setFB_SUBJECT(String fB_SUBJECT) {
		FB_SUBJECT = fB_SUBJECT;
	}
	public String getFB_CONTENT() {
		return FB_CONTENT;
	}
	public void setFB_CONTENT(String fB_CONTENT) {
		FB_CONTENT = fB_CONTENT;
	}
	public String getFB_FILE() {
		return FB_FILE;
	}
	public void setFB_FILE(String fB_FILE) {
		FB_FILE = fB_FILE;
	}
	public String getFB_HEAD() {
		return FB_HEAD;
	}
	public void setFB_HEAD(String fB_HEAD) {
		FB_HEAD = fB_HEAD;
	}
	public int getFB_READCOUNT() {
		return FB_READCOUNT;
	}
	public void setFB_READCOUNT(int fB_READCOUNT) {
		FB_READCOUNT = fB_READCOUNT;
	}
	public Date getFB_DATE() {
		return FB_DATE;
	}
	public void setFB_DATE(Date fB_DATE) {
		FB_DATE = fB_DATE;
	}
	
}