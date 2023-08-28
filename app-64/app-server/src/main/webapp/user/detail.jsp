<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ page import="nc7.javaproject.vo.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="userDao" type="nc7.javaproject.dao.UserDao" scope="application"/>
<c:set var="user" value="${userDao.findBy(param.no)}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원</title>
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
    form {
        width: 50%;
        margin: 20px auto;
        padding: 20px;
        background-color: #ffffff;
        border: 1px solid #d1d1d1;
        border-radius: 5px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 15px;
    }
    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #d1d1d1;
    }
    th {
        background-color: #f0f0f0;
    }
    input[type="text"], input[type="email"], input[type="password"], select {
        width: 100%;
        padding: 8px;
        border: 1px solid #d1d1d1;
        border-radius: 3px;
    }
    button {
        background-color: #8b51ff;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }
    button[type="reset"] {
        background-color: #d1d1d1;
        color: #333;
        cursor: pointer;
    }
    a {
        margin-left: 10px;
        text-decoration: none;
    }
</style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원</h1>
<c:if test="${empty user}">
    <p>해당 번호의 회원이 없습니다!</p>
</c:if>
<c:if test="${not empty user}">
  <form action='/user/update.jsp' method='post' enctype='multipart/form-data'>
  <table border='1'>
  <tr>
      <th style='width:120px;'>사진</th>
      <td style='width:300px;'>
        <c:if test="${empty user.photo}">
          <img style='height:80px' src='/images/avatar.png'>
        </c:if>
        <c:if test="${not empty user.photo}">
          <a href='https://kr.object.ncloudstorage.com/bitcamp-bucket-05/user/${user.photo}'>
            <img src='http://qjeteawhqfgf19010749.cdn.ntruss.com/user/${user.photo}?type=f&w=60&h=80&faceopt=true&ttype=jpg'>
          </a>
        </c:if>
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
</c:if>
<jsp:include page="../footer.jsp"/>

</body>
</html>
