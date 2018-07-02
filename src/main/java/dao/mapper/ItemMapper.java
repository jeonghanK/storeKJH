package dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Item;

public interface ItemMapper {
	@Select("select ifnull(max(id),0) from item") // null이라면 0으로 가져와라. (아이템 테이블에 레코드가 0일때) 레코드 자체가 없으면 int로 줄 수 없어 0으로 전달
	int maxid();

	@Insert("insert into item (id, name, price, description, pictureUrl) "
			+ " values(#{id}, #{name}, #{price}, #{description}, #{pictureUrl})")
	void insert(Item item);

	@Update("update item set name=#{name}, price=#{price}, description=#{description}, pictureUrl=#{pictureUrl} where id=#{id}")
	void update(Item item);

	// Map이나 프로퍼티로 들어온 객체가 아닌 String, Int 같이 키값이 없다면 value로 작성
	@Delete("delete from item where id=#{value}")
	void delete(String id);
}
