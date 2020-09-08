package dqyy;

import dqyy.Hr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    Hr selectByUsername(String username);

    Hr cheack(String username, String password);

    List<Hr> selectAll();

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    int updatepasswordByPrimaryKey(@Param("pwd") String newpassword, @Param("id") Integer id);
}