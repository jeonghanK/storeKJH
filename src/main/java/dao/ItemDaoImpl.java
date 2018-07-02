package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.ItemMapper;
import logic.Item;

@Repository // Model Controller ����. DB���� �������. @Service�������� ��ġ�� �κ� DB�� ������ View������ ����.
public class ItemDaoImpl implements ItemDao {
	
	// sqlSession : org.mybatis.spring.SqlSessionTemplate ��ü ����
	@Autowired
	private SqlSessionTemplate sqlSession; // ���̹�Ƽ���� ���ǰ� ����� ���. ����� �� �ִ� �����ϳ��� ������ ��. ���������� �� �������.
	private final String NS = "dao.mapper.ItemMapper."; // NS : Item.xml�� ���ӽ����̽��� ���ڷ� ��������. final�� �Ͽ� �ٸ� ������ ���ӽ����̽����� �ٲ� �� ������ ����.

	@Override
	public List<Item> list() {
		// Item ���̺��� ��� ���� ����
		// selectList : ��ȸ�� ��� ���ڵ带 ��ü�� �����Ͽ� List Ÿ������ ����
		// Item.xml�� id�� "list"�� ������ �����ϴ� ��
		// mybatis-config���� �˸��ƽ�(����)�� logic.Item�� Item���� �θ���� �����ؼ� Item.xml���� resultType�� �׳� Item���� �ۼ��ص� ��. 
		return sqlSession.selectList(NS + "list");
	}

	@Override
	public void insert(Item item) {
		// int i : DB�� Item���̺��� id���� �ִ밪��  int i�� ����.
		int i = sqlSession.getMapper(ItemMapper.class).maxid();
		item.setId(++i); // �Ű������� �޾ƿ� item�� ������ �Ѵ�. ������ max id���� �ϳ� �� ���� ��Ŵ. (������ũ����Ʈ). �ִ밪�� +1 ����.
		sqlSession.getMapper(ItemMapper.class).insert(item); // �� �� item �Ű������� Mapper�� insert�� �̵�
	}

	@Override
	public Item detail(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id); // �ؽ����� ����� id�� ����־ ���������̱� ������ Item.xml�� "list"������ ������. id : ��ȸ�ϰ��� �ϴ� ��ǰ�� ��ȣ
		// selectOne : ��ȸ�� ���ڵ尡 �ѰǸ� ����. ��ȸ�� ���ڵ尡 ���ٸ� ���ܰ� �߻����� �ʰ� null�� ��ȯ.
		return sqlSession.selectOne(NS + "list", map);
	}

	@Override
	public void update(Item item) {
		sqlSession.getMapper(ItemMapper.class).update(item);
	}

	@Override
	public void delete(String id) { // id�� ���ڿ��� ��. ��Ʈ������ ������Ƽ�� Ű���� ��� ���ۿ��� value�� ������ ��.
		sqlSession.getMapper(ItemMapper.class).delete(id);
	}
}
