package ans_vo;

import java.sql.Date;

public class ansBoardBean {
	

	
	private int ANS_NUM;
	private String ANS_NAME;
	private String ANS_PASS;
	private String ANS_SUBJECT;
	private String ANS_CONTENT;
	private int ANS_READCOUNT;
	private Date ANS_DATE;

	public int getANS_NUM() {
		return ANS_NUM;
	}
	public void setANS_NUM(int aNS_NUM) {
		ANS_NUM = aNS_NUM;
	}
	public String getANS_NAME() {
		return ANS_NAME;
	}
	public void setANS_NAME(String aNS_NAME) {
		ANS_NAME = aNS_NAME;
	}
	public String getANS_PASS() {
		return ANS_PASS;
	}
	public void setANS_PASS(String aNS_PASS) {
		ANS_PASS = aNS_PASS;
	}
	public String getANS_SUBJECT() {
		return ANS_SUBJECT;
	}
	public void setANS_SUBJECT(String aNS_SUBJECT) {
		ANS_SUBJECT = aNS_SUBJECT;
	}
	public String getANS_CONTENT() {
		return ANS_CONTENT;
	}
	public void setANS_CONTENT(String aNS_CONTENT) {
		ANS_CONTENT = aNS_CONTENT;
	}
	public int getANS_READCOUNT() {
		return ANS_READCOUNT;
	}
	public void setANS_READCOUNT(int aNS_READCOUNT) {
		ANS_READCOUNT = aNS_READCOUNT;
	}
	public Date getANS_DATE() {
		return ANS_DATE;
	}
	public void setANS_DATE(Date aNS_DATE) {
		ANS_DATE = aNS_DATE;
	}

}