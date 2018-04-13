<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.*" %>

<%
	//fileCheck에서 파일이름을 클랙했을 때 get방식으로 넘겨주는 file이름을 받는 부분
	String fileName=request.getParameter("file_name");

	//서버에 파일이 업로드된 경로를 지정
	String savePath="boardUpload";
	//context 프로젝트의 이름
	ServletContext context=getServletContext();
	String sDownloadPath=context.getRealPath(savePath);
	//file의 실제 경로를 작성해주는 부분
	String sFilePath=sDownloadPath+"\\"+fileName;
	//윗 줄의 실제 출력될 내용을 예상
	//"SkinProject/boardUpload/첫번째 파일의 이름"
	
	//파일을 바이트 단위로 읽고 출력하기 위해 바이트 타입의 변수를 선언.
	byte b[]=new byte[4096];
	
	FileInputStream in = new FileInputStream(sFilePath);
	String sMimeType=getServletContext().getMimeType(sFilePath);
	System.out.println("sMimeType>>"+sMimeType);
	//Mime: Multipupose Internet Mail Extensions
		//: 이메일 등을 위한 인터넷 표준 포맷인데 요즘은 httl 프로토콜을 이용하는 메시지 또는
		//정보에도 적용되어 사용하고 있다.
		//각 파일별로 타입을 구분할 수 있도록 하며, text,jpeg,mp4,html 등등을 타입으로 지정하고 있다.
		
		
	if(sMimeType==null)
		sMimeType="application/octet-stream";
	
	response.setContentType("sMimeType");
	String agent=request.getHeader("User-Agent");
	boolean ieBrowser=(agent.indexOf("MSIE")> -1) || 
			(agent.indexOf("Trident")>-1);
	
	//한글이름으로 된 파일의 이름이 깨지지 않도록 처리하는 부분.(파일 다운로드 시에)
	if(ieBrowser){
		fileName=URLEncoder.encode(fileName, "UTF-8").
				replaceAll("\\+","%20");
	} else {
		fileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");
	}
	
	response.setHeader("Content-Disposition", "attachment; fileName="
			+fileName);
	
	//자바에서 제공하는 입력, 출력 스트림을 사용한다.

	try{
		
		out.clear();
		ServletOutputStream out2=response.getOutputStream();
		
		int numRead;
		
		while((numRead=in.read(b,0,b.length))!=-1){
			out2.write(b,0,numRead);
			
		}
		out2.flush();
		out2.close();
		
		in.close(); //파일 입출력에 대한 준비를 마친다.

		}catch(Exception ex){
			System.out.println("report_file out 에러 : "+ex);
		}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>