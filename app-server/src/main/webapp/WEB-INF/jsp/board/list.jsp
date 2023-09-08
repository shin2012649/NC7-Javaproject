<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시글</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f0ff;
        margin: 0;
        padding: 0;
    }
    h1 {
        color: #7e57c2;
    }
    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        border: 1px solid #d1d1d1;
        border-radius: 5px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }
    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #d1d1d1;
    }
    th {
        background-color: #f0f0f0;
    }
    a {
        color: #7e57c2;
        text-decoration: none;
    }
    a:hover {
        text-decoration: underline;
    }
    .new-post {
        margin: 5px;
    }
    .main-link {
        display: block;
        margin: 20px auto;
        width: 80%;
        text-align: center;
    }
</style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>게시글 목록</h1>
<div style='margin:5px;'>
<a href='form?category=${param.category}'>새 글</a>
</div>
<table border='1'>
<thead>
  <tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>
</thead>

<tbody>
<c:forEach items="${list}" var="board">
    <tr>
      <td>${board.no}</td>
      <td><a href='detail/${param.category}/${board.no}'>
        ${board.title.length() > 0 ? board.title : "제목없음"}
        </a>
      </td>
      <td>${board.writer.name}</td>
      <td>${board.viewCount}</td>
      <td><fmt:formatDate value="${board.createdDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
</c:forEach>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html
