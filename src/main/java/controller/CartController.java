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
		// selectedItem : id에 해당하는 상품정보를 db에서 읽어서 저장
		Item selectedItem = service.itemDetail(id);
		Cart cart = (Cart) session.getAttribute("CART"); // session에 속성 값 등록. 오브젝트로 가져오기때문에 형변환해줌.
		if (cart == null) { // session에 "CART" 속성이 등록되지 않은 경우.
			cart = new Cart(); // 참조변수와 세션에 등록되어있는 cart
			session.setAttribute("CART", cart);
		}
		// Cart 클래스에 push메서드. 내가 선택한 상품을 cart에 집어넣음. ItemSet은 수량까지 가지고 있는 값임.
		cart.push(new ItemSet(selectedItem, quantity));
		ModelAndView mav = new ModelAndView("cart/cart"); // cart.jsp
		mav.addObject("message", selectedItem.getName() + "을/를" + quantity + "개 카트에 추가");
		mav.addObject("cart", cart);
		return mav;
	}

	@RequestMapping("cart/cartDelete")
	public ModelAndView delete(Integer index, HttpSession session) {
		// index : 상품 목록 순서 정보. index가 3이라면, 카트 목록의 4번째를 지운다는 것.
		Cart cart = (Cart) session.getAttribute("CART"); // 카트의 정보를 가지고옴.
		ModelAndView mav = new ModelAndView("cart/cart");
		ItemSet delete = null; // delete 초기화
		try {
			delete = cart.getItemList().get(index); // 리스트의 index(순서)를 기준으로 지울 대상이 누군지 delete라는 객체에 저장.
			cart.getItemList().remove(delete); // delete객체의 그 값을 지움.
			mav.addObject("message", delete.getItem().getName() + "을(를) 카트에서 제거함");
		} catch (Exception e) {
			mav.addObject("message", "카트에 상품이 없습니다");
		}
		mav.addObject("cart", cart);
		return mav;
	}

	@RequestMapping("cart/cartView") // 리스트에서 우측상단 장바구니 클릭시 작동하는 view 메서드.
	public ModelAndView view(HttpSession session) { // 세션으로 부터 가져옴
		Cart cart = (Cart) session.getAttribute("CART"); // 뷰딴에서 CART 대문자로 사용하도록 설정
		ModelAndView mav = new ModelAndView("cart/cart");
		if (cart == null || cart.isEmpty()) { // 카트라는 것이 세션에 등록되지않은 경우나, 카트에 상품이 없는 경우.
			throw new CartEmptyException("카트에 상품이 없습니다. 상품 목록으로 돌아갑니다.", "../item/list.shop"); // 이 구문은 예외 처리하고 싶지않아서
																								// 런타임예외를 상속받음.
		}
		mav.addObject("message", "카트 상품 조회 입니다.");
		mav.addObject("cart", cart);
		return mav;
	}

	// AOP 추가하기 - 로그인 상태여야만 상품 구매 확정이 가능하도록 하기.
	// 1. 로그인이 안된경우 : 로그인이 필요합니다. /user/login.shop으로
	// 2. 카트가 비어 있는 경우 : 장바구니가 비었습니다. /item/list.shop으로
	@RequestMapping("cart/checkout")
	public String checkout(HttpSession session) { // AOP에서 사용하기 때문에 session선언은 지우면 안된다.
		return null; // view의 이름. 이름이 없으니 맵핑의 checkout(기본 경로)로 이동하게된다.
	}

	@RequestMapping("cart/end")
	public ModelAndView checkend(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("CART");
		User loginUser = (User) session.getAttribute("loginUser");
		if (cart == null || cart.isEmpty()) {
			throw new CartEmptyException("장바구니에 계산할 상품이 없습니다.", "../item/list.shop");
		}
		/*
		 * 주문 정보 생성.
		 * sale 객체에 정보를 저장하여 리턴.
		 * db에 주문 정보와 주문 상품 정보 저장.
		 */
		Sale sale = service.checkEnd(loginUser, cart);
		List<ItemSet> itemList = cart.getItemList();
		int total = cart.getTotalAmount(); // 총 금액 산출
		cart.clearAll(session); // db에 저장했으니 이제 장바구니 비우기
		ModelAndView mav = new ModelAndView();
		mav.addObject("sale", sale);
		mav.addObject("totalAmount", total);
		return mav; // end 뷰로 보내줌.
	}
}
