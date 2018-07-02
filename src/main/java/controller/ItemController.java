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

@Controller // �̹� ��üȭ�� �Ǿ� ����. @Component�� ���� ��ü. @Component + Controller ��� �ο�
// View������ ���⿡ �ռ� logic�� @Service�� ��� ������ ���� DB�� ����ϴ� dao�� �̵�.
public class ItemController {
	@Autowired
	private ShopService service;

	@RequestMapping("item/list") // ��û������ ���� ȣ��Ǵ� �޼��� ����
	public ModelAndView list() {
		// itemList : item ���̺��� ��� ������ Item ��ü�� List�� ����
		List<Item> itemList = service.getItemList();
		ModelAndView mav = new ModelAndView(); // view�� list.jsp�� ��û
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
	// Item : Item.java Ŭ������. @Valid : ��ȿ�� ���� ������̼�
	public ModelAndView register(@Valid Item item, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("item/add"); // ��Ƽ� add.jsp�� ������
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav; // ���� �߻��� ���� �������� �̵�.
		}
		service.itemCreate(item, request);
		mav.setViewName("redirect:/item/list.shop"); // ȭ���� url�� list.shop�� ��.
		return mav;
	}

	/* ��ǰ �����ϱ� : /item/update.shop?id=1 ���Ͼ��ε�. ��ȿ�� ���� */
	@RequestMapping("item/update")
	public ModelAndView update(@Valid Item item, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("item/edit"); // update�� �̵������ʰ� ������ �߻��Ͽ� ��ȿ�� �˻� �޼����� ������ �ٽ� edit���� ���Բ� ����
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			// mav.setViewName("item/edit"); ���⿡ ��� �������൵ ��. // �����̷�Ʈ�� ��� url�� ���
			// update.shop���� ������.
			return mav; // ���� �߻��� ���� �������� �̵�.
		}
		service.itemUpdate(item, request);
		// ���� �� �󼼺��� �������� �̵��Ϸ��� � id���� �˱����� �Ķ���͸� �ٿ�����Ѵ�.
		// ������ ��û�κ�. server���� ��û�ϴ� .jsp�� ����, �ܺ��� ����� �ٸ�. .shop�� url�κ��̿��� �� �ٿ������.
		mav.setViewName("redirect:/item/detail.shop?id=" + item.getId());
		// �����̷�Ʈ�� �ٽ� �������� ��û�Ͽ� url�� update -> list.shop�� ��. url�κ� ������ ���ؼ� �ʿ���.
		return mav;
	}

	@RequestMapping("item/delete")
	public String delete(String id) {
		// �Ѱ��� ���� ���ٸ� String���ε� ����. �̶� return���� �Ѱ��ָ� �ȴ�.
		try {
			service.itemDelete(id);
			return "redirect:list.shop";
		} catch (Exception e) {
			return "redirect:confirm.shop?id=" + id;
		}
	}

	// /...../item/detail.shop?id=1
	// ��ǰ�󼼺��� : id �Ķ���Ͱ� ���۵�.
	// 1. id �Ķ���͸� �̿��Ͽ� db ��ȸ�ϱ�
	// 2. ��ȸ�� db ������ view�� �����ϱ�
	@RequestMapping("item/*") // *�� �׻� ���� �ؿ� �־���Ѵ�.
	public ModelAndView detail(String id) {
		ModelAndView mav = new ModelAndView(); // ���� ()�ӿ� ��ΰ� ������ �ȵ�. ������ ������ �ش� ��η� �̵��ϰԵ�.
		Item item = service.itemDetail(id);
		mav.addObject("item", item);
		return mav;
	}

	/*
	 * @RequestMapping("item/edit") public ModelAndView edit(String id) { return
	 * detail(id); }
	 */
}
