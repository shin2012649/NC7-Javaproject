package nc7.mapp.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Board;
import nc7.mapp.vo.Participant;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Participant loginUser = (Participant) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    int category = Integer.parseInt(request.getParameter("category"));

    Board b = new Board();
    b.setNo(Integer.parseInt(request.getParameter("no")));
    b.setWriter(loginUser);
    b.setCategory(category);

    try {
      if (InitServlet.boardDao.delete(b) == 0) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        response.sendRedirect("/board/list?category=" + category);
      }
      InitServlet.sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
