package nc7.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.vo.Board;

public class MysqlBoardDao implements BoardDao {

  Connection con;
  int category;

  public  MysqlBoardDao(Connection con, int category) {
    this.con = con;
    this.category = category;
  }

  @Override
  public void insert(Board board) {
    try (Statement stmt = con.createStatement();) {

      stmt.executeUpdate(String.format(
          "insert into JavaProject_board(title, content, writer, password, category) values('%s', '%s', '%s', '%s', '%d')",
          board.getTitle(), board.getContent(), board.getWriter(), board.getPassword(), category));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Board> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(
            "select board_no, title,  writer, view_count, created_date from JavaProject_board where category=%d order by board_no asc",
            category))) {

      List<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setWriter(rs.getString("writer"));
        board.setViewCount(rs.getInt("view_count"));
        board.setCreatedDate(rs.getDate("created_date"));

        list.add(board);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Board findBy(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(
            "select board_no, title, content, writer, password, view_count, created_date from JavaProject_board where board_no=%d and category=%d",
            no, category))) {

      if (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_no"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setWriter(rs.getString("writer"));
        board.setPassword(rs.getString("password"));
        board.setViewCount(rs.getInt("view_count"));
        board.setCreatedDate(rs.getDate("created_date"));

        return board;
      }

      return null;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Board board) {
    try (Statement stmt = con.createStatement();) {

      return stmt.executeUpdate(String.format(
          "update JavaProject_board set title='%s', content='%s' where board_no=%d and category=%d",
          board.getTitle(), board.getContent(), board.getNo(), category));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement();) {

      return stmt.executeUpdate(
          String.format("delete from JavaProject_board where board_no=%d and category=%d", no, category));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
