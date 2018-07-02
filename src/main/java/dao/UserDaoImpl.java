package dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import dao.mapper.ItemMapper;
import dao.mapper.UserMapper;
import logic.User;

@Repository // @Component + db에 관련있는 객체 기능을 가짐.
public class UserDaoImpl implements UserDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	private final String NS = "dao.mapper.UserMapper.";

	@Override
	public void insert(User user) {
		sqlSession.getMapper(UserMapper.class).insert(user);
	}

	@Override
	public User select(String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId); // 해쉬맵을 만들고 id를 집어넣어서 동적쿼리이기 때문에 Item.xml의 "list"쿼리를 실행함. id : 조회하고자 하는 상품의 번호
		// selectOne : 조회된 레코드가 한건만 가능. 조회된 레코드가 없다면 예외가 발생하지 않고 null로 반환.
		return sqlSession.selectOne(NS + "list", map);
	}

	@Override
	public void update(User user) {
		sqlSession.getMapper(UserMapper.class).update(user);
	}

	@Override
	public void delete(String userId) {
		sqlSession.getMapper(UserMapper.class).delete(userId);
	}

	@Override
	public List<User> list() {
		return sqlSession.selectList(NS + "list");
		// 셀렉트 원처럼 userId가 들어가는 조건이 없으니 User.xml의 기본 쿼리인 전체 user조회만 거침
	}

	@Override
	public List<User> list(String[] ids) {
		// List 객체 : 가변배열로도 부름. 배열형태로 설정됨.
		List<String> idlist = Arrays.asList(ids);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("idlist", idlist);
		return sqlSession.selectList(NS + "list", map);
	}

	@Override
	public List<Map<String, Object>> mygraph(String id) {
		return sqlSession.getMapper(UserMapper.class).mygraph(id);
	}
}
