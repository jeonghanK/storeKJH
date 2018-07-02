package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Item;
import logic.ShopService;

@Controller // 이미 객체화가 되어 있음. @Component의 하위 객체. @Component + Controller 기능 부여
// View딴으로 가기에 앞서 logic의 @Service를 들려 업무를 덜고 DB를 담당하는 dao로 이동.
public class ItemController {
	@Autowired
	private ShopService service;

	@RequestMapping("item/list") // 요청정보에 따라 호출되는 메서드 설정
	public ModelAndView list() {
		// itemList : item 테이블의 모든 정보를 Item 객체의 List로 저장
		List<Item> itemList = service.getItemList();
		ModelAndView mav = new ModelAndView(); // view는 list.jsp를 요청
		mav.addObject("itemList", itemList);
		return mav;
	}

	@RequestMapping("item/create")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("item/add");
		mav.addObject(new Item());
		return mav;
	}

	@RequestMapping("item/register")
	// Item : Item.java 클래스임. @Valid : 유효성 검증 어노테이션
	public ModelAndView register(@Valid Item item, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("item/add"); // 담아서 add.jsp로 보내줌
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav; // 예외 발생시 원래 페이지로 이동.
		}
		service.itemCreate(item, request);
		mav.setViewName("redirect:/item/list.shop"); // 화면의 url이 list.shop이 됨.
		return mav;
	}

	/* 상품 수정하기 : /item/update.shop?id=1 파일업로드. 유효성 검증 */
	@RequestMapping("item/update")
	public ModelAndView update(@Valid Item item, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("item/edit"); // update로 이동되지않고 에러가 발생하여 유효성 검사 메세지를 보려면 다시 edit으로 가게끔 설정
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			// mav.setViewName("item/edit"); 여기에 경로 지정해줘도 됨. // 리다이렉트가 없어서 url은 계속
			// update.shop으로 유지됨.
			return mav; // 예외 발생시 원래 페이지로 이동.
		}
		service.itemUpdate(item, request);
		// 수정 후 상세보기 페이지로 이동하려면 어떤 id인지 알기위해 파라미터를 붙여줘야한다.
		// 브라우저 요청부분. server에서 요청하는 .jsp와 내부, 외부의 계념이 다름. .shop은 url부분이여서 꼭 붙여줘야함.
		mav.setViewName("redirect:/item/detail.shop?id=" + item.getId());
		// 리다이렉트로 다시 브라우저를 요청하여 url이 update -> list.shop이 됨. url부분 변경을 위해서 필요함.
		return mav;
	}

	@RequestMapping("item/delete")
	public String delete(String id) {
		// 넘겨줄 값이 없다면 String으로도 가능. 이땐 return으로 넘겨주면 된다.
		try {
			service.itemDelete(id);
			return "redirect:list.shop";
		} catch (Exception e) {
			return "redirect:confirm.shop?id=" + id;
		}
	}

	// /...../item/detail.shop?id=1
	// 상품상세보기 : id 파라미터가 전송됨.
	// 1. id 파라미터를 이용하여 db 조회하기
	// 2. 조회된 db 내용을 view로 전송하기
	@RequestMapping("item/*") // *는 항상 가장 밑에 있어야한다.
	public ModelAndView detail(String id) {
		ModelAndView mav = new ModelAndView(); // 여긴 ()속에 경로가 있으면 안됨. 있으면 무조건 해당 경로로 이동하게됨.
		Item item = service.itemDetail(id);
		mav.addObject("item", item);
		return mav;
	}

	/*
	 * @RequestMapping("item/edit") public ModelAndView edit(String id) { return
	 * detail(id); }
	 */
}
