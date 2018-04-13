
<%@page import="vo.ProductBean"%>
<%@page import="vo.ReplyBean"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page language="java" contentType="text/html; charset=UTF-8"%>

<%
@SuppressWarnings("unchecked")
ProductBean product = (ProductBean)request.getAttribute("product");

ArrayList<ReplyBean> replyList=(ArrayList<ReplyBean>)request.getAttribute("replyList");
%>


<!DOCTYPE html>
<html>
<head>

<meta name="author" content="www.twitter.com/cheeriottis">
  <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="css/core.css">

<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

body {
    font-family: myFirst Font, NanumGothic Web,Verdana,Dotum,"돋움",sans-serif;
    font-size: 13px;
    line-height: 2;
    color: #666;
}

.contents h3 {
    font-size: 18px;
    color: #5660b9;}

h3 {font-size: 18px;
    color: #5660b9;
    font-weight: normal;
   display: block;}

.box_gray {
   padding: 15px;
   border: 10px solid #eee;}

ul {list-style: none;
   display: block;
   list-style-type: disc;}

li {list-style: none;
   display: list-item;
    text-align: -webkit-match-parent;}

.bg_dot li {
   padding: 5px 0 5px 10px;
    background: url(source/images/aa.png) no-repeat 0 17px;}
   
.txt_bold {color: #04B486;}

li {display: list-item;
    text-align: -webkit-match-parent;}
    
.textStyle3 {color: #283dda;}
.textStyle4 {color: #56bc42;}
.textStyle5 {color: #d23023;}

textarea {
  margin-top: 10px;
  margin-left: 50px;
  width: 500px;
  height: 100px;
  background: none repeat scroll 0 0 #FFFFFF;
  border-color: -moz-use-text-color #FFFFFF #FFFFFF -moz-use-text-color;
  border-image: none;
  border-radius: 6px 6px 6px 6px;
  border-style: none solid solid none;
  border-width: medium 1px 1px medium;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.12) inset;
  color: #555555;
  font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
  font-size: 1em;
  line-height: 1.4em;
  padding: 5px 8px;
  transition: background-color 0.2s ease 0s;
      position: absolute;
    margin-left: -737px;
    margin-top: -31px;
}

.animate
{
   transition: all 0.1s;
   -webkit-transition: all 0.1s;
}

.action-button
{
   position: relative;
   padding: 10px 40px;
  margin: 0px 10px 10px 0px;
  float: left;
   border-radius: 10px;
   font-family: 'Pacifico', cursive;
   font-size: 25px;
   color: #FFF;
   text-decoration: none;   
}

.blue
{
   background-color: #F2CF66;
   border-bottom: 5px solid #D1B358;
   text-shadow: 0px -2px #D1B358;
   position: absolute;
    margin-left: 12px;
        width: 99px;
}

.red
{
   background-color: #E74C3C;
   border-bottom: 5px solid #BD3E31;
   text-shadow: 0px -2px #BD3E31;
   margin-left: 527px;
       width: 99px;
}

.green
{
   background-color: #82BF56;
   border-bottom: 5px solid #669644;
   text-shadow: 0px -2px #669644;
       width: 99px;
}

.yellow
{
   background-color: #F2CF66;
   border-bottom: 5px solid #D1B358;
   text-shadow: 0px -2px #D1B358;
}

.action-button:active
{
   transform: translate(0px,5px);
  -webkit-transform: translate(0px,5px);
   border-bottom: 1px solid;
}

</style>

<script type="text/javascript">
   var locked = 0;
   
   
   function show(star){
      
      
      
      var i;
      var image;
      var el;
      var e = document.getElementById('startext');
      var stateMsg;
      
      
      for (i = 1; i<= star; i++){
         image = 'star' + i;
         el = document.getElementById(image);
         el.src = "source/img/star-on.png";
      }
      if(locked) return;
      
      switch (star){
      case 1:
         stateMsg = "괜히썼어요";
         break;
      case 2:
         stateMsg = "기대하진 말아요";
         break;
      case 3:
         stateMsg = "평범합니다";
         break;
      case 4:
         stateMsg = "기대해도 좋아요!"
         break;
      case 5:
         stateMsg = "너무 좋은 상품이에요!";
         break;
      default:
         stateMsh = "";
      }
      e.innerHTML = stateMsg;
   }
   
   function noshow(){
      
      
      
      var i;
      var image;
      var el;
      
      
      for (i = 1; i <= 5; i++){
         image = 'star' + i;
         el = document.getElementById(image);
         el.src = "source/img/star-off.png";         
      }
      if (locked) return;
   }
   function clear_star(){
      locked = 1;
      noshow();
   }
   
   function lock(star){
      locked = 1;
      show(star);   
      
   }
   
   function mark(star){
      clear_star();
      lock(star);
      alert("선택"+star);
      document.starform.starpoint.value = star;
   }
   

   
</script>



</head>
<body>
<h3 style = "font-weight:bold">건성제품 상세정보 페이지 입니다.</h3>
   
<table border="1" style=" border:20px solid pink; margin-left:430px; width:1050px; height:500px;">
<tr>
<td rowspan="6"><img width=100% height=100% src="source/images/<%=product.getPicture()%>"></td>

<td>제품코드 : <%=product.getProduct_num() %></td>
</tr>
<tr>
<td>제품명 : <%=product.getProduct_id() %></td>
</tr>
<tr>
<td>제품타입 : <%=product.getProduct_type() %></td>
</tr>
<tr>
<td>성분 정보 : <%=product.getProduct_info() %></td>
</tr>
<tr>
<td>제품 가격 : <%=product.getProduct_pr()%></td>
</tr>
<tr>
<td>별점 : <%=product.getStarpoint() %></td>
</tr>
</table>

<% if(session.getAttribute("login_id") != null){ %>
<div class = "pr" style="display: inline-flex;">
<div class = "pr_starpoint" style="margin-left: 465px; width: 104px;">
   
   <form action="product_starpoint.pr" method = "post" name = "starform" style="margin-top: 38px; margin-bottom: 72px;">
      
         <img src = "source/img/star-off.png"  onclick = "mark(1);"  id = "star1" style="margin-top: 26px;"> 
         <img src = "source/img/star-off.png"  onclick = "mark(2);"  id = "star2" style="margin-top: 26px;"> 
         <img src = "source/img/star-off.png"  onclick = "mark(3);"  id = "star3" style="margin-top: 26px;"> 
         <img src = "source/img/star-off.png"  onclick = "mark(4);"  id = "star4" style="margin-top: 26px;"> 
         <img src = "source/img/star-off.png"  onclick = "mark(5);"  id = "star5" style="margin-top: 26px;"> 
         <input type = "hidden" name = "starpoint">
         <input type = "hidden" name = "productCode" value = "<%=product.getProduct_num() %>">
         <input type = "hidden" name = "productType" value = "<%=product.getProduct_type() %>">
         
         
      <a href="javascript:starform.submit()" class="action-button shadow animate blue"><span style="margin-left: -12px;">Star</span></a>
   </form>
</div>
<div class = "pr_coment" style="margin-left: 130px; margin-top: 38px;">
   <form action="product_comentWrite.pr" method = "post" name="comment">
      <input type = "hidden" name = "productCode" value = "<%=product.getProduct_num() %>">
      <input type = "hidden" name = "productType" value = "<%=product.getProduct_type() %>">
      <table>
         <tr>
            <td><textarea rows="5" cols="65" name = "pr_coment" style=" margin-top: -15px;"></textarea></td>
         </tr>
         
         <a href="javascript:comment.submit()" class="action-button shadow animate red"><span style="margin-left: -12px;">Save</span></a>
           <a href="javascript:comment.reset()" class="action-button shadow animate green"><span style="margin-left: -22px;">Delete</span></a>
      
      </table>
   </form>
</div>
</div>
<% } %>
   <section id="replyArea">
         <table style="margin: auto; width: 1000px;">
   <%
   if(replyList != null){
      for(int i=0;i<replyList.size();i++){
   %>      
            <tr >
            
               <td style="width: 80px;">
                  <%=replyList.get(i).getM_ID() %>
               </td>
               <td>
                  <%=replyList.get(i).getFB_REPLY() %>
               </td>
               <td style="width: 100px;">
                  <%=replyList.get(i).getREPLY_DATE() %>
               </td>
               
            </tr>
   <%    }
   }
   %>
         </table>
      </section>
</body>
</html>