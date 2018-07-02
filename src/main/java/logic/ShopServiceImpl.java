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
		// 업로드된 이미지가 존재하는 경우
		if (item.getPicture() != null && !item.getPicture().isEmpty()) {
			// 여기서 밑의 업로드파일크리에이드 메서드가 실행됨
			uploadFileCreate(item.getPicture(), request); // 파일 생성 밑의 업로드파일크리에이트 메서드가 완료되면 다시 이리 돌아옴.
			// 파일의 이름 등록
			item.setPictureUrl(item.getPicture().getOriginalFilename());
		}
		itemDao.insert(item);
	}

	private void uploadFileCreate(MultipartFile picture, HttpServletRequest request) {
		// picture : 업로드된 파일의 내용 저장.
		String uploadPath = // 파일 업로드 위치 설정
				request.getServletContext().getRealPath("/") + "/picture/";
		String orgFile = picture.getOriginalFilename(); // 파일의 이름
		try {
			// new File(uploadPath + orgFile) : 파일 객체 설정. 이 부분에서 파일이 만들어지게 됨.
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
			// 여기서 밑의 업로드파일크리에이드 메서드가 실행됨
			uploadFileCreate(item.getPicture(), request); // 파일 생성 밑의 업로드파일크리에이트 메서드가 완료되면 다시 이리 돌아옴.
			// 파일의 이름 등록
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
		userDao.delete(userId); // 인터페이스에서 설정한 매개변수를 여기서 설정해줌.

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
		sale.setUser(loginUser); // 주문자
		sale.setUpdateTime(new Date()); // 주문시간
		// 주문 상품 목록 (개선된 for문도 가능)
		List<ItemSet> itemList = cart.getItemList();
		for (int i = 0; i < itemList.size(); i++) {
			ItemSet itemSet = itemList.get(i);
			int saleItemId = i + 1;
			SaleItem saleItem = new SaleItem(sale.getSaleId(), saleItemId, itemSet, sale.getUpdateTime());
			sale.getSaleItemList().add(saleItem);

		}
		// db의 sale 테이블에 추가하기
		saleDao.insert(sale);
		List<SaleItem> saleItemList = sale.getSaleItemList();
		for (SaleItem saleItem : saleItemList) {
			saleItemDao.insert(saleItem); // 주문상품 정보를 saleitem 테이블에 저장
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
			uploadBoardFileCreate(board.getFile1(), request); // 파일생성
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
		String uploadPath = // 파일 업로드 위치 설정
				request.getServletContext().getRealPath("/") + "/file/";
		String orgFile = file1.getOriginalFilename(); // 파일의 이름
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
	 * 원글 정보 : num, ref, reflevel, refstep 답글 정보 : name, title, subject, pass 1. 기존의
	 * 등록된 게시글 중 같은 ref 값을 가진 레코드들의 refstep을 하나 증가. refstep의 값보다 큰 값을 가진 레코드 수정 2.
	 * 답글 정보, ref, reflevel + 1, refstep+1 레코드 추가
	 */
	@Override
	public void boardReply(Board board) {
		boardDao.refstepadd(board);
		int num = boardDao.maxNum(); // 게시글의 최대 num 값
		board.setNum(++num); // 추가될 게시글의 키
		// board.getRef : 원글과 같은 값
		// board.getReflevel() : 원글의 reflevel 값
		// board.getRefstep() : 원글의 refstep 값
		board.setReflevel(board.getReflevel() + 1);
		board.setRefstep(board.getRefstep() + 1);
		boardDao.write(board);
	}

	@Override
	public void boardUpdate(Board board, HttpServletRequest request) {
		if (board.getFile1() != null && !board.getFile1().isEmpty()) {
			uploadBoardFileCreate(board.getFile1(), request); // 파일생성
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
