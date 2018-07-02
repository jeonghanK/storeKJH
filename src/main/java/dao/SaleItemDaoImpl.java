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

import dao.mapper.SaleItemMapper;
import dao.mapper.SaleMapper;
import logic.SaleItem;

@Repository
public class SaleItemDaoImpl implements SaleItemDao {
	@Autowired
	private SqlSessionTemplate sqlSession; // ���̹�Ƽ���� ���ǰ� ����� ���. ����� �� �ִ� �����ϳ��� ������ ��. ���������� �� �������.
	private final String NS = "dao.mapper.SaleItemMapper.";

	@Override
	public void insert(SaleItem saleItem) {
		sqlSession.getMapper(SaleItemMapper.class).insert(saleItem);
	}

	@Override
	public List<SaleItem> list(Integer saleId) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("saleId", saleId);
		return sqlSession.selectList(NS + "list", param);
	}
}
