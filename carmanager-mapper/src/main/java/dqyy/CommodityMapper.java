package dqyy;


import java.util.List;

public interface CommodityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer id);

    Commodity selectByName(String name);

    List<Commodity> selectLikename(String name);

    List<Commodity> selectAll();

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
}