package nc7.javaproject.controller;


import nc7.javaproject.service.BoardService;
import nc7.javaproject.service.NcpObjectStorageService;
import nc7.javaproject.vo.AttachedFile;
import nc7.javaproject.vo.Board;
import nc7.javaproject.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/board")
public class BoardController {

  {
    System.out.println("BoardController 생성됨!");
  }

  @Autowired
  BoardService boardService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @GetMapping("form")
  public void form() {
  }

  @PostMapping("add")
  public String add(
          Board board,
          MultipartFile[] files,
          Model model,
          HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/form";
    }

    board.setWriter(loginUser);

    try {
      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      for (MultipartFile part : files) {
        if (part.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService.uploadFile(
                  "bitcamp-bucket-05", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      boardService.add(board);
      return "redirect:list?category=" + board.getCategory();

    } catch (Exception e) {
      model.addAttribute("message", "게시글 등록 오류!");
      model.addAttribute("refresh", "2;url=list?category=" + board.getCategory());
      throw e;
    }
  }

  @GetMapping("delete")
  public String delete(
          int no,
          int category,
          Model model,
          HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/form";
    }

    try {
      Board b = boardService.get(no);

      if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        boardService.delete(b.getNo());
        return "redirect:list?category=" + category;
      }

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=list?category=" + category);
      throw e;
    }
  }

  @GetMapping("detail/{category}/{no}")
  public String detail(
          @PathVariable int category,
          @PathVariable int no,
          Model model) throws Exception {
    try {
      Board board = boardService.get(no);
      if (board != null) {
        boardService.increaseViewCount(no);
        model.addAttribute("board", board);
      }
      return "board/detail";

    } catch (Exception e) {
      model.addAttribute("refresh", "5;url=/board/list?category=" + category);
      throw e;
    }
  }

  @GetMapping("list")
  public void list(
          int category,
          Model model) throws Exception {
    try {
      model.addAttribute("list", boardService.list(category));
    } catch (Exception e) {
      model.addAttribute("refresh", "1;url=/");
      throw e;
    }
  }

  @PostMapping("update")
  public String update(
          Board board,
          MultipartFile[] files,
          Model model,
          HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/form";
    }

    try {
      Board b = boardService.get(board.getNo());
      if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("게시글이 존재하지 않거나 변경 권한이 없습니다.");
      }

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
      for (MultipartFile part : files) {
        if (part.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService.uploadFile(
                  "bitcamp-bucket-05", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      boardService.update(board);
      return "redirect:list?category=" + b.getCategory();

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=detail?no=" + board.getNo());
      throw e;
    }
  }

  @GetMapping("fileDelete")
  public String fileDelete(
          int no,
          Model model,
          HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/form";
    }

    Board board = null;
    try {
      AttachedFile attachedFile = boardService.getAttachedFile(no);
      board = boardService.get(attachedFile.getBoardNo());
      if (board.getWriter().getNo() != loginUser.getNo()) {
        throw new Exception("게시글 변경 권한이 없습니다!");
      }

      if (boardService.deleteAttachedFile(no) == 0) {
        throw new Exception("해당 번호의 첨부파일이 없다.");
      } else {
        return "redirect:detail?category=" + board.getCategory() + "&no=" + board.getNo();
      }

    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=detail?category=" + board.getCategory() + "&no=" + board.getNo());
      throw e;
    }
  }
}











