<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="nc7.javaproject.vo.AttachedFile"%>
<%@ page import="nc7.javaproject.vo.Board"%>

<jsp:useBean id="boardDao" type="nc7.javaproject.dao.BoardDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="nc7.util.NcpObjectStorageService" scope="application"/>
<jsp:useBean id="loginUser" class="nc7.javaproject.vo.User" scope="session"/>
<%
    if (loginUser.getNo() == 0) {
      response.sendRedirect("/auth/form.jsp");
      return;
    }

    request.setAttribute("refresh", "2;url=list.jsp?category=" + request.getParameter("category"));

    Board board = new Board();
    board.setWriter(loginUser);
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));
    board.setCategory(Integer.parseInt(request.getParameter("category")));

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
    for (Part part : request.getParts()) {
        if (part.getName().equals("files") && part.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService.uploadFile(
                  "bitcamp-bucket-05", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
    }
    board.setAttachedFiles(attachedFiles);

    if (boardDao.update(board) == 0) {
        throw new Exception("게시글이 없거나 변경 권한이 없습니다.");
    } else {
        if (attachedFiles.size() > 0) {
          int count = boardDao.insertFiles(board);
          System.out.println(count);
        }

        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list.jsp?category=" + request.getParameter("category"));
    }
%>











