<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="nc7.javaproject.vo.User"%>

<jsp:useBean id="userDao" type="nc7.javaproject.dao.UserDao" scope="application"/>
<%
    User user = userDao.findBy(Integer.parseInt(request.getParameter("no")));
    pageContext.setAttribute("user", user);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원</h1>

<%
    if (user == null) {
%>
<p>해당 번호의 회원이 없습니다!</p>
<%
    } else {
%>
  <form action='/user/update.jsp' method='post' enctype='multipart/form-data'>
  <table border='1'>
  <tr>
      <th style='width:120px;'>사진</th>
      <td style='width:300px;'>
        <% if (user.getPhoto() == null) { %>
          <img style='height:80px' src='/images/avatar.png'>
        <% } else { %>
          <a href='https://kr.object.ncloudstorage.com/bitcamp-bucket-05/user/${user.photo}'>
            <img src='http://qjeteawhqfgf19010749.cdn.ntruss.com/user/${user.photo}?type=f&w=60&h=80&faceopt=true&ttype=jpg'>
          </a>
        <% } %>
          <input type='file' name='photo'></td></tr>
  <tr>
      <th style='width:120px;'>번호</th>
      <td style='width:300px;'><input type='text' name='no' value='${user.no}' readonly></td></tr>
  <tr>
      <th>이름</th>
      <td><input type='text' name='name' value='${user.name}'></td></tr>
  <tr>
      <th>이메일</th>
      <td><input type='email' name='email' value='${user.email}'></td></tr>
  <tr>
      <th>암호</th>
      <td><input type='password' name='password'></td></tr>
  <tr>
      <th>성별</th>
      <td><select name='gender'>
          <option value='M' ${String.valueOf(user.getGender()) == 'M' ? "selected" : ""}>남자</option>
          <option value='W' ${String.valueOf(user.getGender()) == 'W' ? "selected" : ""}>여자</option></select></td></tr>
  </table>
  <div>
  <button>변경</button>
  <button type='reset'>초기화</button>
      <a href='/user/delete.jsp?no=${user.no}'>삭제</a>
  <a href='/user/list.jsp'>목록</a>
  </div>
  </form>
<%
    }
%>
<jsp:include page="../footer.jsp"/>

</body>
</html>
