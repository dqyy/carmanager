package dqyy;

import dqyy.Records;

import java.util.List;

public interface RecordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Records record);

    int insertSelective(Records record);

    Records selectByPrimaryKey(Integer id);

    List<Records> selectLikename(String name);


    int updateByPrimaryKeySelective(Records record);

    int updateByPrimaryKey(Records record);
}