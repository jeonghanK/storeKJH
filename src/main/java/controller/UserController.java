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

	// ��ȿ�� �˻�
	@RequestMapping("user/userEntry")
	public ModelAndView userEntry(@Valid User user, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("user/userForm");
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		try {
			// user : ȭ�鿡�� �Էµ� ���� �����ϰ� �ִ� ��ü
			service.userCreate(user); // �޼���� �ҹ��ڷ� ����
			mav.setViewName("user/login");
			mav.addObject("user", user);
			return mav;
			// DataIntegrityViolationException : �⺻Ű�� �ߺ��� ��� �߻��Ǵ� ����
		} catch (DataIntegrityViolationException e) {
			bindingResult.reject("error.duplicate.user");
		}
		return mav;
	}

	@InitBinder // ���� ����ȯ�� ���� ������̼�
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute // view���� form:form = user �κ��� ����.
	public User getUser() {
		return new User();
	}

	// �� ������ ���� ������ �ϳ��� �ۼ��� ���� �ִ�. shop-2 ��Ʈ�ѷ� ����
	@RequestMapping(value = "user/login", method = RequestMethod.GET) // GET�� ȭ�鸸 ������
	public String loginForm() {
		return "user/login";
	}

	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	// POST�������� ���� �Ѱ��ְ� ��ȿ�� �˻縦 �Ҷ� ����� �̸��� �ʼ� �Է����� User.java���� �����س��� login������� ���簪����
	// ���������� ����������
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
	public ModelAndView mypage(String id, HttpSession session) { // mypage�� aop�� �ɷ����� @Around : ���� �� �� ����
		ModelAndView mav = new ModelAndView();
		User loginUser = (User) session.getAttribute("loginUser");
		User user = service.getUser(id);
		List<Sale> salelist = service.saleList(id);
		for (Sale sale : salelist) {
			List<SaleItem> saleItemList = service.saleItemList(sale.getSaleId());
			int amount = 0;
			for (SaleItem sitem : saleItemList) {
				Item item = service.itemDetail(sitem.getItemId() + ""); // ���ڿ��� ����� ���� + ""
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

	// ���� GET, POST��� �����غ���
	@RequestMapping(value = "user/update", method = RequestMethod.GET)
	public ModelAndView myupdateForm(String id, HttpSession session) { // @Around�� my�� �����ϴ� �޼��带 ��Ī�ϰ� �־ my�� �ٿ���
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
			throw new ShopException("��й�ȣ�� Ʋ���ϴ�.", "update.shop?id=" + user.getUserId());
		}
		try {
			service.updateUser(user);
			mav.setViewName("redirect:mypage.shop?id=" + user.getUserId());
			// mav.addObject("user", user); // �����̷�Ʈ���� �� ������ �ʿ����.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	// ȸ�� Ż�� ���
	// GET������� ȭ���� ���� �������� Ȯ��. (url����)
	@RequestMapping(value = "user/delete", method = RequestMethod.GET)
	public ModelAndView mydeleteForm(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = service.getUser(id);
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping(value = "user/delete", method = RequestMethod.POST)
	// �信���Ѿ�� id�� password. ������ ���� ������ ������. aop���� �迭�� �������� ������ �Ű����� ���� ���Ѿ��Ѵ�.
	public String mydelete(String id, HttpSession session, String password) {
		User loginUser = (User) session.getAttribute("loginUser"); // ������ �α��� ����
		User user = service.getUser(id); // ������ ��(�Էµ� id�� db�� ����� ȸ������
		if (user == null) { // �ش� ���̵� ���ٸ�
			throw new ShopException("���� ��� ����ڰ� �������� �ʽ��ϴ�.", "../user/mypage.shop?id=" + id);
		}
		if (loginUser.getUserId().equals("admin")) { // ������ �Ӽ���. ��, �α��ε� ID�� �����̶��
			if (!loginUser.getPassword().equals(password)) { // �ٵ� �̶�, �Էµ� �н������
				throw new LoginException("������ ��й�ȣ�� Ʋ���ϴ�.", "delete.shop?id=" + id);
			}
			/*
			 * Exception�κ��� ��ũ��Ʈ�� ����Ǳ⶧���� �����̷�Ʈ�� ���û�ϸ� �ȵȴ�. throw�κ��� ��ΰ��� redirect�� ������ �ȵȴ�.
			 */
		} else { // �Ϲ� ������� ���
			if (!user.getPassword().equals(password)) {
				throw new LoginException("��й�ȣ�� Ʋ���ϴ�.", "delete.shop?id=" + id);
			}
		}
		try {
			service.userDelete(id);
			if (loginUser.getUserId().equals("admin")) { // �����ڷα���
				return "redirect:../admin/admin.shop"; // �����ڰ� ������ ����������.
			} else {
				session.invalidate();
				return "redirect:login.shop"; // ���� ���� ���ߵ� ����� �̸�. return�κ��� �����̷�Ʈ�� �ʿ��ϴ�. ��û�� �ְ�ޱ� ����.
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ShopException("������ ���� �߻�", "../user/mypage.shop?id=" + id);
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