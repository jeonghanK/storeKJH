package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import exception.ShopException;
import logic.Board;
import logic.ShopService;

@Controller
public class BoardController {
	@Autowired
	private ShopService service;

	@RequestMapping("board/list")
	public ModelAndView list(Integer pageNum, String searchType, String searchContent) {
		if (pageNum == null || pageNum.toString().equals("")) {
			pageNum = 1;
		}

		ModelAndView mav = new ModelAndView();
		int limit = 10;
		int listcount = service.boardcount(searchType, searchContent);
		List<Board> boardlist = service.boardList(searchType, searchContent, pageNum, limit);
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = ((int) ((pageNum / 10.0 + 0.9) - 1)) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardcnt = listcount - (pageNum - 1) * limit;
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount", listcount);
		mav.addObject("boardlist", boardlist);
		mav.addObject("boardcnt", boardcnt);
		return mav;
	}

	// 게시글 등록하기
	/*
	 * 1. 유효성 검증 2. DB에 등록하기 3. 등록성공 : list.shop, 등록실패 : write.shop 페이지 이동
	 */
	@RequestMapping(value = "board/write", method = RequestMethod.POST)
	public ModelAndView write(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav; // 예외 발생시 원래 페이지로 이동.
		}
		try {
			service.boardWrite(board, request);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("게시물 등록 실패", "write.shop");
		}
		return mav;
	}

	@RequestMapping(value = "board/reply", method = RequestMethod.POST)
	// HttpServletRequest : 첨부파일 path를 쓰기위해 사용
	public ModelAndView reply(@Valid Board board, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			board = service.getBoard(board.getNum());
			mav.addObject("board", board);
			return mav; // 예외 발생시 원래 페이지로 이동.
		}
		try {
			service.boardReply(board);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("게시물 등록 실패", "reply.shop");
		}
		return mav;
	}

	@RequestMapping(value = "board/update", method = RequestMethod.POST)
	// HttpServletRequest : 첨부파일 path를 쓰기위해 사용
	public ModelAndView update(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Board dbBoard = service.getBoard(board.getNum());
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			mav.addObject("board", dbBoard); // 유효성 검사시 비어있는 값이 있을때, 사라지는 첨부파일을 보존
			return mav; // 유효성 검사를 하고, 예외 발생시 원래 페이지로 이동. 위에서 dbBoard : 즉, db에 저장된 원래 게시물의 정보를 mav에 저장해서
						// 리턴해야 첨부파일이 보존됨.
		}
		if (board.getPass().equals(dbBoard.getPass())) {
			throw new ShopException("비밀번호를 확인해주세요",
					"update.shop?num=" + board.getNum() + "&pageNum=" + request.getParameter("pageNum"));
		}
		// 첨부파일 수정이 안된 경우. 기존 첨부파일을 유지
		if (board.getFile1() == null || board.getFile1().isEmpty()) {
			board.setFileurl(request.getParameter("file2"));
		}
		try {
			service.boardUpdate(board, request);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("수정 실패",
					"update.shop?num=" + board.getNum() + "&pageNum=" + request.getParameter("pageNum"));
		}
		return mav;
	}

	// @RequestMapping(value = "board/delete", method = RequestMethod.POST)
	// HttpServletRequest : 첨부파일 path를 쓰기위해 사용
	public ModelAndView delete(String pass, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int num = Integer.parseInt(request.getParameter("num"));

		// dbpass : db에 저장된 비밀번호, pass : 입력된 비밀번호
		String dbpass = service.getBoard(num).getPass();

		if (!pass.equals(dbpass)) {
			throw new ShopException("비밀번호를 확인해주세요",
					"delete.shop?num=" + num + "&pageNum=" + request.getParameter("pageNum"));
		}
		try {
			service.boardDelete(num);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("삭제 실패", "delete.shop?num=" + num + "&pageNum=" + request.getParameter("pageNum"));
		}
		return mav;
	}

	// 파라미터와 매개변수 값이 같으면 넘겨줌
	@RequestMapping(value = "board/*", method = RequestMethod.GET) // GET 방식이면서 이름이 없는 애들을 걸러낸다
	public ModelAndView detail(Integer num, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Board board = new Board();
		if (num != null) { // 넘이라는 값이 있다면
			board = service.getBoard(num);
			String url = request.getServletPath();
			if (url.contains("/board/detail.shop")) {
				service.updatereadcnt(num);
			}
		}
		mav.addObject("board", board); // 넘이 없다면 뷰딴의 modelAttribute board로 이동함
		return mav;
	}

	@RequestMapping(value = "board/delete", method = RequestMethod.POST)
	public ModelAndView delete2(@RequestParam HashMap<String, String> map) {
		ModelAndView mav = new ModelAndView();
		
		int num = Integer.parseInt(map.get("num"));
		int pageNum = Integer.parseInt(map.get("pageNum"));
		
		String pass = map.get("pass");
		String dbpass = service.getBoard(num).getPass();

		if (!pass.equals(dbpass)) {
			throw new ShopException("비밀번호를 확인해주세요", "delete.shop?num=" + num + "&pageNum=" + pageNum);
		}
		try {
			service.boardDelete(num);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("삭제 실패", "delete.shop?num=" + num + "&pageNum=" + pageNum);
		}
		return mav;
	}
}
