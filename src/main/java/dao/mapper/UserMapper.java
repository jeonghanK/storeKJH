package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.User;

public interface UserMapper {
	@Insert("insert into userAccount (userId,password,userName,phoneNo,postcode,address,email,birthDay) "
			+ "values (#{userId},#{password},#{userName},#{phoneNo},#{postcode},#{address},#{email},#{birthDay})")
	void insert(User user);

	@Update("update userAccount set username=#{userName}, phoneno=#{phoneNo}, postcode=#{postcode}, "
			+ "address=#{address}, email=#{email}, birthday=#{birthDay} where userid=#{userId}")
	void update(User user);

	@Delete("delete from userAccount where userId=#{value}")
	void delete(String userId);

	@Select("select i.name key1, sum(si.quantity) value1 from  item i, sale s, saleitem si where i.id = si.itemid and s.saleid = si.saleid and s.userid = #{value} group by i.name")
	List<Map<String, Object>> mygraph(String id);
}
