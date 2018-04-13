package Notice_vo;

import java.sql.Date;

public class noticeBoardBean {
	
	public int getNOTICE_NUM() {
		return NOTICE_NUM;
	}
	public void setNOTICE_NUM(int nOTICE_NUM) {
		NOTICE_NUM = nOTICE_NUM;
	}
	public String getNOTICE_NAME() {
		return NOTICE_NAME;
	}
	public void setNOTICE_NAME(String nOTICE_NAME) {
		NOTICE_NAME = nOTICE_NAME;
	}
	public String getNOTICE_CONTENT() {
		return NOTICE_CONTENT;
	}
	public void setNOTICE_CONTENT(String nOTICE_CONTENT) {
		NOTICE_CONTENT = nOTICE_CONTENT;
	}
	public int getNOTICE_READCOUNT() {
		return NOTICE_READCOUNT;
	}
	public void setNOTICE_READCOUNT(int nOTICE_READCOUNT) {
		NOTICE_READCOUNT = nOTICE_READCOUNT;
	}
	public Date getNOTICE_DATE() {
		return NOTICE_DATE;
	}
	public void setNOTICE_DATE(Date nOTICE_DATE) {
		NOTICE_DATE = nOTICE_DATE;
	}
	public String getNOTICE_PASS() {
		return NOTICE_PASS;
	}
	public void setNOTICE_SUBJECT(String nOTICE_SUBJECT) {
		NOTICE_SUBJECT = nOTICE_SUBJECT;
	}
	
	private int NOTICE_NUM;
	private String NOTICE_NAME;
	private String NOTICE_PASS;
	private String NOTICE_SUBJECT;
	private String NOTICE_CONTENT;
	private int NOTICE_READCOUNT;
	private Date NOTICE_DATE;

	public String getNOTICE_SUBJECT() {
		return NOTICE_SUBJECT;
	}
	public void setNOTICE_PASS(String nOTICE_PASS) {
		NOTICE_PASS = nOTICE_PASS;
	}
	
	
	
	
}