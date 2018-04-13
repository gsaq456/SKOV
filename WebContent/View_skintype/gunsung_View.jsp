<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="vo.PageInfo"%>
<%@page import="vo.ProductBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	@SuppressWarnings("unchecked")
	ArrayList<ProductBean> productList=(ArrayList<ProductBean>)request.getAttribute("productList");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>




<style type="text/css">
#pageList{
position: absolute;
    left: 900px;
    bottom: -160px;
}

body {
  font-family: open sans;
}

a {
  cursor: pointer;
}


/*--------------------------
		
		Product Pages 
		
----------------------------*/
.general-content{
	width:1890px;
	height: 400px;
}

.product-area {
  position: relative;
  overflow: hidden;
  display: inline;
  
}

.product-wrap {
  overflow: hidden;
  float: left;
  width: 24%;
  margin: 1% 0.5%;
}


.preview-img {
  background-size: cover;
  position: relative;
  height: 300px;
  width: 400px;
  background-color: pink;
  border-radius: 5px;
}

a.preview-btn {
  position: absolute;
  color: #FFF;
  background-color : #F1748D;
  font-weight: bold;
  font-size: 14px;
  padding: 0;
  width: 122px;
  text-align: center;
  height: 40px;
  line-height: 40px;
  left: 278px;
  top: 302px;
  border-radius: 2px;
}

a.preview-btn:hover {
  /* border-color: #f56b0b; */
  color: #F1748D;
  background: #FFF;
  
}

.preview-txt {
  
  padding-top: 5px;
  padding-left: 6px;
  color: #000;
  font-size: 12px;
  width: auto;
  font-weight: bold;
}

.preview-txt p {
  text-align: left;
  color: #000;
  font-family: 'Open Sans', sans-serif;
  font-weight: normal;
  font-size: 13px;
}




</style>

</head>
<body>
<span style="font-size: 30px; font-weight: bolder;"> 건성 페이지입니다.</span>

<%
if(productList != null && listCount > 0){
%>
상품정보 



	
<div class="general-content">
  
  <%	
		if(nowPage==1){
			for(int i=0; i<productList.size();i++){
	%>
  <div class="product-area">
    <div class="product-wrap">
      <div class="preview">
        <div class="preview-img" >
        <img style=" width: 350px; height: 250px; margin-left: 25px; padding-top: 25px;" src="source/images/<%=productList.get(i).getPicture()%>" >
        <a class="show preview-btn" href="gunsung_product1.pr?product_num=<%=i+1%>">See More</a></div>
        <div class="preview-txt">
           <p> <%=productList.get(i).getProduct_id()%> | <%=productList.get(i).getProduct_pr()%></p>
        </div>
        <!-- end preview txt -->
        <div style="clear:both"></div>
      </div>
      <!-- end preview -->
	 </div>
    <!-- end product-wrap-->
	</div>
  <!-- end product area -->
<%
			}
%>

</div>
<!-- end general content -->


<div class="general-content">
 <%
		} else if(nowPage==2) {
			for(int i=0; i<productList.size();i++){
	%>
  <div class="product-area">
    <div class="product-wrap">
      <div class="preview">
        <div class="preview-img" >
        <img style=" width: 350px; height: 250px; margin-left: 25px; padding-top: 25px;" src="source/images/<%=productList.get(i).getPicture()%>" >
        <a class="show preview-btn" href="gunsung_product1.pr?product_num=<%=(i+5)%>">See More</a></div>
        <div class="preview-txt">
           <p> <%=productList.get(i).getProduct_id()%> | <%=productList.get(i).getProduct_pr()%></p>
        </div>
        <!-- end preview txt -->
        <div style="clear:both"></div>
      </div>
      <!-- end preview -->
	 </div>
    <!-- end product-wrap-->
	</div>
  <!-- end product area -->
<%
			}
%>
</div>
<!-- end general content -->



			
<div class="general-content">
  <% }
	 else if(nowPage==3) {
		for(int i=0; i<productList.size();i++){
	%>
  <div class="product-area">
    <div class="product-wrap">
      <div class="preview">
        <div class="preview-img" >
        <img style=" width: 350px; height: 250px; margin-left: 25px; padding-top: 25px;" src="source/images/<%=productList.get(i).getPicture()%>" >
        <a class="show preview-btn" href="gunsung_product1.pr?product_num=<%=(i+9)%>">See More</a></div>
        <div class="preview-txt">
           <p> <%=productList.get(i).getProduct_id()%> | <%=productList.get(i).getProduct_pr()%></p>
        </div>
        <!-- end preview txt -->
        <div style="clear:both"></div>
      </div>
      <!-- end preview -->
	 </div>
    <!-- end product-wrap-->
	</div>
  <!-- end product area -->
<%
		}
	}
%>
</div>
<!-- end general content -->




	<section id="pageList">
		<%if(nowPage<=1){ %>
		[이전]&nbsp;
		<%}else{ %>
		<a href="gunsungList.pr?page=<%=nowPage-1 %>">[이전]</a>&nbsp;
		<%} %>

		<%for(int a=startPage; a<=endPage; a++){
				if(a==nowPage){%>
		[<%=a %>]
		<%}else{ %>
		<a href="gunsungList.pr?page=<%=a %>">[<%=a %>]
		</a>&nbsp;
		<%} %>
		<%} %>

		<%if(nowPage>=maxPage){ %>
		[다음]
		<%}else{ %>
		<a href="gunsungList.pr?page=<%=nowPage+1 %>">[다음]</a>
		<%} %>
	</section>
	<%
    }
	else
	{
	%>
	<section id="emptyArea">등록된 화장품정보가 없습니다.<%=productList %></section>
	<%
	}
%>



</body>
</html>