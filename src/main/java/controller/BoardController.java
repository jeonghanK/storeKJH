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

	// �Խñ� ����ϱ�
	/*
	 * 1. ��ȿ�� ���� 2. DB�� ����ϱ� 3. ��ϼ��� : list.shop, ��Ͻ��� : write.shop ������ �̵�
	 */
	@RequestMapping(value = "board/write", method = RequestMethod.POST)
	public ModelAndView write(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav; // ���� �߻��� ���� �������� �̵�.
		}
		try {
			service.boardWrite(board, request);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("�Խù� ��� ����", "write.shop");
		}
		return mav;
	}

	@RequestMapping(value = "board/reply", method = RequestMethod.POST)
	// HttpServletRequest : ÷������ path�� �������� ���
	public ModelAndView reply(@Valid Board board, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			board = service.getBoard(board.getNum());
			mav.addObject("board", board);
			return mav; // ���� �߻��� ���� �������� �̵�.
		}
		try {
			service.boardReply(board);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("�Խù� ��� ����", "reply.shop");
		}
		return mav;
	}

	@RequestMapping(value = "board/update", method = RequestMethod.POST)
	// HttpServletRequest : ÷������ path�� �������� ���
	public ModelAndView update(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Board dbBoard = service.getBoard(board.getNum());
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			mav.addObject("board", dbBoard); // ��ȿ�� �˻�� ����ִ� ���� ������, ������� ÷�������� ����
			return mav; // ��ȿ�� �˻縦 �ϰ�, ���� �߻��� ���� �������� �̵�. ������ dbBoard : ��, db�� ����� ���� �Խù��� ������ mav�� �����ؼ�
						// �����ؾ� ÷�������� ������.
		}
		if (board.getPass().equals(dbBoard.getPass())) {
			throw new ShopException("��й�ȣ�� Ȯ�����ּ���",
					"update.shop?num=" + board.getNum() + "&pageNum=" + request.getParameter("pageNum"));
		}
		// ÷������ ������ �ȵ� ���. ���� ÷�������� ����
		if (board.getFile1() == null || board.getFile1().isEmpty()) {
			board.setFileurl(request.getParameter("file2"));
		}
		try {
			service.boardUpdate(board, request);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("���� ����",
					"update.shop?num=" + board.getNum() + "&pageNum=" + request.getParameter("pageNum"));
		}
		return mav;
	}

	// @RequestMapping(value = "board/delete", method = RequestMethod.POST)
	// HttpServletRequest : ÷������ path�� �������� ���
	public ModelAndView delete(String pass, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int num = Integer.parseInt(request.getParameter("num"));

		// dbpass : db�� ����� ��й�ȣ, pass : �Էµ� ��й�ȣ
		String dbpass = service.getBoard(num).getPass();

		if (!pass.equals(dbpass)) {
			throw new ShopException("��й�ȣ�� Ȯ�����ּ���",
					"delete.shop?num=" + num + "&pageNum=" + request.getParameter("pageNum"));
		}
		try {
			service.boardDelete(num);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("���� ����", "delete.shop?num=" + num + "&pageNum=" + request.getParameter("pageNum"));
		}
		return mav;
	}

	// �Ķ���Ϳ� �Ű����� ���� ������ �Ѱ���
	@RequestMapping(value = "board/*", method = RequestMethod.GET) // GET ����̸鼭 �̸��� ���� �ֵ��� �ɷ�����
	public ModelAndView detail(Integer num, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Board board = new Board();
		if (num != null) { // ���̶�� ���� �ִٸ�
			board = service.getBoard(num);
			String url = request.getServletPath();
			if (url.contains("/board/detail.shop")) {
				service.updatereadcnt(num);
			}
		}
		mav.addObject("board", board); // ���� ���ٸ� ����� modelAttribute board�� �̵���
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
			throw new ShopException("��й�ȣ�� Ȯ�����ּ���", "delete.shop?num=" + num + "&pageNum=" + pageNum);
		}
		try {
			service.boardDelete(num);
			mav.setViewName("redirect:list.shop");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("���� ����", "delete.shop?num=" + num + "&pageNum=" + pageNum);
		}
		return mav;
	}
}
