package vo;

import java.sql.Date;

public class ReplyBean {
	
	private String M_ID;
	private int FB_NUM;
	private int UP_NUM;
	private int MTM_NUM;
	private int REPORT_NUM;
	private Date REPLY_DATE;
	private String FB_REPLY;
	
	public String getM_ID() {
		return M_ID;
	}
	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}
	public int getFB_NUM() {
		return FB_NUM;
	}
	public void setFB_NUM(int fB_NUM) {
		FB_NUM = fB_NUM;
	}
	public int getUP_NUM() {
		return UP_NUM;
	}
	public void setUP_NUM(int uP_NUM) {
		UP_NUM = uP_NUM;
	}
	public int getMTM_NUM() {
		return MTM_NUM;
	}
	public void setMTM_NUM(int mTM_NUM) {
		MTM_NUM = mTM_NUM;
	}
	public int getREPORT_NUM() {
		return REPORT_NUM;
	}
	public void setREPORT_NUM(int rEPORT_NUM) {
		REPORT_NUM = rEPORT_NUM;
	}
	public Date getREPLY_DATE() {
		return REPLY_DATE;
	}
	public void setREPLY_DATE(Date rEPLY_DATE) {
		REPLY_DATE = rEPLY_DATE;
	}
	public String getFB_REPLY() {
		return FB_REPLY;
	}
	public void setFB_REPLY(String fB_REPLY) {
		FB_REPLY = fB_REPLY;
	}
	
	
	
	
	
}
