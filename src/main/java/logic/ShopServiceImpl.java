package logic;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.BoardDao;
import dao.ItemDao;
import dao.SaleDao;
import dao.SaleItemDao;
import dao.UserDao;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ItemDao itemDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SaleDao saleDao;

	@Autowired
	private SaleItemDao saleItemDao;

	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Item> getItemList() {
		return itemDao.list();
	}

	@Override
	public void itemCreate(Item item) {
		itemDao.insert(item);
	}

	@Override
	public void itemCreate(Item item, HttpServletRequest request) {
		// ���ε�� �̹����� �����ϴ� ���
		if (item.getPicture() != null && !item.getPicture().isEmpty()) {
			// ���⼭ ���� ���ε�����ũ�����̵� �޼��尡 �����
			uploadFileCreate(item.getPicture(), request); // ���� ���� ���� ���ε�����ũ������Ʈ �޼��尡 �Ϸ�Ǹ� �ٽ� �̸� ���ƿ�.
			// ������ �̸� ���
			item.setPictureUrl(item.getPicture().getOriginalFilename());
		}
		itemDao.insert(item);
	}

	private void uploadFileCreate(MultipartFile picture, HttpServletRequest request) {
		// picture : ���ε�� ������ ���� ����.
		String uploadPath = // ���� ���ε� ��ġ ����
				request.getServletContext().getRealPath("/") + "/picture/";
		String orgFile = picture.getOriginalFilename(); // ������ �̸�
		try {
			// new File(uploadPath + orgFile) : ���� ��ü ����. �� �κп��� ������ ��������� ��.
			picture.transferTo(new File(uploadPath + orgFile));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Item itemDetail(String id) {
		return itemDao.detail(id);
	}

	@Override
	public void itemUpdate(Item item, HttpServletRequest request) {
		if (item.getPicture() != null && !item.getPicture().isEmpty()) {
			// ���⼭ ���� ���ε�����ũ�����̵� �޼��尡 �����
			uploadFileCreate(item.getPicture(), request); // ���� ���� ���� ���ε�����ũ������Ʈ �޼��尡 �Ϸ�Ǹ� �ٽ� �̸� ���ƿ�.
			// ������ �̸� ���
			item.setPictureUrl(item.getPicture().getOriginalFilename());
		}
		itemDao.update(item);
	}

	@Override
	public void itemDelete(String id) {
		itemDao.delete(id);
	}

	@Override
	public void userCreate(User user) {
		userDao.insert(user);

	}

	@Override
	public User getUser(String userId) {
		return userDao.select(userId);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void userDelete(String userId) {
		userDao.delete(userId); // �������̽����� ������ �Ű������� ���⼭ ��������.

	}

	@Override
	public List<User> userList() {
		return userDao.list();
	}

	@Override
	public List<User> userList(String[] ids) {
		return userDao.list(ids);
	}

	@Override
	public Sale checkEnd(User loginUser, Cart cart) {
		Sale sale = new Sale();
		sale.setSaleId(saleDao.getMaxSaleId());
		sale.setUser(loginUser); // �ֹ���
		sale.setUpdateTime(new Date()); // �ֹ��ð�
		// �ֹ� ��ǰ ��� (������ for���� ����)
		List<ItemSet> itemList = cart.getItemList();
		for (int i = 0; i < itemList.size(); i++) {
			ItemSet itemSet = itemList.get(i);
			int saleItemId = i + 1;
			SaleItem saleItem = new SaleItem(sale.getSaleId(), saleItemId, itemSet, sale.getUpdateTime());
			sale.getSaleItemList().add(saleItem);

		}
		// db�� sale ���̺� �߰��ϱ�
		saleDao.insert(sale);
		List<SaleItem> saleItemList = sale.getSaleItemList();
		for (SaleItem saleItem : saleItemList) {
			saleItemDao.insert(saleItem); // �ֹ���ǰ ������ saleitem ���̺� ����
		}
		return sale;
	}

	@Override
	public List<Sale> saleList(String id) {
		return saleDao.list(id);
	}

	@Override
	public List<SaleItem> saleItemList(Integer saleId) {
		return saleItemDao.list(saleId);

	}

	@Override
	public int boardcount(String searchType, String searchContent) {
		return boardDao.count(searchType, searchContent);
	}

	@Override
	public List<Board> boardList(String searchType, String searchContent, Integer pageNum, int limit) {
		return boardDao.list(searchType, searchContent, pageNum, limit);
	}

	@Override
	public Board getBoard(Integer num) {
		return boardDao.getBoard(num);
	}

	@Override
	public void boardWrite(Board board, HttpServletRequest request) {
		if (board.getFile1() != null && !board.getFile1().isEmpty()) {
			uploadBoardFileCreate(board.getFile1(), request); // ���ϻ���
			board.setFileurl(board.getFile1().getOriginalFilename());
		}
		int num = boardDao.maxNum();
		board.setRef(++num);
		board.setRef(num);
		board.setReflevel(0);
		board.setRefstep(0);
		boardDao.write(board);
	}

	private void uploadBoardFileCreate(MultipartFile file1, HttpServletRequest request) {
		String uploadPath = // ���� ���ε� ��ġ ����
				request.getServletContext().getRealPath("/") + "/file/";
		String orgFile = file1.getOriginalFilename(); // ������ �̸�
		try {
			file1.transferTo(new File(uploadPath + orgFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updatereadcnt(Integer num) {
		boardDao.updatereadcnt(num);
	}

	/*
	 * ���� ���� : num, ref, reflevel, refstep ��� ���� : name, title, subject, pass 1. ������
	 * ��ϵ� �Խñ� �� ���� ref ���� ���� ���ڵ���� refstep�� �ϳ� ����. refstep�� ������ ū ���� ���� ���ڵ� ���� 2.
	 * ��� ����, ref, reflevel + 1, refstep+1 ���ڵ� �߰�
	 */
	@Override
	public void boardReply(Board board) {
		boardDao.refstepadd(board);
		int num = boardDao.maxNum(); // �Խñ��� �ִ� num ��
		board.setNum(++num); // �߰��� �Խñ��� Ű
		// board.getRef : ���۰� ���� ��
		// board.getReflevel() : ������ reflevel ��
		// board.getRefstep() : ������ refstep ��
		board.setReflevel(board.getReflevel() + 1);
		board.setRefstep(board.getRefstep() + 1);
		boardDao.write(board);
	}

	@Override
	public void boardUpdate(Board board, HttpServletRequest request) {
		if (board.getFile1() != null && !board.getFile1().isEmpty()) {
			uploadBoardFileCreate(board.getFile1(), request); // ���ϻ���
			board.setFileurl(board.getFile1().getOriginalFilename());
		}
		boardDao.update(board);
	}

	@Override
	public void boardDelete(int num) {
		boardDao.delete(num);
	}
	
	@Override
	public Map<String, Object> graph() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map<String, Object> m : boardDao.graph()) {
			map.put((String) m.get("key1"), m.get("value1"));
		}
		return map;
	}
	
	@Override
	public Map<String, Object> mygraph(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map<String, Object> m : userDao.mygraph(id)) {
			map.put((String) m.get("key1"), m.get("value1"));
		}
		return map;
	}
}
