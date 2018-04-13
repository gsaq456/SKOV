<%@page import="grade_vo.Grade_PageInfo"%>
<%@page import="grade_vo.Grade_Bean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	ArrayList<Grade_Bean> articleList=(ArrayList<Grade_Bean>)request.getAttribute("articleList");
    Grade_PageInfo pageInfo = (Grade_PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage();
	int endPage=pageInfo.getEndPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>MVC 게시판</title>
<style type="text/css">
#registForm {
	width: 500px;
	height: 600px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}
h4 {
	text-align: center;
}

table {
	margin: auto;
	width: 600px;
	margin-bottom: 50px;
}

#tr_top {
	background: orange;
	text-align: center;
}

#pageList {
	margin: auto;
	width: 500px;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
}
* {margin:0;
   padding:0;}   

a {text-decoration : none;
   color : #333;}

.list-table thead th {height : 40px;
                 border-top : 2px solid #09C;
                 border-bottom : 1px solid #BDBDBD;
                 font : bold 17px 'malgun gothic';}

.list-table tbody td {text-align : center;
                 padding : 10px 0;
                 border-bottom : 1px solid #BDBDBD;
                 height : 20px;
                 font : 14px 'malgun gothic';}
#pagination {
	width: 400px;
    margin-left: 700px;
	text-align: center;
    display: inline-block;
    vertical-align: middle;
    border-radius: 4px;
    padding: 1px 2px 4px 2px;
    border-top: 1px solid #AEAEAE;
    border-bottom: 1px solid #FFFFFF;
    background-color: #DADADA;
    background-image: -webkit-linear-gradient(top, #DBDBDB, #E2E2E2);
    background-image:    -moz-linear-gradient(top, #DBDBDB, #E2E2E2);
    background-image:     -ms-linear-gradient(top, #DBDBDB, #E2E2E2);
    background-image:      -o-linear-gradient(top, #DBDBDB, #E2E2E2);
    background-image:         linear-gradient(top, #DBDBDB, #E2E2E2);
}
#pagination a, #pagination i {
    display: inline-block;
    vertical-align: middle;
    width: 22px;
    color: #7D7D7D;
    text-align: center;
    font-size: 10px;
    padding: 3px 0 2px 0;
    -webkit-user-select:none;
       -moz-user-select:none;
        -ms-user-select:none;
         -o-user-select:none;
            user-select:none;
}

#pagination a {
    margin: 0 2px 0 2px;
    border-radius: 4px;
    border: 1px solid #E3E3E3;
    cursor: pointer;
    box-shadow: inset 0 1px 0 0 #FFF, 0 1px 2px #666;
    text-shadow: 0 1px 1px #FFF;
    background-color: #E6E6E6;
    background-image: -webkit-linear-gradient(top, #F3F3F3, #D7D7D7);
    background-image:    -moz-linear-gradient(top, #F3F3F3, #D7D7D7);
    background-image:     -ms-linear-gradient(top, #F3F3F3, #D7D7D7);
    background-image:      -o-linear-gradient(top, #F3F3F3, #D7D7D7);
    background-image:         linear-gradient(top, #F3F3F3, #D7D7D7);
}


</style>
</head>

<body>
	<!-- 게시판 리스트 -->

	<section id="listForm">
		<span style="font-size: 30px; font-weight: bolder; text-align: center;">등업 게시판</span>
	<%if(session.getAttribute("login_id") != null){ %>
		<h4><a href="Grade_WriteForm.gradebo">게시판글쓰기</a></h4>
	<%} %>
		<table class = "list-table" style = "width:80%; border:0; margin-top:50px; background-color: gainsboro;">
			<%
if(articleList != null && listCount > 0){
%>
<thead style="background-color: cadetblue;">   
  	<tr>
      <th width = "70">번호</th>
      <th width = "400">제목</th>
      <th width = "120">작성자</th>
      <th width = "100">등록일자</th>
    </tr>
</thead>
		

			<%
		for(int i=0;i<articleList.size();i++){
			
	%>
	<tbody>
 	 <tr>
    	<td width = "70"><%=articleList.get(i).getUP_NUM()%></td>
    	<td width = "500">
         	<a href="Grade_Detail.gradebo?up_num=<%=articleList.get(i).getUP_NUM()%>&page=<%=nowPage%>">
						<%=articleList.get(i).getUP_SUBJECT()%>
        	 </a>
    	</td>
		<td width = "120"><%=articleList.get(i).getUP_NAME() %></td>
    	<td width = "100"><%=articleList.get(i).getUP_DATE() %></td>
     
     </tr>
	</tbody>
			
			
			
			<%} %>
		</table>
	</section>

	<script type="text/javascript">
/* * * * * * * * * * * * * * * * *
 * Pagination
 * javascript page navigation
 * * * * * * * * * * * * * * * * */
var page = 0;
var Pagination = {

    code: '',

    // --------------------
    // Utility
    // --------------------

    // converting initialize data
    Extend: function(data) {
        data = data || {};
        Pagination.size = data.size || 300;
        Pagination.page = data.page || 1;
        Pagination.step = data.step || 3;
    },

    // add pages by number (from [s] to [f])
    Add: function(s, f) {
        for (var i = s; i < f; i++) {
            Pagination.code += '<a>' + i + '</a>';
        }
    },

    // add last page with separator
    Last: function() {
        Pagination.code += '<i>...</i><a>' + Pagination.size + '</a>';
    },

    // add first page with separator
    First: function() {
        Pagination.code += '<a>1</a><i>...</i>';
    },



    // --------------------
    // Handlers
    // --------------------

    // change page
    Click: function() {
        Pagination.page = +this.innerHTML;
        location.href = "Grade_List.gradebo?page="+Pagination.page;
        Pagination.Start();
    },

    // previous page
    Prev: function() {
        Pagination.page--;
        if (Pagination.page < 1) {
            Pagination.page = 1;
        }
        Pagination.Start();
    },

    // next page
    Next: function() {
        Pagination.page++;
        if (Pagination.page > Pagination.size) {
            Pagination.page = Pagination.size;
        }
        Pagination.Start();
    },



    // --------------------
    // Script
    // --------------------

    // binding pages
    Bind: function() {
        var a = Pagination.e.getElementsByTagName('a');
        for (var i = 0; i < a.length; i++) {
            if (+a[i].innerHTML === Pagination.page) a[i].className = 'current';
            a[i].addEventListener('click', Pagination.Click, false);
        }
    },

    // write pagination
    Finish: function() {
        Pagination.e.innerHTML = Pagination.code;
        Pagination.code = '';
        Pagination.Bind();
    },

    // find pagination type
    Start: function() {
        if (Pagination.size < Pagination.step * 2 + 6) {
            Pagination.Add(1, Pagination.size + 1);
        }
        else if (Pagination.page < Pagination.step * 2 + 1) {
            Pagination.Add(1, Pagination.step * 2 + 4);
            Pagination.Last();
        }
        else if (Pagination.page > Pagination.size - Pagination.step * 2) {
            Pagination.First();
            Pagination.Add(Pagination.size - Pagination.step * 2 - 2, Pagination.size + 1);
        }
        else {
            Pagination.First();
            Pagination.Add(Pagination.page - Pagination.step, Pagination.page + Pagination.step + 1);
            Pagination.Last();
        }
        Pagination.Finish();
    },



    // --------------------
    // Initialization
    // --------------------

    // binding buttons
    Buttons: function(e) {
        var nav = e.getElementsByTagName('a');
        nav[0].addEventListener('click', Pagination.Prev, false);
        nav[1].addEventListener('click', Pagination.Next, false);
    },

    // create skeleton
    Create: function(e) {

        var html = [
            '<a>&#9668;</a>', // previous button
            '<span></span>',  // pagination container
            '<a>&#9658;</a>'  // next button
        ];

        e.innerHTML = html.join('');
        Pagination.e = e.getElementsByTagName('span')[0];
        Pagination.Buttons(e);
    },

    // init
    Init: function(e, data) {
        Pagination.Extend(data);
        Pagination.Create(e);
        Pagination.Start();
    }
};



/* * * * * * * * * * * * * * * * *
* Initialization
* * * * * * * * * * * * * * * * */

var init = function() {
    Pagination.Init(document.getElementById('pagination'), {
        size: 10, // pages size
        page: 1,  // selected page
        step: 3   // pages before and after current
    });
};

document.addEventListener('DOMContentLoaded', init, false);


</script>
	<section id = "pagination"></section>
	<%
    }
	else
	{
	%>
	<section id="emptyArea">등록된 글이 없습니다.</section>
	<%
	}
%>

</body>
</html>