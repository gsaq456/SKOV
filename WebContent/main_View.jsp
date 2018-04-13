<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="source/CSS/style.css">
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.blank{
   
   height: 200px;
}
header {
   width:1890px;
   height : 200px;
   display: block;
    overflow: hidden;
    padding: 40px 0 0;
    position: relative;
    margin-bottom: 100px;
}

.main_page{
   margin:0px;
   width:1890px;
   height: auto;
}


/* border-box */
html { box-sizing: border-box; }
*, *:before, *:after { box-sizing: inherit; }

/* Undoes normalize.css setting box-sizing to content-box */
input[type="search"] {
  box-sizing: border-box;
}


.container {
  float: right;
}



.Search {
  position: relative;
  display: flex;
  font-weight: 300;
  font-size: 40px;
  color: #555;
  width: 332px;
  float: right;
  border: solid;
  margin: 33px;
}



</style>


</head>
<body class="page1" id="top" style="background-color:#F4EEEE">

<div class = first_form>
<header style="position: absolute; ">
   <h1>
      <a href = "./main.me"><img class="img1"  src = "source/images/SKOV_logo.png"></a>
   </h1>

        
</header>
<div class = "blank"></div>
<div class="container">
  
  
<form action="searchList.sh" class="Search">
<select name="select" style="height:32px;"> <option value="건성">건성</option>
                      <option value="지성">지성</option>
                      <option value="복합성">복합성</option>
                      <option value="트러블">트러블</option>
                      </select>
                      
    <input type="text" name="searchid" id="Search-box" autocomplete="off" style="height:32px;width: 228px; font-size: 15px;"/> 
    <input type="submit" value="검색" style="height:32px;font-size: 17px;background-color: #555; color: white; margin-left: -29px; width: 63px;"/>
     
  </form>
</div>

</div>

<div id = "blogMenu"  style = "width : 1890px; position:absolute; top:300px;">
   <ul>
      <li class = "category"><a class = "" href = "#">카테고리</a>
         <ul>
            <li><a href = "gunsungList.pr">건성</a></li>
            <li><a href = "jisungList.pr">지성</a></li>
            <li><a href = "bokhapList.pr">복합성</a></li>
            <li><a href = "troubleList.pr">트러블</a></li>
         </ul>
      </li>
      <li class = "advice"><a href = "./mtmboardList.mtmbo">1:1 상담</a></li>
      <li class = "freeboard"><a class = "" href = "#">게시판</a>
         <ul>
            <li><a href = "boardList.bo">자유게시판</a></li>
            <li><a href = "Grade_List.gradebo">등업게시판</a></li>
         </ul>
      </li>
      <li class = "reportboard"><a class = "" href = "reportboardList.reportbo">신고게시판</a></li>
   </ul>
</div>
<div class = "blank"></div>

<section class = "main_page" >
   <div class = "page">
      
      
      <%
         if(request.getParameter("main_page") == null || request.getParameter("main_page").equals("base")){
      %>
         <jsp:include page="base_View.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("gunsungList")){
      %>
         <jsp:include page="View_skintype/gunsung_View.jsp"/>
      <%   
         } else if(request.getParameter("main_page").equals("gunsung1")){
      %>
         <jsp:include page="View_skinproduct/gunsung1.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("jisungList")){
      %>
         <jsp:include page="View_skintype/jisung_View.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("jisung1")){
      %>
            <jsp:include page="View_skinproduct/jisung1.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("bokhapList")){
      %>
         <jsp:include page="View_skintype/bokhap_View.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("bokhap1")){
      %>
            <jsp:include page="View_skinproduct/bokhap1.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("troubleList")){
      %>
         <jsp:include page="View_skintype/trouble_View.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("trouble1")){
      %>
            <jsp:include page="View_skinproduct/trouble1.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("jisung")){
      %>
         <jsp:include page="View_skintype/jisung_View.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("bokhap")){
      %>
         <jsp:include page="View_skintype/bokhap_View.jsp"/>
      <%      
         } else if(request.getParameter("main_page").equals("trouble")){
      %>
         <jsp:include page="View_skintype/trouble_View.jsp"/>
      <%   
         } else if(request.getParameter("main_page").equals("free")){
      %>
         <jsp:include page = "freeboard/qna_board_list.jsp"/>
      <%   
         } else if(request.getParameter("main_page").equals("free_write")){
      %>
         <jsp:include page = "freeboard/qna_board_write.jsp"/>   
      <%   
         } else if(request.getParameter("main_page").equals("free_write_view")){
      %>
         <jsp:include page = "/freeboard/qna_board_view.jsp"/>
      <%   
         } else if(request.getParameter("main_page").equals("free_modify")){
      %>
         <jsp:include page = "/freeboard/qna_board_modify.jsp"/>      
      <%
         } else if(request.getParameter("main_page").equals("grade")){
      %>
         <jsp:include page="gradeboard/Grade_board_list.jsp"/>
      <%   
         } else if(request.getParameter("main_page").equals("grade_write")){
      %>
         <jsp:include page = "gradeboard/Grade_board_write.jsp"/>
      <%
         } else if(request.getParameter("main_page").equals("grade_write_view")){
      %>
         <jsp:include page = "/gradeboard/Grade_board_view.jsp"/>
      <%
         } else if(request.getParameter("main_page").equals("grade_modify")){
      %>
         <jsp:include page = "/gradeboard/Grade_board_modify.jsp"/>   
      <%
         } else if(request.getParameter("main_page").equals("mantoman")){
      %>
         <jsp:include page="mtmboard/mtm_board_list.jsp"/>
      <%   
         } else if(request.getParameter("main_page").equals("mtm_write")){
      %>
         <jsp:include page = "mtmboard/mtm_board_write.jsp"/>   
      <%   
         } else if(request.getParameter("main_page").equals("mtm_write_view")){
      %>
         <jsp:include page = "mtmboard/mtm_board_view.jsp"/>   
      <%   
         } else if(request.getParameter("main_page").equals("mtm_modify")){
      %>
         <jsp:include page = "mtmboard/mtm_board_modify.jsp"/>
      <%   
         } else if(request.getParameter("main_page").equals("mtm_reply")){
      %>
         <jsp:include page = "mtmboard/mtm_board_reply.jsp"/>   
      <%
         } else if(request.getParameter("main_page").equals("report")){
      %>
         <jsp:include page="reportboard/report_board_list.jsp"/>
      <%   
         } else if(request.getParameter("main_page").equals("report_write")){
      %>
         <jsp:include page = "reportboard/report_board_write.jsp"/>   
      <%
         } else if(request.getParameter("main_page").equals("report_write_view")){
      %>
         <jsp:include page = "reportboard/report_board_view.jsp"/>
      <%
         } else if(request.getParameter("main_page").equals("report_modify")){
      %>
         <jsp:include page = "reportboard/report_board_modify.jsp"/>   
      <%      
         }else if(request.getParameter("main_page").equals("ans")){
      %>
         <jsp:include page="ansboard/ans_board_list.jsp"/>
      <%   
         }else if(request.getParameter("main_page").equals("ans_write")){
      %>
         <jsp:include page ="ansboard/ans_board_write.jsp"/>      
      <%
         } else if(request.getParameter("main_page").equals("ans_write_view")){
      %>
         <jsp:include page = "ansboard/ans_board_view.jsp"/>   
      <%
         } else if(request.getParameter("main_page").equals("ans_modify")){
      %>
         <jsp:include page = "ansboard/ans_board_modify.jsp"/>   
      <%
         } else if(request.getParameter("main_page").equals("notice")){
      %>
         <jsp:include page="noticeboard/Notice_board_list.jsp"/>
      <%   
         } else if(request.getParameter("main_page").equals("notice_write")){
      %>
         <jsp:include page ="noticeboard/Notice_board_write.jsp"/>   
      <%
         } else if(request.getParameter("main_page").equals("notice_write_view")){
      %>
         <jsp:include page = "noticeboard/Notice_board_view.jsp"/>
      <%
         } else if(request.getParameter("main_page").equals("notice_modify")){
      %>
         <jsp:include page = "noticeboard/Notice_board_modify.jsp"/>
      <%
         } else if(request.getParameter("main_page").equals("psearchList")){
      %>
         <jsp:include page = "psearch_View.jsp"/>
      <%   
         }
      %>
      
   </div>
