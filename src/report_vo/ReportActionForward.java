package report_vo;


//�������� ���õ� ������ �����ϴ� Ŭ����
//�������ҋ� redirect ����, �������� ��ΰ��� ���� ���� ���� �� �� ����.
public class ReportActionForward {
	
	private boolean isRedirect = false;
	private String path = null;
	
	
	//��Ÿ���� getter�� ������ ���� �ٸ���
	public boolean isRedirect() { 
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
