package logic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ShopService {

	List<Item> getItemList();

	void itemCreate(Item item);

	void itemCreate(Item item, HttpServletRequest request);

	Item itemDetail(String id);

	void itemUpdate(Item item, HttpServletRequest request);

	void itemDelete(String id);

	void userCreate(User user);

	User getUser(String userId);

	void updateUser(User user);

	void userDelete(String userId);

	List<User> userList();

	List<User> userList(String[] ids);

	Sale checkEnd(User loginUser, Cart cart);

	List<Sale> saleList(String id);

	List<SaleItem> saleItemList(Integer saleId);

	int boardcount(String searchType, String searchContent);

	List<Board> boardList(String searchType, String searchContent, Integer pageNum, int limit);

	Board getBoard(Integer num);

	void boardWrite(Board board, HttpServletRequest request);

	void updatereadcnt(Integer num);

	void boardReply(Board board);

	void boardUpdate(Board board, HttpServletRequest request);

	void boardDelete(int num);

	Map<String, Object> graph();

	Map<String, Object> mygraph(String id);
}
