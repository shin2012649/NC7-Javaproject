package nc7.javaproject.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import nc7.javaproject.vo.AttachedFile;
import nc7.javaproject.vo.Board;
import nc7.javaproject.vo.Participant;

@WebServlet("/board/add")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class BoardAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Participant loginUser = (Participant) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    try {
      Board board = new Board();
      board.setWriter(loginUser);
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setCategory(Integer.parseInt(request.getParameter("category")));

      String uploadDir = request.getServletContext().getRealPath("/upload/board/");
      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      for (Part part : request.getParts()) {
        //        System.out.println(part.getName());
        if (part.getName().equals("files") && part.getSize() > 0) {
          String filename = UUID.randomUUID().toString();
          part.write(uploadDir + filename);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(filename);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);


      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='refresh' content='1;url=/board/list?category=%d'>\n", board.getCategory());
      out.println("<title>게시글</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>게시글 등록</h1>");
      try {
        //        System.out.println(board.getNo());
        InitServlet.boardDao.insert(board);
        //        System.out.println(board.getNo());

        if (attachedFiles.size() > 0) {
          int count = InitServlet.boardDao.insertFiles(board);
          System.out.println(count);
        }

        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>등록 성공입니다!</p>");

      } catch (Exception e) {
        InitServlet.sqlSessionFactory.openSession(false).rollback();
        out.println("<p>등록 실패입니다!</p>");
        e.printStackTrace();
      }
      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}