</section>




<footer style="background-color:#252323;width:1890px; height: 331px;">
      <div style="width:100%;text-align:center; margin-top:90px;  color:#FFFFFF;">
         <div style="width:228px;display:inline-block;text-align:left;;margin-bottom: 100px;">
         <span style="display:block;padding-bottom:15px;">주소</span>
         <span style="display:block;padding-bottom:11px;"> 인천광역시 남구 학익동 663-1  <br> 태승빌딩 5층</span>
         <span style="display:block;padding-bottom:11px;">032-876-3332 </span>
         <span>info@gmail.com</span>
      </div>
      <div style="width:125px;display:inline-block;text-align:left;margin-left:110px; margin-top:90px; margin-bottom:20px; color:#FFFFFF;   " >
         <span style="display:block;padding-bottom:15px; font-size: 13px;">Contact</span>
         <span style="display:block;"><a href="./main.me" style="text-decoration:none;color:#FFFFFF;font-size: 14px;">Home</a></span>
         <span style="display:block;"><a href="./gunsung.me" style="text-decoration:none;color:#FFFFFF;font-size: 14px;">카테고리</a></span>
         <span style="display:block;"><a href="./mantoman.me" style="text-decoration:none;color:#FFFFFF;font-size: 14px;">1:1상담게시판</a></span>
         <span style="display:block;"><a href="./free.me" style="text-decoration:none;color:#FFFFFF;font-size: 14px;">자유게시판</a></span>
         <span><a href="#" style="text-decoration:none;color:#FFFFFF;font-size: 14px;">신고게시판</a></span>
      </div>
      <!-- 여기서부터 -->
      <div style="width:400px;text-align:left; float: right; margin-top:90px; margin-right:684px;"  >
         <span style="padding-bottom:15px;">SNS</span>
         <h3>
         <span ><a href="#" ><img src="source/images/f.png"style="width: 55px; height: auto; margin-bottom : 10px;"></a></span>
         <span ><a href="#"><img src="source/images/i.png"style="width: 75px; height: auto; margin-top : 10px; "></a></span>
         <span ><a href="#" ><img src="source/images/y.png"style="width:60px; height: auto; margin-bottom : 10px;"></a></span>
         </h3>   
      </div>
      <div class="youtube"><embed src="https://www.youtube.com/embed/Ysjmeu5U1d4" width="300px" height="200px"></embed></div>
      <!-- 여기까지가 한텝 -->
      
      <!--  
      <div class="form">
            <span class="header-form">SIGN UP</span>    
           <form id="search-form" class="form-search form-horizontal">
            <input type="text" class="input-search" placeholder="">
            <button type="submit" class="btn-search"><span style="font-size:11px;">SUBMIT</span></button>
            </form>
        </div>
       -->
      </div>
</footer>

</div>

</body>
</html>