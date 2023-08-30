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
        background-color: #f4f0ff;
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
    }
    h1 {
        color: #7e57c2;
    }
    form {
        background-color: #ffffff;
        border: 1px solid #d1d1d1;
        border-radius: 5px;
        padding: 20px;
        width: 300px;
        margin: 20px auto;
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
    input[type="email"], input[type="password"] {
        width: 100%;
        padding: 8px;
        border: 1px solid #d1d1d1;
        border-radius: 3px;
    }
    button {
        background-color: #8b51ff; /* 변경된 색상 */
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }
    button:hover {
        background-color: #7b47ff; /* 변경된 색상 */
    }
    label {
        display: block;
        margin-bottom: 8px;
    }
    input[type="checkbox"] {
        vertical-align: middle;
        margin-right: 5px;
    }
    .login-controls {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
</style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>로그인</h1>

<form action='/auth/login' method='post'>
<table border='1'>
<tr>
  <th>이메일</th> <td><input type='email' name='email' value='${cookie.email.value}'></td>
</tr>
<tr>
  <th>암호</th> <td><input type='password' name='password'></td>
</tr>
</table>
<button>로그인</button>
 <input type='checkbox' name='saveEmail' ${cookie.email != null ? "checked" : ""}> 이메일 저장
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>
