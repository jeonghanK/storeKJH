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

@Repository // @Component + db�� �����ִ� ��ü ����� ����.
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
		map.put("userId", userId); // �ؽ����� ����� id�� ����־ ���������̱� ������ Item.xml�� "list"������ ������. id : ��ȸ�ϰ��� �ϴ� ��ǰ�� ��ȣ
		// selectOne : ��ȸ�� ���ڵ尡 �ѰǸ� ����. ��ȸ�� ���ڵ尡 ���ٸ� ���ܰ� �߻����� �ʰ� null�� ��ȯ.
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
		// ����Ʈ ��ó�� userId�� ���� ������ ������ User.xml�� �⺻ ������ ��ü user��ȸ�� ��ħ
	}

	@Override
	public List<User> list(String[] ids) {
		// List ��ü : �����迭�ε� �θ�. �迭���·� ������.
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
