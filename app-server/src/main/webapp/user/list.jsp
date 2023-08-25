<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%> <%-- directive element --%>
<%@ page import="java.util.List"%>
<%@ page import="nc7.javaproject.vo.User"%>

<jsp:useBean id="userDao" type="nc7.javaproject.dao.UserDao" scope="application"/>

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
<a href='/user/form.jsp'>새 회원</a>
</div>
<table border='1'>
<thead>
  <tr><th>번호</th> <th>이름</th> <th>이메일</th></tr>
</thead>

<%
    List<User> list = userDao.findAll();
    for (User user : list) {
      pageContext.setAttribute("user", user);
%>
    <tr>
        <td>${user.no}</td>
        <td>
            <img src='http://qjeteawhqfgf19010749.cdn.ntruss.com/user/${user.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
            <a href='/user/detail.jsp?no=${user.no}'>${user.name}</a></td>
        <td>${user.email}</td>
    </tr>

<%
    }
%>

</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>
</body>
</html>
