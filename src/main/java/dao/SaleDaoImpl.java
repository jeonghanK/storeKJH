package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import dao.mapper.SaleMapper;
import logic.Sale;

@Repository
public class SaleDaoImpl implements SaleDao {
	@Autowired
	private SqlSessionTemplate sqlSession; // 마이바티스의 세션과 비슷한 계념. 사용할 수 있는 세션하나를 얻어오는 것. 쿼리같은게 다 들어있음.
	private final String NS = "dao.mapper.SaleMapper.";

	@Override
	public Integer getMaxSaleId() {
		// sale 테이블에 저장된 saleid 값 중 최대값을 리턴
		int i = sqlSession.getMapper(SaleMapper.class).maxSaleid();
		return i + 1;
	}

	@Override
	public void insert(Sale sale) {
		sqlSession.getMapper(SaleMapper.class).insert(sale);
	}

	@Override
	public List<Sale> list(String userId) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		return sqlSession.selectList(NS + "list", param);
	}

}
