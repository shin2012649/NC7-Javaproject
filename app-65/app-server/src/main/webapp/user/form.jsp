<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프</title>
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
    button:hover {
        background-color: #7b47ff;
    }
</style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>회원</h1>
<form action='/user/add' method='post' enctype='multipart/form-data'>
<table>
<tr>
  <th>이름</th> <td><input type='text' name='name'></td>
</tr>
<tr>
  <th>이메일</th> <td><input type='email' name='email'></td>
</tr>
<tr>
  <th>암호</th> <td><input type='password' name='password'></td>
</tr>
<tr>
  <th>성별</th>
  <td>
    <select name='gender'>
      <option value='M'>남자</option>
      <option value='W'>여자</option>
    </select>
  </td>
</tr>
<tr>
  <th>사진</th> <td><input type='file' name='photo'></td>
</tr>
</table>
<button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>
