package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.ItemMapper;
import logic.Item;

@Repository // Model Controller 역할. DB딴을 담당해줌. @Service다음으로 거치는 부분 DB의 내용을 View딴으로 전달.
public class ItemDaoImpl implements ItemDao {
	
	// sqlSession : org.mybatis.spring.SqlSessionTemplate 객체 주입
	@Autowired
	private SqlSessionTemplate sqlSession; // 마이바티스의 세션과 비슷한 계념. 사용할 수 있는 세션하나를 얻어오는 것. 쿼리같은게 다 들어있음.
	private final String NS = "dao.mapper.ItemMapper."; // NS : Item.xml의 네임스페이스의 약자로 변수명함. final로 하여 다른 곳에서 네임스페이스명을 바꿀 수 없도록 설정.

	@Override
	public List<Item> list() {
		// Item 테이블의 모든 정보 리턴
		// selectList : 조회된 모든 레코드를 객체로 저장하여 List 타입으로 리턴
		// Item.xml의 id가 "list"인 쿼리를 실행하는 것
		// mybatis-config에서 알리아스(별명)로 logic.Item은 Item으로 부르기로 설정해서 Item.xml에서 resultType은 그냥 Item으로 작성해도 됨. 
		return sqlSession.selectList(NS + "list");
	}

	@Override
	public void insert(Item item) {
		// int i : DB의 Item테이블의 id값의 최대값을  int i로 지정.
		int i = sqlSession.getMapper(ItemMapper.class).maxid();
		item.setId(++i); // 매개변수로 받아온 item에 설정을 한다. 가져온 max id값을 하나 더 증가 시킴. (오토인크리먼트). 최대값에 +1 해줌.
		sqlSession.getMapper(ItemMapper.class).insert(item); // 그 후 item 매개변수를 Mapper의 insert로 이동
	}

	@Override
	public Item detail(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id); // 해쉬맵을 만들고 id를 집어넣어서 동적쿼리이기 때문에 Item.xml의 "list"쿼리를 실행함. id : 조회하고자 하는 상품의 번호
		// selectOne : 조회된 레코드가 한건만 가능. 조회된 레코드가 없다면 예외가 발생하지 않고 null로 반환.
		return sqlSession.selectOne(NS + "list", map);
	}

	@Override
	public void update(Item item) {
		sqlSession.getMapper(ItemMapper.class).update(item);
	}

	@Override
	public void delete(String id) { // id값 문자열로 들어감. 스트링에는 프로퍼티나 키값이 없어서 맵퍼에서 value로 쿼리가 들어감.
		sqlSession.getMapper(ItemMapper.class).delete(id);
	}
}
