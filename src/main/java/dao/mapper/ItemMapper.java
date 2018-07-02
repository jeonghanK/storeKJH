package dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Item;

public interface ItemMapper {
	@Select("select ifnull(max(id),0) from item") // null�̶�� 0���� �����Ͷ�. (������ ���̺� ���ڵ尡 0�϶�) ���ڵ� ��ü�� ������ int�� �� �� ���� 0���� ����
	int maxid();

	@Insert("insert into item (id, name, price, description, pictureUrl) "
			+ " values(#{id}, #{name}, #{price}, #{description}, #{pictureUrl})")
	void insert(Item item);

	@Update("update item set name=#{name}, price=#{price}, description=#{description}, pictureUrl=#{pictureUrl} where id=#{id}")
	void update(Item item);

	// Map�̳� ������Ƽ�� ���� ��ü�� �ƴ� String, Int ���� Ű���� ���ٸ� value�� �ۼ�
	@Delete("delete from item where id=#{value}")
	void delete(String id);
}
