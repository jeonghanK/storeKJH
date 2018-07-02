package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import exception.CartEmptyException;
import logic.Cart;
import logic.Item;
import logic.ItemSet;
import logic.Sale;
import logic.ShopService;
import logic.User;

@Controller
public class CartController {
	@Autowired
	private ShopService service;

	@RequestMapping("cart/cartAdd")
	public ModelAndView add(String id, Integer quantity, HttpSession session) {
		// selectedItem : id�� �ش��ϴ� ��ǰ������ db���� �о ����
		Item selectedItem = service.itemDetail(id);
		Cart cart = (Cart) session.getAttribute("CART"); // session�� �Ӽ� �� ���. ������Ʈ�� �������⶧���� ����ȯ����.
		if (cart == null) { // session�� "CART" �Ӽ��� ��ϵ��� ���� ���.
			cart = new Cart(); // ���������� ���ǿ� ��ϵǾ��ִ� cart
			session.setAttribute("CART", cart);
		}
		// Cart Ŭ������ push�޼���. ���� ������ ��ǰ�� cart�� �������. ItemSet�� �������� ������ �ִ� ����.
		cart.push(new ItemSet(selectedItem, quantity));
		ModelAndView mav = new ModelAndView("cart/cart"); // cart.jsp
		mav.addObject("message", selectedItem.getName() + "��/��" + quantity + "�� īƮ�� �߰�");
		mav.addObject("cart", cart);
		return mav;
	}

	@RequestMapping("cart/cartDelete")
	public ModelAndView delete(Integer index, HttpSession session) {
		// index : ��ǰ ��� ���� ����. index�� 3�̶��, īƮ ����� 4��°�� ����ٴ� ��.
		Cart cart = (Cart) session.getAttribute("CART"); // īƮ�� ������ �������.
		ModelAndView mav = new ModelAndView("cart/cart");
		ItemSet delete = null; // delete �ʱ�ȭ
		try {
			delete = cart.getItemList().get(index); // ����Ʈ�� index(����)�� �������� ���� ����� ������ delete��� ��ü�� ����.
			cart.getItemList().remove(delete); // delete��ü�� �� ���� ����.
			mav.addObject("message", delete.getItem().getName() + "��(��) īƮ���� ������");
		} catch (Exception e) {
			mav.addObject("message", "īƮ�� ��ǰ�� �����ϴ�");
		}
		mav.addObject("cart", cart);
		return mav;
	}

	@RequestMapping("cart/cartView") // ����Ʈ���� ������� ��ٱ��� Ŭ���� �۵��ϴ� view �޼���.
	public ModelAndView view(HttpSession session) { // �������� ���� ������
		Cart cart = (Cart) session.getAttribute("CART"); // ������� CART �빮�ڷ� ����ϵ��� ����
		ModelAndView mav = new ModelAndView("cart/cart");
		if (cart == null || cart.isEmpty()) { // īƮ��� ���� ���ǿ� ��ϵ������� ��쳪, īƮ�� ��ǰ�� ���� ���.
			throw new CartEmptyException("īƮ�� ��ǰ�� �����ϴ�. ��ǰ ������� ���ư��ϴ�.", "../item/list.shop"); // �� ������ ���� ó���ϰ� �����ʾƼ�
																								// ��Ÿ�ӿ��ܸ� ��ӹ���.
		}
		mav.addObject("message", "īƮ ��ǰ ��ȸ �Դϴ�.");
		mav.addObject("cart", cart);
		return mav;
	}

	// AOP �߰��ϱ� - �α��� ���¿��߸� ��ǰ ���� Ȯ���� �����ϵ��� �ϱ�.
	// 1. �α����� �ȵȰ�� : �α����� �ʿ��մϴ�. /user/login.shop����
	// 2. īƮ�� ��� �ִ� ��� : ��ٱ��ϰ� ������ϴ�. /item/list.shop����
	@RequestMapping("cart/checkout")
	public String checkout(HttpSession session) { // AOP���� ����ϱ� ������ session������ ����� �ȵȴ�.
		return null; // view�� �̸�. �̸��� ������ ������ checkout(�⺻ ���)�� �̵��ϰԵȴ�.
	}

	@RequestMapping("cart/end")
	public ModelAndView checkend(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("CART");
		User loginUser = (User) session.getAttribute("loginUser");
		if (cart == null || cart.isEmpty()) {
			throw new CartEmptyException("��ٱ��Ͽ� ����� ��ǰ�� �����ϴ�.", "../item/list.shop");
		}
		/*
		 * �ֹ� ���� ����.
		 * sale ��ü�� ������ �����Ͽ� ����.
		 * db�� �ֹ� ������ �ֹ� ��ǰ ���� ����.
		 */
		Sale sale = service.checkEnd(loginUser, cart);
		List<ItemSet> itemList = cart.getItemList();
		int total = cart.getTotalAmount(); // �� �ݾ� ����
		cart.clearAll(session); // db�� ���������� ���� ��ٱ��� ����
		ModelAndView mav = new ModelAndView();
		mav.addObject("sale", sale);
		mav.addObject("totalAmount", total);
		return mav; // end ��� ������.
	}
}
