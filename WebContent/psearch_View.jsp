<%@page import="vo.ProductBean"%>
<%@page import="vo.PageInfo"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%
@SuppressWarnings("unchecked")

ArrayList<ProductBean> psearchList=(ArrayList<ProductBean>)request.getAttribute("psearchList");
String product_type = psearchList.get(0).getProduct_type();
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

</style>
</head>
<body>

<%
	if(psearchList != null && listCount > 0){
		
%>
	
<div class="general-content">
  <div class = "">
  <%	
		if(nowPage==1){
			for(int i=0; i<psearchList.size();i++){
	%>
  <div class="product-area">
    <div class="product-wrap">
      <div class="preview">
        <div class="preview-img" >
        <img style=" width: 350px; height: 250px; margin-left: 25px; padding-top: 25px;" src="source/images/<%=psearchList.get(i).getPicture()%>" >
      
<% if(product_type.equals("건성")){ %>
        <a class="show preview-btn" href="gunsung_product1.pr?product_num=<%=psearchList.get(i).getProduct_num()%>">See More</a>
<% } else if(product_type.equals("지성")){ %>
        <a class="show preview-btn" href="jisung_product1.pr?product_num=<%=psearchList.get(i).getProduct_num()%>">See More</a>
<% } else if(product_type.equals("복합성")){ %>
        <a class="show preview-btn" href="bokhap_product1.pr?product_num=<%=psearchList.get(i).getProduct_num()%>">See More</a>
<% } else if(product_type.equals("트러블")){ %>
        <a class="show preview-btn" href="trouble_product1.pr?product_num=<%=psearchList.get(i).getProduct_num()%>">See More</a>
<% } %>
		</div>
        <div class="preview-txt">
           <p> <%=psearchList.get(i).getProduct_id()%> | <%=psearchList.get(i).getProduct_pr()%></p>
        
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
</div>
<!-- end general content -->

<div class="general-content">
 <%
		} else if(nowPage==2) {
			for(int i=0; i<psearchList.size();i++){
	%>
  <div class="product-area">
    <div class="product-wrap">
      <div class="preview">
        <div class="preview-img" >
        <a class="show preview-btn" href="gunsung_product1.pr?product_num=<%=(i+5)%>">See More</a></div>
        <div class="preview-txt">
           <p> <%=psearchList.get(i).getProduct_id()%> | <%=psearchList.get(i).getProduct_pr()%></p>
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
	} else if(nowPage==3) {
		for(int i=0; i<psearchList.size();i++){
	%>
  <div class="product-area">
    <div class="product-wrap">
      <div class="preview">
        <div class="preview-img" >
        <a class="show preview-btn" href="gunsung_product1.pr?product_num=<%=(i+9)%>">See More</a></div>
        <div class="preview-txt">
           <p> <%=psearchList.get(i).getProduct_id()%> | <%=psearchList.get(i).getProduct_pr()%></p>
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
}else{
%>
		검색하신 제품이 없습니다.
<%
}
%>

</table>

</body>
</html>