package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import logic.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSession; // 마이바티스의 세션과 비슷한 계념. 사용할 수 있는 세션하나를 얻어오는 것. 쿼리같은게 다 들어있음.
	private final String NS = "dao.mapper.BoardMapper.";

	@Override
	public int count(String searchType, String searchContent) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("searchContent", searchContent);
		return sqlSession.selectOne(NS + "count", map);
	}

	@Override
	public List<Board> list(String searchType, String searchContent, Integer pageNum, int limit) {
		Map<String, Object> param = new HashMap<String, Object>();
		int startrow = (pageNum - 1) * limit;
		param.put("searchType", searchType);
		param.put("searchContent", searchContent);
		param.put("startrow", startrow);
		param.put("limit", limit);
		return sqlSession.selectList(NS + "list", param);
	}

	@Override
	public Board getBoard(Integer num) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("num", num);
		param.put("startrow", 0);
		param.put("limit", 1);
		return sqlSession.selectOne(NS + "list", param);
	}

	@Override
	public void write(Board board) {
		sqlSession.getMapper(BoardMapper.class).insert(board);
	}

	@Override
	public void updatereadcnt(Integer num) {
		sqlSession.getMapper(BoardMapper.class).cntUpdate(num);
	}

	@Override
	public int maxNum() {
		int a = sqlSession.getMapper(BoardMapper.class).maxNum();
		return a;
	}

	@Override
	public void refstepadd(Board board) {
		sqlSession.getMapper(BoardMapper.class).refstepadd(board);
	}

	@Override
	public void update(Board board) {
		sqlSession.getMapper(BoardMapper.class).update(board);
	}

	@Override
	public void delete(Integer num) {
		sqlSession.getMapper(BoardMapper.class).delete(num);
	}

	@Override
	public List<Map<String, Object>> graph() {
		return sqlSession.getMapper(BoardMapper.class).graph();
	}
}
