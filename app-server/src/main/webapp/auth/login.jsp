<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="nc7.javaproject.dao.UserDao"%>
<%@ page import="nc7.javaproject.vo.User"%>

<%
    request.setAttribute("refresh", "2;url=/auth/form.jsp");

    User u = new User();
    u.setEmail(request.getParameter("email"));
    u.setPassword(request.getParameter("password"));

    if (request.getParameter("saveEmail") != null) {
      Cookie cookie = new Cookie("email", u.getEmail());
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
%>

<jsp:useBean id="userDao" type="nc7.javaproject.dao.UserDao" scope="application"/>

<%
    User loginUser = userDao.findByEmailAndPassword(u);
    if (loginUser == null) {
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }

    session.setAttribute("loginUser", loginUser);
    response.sendRedirect("/");
%>
