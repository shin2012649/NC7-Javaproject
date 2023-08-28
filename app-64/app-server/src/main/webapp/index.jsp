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
            text-align: center;
            margin-top: 50px;
        }
        p {
            text-align: center;
            font-size: 18px;
            color: #555;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #d1d1d1;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .main-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #7e57c2;
            text-decoration: none;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">
    <h1>MyApp(JSP)</h1>
    <p>개인 실습 프로젝트입니다</p>
    <a href="/" class="main-link">메인 페이지로 돌아가기</a>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
