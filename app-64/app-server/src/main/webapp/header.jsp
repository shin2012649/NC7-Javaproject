<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<div style='height:50px;background-color:#f4f0ff;text-align:left;'>
    <img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTy3sDvOSn4gxmzW3m6G7QtcmUCjWcOXcFFEA&usqp=CAU' style='height:50px; margin-right: 10px;'>
    <jsp:useBean id="loginUser" class="nc7.javaproject.vo.User" scope="session"/>
    <%
        if (loginUser.getNo() == 0) {
            out.println("<a href='/auth/form.jsp'>로그인</a>");
        } else {
            if (loginUser.getPhoto() == null) {
                out.println("<img style='height:40px' src='/images/avatar.png'>");
            } else {
                out.println(String.format(
                    "<img src='http://qjeteawhqfgf19010749.cdn.ntruss.com/user/%s?type=f&w=30&h=40&faceopt=true&ttype=jpg'>",
                    loginUser.getPhoto()));
            }
            out.println(String.format("%s <a href='/auth/logout.jsp'>로그아웃</a>", loginUser.getName()));
        }
    %>
    <a href='/user/list.jsp'>회원</a>
    <a href='/board/list.jsp?category=1'>게시글</a>
    <a href='/board/list.jsp?category=2'>평론</a>
</div>
