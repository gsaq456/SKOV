<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="source/CSS/style.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.base_page {
	width : 1890px;
	height: 530px;
	padding-top: 30px;
	
}
.slide_info{
	width: 1400px;
	height :500px;
	float: left;
}
.login_page{
	margin-right: 35px;
    
	align-content:center;
	width: 410px;
    height: 322px;
    background-color: #F4EEEE;
	float: right;
	
}
.login_form{
	margin-top: 29px;
	width: 350px;
	height: 400px;
	margin-left: 49px;
	position: absolute;
}
</style>
<script type="text/javascript">
function showPopup2() { 
	   window.open("navi.jsp", "찾아오시는 길", "width=700, height=600, left=100, top=50"); 
	}
</script>



</head>
<body>


<section class = "base_page">

	<div class = "slide_info" >
					<a href = "./noticeboardList.noticebo"><img class = "mySlides" src = "source/images/sale1(1).jpg" style = "width:1290px; height:500px; margin-left : 100px;"></a>
					<a href = "./noticeboardList.noticebo"><img class = "mySlides" src = "source/images/sale2.jpg" style = "width:1290px; height:500px; margin-left : 100px;"></a>
	</div>
	
	<script>
	
	
		var myIndex = 0;
		carousel();

		function carousel() {
    		var i;
   		 	var x = document.getElementsByClassName("mySlides");
    		for (i = 0; i < x.length; i++) {
       			x[i].style.display = "none";  
    			}
   			myIndex++;
    		if (myIndex > x.length) {myIndex = 1}    
    		x[myIndex-1].style.display = "block";  
    		setTimeout(carousel, 3000); // Change image every 3 seconds
		}
	</script>
	<div class = "login_page">
	<%
			if(session.getAttribute("login_id") == null){
		%>
			<jsp:include page="View_access/login_View.jsp"/>
		<%		
			} else if(session.getAttribute("login_id").equals("admin")){
		%>
			<jsp:include page="View_access/login_admin.jsp"/>
		<%		
			}else {
		%>
			<jsp:include page="View_access/login_user.jsp"/>
		<%
			}
		%>
	</div>
</section>


<section>
	<jsp:include page="productRank_View.jsp"/>


</section>


	
<div class = "notice">
		
		<div class="button"><a href = "./noticeboardList.noticebo">  <img src="source/images/img11.png" class="image3"> <span class="overlay"></span></a></div>
		<div class="button"><a href = "./notice.me">  <img src="source/images/img44.png" class="image2"> <span class="overlay"></span></a></div>
		<div class="button"><a onclick = "showPopup2();">  <img src="source/images/img22.png" class="image2"> <span class="overlay"></span></a></div>
		<div class="button"><a href = "./ansboardList.ansbo">  <img src="source/images/img33.png" class="image2"> <span class="overlay"></span></a></div>
	
</div>
</body>
</html>