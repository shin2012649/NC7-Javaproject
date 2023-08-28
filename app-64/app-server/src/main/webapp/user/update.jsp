<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="nc7.javaproject.vo.User"%>

<jsp:useBean id="userDao" type="nc7.javaproject.dao.UserDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="nc7.util.NcpObjectStorageService" scope="application"/>
<%
    request.setAttribute("refresh", "2;url=list.jsp");

    User user = new User();
    user.setNo(Integer.parseInt(request.getParameter("no")));
    user.setName(request.getParameter("name"));
    user.setEmail(request.getParameter("email"));
    user.setPassword(request.getParameter("password"));
    user.setGender(request.getParameter("gender").charAt(0));

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile(
          "bitcamp-bucket-05", "user/", photoPart);
      user.setPhoto(uploadFileUrl);
    }

    if (userDao.update(user) == 0) {
        throw new Exception("회원이 없습니다.");
    } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list.jsp");
    }

%>
