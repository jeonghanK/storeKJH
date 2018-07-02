package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import exception.LoginException;
import exception.ShopException;
import logic.Item;
import logic.Sale;
import logic.SaleItem;
import logic.ShopService;
import logic.User;

@Controller
public class UserController {
	@Autowired
	ShopService service;

	@RequestMapping("user/userForm")
	public ModelAndView userForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(new User());
		return mav;
	}

	// 유효성 검사
	@RequestMapping("user/userEntry")
	public ModelAndView userEntry(@Valid User user, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("user/userForm");
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		try {
			// user : 화면에서 입력된 정보 저장하고 있는 객체
			service.userCreate(user); // 메서드는 소문자로 시작
			mav.setViewName("user/login");
			mav.addObject("user", user);
			return mav;
			// DataIntegrityViolationException : 기본키가 중복된 경우 발생되는 예외
		} catch (DataIntegrityViolationException e) {
			bindingResult.reject("error.duplicate.user");
		}
		return mav;
	}

	@InitBinder // 생일 형변환을 위한 어노테이션
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute // view딴의 form:form = user 부분을 위함.
	public User getUser() {
		return new User();
	}

	// 이 구문과 위의 구문을 하나로 작성할 수도 있다. shop-2 컨트롤러 참고
	@RequestMapping(value = "user/login", method = RequestMethod.GET) // GET은 화면만 보여줌
	public String loginForm() {
		return "user/login";
	}

	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	// POST형식으로 값을 넘겨주고 유효성 검사를 할때 사용자 이름은 필수 입력으로 User.java에서 설정해놔서 login뷰딴에서 히든값으로
	// 유저네임을 가지고있음
	public ModelAndView login(@Valid User user, BindingResult bindingResult, HttpSession session) {
		ModelAndView mav = new ModelAndView("user/login");
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		User dbUser = service.getUser(user.getUserId());
		if (dbUser == null) {
			bindingResult.reject("error.login.id");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		if (dbUser.getPassword().equals(user.getPassword())) {
			mav.addObject("dbUser", dbUser);
			mav.setViewName("user/loginSuccess");
			session.setAttribute("loginUser", dbUser);
		} else {
			bindingResult.reject("error.login.password");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		return mav;
	}

	@RequestMapping("user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "user/login";
	}

	@RequestMapping("user/mypage")
	public ModelAndView mypage(String id, HttpSession session) { // mypage에 aop가 걸려있음 @Around : 실행 전 후 실행
		ModelAndView mav = new ModelAndView();
		User loginUser = (User) session.getAttribute("loginUser");
		User user = service.getUser(id);
		List<Sale> salelist = service.saleList(id);
		for (Sale sale : salelist) {
			List<SaleItem> saleItemList = service.saleItemList(sale.getSaleId());
			int amount = 0;
			for (SaleItem sitem : saleItemList) {
				Item item = service.itemDetail(sitem.getItemId() + ""); // 문자열로 만들기 위해 + ""
				sitem.setItem(item);
				amount += sitem.getQuantity() * item.getPrice();
			}
			sale.setSaleItemList(saleItemList);
			sale.setAmount(amount);
		}
		mav.addObject("salelist", salelist);
		mav.addObject("user", user);
		return mav;
	}

	// 직업 GET, POST방식 설정해보기
	@RequestMapping(value = "user/update", method = RequestMethod.GET)
	public ModelAndView myupdateForm(String id, HttpSession session) { // @Around가 my로 시작하는 메서드를 지칭하고 있어서 my를 붙여줌
		ModelAndView mav = new ModelAndView();
		User user = service.getUser(id);
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "user/update", method = RequestMethod.POST)
	public ModelAndView myupdate(@Valid User user, BindingResult bindingResult, HttpSession session) {
		ModelAndView mav = new ModelAndView("user/update");
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		User dbUser = service.getUser(user.getUserId());
		if (!user.getPassword().equals(dbUser.getPassword())) {
			throw new ShopException("비밀번호가 틀립니다.", "update.shop?id=" + user.getUserId());
		}
		try {
			service.updateUser(user);
			mav.setViewName("redirect:mypage.shop?id=" + user.getUserId());
			// mav.addObject("user", user); // 리다이렉트여서 이 구문은 필요없다.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	// 회원 탈퇴 담당
	// GET방식으로 화면이 먼저 들어오는지 확인. (url접속)
	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public ModelAndView mydeleteForm(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = service.getUser(id);
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "user/delete", method = RequestMethod.POST)
	// 뷰에서넘어온 id와 password. 서버가 가진 세션을 가져옴. aop에서 배열로 지정해준 순서를 매개변수 또한 지켜야한다.
	public String mydelete(String id, HttpSession session, String password) {
		User loginUser = (User) session.getAttribute("loginUser"); // 세션의 로그인 정보
		User user = service.getUser(id); // 수정될 값(입력된 id의 db에 저장된 회원정보
		if (user == null) { // 해당 아이디가 없다면
			throw new ShopException("삭제 대상 사용자가 존재하지 않습니다.", "../user/mypage.shop?id=" + id);
		}
		if (loginUser.getUserId().equals("admin")) { // 세션의 속성이. 즉, 로그인된 ID가 어드민이라면
			if (!loginUser.getPassword().equals(password)) { // 근데 이때, 입력된 패스워드와
				throw new LoginException("관리자 비밀번호가 틀립니다.", "delete.shop?id=" + id);
			}
			/*
			 * Exception부분은 스크립트가 실행되기때문에 리다이렉트로 재요청하면 안된다. throw부분의 경로값은 redirect가 붙으면 안된다.
			 */
		} else { // 일반 사용자인 경우
			if (!user.getPassword().equals(password)) {
				throw new LoginException("비밀번호가 틀립니다.", "delete.shop?id=" + id);
			}
		}
		try {
			service.userDelete(id);
			if (loginUser.getUserId().equals("admin")) { // 관리자로그인
				return "redirect:../admin/admin.shop"; // 관리자가 맞으면 재전송해줌.
			} else {
				session.invalidate();
				return "redirect:login.shop"; // 현재 내가 가야될 뷰딴의 이름. return부분은 리다이렉트가 필요하다. 요청을 주고받기 때문.
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("삭제시 오류 발생", "../user/mypage.shop?id=" + id);
		}
	}

	@RequestMapping("user/mygraph*")
	public ModelAndView graph(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.getAttribute("loginUser");
		Map<String, Object> map = service.mygraph(id);
		Map<String, Object> treemap = new TreeMap<String, Object>(map);
		mav.addObject("map", treemap);
		return mav;
	}
}