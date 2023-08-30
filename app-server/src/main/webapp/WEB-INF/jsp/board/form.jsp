<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>비트캠프</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #7e57c2;
        }
        form {
            background-color: #f5f5f5;
            padding: 20px;
            border: 1px solid #d1d1d1;
            border-radius: 5px;
            margin: 20px auto;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        input[type="text"], textarea, input[type="file"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #d1d1d1;
            border-radius: 3px;
        }
        textarea {
            resize: vertical;
            height: 150px;
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

<h1>게시글</h1>
<form action='/board/add' method='post' enctype='multipart/form-data'>
    <label for="title">제목</label>
    <input type='text' name='title' id="title">
    <br>
    <label for="content">내용</label>
    <textarea name='content' id="content"></textarea>
    <br>
    <label for="files">파일</label>
    <input type='file' name='files' multiple id="files">
    <br>
    <input type='hidden' name='category' value='1'>
    <button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>
