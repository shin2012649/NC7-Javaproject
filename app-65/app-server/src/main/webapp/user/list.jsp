<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원 목록</h1>
<div style='margin:5px;'>
<a href='/user/add'>새 회원</a>
</div>
<table border='1'>
<thead>
  <tr><th>번호</th> <th>이름</th> <th>이메일</th></tr>
</thead>
<tbody>
<c:forEach items="${list}" var="user">
    <tr>
        <td>${user.no}</td>
        <td>
            <img src='http://qjeteawhqfgf19010749.cdn.ntruss.com/user/${user.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
            <a href='/user/detail?no=${user.no}'>${user.name}</a></td>
        <td>${user.email}</td>
    </tr>
</c:forEach>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>
</body>
</html>