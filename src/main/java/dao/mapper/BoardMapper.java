package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Board;

public interface BoardMapper {

	@Select("select ifnull(max(num),0) from board")
	int maxNum();

	@Insert("insert into board (num,name,pass,subject,content,readcnt," + "file1,regdate,ref,reflevel,refstep) "
			+ "values (#{num},#{name},#{pass},#{subject},#{content},0,#{fileurl},now(),#{ref},#{reflevel},#{refstep})")
	int insert(Board board);

	@Update("update board set refstep = refstep + 1 where ref = #{ref} and refstep > #{refstep}")
	void refstepadd(Board board);

	@Update("update board set name=#{name}, subject=#{subject}, content=#{content}, file1=#{fileurl} where num=#{num}")
	void update(Board board);

	@Delete("delete from board where num = #{value}")
	void delete(Integer num);

	@Update("update board set readcnt = readcnt + 1 where num=${value}")
	void cntUpdate(Integer num);

	@Select("select name key1, count(*) value1 from board " + " group by name")
	List<Map<String, Object>> graph();
}